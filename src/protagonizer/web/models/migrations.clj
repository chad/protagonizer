(ns protagonizer.web.models.migration
  (:require [clojure.java.jdbc :as sql]))

(defn create-people []
  (sql/with-connection (System/getenv "DATABASE_URL")
    (sql/create-table :people
                      [:id :serial "PRIMARY KEY"]
                      [:name :varchar "NOT NULL"]
                      [:github_id :varchar "NOT NULL"]
                      [:tracker_id :varchar "NOT NULL"]
                      [:created_at :timestamp "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"])))

(defn -main []
  (print "Creating database structure...") (flush)
  (create-people)
  (println " done"))
