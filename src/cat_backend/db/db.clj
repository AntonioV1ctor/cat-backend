(ns cat_backend.db.db
  (:require
   [clojure.java.jdbc :as j]))

(def mysql-db {:dbtype "mysql"
               :dbname "cat"
               :user "root"
               :password "62008f36d4a40a15d1c7c0720bbbf847680be979"})

(defn pass-random []
 (rand-int 99999999)
)

(defn search-user [name]
  (try
    (first(j/query mysql-db
              ["SELECT * FROM users WHERE agent = ?" name]))
  (catch Exception e
    (str "Unable to search for user"))))


(defn create-user [name pass]
  (try
    (j/execute! mysql-db
              ["INSERT INTO users (agent, pass) VALUES (?, ?)" name pass])
  (catch Exception e
    (str "Unable to create a user"))))


(defn exclude-user [name]
  (try
    (j/execute! mysql-db
              ["DELETE FROM users WHERE agent=?" name])
    (catch Exception e
    (str "Unable to remove user"))))


(defn send-message [authorID msg]
  (try
    (j/execute! mysql-db
                ["INSERT INTO mensagens (user_id, msg) VALUES (?,?)" authorID msg])
  (catch Exception e
    (str "Unable to save message"))))


(defn load-all-messages []
  (try
    (j/query mysql-db
           ["SELECT msg FROM mensagens"])
  (catch Exception e
    (str "Unable to load all messages"))))


(defn load-messages-byID [position]
  (try
    (get(nth (j/query mysql-db
                      ["SELECT msg FROM mensagens"])position):msg)
    (catch Exception e
      (str "Unable to load message by ID"))))

