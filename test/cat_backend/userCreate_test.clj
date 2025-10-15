(ns cat_backend.userCreate_test
(:require 
[cat_backend.services.userCreate :refer :all]
            [clojure.test :refer :all]
            [cat_backend.core :refer :all]))


;; caminho feliz
(deftest user-random-test
  (testing "Testando a função de criação de usuário aleatório"
    (testing "user-random"
      (is (> (count (user-random))1) "Essa função verifica se o nome gerado possui pelo menos 15 caracters!"))))

(deftest pass-random-test
  (testing "Testando a função de criação de senha aleatória do usuário"
    (testing "pass-random"
      (is (> (count (str(pass-random)))5)))))

(deftest generate-user-format-test
  (testing "Testando o formato do JSON gerado"
    (testing "generate-user"
      (is (.contains (generate-user) "user")))))

;; caminho triste
(deftest user-login-fail-test
  (testing "Testando a função de login com usuário inexistente"
    (testing "user-login"
      (is (= (:status (user-login "inexistente" "123")) 401)))))

(deftest user-login-wrong-pass-test
  (testing "Testando a função de login com senha errada"
    (testing "user-login"
      (is (= (:status (user-login "test" "senhaerrada")) 401)))))
