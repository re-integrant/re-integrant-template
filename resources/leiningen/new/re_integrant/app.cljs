(ns {{name}}.module.app
  (:require [integrant.core :as ig]
            [reagent.core :as reagent]
            [{{name}}.views :as views]
            [re-integrant.core :as re-integrant :refer-macros [defevent defsub deffx]]))

;; Initial DB
(def initial-db {::errors nil ::loading? false})

;; Subscriptions
(defmulti reg-sub identity)
(defsub reg-sub ::errors #(::loading? %))
(defsub reg-sub ::loading? #(::loading? %))

;; Events
(defmulti reg-event identity)
(defevent reg-event ::init
  (fn [db _]
    (merge db initial-db)))
(defevent reg-event ::halt
  (fn [db _]
    (->> db
         (remove #(= (namespace (key %)) (namespace ::x)))
         (into {}))))

;; Effects
(defmulti reg-fx identity)
(deffx reg-fx ::redirect
  (fn [path]
    (set! js/location.href path)))

;; Init
(defmethod ig/init-key :{{name}}.module/app [k {:keys [:mount-point-id]}]
  (js/console.log (str "Initializing " k))
  (let [container (.getElementById js/document mount-point-id)]
    (when container (reagent/render [views/app-container] container))
    (re-integrant/init-module
     {:reg-sub reg-sub :reg-event reg-event :reg-fx reg-fx
      :init-event [::init] :halt-event [::halt]
      :container container})))

;; Halt
(defmethod ig/halt-key! :{{name}}.module/app [k {:keys [:container] :as module}]
  (js/console.log (str "Halting " k))
  (reagent/unmount-component-at-node container)
  (re-integrant/halt-module module))
