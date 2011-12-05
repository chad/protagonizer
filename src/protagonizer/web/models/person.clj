(ns protagonizer.web.models.person
  (:require [clojure.java.jdbc :as sql]))

(defn all []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/with-query-results results
      ["select * from people order by id desc"]
      (into [] results))))

(defn create [person]
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/insert-values :person [:name :github_id :tracker_id] [person])))
