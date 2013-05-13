(ns generator.common
  (:use cljss.core))

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

    [:.console :> :li]})

(def inline-errors
  #{[:.inline-exception :pre]
    [:#instarepl :.usage.exception]})

