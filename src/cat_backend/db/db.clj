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
  (first(j/query mysql-db
                 ["SELECT * FROM users WHERE agent = ?" name])))

(defn create-user [name pass]
  (j/execute! mysql-db
              ["INSERT INTO users (agent, pass) VALUES (?, ?)" name pass]))

(defn send-message [authorID msg]
  (j/execute! mysql-db
              ["INSERT INTO mensagens (user_id, msg) VALUES (?,?)" authorID msg]))

(defn load-all-messages []
  (j/query mysql-db
           ["SELECT msg FROM mensagens"]))

(defn load-messages-byID [position]
  (get(nth (j/query mysql-db
              ["SELECT msg FROM mensagens"])position):msg))


