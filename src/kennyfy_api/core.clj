(ns kennyfy-api.core
  (:require [org.httpkit.server :refer [run-server]]
            [ring.middleware.reload :as reload]
            [clojure.tools.cli :refer [parse-opts]]
            [compojure.core :refer [defroutes POST]]
            [ring.middleware.json :refer [wrap-json-params]]
            [clojure.data.json :as json]
            [radmen.kennyfy :refer [kenny-speak kenny-translate]])
  (:gen-class))

(def cli-options
  [["-d" nil "Run in dev mode"
    :id :dev?]
   ["-p" "--port PORT" "Port number"
    :default 8080
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]])

(defn json-response
  [data & [status]]
  {:status (or status 200)
   :headers {"Content-type" "application/json"}
   :body (json/write-str data)})

(defroutes all-routes
  (POST "/speak" [text]
    (json-response {:text (kenny-speak text)}))
  (POST "/translate" [text]
    (json-response {:text (kenny-translate text)})))

(defn is-dev?
  [{:keys [options]}]
  (:dev? options))

(defn create-handler
  [{:keys [dev?]}]
  (let [base-handler (if dev?
                       (reload/wrap-reload #'all-routes)
                       #'all-routes)]
    (-> base-handler
        wrap-json-params)))

(defn -main
  [& args]
    (let [options (parse-opts args cli-options)
          handler (create-handler {:dev? (is-dev? options)})
          port    (->> options :options :port)]
      (do
        (run-server handler {:port port})
        (println "Server running on port" port))))
