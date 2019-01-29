(ns kennyfy-api.server
  (:require [org.httpkit.server :as server]
            [ring.middleware.reload :as reload]
            [ring.middleware.json :refer [wrap-json-params]]
            [clojure.data.json :as json]))

(defn create-handler
  [{:keys [dev? routes]}]
  (let [base-handler (if dev?
                       (reload/wrap-reload routes)
                       routes)]
    (-> base-handler
        wrap-json-params)))

(defn json-response
  [data & [status]]
  {:status (or status 200)
   :headers {"Content-type" "application/json"}
   :body (json/write-str data)})

(defn run-server
  [{:keys [routes dev?] :as all-options}]
  (let [options (dissoc all-options :routes :dev?)
        handler (create-handler {:dev? dev? :routes routes})]
    (server/run-server handler options)))
