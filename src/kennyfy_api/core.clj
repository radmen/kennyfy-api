(ns kennyfy-api.core
  (:require [org.httpkit.server :refer [run-server]]
            [ring.middleware.reload :as reload])
  (:gen-class))

(defn app
  [req]
  {:status 200
   :headers {"Content-type" "text/html"}
   :body "Master Kenobi!"})

(defn is-dev?
  [& args]
  (do
    (println (first args))
    true)) ;; @TODO do something

(defn -main
  [& args]
    (let [handler (if (is-dev? args)
                    (reload/wrap-reload #'app)
                    #'app)]
      (do         
        (run-server handler {:port 8080})
        (println "Server running on port 8080"))))
