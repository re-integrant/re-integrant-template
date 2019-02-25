(ns leiningen.new.re-integrant
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "re-integrant"))

(defn re-integrant
  "Create a new re-integrant web application."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' re-integrant project.")
    (->files data
             ["README.md" (render "README.md" data)]
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/config.cljs" (render "config.cljs" data)]
             ["src/{{sanitized}}/core.cljs" (render "core.cljs" data)]
             ["src/{{sanitized}}/views.cljs" (render "views.cljs" data)]
             ["src/{{sanitized}}/module/app.cljs" (render "app.cljs" data)]
             ["resources/public/index.html" (render "index.html" data)]
             ["resources/public/css/styles.css" (render "styles.css" data)]
             ["dev/user.clj" (render "user.clj" data)]
             ["dev/user.cljs" (render "user.cljs" data)]
             [".gitignore" (render "gitignore" data)])))
