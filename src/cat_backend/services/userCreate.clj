(ns cat_backend.services.userCreate
(:require
    [cheshire.core :refer :all]
    [cat_backend.db.db :as db]
    [cat_backend.services.jwt :refer :all]
    [clojure.java.jdbc :as j]))

;; default JSON page: application/json
;; default HTML page: "Content-Type" "text/html
(def second-name [
  "fast", "strong", "bright", "dark", "calm", "happy", 
  "agile", "wise", "brave", "mysterious", "stealthy", 
  "silent", "glitchy", "encrypted", "anonymous", "shadow", 
  "cyber", "ghostly", "neon", "binary"])

(def primary-name [
  "wolf", "dragon", "tiger", "owl", "phoenix", "star", 
  "mountain", "river", "warrior", "deer", "hacker", "ninja",
  "samurai", "cipher", "matrix", "ghost", "phantom", "byte", 
  "root", "virus"])

(defn user-random []
 (str (rand-nth primary-name) (rand-nth second-name)"#"(rand-int 9999)))

(defn pass-random []
 (rand-int 99999999))

(defn generate-user []
  (let [user (user-random)
        pass (pass-random)]
    (db/create-user user pass)
    (generate-string {:user user :pass pass})))

(defn user-login [user pass]
  (if (= user (get (db/search-user user) :agent))
    (if (= pass (get (db/search-user user) :pass))
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body (generate-string {:msg "Sucessful Login" 
                               :login "true" 
                               :token (generate-token user)})}
                               
      {:status 401
       :headers {"Content-Type" "application/json"}
       :body (generate-string {:msg "USER or PASS incorrect!" 
                               :login "false"})})

    {:status 401
     :headers {"Content-Type" "application/json"}
     :body (generate-string {:msg "USER or PASS incorrect!"  
                             :login "false"})}))

(defn generate-initial-admin []
  (if (= "admin" (get (db/search-user "admin") :agent))
    (println "Login with admin account")
    (let [password (pass-random)]
      (j/execute! db/mysql-db
                  ["INSERT INTO users (agent, pass) VALUES (?, ?)" "admin" password])
      (println (format "Admin Panel: admin:%s" password)))))
