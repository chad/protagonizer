(ns protagonizer.github
  (:require [clojure.xml :as parser]
       [clj-http.client :as client]
       [clj-time.format :as time-format]))

(def root-github-url "https://github.com")

(defn url-for-user [user]
  (str root-github-url "/" user ".atom"))

(defn parse-date [element, name]
  (let [formatter (time-format/formatters :date-time-noms)
        date (time-format/parse formatter (first (seq (:content element))))]
                                {name date}))

(defmulti parse-content :tag)
(defmethod parse-content :updated [element]
  (parse-date element :updated))
(defmethod parse-content :published [element]
  (parse-date element :published))
(defmethod parse-content :link [element]
  {:link (:content element)})
(defmethod parse-content :title [element]
  {:title (:content element)})
(defmethod parse-content :content [element]
  {:content (:content element)})
(defmethod parse-content :id [element]
  {:id (:content element)})
(defmethod parse-content :default [element]
  nil
  )

(defn parse-entry [element]
  (apply merge {} (remove nil? (map parse-content element))))

(defn parse [user]
  (parser/parse (url-for-user user)))

(defn entries-from [elements]
  (filter #(= :entry (:tag %)) elements))

(defn activity-for [user]
  (map parse-entry (map :content (entries-from (xml-seq (parse user)))))
  )
