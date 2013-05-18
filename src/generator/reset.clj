(ns generator.reset
  (:require [cljss.units.colors :as colors :refer (rgba hsla)])
  (:use cljss.core
        [cljss.units.colors :only (alpha)]))

; mixins
(def transparent-bg
  '(:background :transparent))

(defn make-reset-colors-mixin [bg-color text-color]
  (list :background bg-color :color text-color))


;; Attempt to have a minimal, functional skin
;; which purpose is to be overridden by a more complete
;; skin.

(defn make-reset-rules
  "Generates rules that reset the skin with the given
  colors."
  [bg-color text-color]
  (let [reset-colors (make-reset-colors-mixin bg-color text-color)
        light-text-color (colors/lighten text-color 50)
        darken-bg-color (colors/darken bg-color 10)]
    (rules
     [:body reset-colors]
     [:a :text-decoration :none]
     [:#intro transparent-bg]

     ["::-webkit-input-placeholder"
      :color light-text-color]
     ["::-webkit-scrollbar" transparent-bg]
     ["::-webkit-scrollbar-track" transparent-bg]
     ["::-webkit-scrollbar-thumb"
      :background (alpha darken-bg-color 0.5)
      :border [:1px :dotted light-text-color]]
     ["::-webkit-scrollbar-corner" transparent-bg]

     [:.popup
      :background-color (alpha bg-color 0.3)
      [[& :> :div :> :div] reset-colors]]

     [:#multi
      [:.list
       transparent-bg

       [[:.tabset :+ :.tabset]
        :border-left [:1px :dotted text-color]]

       [:li :box-sizing :border-box
        :height :29px
        reset-colors]

       [(-> :.dirty after) :content (css-str "*")]]

      [(-> :.dirty.ui-sortable-placeholder after)
       :content [(css-str "") "!important"]]]



     [:#side
      [[:.workspace :.recent :> :div :> :ul]
        :margin-top :15px]

      [[:.clients :.load-wrapper :.img]
        :width  :20px
        :height :20px
        :background (str "url(" (css-str "../../img/connectingloader.gif") ")")]]

     [[:#find-bar :input] reset-colors]
     [[:#statusbar :.console-toggle]
       :cursor :default
       :width :20px
       :display :inline-block
       :text-align :center
       :margin-left :0px]

     [[:.load-wrapper :.img]
       :background (str "url(" (css-str "../../img/loaderdark.gif") ")")]


     [[:#keybinding :.filter-list :ul] reset-colors]


     [[:.cm-s-default :span.CodeMirror-matchingbracket]
       :box-sizing :border-box
       :font-weight :normal]

     [:.CodeMirror
       [:div.CodeMirror-cursor
         :border-left [:1px :solid text-color]]

       [:div.CodeMirror-secondarycursor
         :border-left [:1px :solid :silver]]

       [:span.CodeMirror-matchingbracket
        :box-sizing :border-box
        transparent-bg
        :font-weight :normal]

       [:span.CodeMirror-nonmatchingbracket
        :box-sizing :border-box
        transparent-bg
        :color :red]]

     [[:.CodeMirror.cm-keymap-fat-cursor :div.CodeMirror-cursor]
       :width :auto
       :border [:none :!important]]

     [:.CodeMirror-hints
       :position :absolute
       :z-index 1000000000000000
       :overflow :hidden
       :list-style :none

       :margin 0
       :padding :2px

       :font-size :90%
       :font-family :inherit

       :max-height :20em
       :overflow-y :auto
       reset-colors

       [:input :display :none]
       [:ul
        reset-colors
        [:li
         :margin 0
         :padding [0 :4px]
         :max-width :19em
         :overflow :hidden
         :white-space :pre
         :cursor :pointer]]])))

