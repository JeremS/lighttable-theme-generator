(ns generator.lesserdark.theme
  (:require [cljss.units.colors :as c])
  (:use generator.core
        [cljss.core :only (compact-css)]

        clojure.repl
        ))

(def theme-name "dark2")
(def background-color (c/rgba 0x26 0x26 0x26))
(def CM-selected (c/rgba 0x45 0x44 0x3B))
(def CM-gutter-text-color (c/rgba 0x70 0x70 0x70))
(def keyword-color (c/rgba 0x59 0x9e 0xff))
(def atom-color (c/rgba 0xC2 0xB4 0x70))
(def number-color (c/rgba 0xB3 0x5E 0x4D))
(def def-color (c/rgba 0xff 0xff 0xff))
(def var1 (c/rgba 0xD9 0xBF 0x8C))
(def var2 (c/rgba 0x66 0x91 0x99))
(def var3 (c/rgba 0xff 0xff 0xff))

(def tag (c/rgba 0x66 0x91 0x99))

(def theme
  (rules
   "/*"
   "  http://lesscss.org/ dark theme"
   "  Ported to CodeMirror by Peter Kroon"
   "*/"

   [:#multi.dark2  :background background-color ]
   [[:div.CodeMirror :.cm-s-dark2
     :span.CodeMirror-matchingbracket]
    :color :#7EFC7E]

   [:.cm-s-dark2

    :line-height :1.3em
    :background background-color
    :color :#EBEFE7
    :text-shadow [0 :-1px :1px background-color]

    (css-comment
     ""
     "Inside parent theme"
     "")

    (inside-rules
     [:div.CodeMirror-selected
      :background: [CM-selected :!important]]

     [:.CodeMirror-cursor
      :border-left: [:1px :solid :white :!important]]

     "/*editable code holder*/"

     [:.CodeMirror-lines
      :margin-left :3px
      :margin-right :3px]

     [:.CodeMirror-gutter
      :background background-color
      :border-right [:1px :solid :#aaa]
      :padding-right :3px
      :min-width :2.5em ]


     [:.CodeMirror-gutter-text :color CM-gutter-text-color]
     [:span.cm-keyword    :color keyword-color]
     [:span.cm-atom       :color atom-color]
     [:span.cm-number     :color number-color]
     [:span.cm-def        :color def-color]
     [:span.cm-variable   :color var1]
     [:span.cm-variable-2 :color var2]
     [:span.cm-variable-3 :color var3]

     [:span.cm-property   :color :#92A75C]
     [:span.cm-operator   :color :#92A75C]
     [:span.cm-comment    :color :#666]
     [:span.cm-string     :color :#BCD279]


     [:span.cm-string-2   :color :#f50]
     [:span.cm-meta       :color :#738C73]
     [:span.cm-error      :color :#9d1e15]
     [:span.cm-qualifier  :color :#555]
     [:span.cm-builtin    :color :#ff9e59]

     [:span.cm-bracket    :color :#EBEFE7]
     [:span.cm-tag        :color tag]
     [:span.cm-attribute  :color :#00c]
     [:span.cm-header     :color :#a0a]
     [:span.cm-quote      :color :#090]
     [:span.cm-hr         :color :#999]
     [:span.cm-link       :color :#00c])]
))

(write-css theme-name theme)

