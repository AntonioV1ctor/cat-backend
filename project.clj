(defproject cat_backend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
  [org.clojure/clojure "1.11.1"]
  [compojure "1.7.2"]
  [http-kit "2.9.0-beta2"]
  [cheshire "6.1.0"]
  [org.clojure/java.jdbc "0.7.12"]
  [mysql/mysql-connector-java "8.0.33"]
  [ring/ring-defaults "0.7.0"]
  [buddy/buddy-sign "3.5.351"]
  [ring-cors "0.1.13"]]


  :main ^:skip-aot cat_backend.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
