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
  (let [reset-colors (make-reset-colors-mixin bg-color text-color)]
    (rules
     [:body reset-colors]
     [:a :text-decoration :none]
     [:#intro transparent-bg]

     [:.popup
      :background-color (alpha bg-color 0.3)
      [(c-> & :div :div) reset-colors]]

     [:#multi
      [:.list
        transparent-bg

       [:li :box-sizing :border-box
            :height :29px
            reset-colors]

       [(-> :.dirty after) :content (css-str "*")]]

      [(-> :.dirty.ui-sortable-placeholder after)
       :content [(css-str "") "!important"]]]

     [:#side
      [[:.workspace (c-> :.recent :div :ul)]
        :margin-top :15px]

      [[:.clients :.load-wrapper :.img]
        :width  :20px
        :height :20px
        :background (str "url(" (css-str "../../img/connectingloader.gif") ")")]]

     [[:#statusbar :.console-toggle]
       :cursor :default
       :width :20px
       :display :inline-block
       :text-align :center
       :margin-left :0px]

     [[:.load-wrapper :.img]
       :background (str "url(" (css-str "../../img/loaderdark.gif") ")")]


     [[:.cm-s-default :span.CodeMirror-matchingbracket]
       :box-sizing :border-box
       :font-weight :normal]

     [:.CodeMirror
       [:div.CodeMirror-cursor
         :border-left [:1px :solid :black]]

       [:div.CodeMirror-secondarycursor
         :border-left [:1px :solid :silver]]

       [:span.CodeMirror-matchingbracket
        :box-sizing :border-box
        transparent-bg
        :border-bottom [:1px :solid :black]
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
       [[:ul :li]
        :margin 0
        :padding [0 :4px]
        :max-width :19em
        :overflow :hidden
        :white-space :pre
        :cursor :pointer]

       [(-> :.selected after)
         :content (css-str "  <")
         :color :grey
         :float :right]])))

