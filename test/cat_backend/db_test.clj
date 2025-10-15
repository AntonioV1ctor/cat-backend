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

;; caminho feliz

(deftest search-user-test
  (testing "Testando a função de buscar usuário"
    (testing "search-user"
      (is (= (get (search-user "test") :agent) "test")))))

(deftest create-user-test
  (testing "Testando a função de criar usuário"
    (testing "create-user"
      (is (= (create-user "test2" "a4Bh5kingcrimson") [1])))))

(deftest exclude-user-test
  (testing "Testando a função de excluir usuário"
    (testing "exclude-user"
      (is (= (exclude-user "test") [1])))))

(deftest send-message-test
  (testing "Testando a função de enviar mensagem"
    (testing "send-message"
      (is (= (send-message 1 "teste") [1])))))

(deftest load-all-messages-test
  (testing "Testando a função de carregar todas as mensagens"
    (testing "load-all-messages"
      (is (seq? (load-all-messages))))))

(deftest load-messages-byID-test
  (testing "Testando a função de carregar mensagem por ID"
    (testing "load-messages-byID"
      (is (string? (load-messages-byID 0))))))

;; caminho triste

(deftest search-user-fail-test
  (testing "Testando a função de buscar usuário com um nome que não existe"
    (testing "search-user"
    (is (= (search-user {:fail "fail"}) "Unable to search for user")))))

(deftest create-user-fail-test
  (testing "Testando a função de criar usuário com um nome inválido."
    (testing "create-user"
    (is (= (create-user {:fail "fail"} {:fail2 "fail2"}) )))))

