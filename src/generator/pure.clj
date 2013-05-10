(ns generator.pure
  (:require
   [cljss.units.colors :as colors :refer (rgba hsla)])
  (:use
   [generator.core :only (make-theme-css-class write-skin write-theme)]
   [generator.reset :only (default-skin reset-colors)]
   cljss.core))

; little black and white theme.

(def theme-name "pure")

;; Palette
(def default-bg-color (rgba 255 255 255))

(def default-text-color (colors/inverse default-bg-color))

(def default-border-color default-text-color)

(def outer-shadow-color default-text-color)

(def inner-shadow-color
  (-> default-text-color
      (colors/lighten 30)))

;; Mixins

(defn default-transition [& properties]
  (mapcat #(list :transition [% :0.3s :ease]) properties))


(defrules button-style
  [&
   :border [:1px :dotted default-border-color]
   :border-radius :3px
   :text-shadow [0 0 :1px (colors/lighten outer-shadow-color 30)]
   (default-transition :all)

   [:h2 :text-shadow :none]

   [(-> & hover)
    :box-shadow [0 0 :5px outer-shadow-color]
    :text-shadow :none
    :border [:1px :dotted  default-bg-color]]])

(defrules selected-style
  (list
   (default-transition :box-shadow)
   :text-shadow :none
   :box-shadow [:inset 0 0 :5px inner-shadow-color]
   :border [:1px :solid inner-shadow-color]))

(def input-style
  (list :border [:1px :dashed default-border-color]))


(defrules filter-list-style
  [(-> & after)
   :content (css-str ">")
   :position :absolute
   :right :5px
   :margin-top :1px])

(def workspace-selection-style
  (list
   :content (css-str "<")
   :float :right))

(def box-style
  (list
   :border [:1px :dashed default-text-color]))

;; Grouping of similar selectors.
(def first-class-containers
  #{:body :.CodeMirror})

(def buttons
  #{[:#sidebar :li]
    [:#side :.clients :.button]
    [:#multi :.list :li]
    [:.popup :.button]
    [:#side :.clients :.connector :li]})

(def selected
  #{[:#sidebar :.current]
    [:#multi :.list :.active]
    [:#side :.clients :.list :.active]})

(def inputs
  [:#side #{:.navigate :.command} :input])

(def side-elements
  #{[:.filter-list #{:.selected (-> :li hover)}]})

(def boxes
  #{[:.inline-result :.truncated]
    [:.inline-result.open :.full]
    [:#side :.clients (c-> :.list :ul :li)]
    [:#side :.clients :.connector :li]
    [:.inline-exception :pre]})

;; Style
(defrules skin-style
  [first-class-containers
   :background-color default-bg-color
   :color default-text-color]
  ""
  ""

  (css-comment "button style")
  [buttons button-style]

  ""
  (css-comment "selectables style")
  [selected selected-style]

  ""
  (css-comment "input style")
  [inputs input-style]

  ""
  (css-comment "Side lists")
  [side-elements filter-list-style]
  [[:#side :.workspace :li (-> :p hover after)]
   workspace-selection-style]

  ""
  (css-comment "clients")
  [#{[:#side :.clients (c-> :.list :ul (c-+ :li :li))]
     [:#side :.clients :.connector :li]}
   :margin-top :3px]

  ""
  (css-comment "misc")
  [[:#multi :.list] :margin-left :1px]

  ""
  (css-comment "boxes")
  [boxes box-style]

  ""
  (css-comment "Underlining propositions")
  [[:.filter-list :em] :border-bottom [:1px :solid default-text-color]]
  ""
  (css-comment "tests"))


(def skin
  (group-rules
   default-skin
   skin-style))

(write-skin theme-name skin)



(defrules default-theme
  [(make-theme-css-class theme-name) reset-colors])

(write-theme theme-name default-theme)
