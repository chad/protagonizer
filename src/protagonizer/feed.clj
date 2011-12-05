(ns protagonizer.feed
  (:require [clojure.xml :as parser]
       [clj-http.client :as client]))
;(def root-github-url "http://svn.livingsocial.com")
(def root-github-url "https://github.com")

(defn url-for-user [user]
  (str root-github-url "/" user ".atom"))

(defmulti parse-content :tag)
(defmethod parse-content :published [element]
  ; parse the date
  {:published (:content element)})
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
