(ns cat_backend.core
(:gen-class)
(:require
[cat_backend.routes :refer :all]
[compojure.core :refer [defroutes]]
[cat_backend.services.userCreate :refer [generate-initial-admin]]
           [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
           [org.httpkit.server :as hk-server]
           [ring.middleware.cors :refer [wrap-cors]]))



(defroutes app
(home-route)             ;; /my-app
(generate-user-route)    ;; /api/v1/generate-user
(user-login-route)       ;; /api/v1/user-login
(jwt-verification-route) ;; /api/v1/protected
(notfound-route)         ;; NotFound 404
)

(defn -main [& args]
  (def my-server (hk-server/run-server (wrap-cors (wrap-defaults app api-defaults) 
  :access-control-allow-origin [#".*"]
  :access-control-allow-methods [:get :put :post :delete]) 
  {:port 5000}))
  (generate-initial-admin)
  (println "Starting server in port 5000."))