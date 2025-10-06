(ns cat_backend.core
(:gen-class)
(:require
           [org.httpkit.server :as hk-server]
           [compojure.core :refer :all]
           [compojure.route :as route]
           [cheshire.core :refer :all]
           [db.db :as db]
           [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
           [buddy.sign.jwt :as jwt]
           [ring.middleware.cors :refer [wrap-cors]]))



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


;; Nessa parte eu vai ser expecificamente para o JWT
(def my-secret "1d06aa7dc13276de6077e5223f595d8c69735e4f")

(defn generate-token [useragent]
  (jwt/sign {:user useragent} my-secret))

;; Preciso estudar sobre algumas partes desse código e de como ele foi construido
(defn verify-token [token]
  (try
    (jwt/unsign token my-secret)
    (catch Exception e
      nil)))

;; Preciso estudar sobre algumas partes desse código e de como ele foi construido
(defn get-token-from-request [request]
  (when-let [auth-header (get-in request [:headers "authorization"])]
    (last (clojure.string/split auth-header #" "))))

;; Fim parte do JWT



(defn user-random []
 (str (rand-nth primary-name) (rand-nth second-name)"#"(rand-int 9999)) 
)

(defn pass-random []
 (rand-int 99999999)
)

(defn generate-user []
  (def user (user-random))
  (def pass (pass-random))
  (db/create-user user pass)
  (generate-string {:user user :pass pass}))

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


(defroutes app
  ;; Rotas Publicas [generate-user/ user-login/]
  ;; Rotas Privadas [global-chat/]
  (GET "/api/v1/generate-user" []
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (generate-user)})
  
  (POST "/api/v1/user-login" [user pass]
    (user-login user pass))


;; Tenho que estudar o funcionamento de algumas coisas presentes nesse código:
;; if-let
;; O que esse trecho de código faz: (if-let [payload (verify-token token))

  (GET "/api/v1/protected" request
  (if-let [token (get-token-from-request request)]
    (if-let [payload (verify-token token)]
      {:status 200
       :headers {"Content-Type" "application/json"}
       :body (generate-string {:msg "Acesso liberado!"
                              :user (:user payload)})}
      {:status 401
       :headers {"Content-Type" "application/json"}
       :body (generate-string {:error "Token inválido"})})
    {:status 401
     :headers {"Content-Type" "application/json"}
     :body (generate-string {:error "Token não enviado"})}))


  
  (POST "/my-app" [name]
    (if name
     {:status 200
      :headers {"Content-Type" "application/json"}
      :body (generate-string {:msg (str "Sucess! " name)})}
     {:status 401
      :headers {"Content-Type" "application/json"}
      :body (generate-string {:msg "Parameter invalid!"})}))

  
  (route/not-found {:status 404
                    :headers {"Content-Type" "application/json"}
                    :body (generate-string {:msg "Not Found 404"})}))

(defn -main [& args]
  (def my-server (hk-server/run-server (wrap-cors (wrap-defaults app api-defaults) 
  :access-control-allow-origin [#".*"]
  :access-control-allow-methods [:get :put :post :delete]) 
  {:port 5000}))
  (generate-initial-admin)
  (println "Starting server in port 5000."))