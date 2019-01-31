(defproject kennyfy-api "0.1.0"
  :description "API converting text to kennyspeak"
  :url "https://github.com/radmen/kennyfy-api"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [http-kit "2.3.0"]
                 [ring/ring-devel "1.7.0"]
                 [ring/ring-core "1.7.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [compojure "1.6.1"]
                 [ring/ring-json "0.4.0"]
                 [org.clojure/data.json "0.2.6"]
                 [com.github.radmen/clojure-kennyfy "0.1.2"]]
  :repositories [["jitpack" "https://jitpack.io"]]
  :main ^:skip-aot kennyfy-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
