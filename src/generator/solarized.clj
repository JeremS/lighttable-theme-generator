(ns generator.solarized
  (:require [cljss.units.colors :as colors :refer (rgba hsla)]
            [clojure.string :as string])
  (:use cljss.core
        generator.common
        [generator.reset :only (make-reset-rules)]
        [generator.core :only (install-skin
                               install-theme
                               write-skin
                               write-theme
                               print-css)]

        clojure.pprint))

;; Solarized palette
(def base03    (rgba 0x00 0x2b 0x36))
(def base02    (rgba 0x07 0x36 0x42))
(def base01    (rgba 0x58 0x6e 0x75))
(def base00    (rgba 0x65 0x7b 0x83))
(def base0     (rgba 0x83 0x94 0x96))
(def base1     (rgba 0x93 0xa1 0xa1))
(def base2     (rgba 0xee 0xe8 0xd5))
(def base3     (rgba 0xfd 0xf6 0xe3))
(def yellow    (rgba 0xb5 0x89 0x00))
(def orange    (rgba 0xcb 0x4b 0x16))
(def red       (rgba 0xdc 0x32 0x2f))
(def magenta   (rgba 0xd3 0x36 0x82))
(def violet    (rgba 0x6c 0x71 0xc4))
(def blue      (rgba 0x26 0x8b 0xd2))
(def cyan      (rgba 0x2a 0xa1 0x98))
(def green     (rgba 0x85 0x99 0x00))


;; utilities
(def accents
  {yellow yellow
   orange orange
   red red
   magenta magenta
   violet violet
   blue blue
   cyan cyan
   green green})

(def default-scheme
  {:dark (merge accents
                {base03 base03
                 base02 base02
                 base01 base01
                 base00 base00
                 base0 base0
                 base1 base1
                 base2 base2
                 base3 base3})
   :light (merge accents
                 {base03 base3
                  base02 base2
                  base01 base1
                  base00 base0
                  base0 base00
                  base1 base01
                  base2 base02
                  base3 base03})
   })

(defn tone [scheme base]
  (get-in default-scheme [scheme base]))


;; palette usage

(defn bg-color [scheme]
  (tone scheme base02))

(defn text-color [scheme]
  (tone scheme base0))

(def border-box
  '(:box-sizing :border-box))

(def button-color
  {:light {:1 (tone :light base03) :2 red }
   :dark  {:1 yellow :2 cyan}})

(defn default-transition [& properties]
  (mapcat #(list :transition [% :0.2s :ease]) properties))

(defn button-style [scheme]
  (list
   border-box
   :border [:2px :dotted (tone scheme base02)]
   :background-color (tone scheme base03)
   :color (-> button-color scheme :1)
   :border-radius :10px
   :text-shadow [:0px :0px :3px (-> button-color scheme :2)]

   (default-transition :all)

   [(-> & hover)
    :color (-> button-color scheme :2)
    :background-color (bg-color scheme)
    :text-shadow :none
    :box-shadow
      [:0px :0px :10px (-> button-color scheme :2)]]))


(defn selected-style [scheme]
  (list
   :background-color (tone scheme base02)
   (default-transition :all)
   :text-shadow :none
   :color (-> button-color scheme :2)
   :box-shadow
      [:0px :0px :10px (-> button-color scheme :2)]))

(defn input-style [scheme]
  (list
   :color (text-color scheme)
   :background-color (tone scheme base03)
   :box-shadow [:inset :0px :0px :20px (tone scheme base02)]
   [(-> & focus)
    :color (text-color scheme)]))

(defn selections-style [scheme]
  (list
   :background-color (tone scheme base02)
   :border-radius :10px))

(defn inline-boxes-style [scheme]
  (list
   :background-color (tone scheme base03)))

(defn pane-style [scheme]
  (list
   :background-color (tone scheme base03)
   :border-radius :5px
   :border [:1px :solid (tone scheme base03)]))

(def panes
  #{:#statusbar
    :#bottombar
    [:#side :.workspace :ul.root]
    [:#side :.workspace :.recent :> :div :> :ul]
    [:#side :.navigate :ul]
    [:#side :.command :ul]

    [:#side :.clients :.list :> :ul :> :li]

    [:#keybinding :.binder]
    [:#keybinding :ul.keys :li]
    })

(defn skin [scheme]
  (rules
   (css-comment "Generated from ../src/generator/pure.clj")

   (make-reset-rules (bg-color scheme) (text-color scheme))

   [[:html :*] :color-profile :sRGB
    :rendering-intent :auto]

   [:body
    :background-color (bg-color scheme)]

   (css-comment "button style")
   [buttons (button-style scheme)]
   [[:#side :.clients :.connector :li :h2]
    :color (-> button-color scheme :2)
    :text-shadow :none]

   (css-comment "selectables style")
   [selected (selected-style scheme)]


   (css-comment "inputs")
   [inputs (input-style scheme)]
   ["::-webkit-input-placeholder"
    :color (tone scheme base00)]


   (css-comment "selections")
   [#{[:.filter-list #{:.selected (-> :li hover)}]
      [:#keybinding :.filter-list :ul]
      [:#side :.workspace :li (-> :p hover)]
      [:#side :.workspace :.recent :> :div :> :ul :> (-> :li hover)]}
    (selections-style scheme)]

   (css-comment "completion box...")
   [inline-selectors (inline-boxes-style scheme)]


   (css-comment "panes")
   [panes (pane-style scheme)]

   [[:.console :> :li]
    :border-bottom [:3px :dotted (tone scheme base02)]]

   (css-comment "Positionning")
   [[:#sidebar :li]
    :margin-right :5px]

   [:#multi
    [:.list :margin-left :10px
     [:li :margin-left :5px]]]

   [#{[:#side :.clients :.list :> :ul :> :li :+ :li]
      [:#side :.clients :.connector :li]}
    :margin-top :3px]

   [:#intro :background-color (tone scheme base03)]


   ))





(def light-skin (skin :light))
(def light-theme "")


(def dark-skin (skin :dark))
(def dark-theme "")









;; ----------------------------------------------------------------------------

(def light-name "s-light")
(def dark-name "s-dark")


; Write the css directly in LT folders.
;(install-theme light-name light-theme)
;(install-theme dark-name dark-theme)

(install-skin light-name light-skin)
(install-skin dark-name dark-skin)


;; write the pretty printed themes in the css folder of the project
;(write-theme theme-name theme)
;(write-skin theme-name skin)
