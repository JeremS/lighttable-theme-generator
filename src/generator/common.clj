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
    [:#side :.workspace :.recent :h2]
    [:#side :.clients :.toggle]
    [:#multi :.list :li]
    [:.popup :.button]
    [:#instarepl :.livetoggler.off]
    [:#version-info :.button]
    [:#keybinding :button]
    [:#keybinding :.all-mappings :.remove]
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
  "Completion boxes"
  #{[:.CodeMirror-hints :ul]
    [:#keybinding :.filter-list :ul]})

(def workspace-selecion
  "Workspace files names"
  [:#side :.workspace :li (-> :p hover after)])

(def selection-options
  "Completion options, commands, navigate files"
  #{[:.filter-list #{:.selected (-> :li hover)}]
    [:#keybinding :.filter-list :ul]})


(def inline-results
  #{[:.inline-result :.truncated]
    [:.inline-result.open :.full]})

(def inline-usage
  #{[:#instarepl :.usage]})

(def inline-errors
  #{[:.inline-exception :pre]
    [:#instarepl :.usage.exception]})


