(ns cat_backend.db_test
  (:require
   [cat_backend.db.db :refer :all]
   [clojure.test :refer :all]))

(if (= (get(search-user "test"):agent) "test")
  "test user are created"(create-user "test" "a4Bh5kingcrimson"))

;; Caminho Feliz
(deftest search-user-test
  (testing "Testando a função de buscar usuário"
    (testing "search-user"
      (is (= (get(search-user "test"):agent) "test")))))

(deftest create-user-test
  (testing "Testando a função de criar usuário"
    (testing "create-user"
      (is (= (create-user "test2" "a4Bh5kingcrimson") [1])))))

;; Caminho Triste
(deftest search-user-fail-test
  (testing "Testando a função de buscar usuário com um nome que não existe"
    (testing "search-user"
    (is (= (search-user {:fail "fail"}) "Unable to search for user")))))

(if (= (get(search-user "test"):agent) "test")
  "test user are created"(create-user "test" "a4Bh5kingcrimson"))