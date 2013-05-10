(ns generator.core
  (:require [clojure.string :as string])
  (:use [cljss.core :only (css-with-style)]
        [cljss.compilation :only (styles)]))


(def home-path (System/getProperty "user.home"))
(def file-separator (System/getProperty "file.separator"))
(def lt-skin-dir ".lighttable/css/skins")
(def lt-theme-dir ".lighttable/css/themes")

(defn make-theme-css-class
  "Construct the css class name used by LT when
  a theme is loaded."
  [theme-name]
  (str ".cm-s-" theme-name))

(defn make-path [theme-name d]
  (str home-path file-separator
       d         file-separator
       theme-name ".css"))

(defn make-skin-path
  "Given a skin name, make the absolute path
  of the file in which spit the compiled rules."
  [skin-name]
  (make-path skin-name lt-skin-dir))

(defn make-theme-path
  "Given a theme name, make the absolute path
  of the file in which spit the compiled rules."
  [theme-name]
  (make-path theme-name lt-theme-dir))

(def compile-style
  (assoc (:compact styles)
    :selector-break 1))

(defn- compile-css [theme out-fn]
  (out-fn (apply css-with-style compile-style theme)))

(defn print-css [theme]
  (compile-css theme println))

(defn write-theme [theme-name theme]
  (let [theme-path (make-theme-path theme-name)]
      (compile-css theme (partial spit theme-path))))

(defn write-skin [skin-name skin]
  (let [skin-path (make-skin-path skin-name)]
      (compile-css skin (partial spit skin-path))))

