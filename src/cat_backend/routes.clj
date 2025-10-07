(ns cat_backend.routes
(:require
[cat_backend.services.userCreate :refer :all]
[cat_backend.services.jwt :refer :all]
    [cheshire.core :refer :all]
    [compojure.route :as route]
    [compojure.core :refer :all]))

(defn generate-user-route []
(GET "/api/v1/generate-user" []
    {:status 200
     :headers {"Content-Type" "application/json"}
     :body (generate-user)}))

(defn user-login-route []
(POST "/api/v1/user-login" [user pass]
    (user-login user pass)))

(defn jwt-verification-route []
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
     :body (generate-string {:error "Token não enviado"})})))

(defn home-route []
(POST "/my-app" [name]
    (if name
     {:status 200
      :headers {"Content-Type" "application/json"}
      :body (generate-string {:msg (str "Sucess! " name)})}
     {:status 401
      :headers {"Content-Type" "application/json"}
      :body (generate-string {:msg "Parameter invalid!"})})))

(defn notfound-route []
(route/not-found {:status 404
                    :headers {"Content-Type" "application/json"}
                    :body (generate-string {:msg "Not Found 404"})}))