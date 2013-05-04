(ns generator.core
  (:use [cljss.core :only (compact-css)]))


(def home-path (System/getProperty "user.home"))
(def file-separator (System/getProperty "file.separator"))
(def lighttable-theme-directory ".lighttable/css/themes")

(defn make-theme-css-class
  "Construct the css class name used by LT when
  a theme is loaded."
  [theme-name]
  (str ".cm-s-" theme-name))

(def rules vector)
(def inside-rules list)
(def group-rules concat)

(defn css-comment [c]
  (str "/* " c " */"))

(defn make-theme-path
  "Given a theme name, make the absolute path
  of the file in which spit the compiled rules."
  [theme-name]
  (str home-path file-separator
       lighttable-theme-directory file-separator
       theme-name
       ".css"))

(defn- compile-css [theme out-fn]
  (out-fn (apply compact-css theme)))

(defn print-css [theme]
  (compile-css theme println))

(defn write-css [theme-name theme]
  (let [theme-path (make-theme-path theme-name)]
      (compile-css theme (partial spit theme-path))))
