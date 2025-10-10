(ns cat_backend.userCreate_test
(:require 
[cat_backend.services.userCreate :refer :all]
            [clojure.test :refer :all]
            [cat_backend.core :refer :all]))


;; Unit Tests
(deftest user-random-test
  (testing "Testando a função de criação de usuário aleatório"
    (testing "user-random"
      (is (> (count (user-random))1) "Essa função verifica se o nome gerado possui pelo menos 15 caracters!"))))

(deftest pass-random-test
  (testing "Testando a função de criação de senha aleatória do usuário"
    (testing "pass-random"
      (is (> (count (str(pass-random)))5)))))


;;(deftest user-login-test
;;  (testing "Testando a função de login do usuário"
;;    (testing "user-login"
;;      (is (user-login )))))
