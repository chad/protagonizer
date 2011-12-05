(defproject protagonizer "1.0.0-SNAPSHOT"
  :description "wtf are you and your people doing? "
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [compojure "0.6.4"]
                 [clj-http "0.1.3"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [hiccup "0.3.7"]]
            :dev-dependencies [[lein-ring "0.4.5"]]
              :ring {:handler protagonizer.web/app}
            :main protagonizer.main)

