(ns {{name}}.config
  (:require [integrant.core :as ig]
            [{{name}}.module.app]
            [re-integrant.module.router]))

(defn system-conf []
  {:re-integrant.module/router
   {:routes ["/" {""      :home
                  "about" :about}]}

   :{{name}}.module/app
   {:mount-point-id "app"
    :router (ig/ref :re-integrant.module/router)}})
