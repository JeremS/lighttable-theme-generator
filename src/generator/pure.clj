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

(def selected-style
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

(def inline-error-style
  (list
   :border [:1px :dashed :red]))

;; Grouping of similar selectors.
(def first-class-containers
  #{:body :.CodeMirror})

(def buttons
  #{[:#sidebar :li]
    [:#side :.clients :.button]
    [:#side :.clients :.connector :li]
    [:#side :.workspace :ul.buttons (-> :li (css-not :.sep))]
    [:#side :.clients :.toggle]
    [:#multi :.list :li]
    [:.popup :.button]
    [:#instarepl :.livetoggler.off]
    [:#version-info :.button]
    })

(def selected
  #{[:#sidebar :.current]
    [:#multi :.list :.active]
    [:#side :.clients :.list :.active]
    [:#instarepl :.livetoggler]
    [:.popup :.button.active]})

(def inputs
  #{[:#side #{:.navigate :.command} :input]
    [:#find-bar :input]})

(def side-elements
  #{[:.filter-list #{:.selected (-> :li hover)}]})


(def boxes
  #{[:.inline-result :.truncated]
    [:.inline-result.open :.full]
    [:#side :.clients (c-> :.list :ul :li)]
    [:#side :.clients :.connector :li]
    :#statusbar
    :.console
    [:#instarepl :.usage]})

(def inline-errors
  #{[:.inline-exception :pre]
    [:#instarepl :.usage.exception]})

;; Style
(defrules skin-style
  \newline
  [first-class-containers
   :background-color default-bg-color
   :color default-text-color]
  \newline

  (css-comment "button style")
  [buttons button-style]

  \newline
  (css-comment "selectables style")
  [selected selected-style]

  \newline
  (css-comment "input style")
  [inputs input-style]

  \newline
  (css-comment "Side lists")
  [side-elements filter-list-style]
  [[:#side :.workspace :li (-> :p hover after)]
   workspace-selection-style]

  \newline
  (css-comment "clients")
  [#{[:#side :.clients (c-> :.list :ul (c-+ :li :li))]
     [:#side :.clients :.connector :li]}
   :margin-top :3px]

  \newline
  (css-comment "boxes")
  [boxes box-style]

  \newline
  (css-comment "inline errors")
  [inline-errors inline-error-style]

  \newline
  (css-comment "Underlining propositions")
  [[:.filter-list :em] :border-bottom [:1px :solid default-text-color]]

  \newline
  (css-comment "misc")
  [:#multi
   [:.list :margin-left :1px]
   [[:.tabset :+ :.tabset]
    :border-left [:1px :dotted default-text-color]]]

  \newline
  (css-comment "tests"))

(def skin
  (group-rules
   default-skin
   skin-style))

(defrules default-theme
  (css-comment "Right now use the code miror default theme."))

; eval to install in the light table folder.
;(write-theme theme-name default-theme)
(write-skin theme-name skin)