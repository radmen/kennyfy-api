(ns kennyfy-api.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [compojure.core :refer [defroutes POST]]
            [kennyfy-api.server :refer :all]
            [radmen.kennyfy :refer [kenny-speak kenny-translate]])
  (:gen-class))

(def cli-options
  [["-d" nil "Run in dev mode"
    :id :dev?]
   ["-p" "--port PORT" "Port number"
    :default 8080
    :parse-fn #(Integer/parseInt %)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]])

(defn is-dev?
  [{:keys [options]}]
  (:dev? options))

(defroutes all-routes
  (POST "/speak" [text]
    (json-response {:text (kenny-speak text)}))
  (POST "/translate" [text]
    (json-response {:text (kenny-translate text)})))

(defn -main
  [& args]
    (let [options (parse-opts args cli-options)
          port    (->> options :options :port)]
      (do
        (run-server {:routes #'all-routes
                     :dev? (is-dev? options)
                     :port port})
        (println "Server running on port" port))))
