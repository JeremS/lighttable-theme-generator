(ns generator.lesserdark.theme
  (:use generator.core
        [cljss.core :only (compact-css)]
        clojure.repl)
  (:import java.io.File))

(def theme-name "dark2")



(def theme
  (rules
   "/*"
   "  http://lesscss.org/ dark theme"
   "  Ported to CodeMirror by Peter Kroon"
   "*/"

   [:#multi.dark2  :background :#262626 ]
   [[:div.CodeMirror :.cm-s-dark2
     :span.CodeMirror-matchingbracket]
    :color :#7EFC7E]

   ""
   ""
   "/*Inside parent theme*/"
   ""
   [:.cm-s-dark2

    :line-height :1.3em
    :background :#262626
    :color :#EBEFE7
    :text-shadow [0 :-1px :1px :#262626]

    [:div.CodeMirror-selected
     :background: [:#45443B :!important]]

    [:.CodeMirror-cursor
     :border-left: [:1px :solid :white :!important]]

    "/*editable code holder*/"

    [:.CodeMirror-lines
     :margin-left :3px
     :margin-right :3px]

    [:.CodeMirror-gutter
     :background :#262626
     :border-right [:1px :solid :#aaa]
     :padding-right :3px
     :min-width :2.5em ]

    [:.CodeMirror-gutter-text :color :#777]
    [:span.cm-keyword    :color :#599eff]
    [:span.cm-atom       :color :#C2B470]
    [:span.cm-number     :color :#B35E4D]
    [:span.cm-def        :color :white]
    [:span.cm-variable   :color :#D9BF8C]
    [:span.cm-variable-2 :color :#669199]
    [:span.cm-variable-3 :color :white]

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
    [:span.cm-tag        :color :#669199]
    [:span.cm-attribute  :color :#00c]
    [:span.cm-header     :color :#a0a]
    [:span.cm-quote      :color :#090]
    [:span.cm-hr         :color :#999]
    [:span.cm-link       :color :#00c]

    (css-comment "I'm there")
    ]
))

(write-css theme-name theme)

