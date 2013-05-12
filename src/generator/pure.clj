(ns generator.pure
  (:require [cljss.units.colors :as colors :refer (rgba hsla)]
            [clojure.string :as string])
  (:use cljss.core
        [generator.reset :only (make-reset-rules)]
        [generator.core :only (make-theme-css-class make-theme-path
                                          write-skin write-theme)]))

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
    [:#keybinding :button]
    [:#statusbar :.console-toggle]
    [:#browser :button]})

(def selected
  #{[:#sidebar :.current]
    [:#multi :.list :.active]
    [:#side :.clients :.list :.active]
    [:#instarepl :.livetoggler]
    [:.popup :.button.active]
    [:#statusbar :.console-toggle.dirty]})

(def inputs
  #{[:#side #{:.navigate :.command} :input]
    [:#find-bar :input]
    [:#keybinding :.binder :input]
    [:#browser :input]})

(def inline-option-style
  (list
   :content (css-str " <")
   :float :right
   :background-color default-bg-color))

(def inline-selectors
  #{[:.CodeMirror-hints :ul]
    [:#keybinding :.filter-list :ul]})

(def selection-options
  #{[:.filter-list #{:.selected (-> :li hover)}]
    [:#keybinding :.filter-list :ul]})


(def boxes
  #{:#intro
    [:.inline-result :.truncated]
    [:.inline-result.open :.full]

    [:#side :.clients (c-> :.list :ul :li)]
    [:#side :.clients :.connector :li]

    :#statusbar

    :#bottombar

    [:#instarepl :.usage]
    [:#keybinding :.binder]
    [:#keybinding :.all-mappings]
    [:#keybinding :.all-mappings :tr]
    [:#keybinding :ul.keys :li]

    [:.console :> :td]})

(def inline-errors
  #{[:.inline-exception :pre]
    [:#instarepl :.usage.exception]})

;; Style
(defrules skin-style
  [first-class-containers
   :background-color default-bg-color
   :color default-text-color]

  (css-comment "button style")
  [buttons button-style]
  [[:#side :.clients :.connector :li :h2]
   :color default-text-color]

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

;(write-skin theme-name skin)


(defrules theme
  (css-comment "Right now use the code miror default theme.")

  (-> (make-theme-path "codemirror")
       slurp
       (string/replace ".cm-s-codemirror" (make-theme-css-class theme-name))))

(make-theme-css-class theme-name)

(write-theme theme-name theme)

