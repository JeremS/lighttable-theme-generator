(ns generator.common
  (:use cljss.core))

;; Attempt at documenting the relevant selector when it comes to style LT

(def code-mirror-classes
  {
   ; BASICS
   :.CodeMirror "Set height, width, borders, and global font properties here"
   :.CodeMirror-scroll "Set scrolling behaviour here"

   ; PADDING
   :.CodeMirror-lines "Vertical padding around content"
   [:.CodeMirror :pre] "Horizontal padding of content"
   :.CodeMirror-scrollbar-filler "The little square between H and V scrollbars"

   ; GUTTER
   :.CodeMirror-gutters ""
   :.CodeMirror-linenumbers ""
   :.CodeMirror-linenumber ""

   ; COMPLETION
   :.CodeMirror-hints "Completion box"
   [:.CodeMirror-hints :ul :li] "Completion choices"
   [:.CodeMirror-hints :input] "Text at the top of the completion box."
   [:.CodeMirror-hints :.selected] "Selected choice"

   ; CURSOR
   [:.CodeMirror :div.CodeMirror-cursor] ""

   ; ???
   [:.cm-s-default :.CodeMirror-selected] ""
   [:.cm-s-default :.CodeMirror-focused :.CodeMirror-selected] ""

   [:.cm-SUBST] ""
   [:.cm-s-default :span.cm-SUBST] ""

   ; Shown when moving in bi-directional text ???
   [:.CodeMirror :div.CodeMirror-secondarycursor] ""
   [:.CodeMirror.cm-keymap-fat-cursor :div.CodeMirror-cursor] ""
   [:.CodeMirror :div.CodeMirror-cursor.CodeMirror-overwrite] "Can style cursor different in overwrite (non-insert) mode"


   [:.cm-s-default :.activeline] ""
   [:.cm-s-default :span.CodeMirror-matchingbracket] ""
   [:.cm-s-default :span.CodeMirror-nonmatchingbracket] ""

   [:.cm-s-default :.cm-searching] ""
   [:.cm-s-default :.searching-current] ""

   ; DEFAULT THEME
   [:.cm-s-default :.cm-keyword] ""
   [:.cm-s-default :.cm-atom] ""
   [:.cm-s-default :.cm-number] ""
   [:.cm-s-default :.cm-def] ""
   [:.cm-s-default :.cm-variable] ""
   [:.cm-s-default :.cm-variable-2] ""
   [:.cm-s-default :.cm-variable-3] ""
   [:.cm-s-default :.cm-property] ""
   [:.cm-s-default :.cm-operator] ""
   [:.cm-s-default :.cm-comment] ""
   [:.cm-s-default :.cm-string] ""
   [:.cm-s-default :.cm-string-2] ""
   [:.cm-s-default :.cm-meta] ""
   [:.cm-s-default :.cm-error] ""
   [:.cm-s-default :.cm-qualifier] ""
   [:.cm-s-default :.cm-builtin] ""
   [:.cm-s-default :.cm-bracket] ""
   [:.cm-s-default :.cm-tag] ""
   [:.cm-s-default :.cm-attribute] ""
   [:.cm-s-default :.cm-header] ""
   [:.cm-s-default :.cm-quote] ""
   [:.cm-s-default :.cm-hr] ""
   [:.cm-s-default :.cm-link] ""

   [:.cm-s-default :span.cm-bracket0] ""
   [:.cm-s-default :span.cm-bracket1] ""
   [:.cm-s-default :span.cm-bracket2] ""
   [:.cm-s-default :span.cm-bracket3] ""
   [:.cm-s-default :span.cm-bracket4] ""
   [:.cm-s-default :span.cm-bracket5] ""
   [:.cm-s-default :span.cm-bracket6] ""
   [:.cm-s-default :span.cm-bracket7] ""
   [:.cm-s-default :span.cm-bracket8] ""
   [:.cm-s-default :span.cm-bracket9] ""
   [:.cm-s-default :span.cm-bracket10] ""
   [:.cm-s-default :span.cm-bracket11] ""
   [:.cm-s-default :span.cm-bracket12] ""

   :.cm-negative ""
   :.cm-positive ""
   :.cm-header ""
   :.cm-strong ""
   :.cm-em ""
   :.cm-emstrong ""
   :.cm-link ""

   :.cm-invalidchar ""

   })


