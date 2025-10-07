(ns cat_backend.services.jwt
(:require
    [buddy.sign.jwt :as jwt]
    [clojure.string :as str]))


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
    (last (str/split auth-header #" "))))