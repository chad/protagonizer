; boiler-plate stolen from abedra's shouter
(ns protagonizer.main
  (:use [compojure.core :only [defroutes]])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as ring]
            [protagonizer.web.controllers.people]
            [protagonizer.web.views.layout :as layout]))

(defroutes routes
  protagonizer.web.controllers.people/main-routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def application (handler/site routes))

(defn start [port]
  (ring/run-jetty (var application) {:port (or port 8080)
                                     :join? false}))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (start port)))

