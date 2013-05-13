(ns generator.pure
  (:require [cljss.units.colors :as colors :refer (rgba hsla)]
            [clojure.string :as string])
  (:use cljss.core
        generator.common
        [generator.reset :only (make-reset-rules)]
        [generator.core :only (make-theme-css-class make-theme-path
                               install-skin install-theme
                               file-separator)]))

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
  (mapcat #(list :transition [% :0.2s :ease]) properties))


(def button-style
  (list
   :background-color default-bg-color
   :border [:1px :dotted default-border-color]
   :border-radius :10px
   :text-shadow [0 0 :3px outer-shadow-color]
   :color default-bg-color
   (default-transition :all)

   [(-> & hover)
    :color default-text-color
    :box-shadow [0 0 :5px outer-shadow-color]
    :text-shadow :none
    :border [:1px :dotted  default-bg-color]]))

(def selected-style
  (list
   :background-color default-bg-color
   (default-transition :all)
   :text-shadow :none
   :color default-text-color
   :box-shadow [:inset 0 0 :5px inner-shadow-color]
   :border [:1px :solid inner-shadow-color]))

(def input-style
  (list :border [:1px :dashed default-border-color]
        :background-color default-bg-color
        :color default-text-color))


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
   :border [:1px :dashed default-text-color]
   :border-radius :10px))

(def inline-option-style
  (list
   :content (css-str " <")
   :float :right
   :background-color default-bg-color))

(def inline-error-style
  (list
   :border [:1px :dashed :red]))


;; Style
(defrules skin-style
  [:body
   :background-color default-bg-color
   :color default-text-color]

  (css-comment "button style")
  [buttons button-style]
  [[:#side :.clients :.connector :li :h2]
   :color default-text-color
   :text-shadow :none]

  (css-comment "selectables style")
  [selected selected-style]

  (css-comment "input style")
  [inputs input-style]

  (css-comment "Side lists")
  [selection-options filter-list-style]
  [[:#side :.workspace :li (-> :p hover after)]
   workspace-selection-style]

  (css-comment "Completions ")
  [inline-selectors inline-option-style]

  (css-comment "clients")
  [#{[:#side :.clients (c-> :.list :ul (c-+ :li :li))]
     [:#side :.clients :.connector :li]}
   :margin-top :3px]

  (css-comment "boxes")
  [boxes box-style]

  (css-comment "inline errors")
  [inline-errors inline-error-style]

  (css-comment "Underlining propositions")
  [[:.filter-list :em] :border-bottom [:1px :solid default-text-color]]

  (css-comment "Positionning")
  [[:#sidebar :li]
   :margin-right :5px

   [(-> & last-child)
    :margin-right :30px]] ;; todo -> move dow the buttons

  [:#multi
   [:.list :margin-left :10px
    [:li :margin-left :5px]]
   [[:.tabset :+ :.tabset]
    :border-left [:1px :dotted default-text-color]]]

  [:#bottombar :margin-bottom :-2px]
  [[:.console :> :li] :margin-bottom :2px]

  [[:#side :.content]
   :margin [0 :10px]
   :height :99.6%]

  (css-comment "misc")
  [:intro :background-color (-> default-bg-color colors/inverse (colors/lighten 50))])

(def skin
  (group-rules
   (make-reset-rules default-bg-color default-text-color)
   skin-style))


(defrules theme
  (css-comment "Right now use the code miror default theme.")

  (-> (make-theme-path "codemirror")
       slurp
       (string/replace ".cm-s-codemirror" (make-theme-css-class theme-name))))

; Write the css directly n LT folders.
(install-theme theme-name theme)
(install-skin theme-name skin)


;; write the pretty printed themes in the css folder of the project
(defn write-theme [theme-name theme]
  (let [theme-name (str theme-name "-theme")
        theme-path (str (System/getProperty "user.dir") file-separator
                        "css" file-separator
                        theme-name ".css" )]
      (spit theme-path (apply css theme))))

(defn write-skin [skin-name skin]
  (let [skin-path (str (System/getProperty "user.dir") file-separator
                       "css" file-separator
                        skin-name ".css" )]
      (spit skin-path (apply css skin))))

;(write-theme theme-name theme)
;(write-skin theme-name skin)


