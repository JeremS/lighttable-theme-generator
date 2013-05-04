(ns generator.lesserdark.theme
  (:use [cljss.core :only (compact-css)]
        clojure.repl)
  (:import java.io.File))


(def theme
  (rules
   "/*
http://lesscss.org/ dark theme
Ported to CodeMirror by Peter Kroon
*/"

[:#multi.dark2  :background :#262626 ]

[:.cm-s-dark2 :line-height :1.3em]

[:.cm-s-dark2
 :background :#262626
 :color :#EBEFE7
 :text-shadow [0 :-1px :1px :#262626]]

 [[:.cm-s-dark2 :div.CodeMirror-selected]
  :background: [:#45443B :!important]] "/* 33322B*/"

[[:.cm-s-dark2 :.CodeMirror-cursor]
 :border-left: [:1px :solid :white :!important]]
   "/*editable code holder*/"
[[:.cm-s-dark2 :.CodeMirror-lines]
 :margin-left :3px
 :margin-right :3px]

   "/*65FC65*/"
[[:div.CodeMirror :.cm-s-dark2
  :span.CodeMirror-matchingbracket]
 :color :#7EFC7E]

[[:.cm-s-dark2 :.CodeMirror-gutter]
 :background :#262626
 :border-right [:1px :solid :#aaa]
 :padding-right :3px
 :min-width :2.5em ]

[[:.cm-s-dark2 :.CodeMirror-gutter-text] :color :#777]

[[:.cm-s-dark2 :span.cm-keyword]    :color :#599eff]
[[:.cm-s-dark2 :span.cm-atom]       :color :#C2B470]
[[:.cm-s-dark2 :span.cm-number]     :color :#B35E4D]
[[:.cm-s-dark2 :span.cm-def]        :color :white]
[[:.cm-s-dark2 :span.cm-variable]   :color :#D9BF8C]
[[:.cm-s-dark2 :span.cm-variable-2] :color :#669199]
[[:.cm-s-dark2 :span.cm-variable-3] :color :white]
[[:.cm-s-dark2 :span.cm-property]   :color :#92A75C]
[[:.cm-s-dark2 :span.cm-operator]   :color :#92A75C]
[[:.cm-s-dark2 :span.cm-comment]    :color :#666]
[[:.cm-s-dark2 :span.cm-string]     :color :#BCD279]
[[:.cm-s-dark2 :span.cm-string-2]   :color :#f50]
[[:.cm-s-dark2 :span.cm-meta]       :color :#738C73]
[[:.cm-s-dark2 :span.cm-error]      :color :#9d1e15]
[[:.cm-s-dark2 :span.cm-qualifier]  :color :#555]
[[:.cm-s-dark2 :span.cm-builtin]    :color :#ff9e59]
[[:.cm-s-dark2 :span.cm-bracket]    :color :#EBEFE7]
[[:.cm-s-dark2 :span.cm-tag]        :color :#669199]
[[:.cm-s-dark2 :span.cm-attribute]  :color :#00c]
[[:.cm-s-dark2 :span.cm-header]     :color :#a0a]
[[:.cm-s-dark2 :span.cm-quote]      :color :#090]
[[:.cm-s-dark2 :span.cm-hr]         :color :#999]
[[:.cm-s-dark2 :span.cm-link]       :color :#00c]
))



(def home-path
  (System/getProperty "user.home"))

(def file-separator
  (System/getProperty "file.separator"))

(def lighttable-theme-directory
  ".lighttable/css/themes")

(def theme-name "dark2")
(def theme-css-class (str ".cm-s-" theme-name))

(def theme-path
  (str home-path file-separator
       lighttable-theme-directory file-separator
       theme-name
       ".css"))


(defn compile-css [out-fn]
  (out-fn (apply compact-css theme)))

(defn print-css []
  (compile-css println))

(defn write-css []
  (compile-css (partial spit theme-path)))

(write-css )