(def lighttable-skin
  {
   ; INLINE RESULTS
   :.inline-result "Inline result wrapper, not visible I think."
   [:.inline-result :.truncated] "Inline results when truncated"
   [:.inline-result.open :.full] "Inline results when opened"

   [:.inline-exception :pre] "Inline exception"

   [:.underline-result :pre] "???"

   [:.inspector-object (-> :h2 before)] "???"
   [:.inspector-object :em] "???"
   :.inspector-object ""

   [:.inline-result :.inspector-object] ""
   [:.inline-result :.inspector-object (-> :h2 before)] ""

   ; MAIN PANE
   :#multi "Main pane."

   [:#multi :.tabsets] ""
   [:#multi :.tabset] ""
   [:#multi :.tabset :+ :.tabset] "Used to put vertical bar between tabsets"

   ; multi .list -> tabs names
   [:#multi :.list] "Tabs bar"
   [:#multi :.list :li]  "Tabs"

   ; current tab
   [:#multi :.list :.active] "Tab that's got the cursor"
   [:#multi :.list :li:hover] "..."

   ; unsaved tab
   [:#multi :.list :.dirty] "Tabs modified & not saved"
   [:#multi :.list (-> :.dirty after)] "Used to put a star on dirty tabs"

   [:#multi :.list.dragging :li] "Moving tab how does that works..."

   ; ???
   [:#multi :.ui-sortable-helper] "???"
   [:#multi (-> :.dirty.ui-sortable-placeholder after)] "???"

   ;  ???
   [:#multi :.fullscreen] "???"
   [:#multi (-> :.fullscreen hover)] "???"

   ; CONSOLE
   [:#bottombar :.content] "Console"
   (c-> :.console :li) "???"
   [:.console :td]     "???"
   (c-> :.console (c-+ :li :li)) "???"
   (c-> :.console (c-+ :li.error :li)) "???"
   [:.console :li.error] "???"
   [:.console :li.error :table :td] "???"
   [:.console :li.error :tr:hover :td] "???"
   [:.console :li :.file] "???"
   [:.console :li :.line] "???"

   ; ???
   :#intro  "???"

   ; INSTAREPL
   [:#instarepl :.main] ""
   [:#instarepl :.error] ""
   [:#instarepl :.livetoggler] ""
   [:#instarepl :.livetoggler.off] ""
   [:#instarepl (-> :.livetoggler.off hover)] ""
   [:#instarepl :.usage] ""
   [:#instarepl :.usage.result] ""
   [:#instarepl :.usage.exception] ""
   [:#instarepl :.iresult] ""


   ; POPUP
   :.popup "Popup for confirmations, new versions ready... contains the whole screen"
   (c-> :.popup :div :div) "The div enclosing the popup message"
   [:.popup :.button] ""
   [:.popup (-> :.button hover)] ""
   [:.popup :.button.active] ""
   [:.popup "::-webkit-scrollbar-thumb"] "???"

   ; ???
   :#notifos "???"
   [:#notifos :li] "???"

   ; ???
   [:#markdown-preview :.preview :p :code] ""
   [:#markdown-preview :.preview :pre] ""

   ; VERSION PANE
   [:#version-info :.info :dt] ""
   [:#version-info :.button] ""
   [:#version-info (-> :.button hover)] ""

   ; LEFT PANE
   [:#side "::-webkit-scrollbar-thumb"] ""

   :#sidebar-wrapper "Wraps the whole side area, is never folded, just transparent."

   [:#sidebar (-> :li hover)] ""
   [:#sidebar :.current] ""

   [:#side :.content] "Content of the selected side menu, it is folded when inactive."

   [:#side :.open] ""
   [:#side :.open :ul.files] ""
   [:#side :.open :.files :li.folder] ""
   [:#side :.open :.files (-> :li hover)] ""
   [:#side :.open :.segs] ""
   [:#side :.open :.segs :li] ""
   [:#side :.open :.segs (-> :li hover)] ""

   [:#side :.command :input] ""
   [:#side :.command (-> :h2 hover)] ""

   [:#side :.navigate :input] ""

   [:.filter-list] "List used in menus like navigate"
   [:.filter-list :em] ""
   [:.filter-list :.selected] ""
   [:.filter-list (-> :li hover)] ""
   [:.filter-list :.selected :em] ""
   [:.filter-list (-> :li hover) :em] ""

   [:#side :.workspace :ul.buttons (-> :li hover (css-not :.sep))] ""
   [:#side :.workspace (c-> (-> :li hover) :div :p)] ""
   [:#side :.workspace :li (-> :p hover)] ""
   [:#side :.workspace (c-> :.recent :div :ul)] ""
   [:#side :.workspace (c-> :.recent :div :ul :li)] ""
   [:#side :.workspace (c-> :.recent :div :ul (-> :li first-child))] ""
   [:#side :.workspace (c-> :.recent :div :ul (-> :li last-child))] ""
   [:#side :.workspace (c-> :.recent :div :ul (c-+ :li  :li))] ""
   [:#side :.workspace (c-> :.recent :div :ul (-> :li hover))] ""
   [:#side :.workspace (c-> :.recent :div :ul (c-+ (-> :li hover) :li))] ""
   [:#side :.workspace :.recent (-> :h2 hover)] ""


   ; CONNECT ?
   [:#side :.clients (c-> :.list :ul :li)] "Clients wrappers"
   [:#side :.clients :h2] ""
   [:#side :.clients :td] ""
   [:#side :.clients (c-+ :td :td)] ""
   [:#side :.clients :.button] ""

   [:#side :.clients (-> :.button hover)] ""
   [:#side :.clients :.load-wrapper :.img] ""
   [:#side :.clients :.list :.active] ""
   [:#side :.clients :.list (c-+ :li :li)] ""
   [:#side :.clients :.list :.active :*] ""

   [:#side :.clients :.list :.active :.button] ""
   [:#side :.clients :.list :.active (-> :.button hover)] ""
   [:#side :.clients :.connector :li] ""
   [:#side :.clients :.connector (-> :li first-child)] ""
   [:#side :.clients :.connector (-> :li last-child)] ""

   [:#side :.clients :.connector (c-+ :li :li)] ""
   [:#side :.clients :.connector :li :h2] ""
   [:#side :.clients :.connector (-> :li hover) :h2] ""
   [:#side :.clients :.connector (-> :li hover)] ""
   [:#side :.clients :.connector (c-+ (-> :li hover) :li)] ""
   [:#side :.clients (-> :.toggle hover)] ""


   :#statusbar ""
   [:#statusbar :.console-toggle] ""
   [:#statusbar (-> :.console-toggle hover)] ""
   [:#statusbar :.console-toggle.dirty] ""
   [:#statusbar :.log :.error] ""
   [:#statusbar :.log :.tip] ""

   [:.load-wrapper :.img] ""


   [:#find-bar :input] ""
   [:#find-bar "::-webkit-input-placeholder"] ""

   [:#keybinding :ul.keys :li] ""
   [:#keybinding :input] ""
   [:#keybinding :.all-mappings] ""
   [:#keybinding :.filter-list :ul] ""
   [:#keybinding :.all-mappings (c-> :td :ul (-> :li hover))] ""
   [:#keybinding :.all-mappings (-> :li hover) :.remove] ""
   [:#keybinding :.all-mappings :li (-> :.remove hover)] ""

   [:#browser :input] ""
   [:#browser :button] ""
   [:#browser :iframe] ""
   [:#browser "::-webkit-scrollbar"] ""
   [:#browser "::-webkit-scrollbar-track"] ""
   [:#browser "::-webkit-scrollbar-corner"] ""
   [:#browser "::-webkit-scrollbar-thumb"] ""

   [:.docs "::-webkit-scrollbar"] ""
   [:.docs "::-webkit-scrollbar-track"] ""
   [:.docs "::-webkit-scrollbar-corner"] ""
   [:.docs "::-webkit-scrollbar-thumb"] ""

   })


(defn CM-sels []
  (-> code-mirror-classes keys set))

(defn LT-sels []
  (-> lighttable-skin keys set))
