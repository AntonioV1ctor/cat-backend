(ns cat_backend.db_test
  (:require
   [cat_backend.db.db :refer :all]
   [clojure.test :refer :all]))


(defn db-fixure [test-fn]
  (create-user "test" "TEST1234kgcrk")
  (test-fn)
  (exclude-user "test")
  (exclude-user "test2"))

(use-fixtures :each db-fixure)
;; Caminho Feliz

(deftest search-user-test
  (testing "Testando a função de buscar usuário"
    (testing "search-user"
      (is (= (get (search-user "test") :agent) "test")))))

(deftest create-user-test
  (testing "Testando a função de criar usuário"
    (testing "create-user"
      (is (= (create-user "test2" "a4Bh5kingcrimson") [1])))))

;; Caminho Triste
(deftest search-user-fail-test
  (testing "Testando a função de buscar usuário com um nome que não existe"
    (testing "search-user"
    (is (= (search-user {:fail "fail"}) "Unable to search for user")))))

