(ns cat_backend.jwt_test
  (:require
   [cat_backend.services.jwt :refer :all]
   [clojure.test :refer :all]))

;; caminho feliz
(deftest generate-token-test
  (testing "Testando a função de gerar token"
    (testing "generate-token"
      (is (string? (generate-token "testuser"))))))

(deftest verify-token-test
  (testing "Testando a função de verificar token"
    (testing "verify-token"
      (let [token (generate-token "testuser")]
        (is (map? (verify-token token)))))))

;; caminho triste
(deftest verify-token-fail-test
  (testing "Testando a função de verificar token inválido"
    (testing "verify-token"
      (is (nil? (verify-token "token-invalido"))))))

(deftest get-token-from-request-fail-test
  (testing "Testando a função de extrair token sem authorization"
    (testing "get-token-from-request"
      (let [request {:headers {}}]
        (is (nil? (get-token-from-request request)))))))
