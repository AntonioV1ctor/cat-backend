(ns cat_backend.cache_test
  (:require
   [cat_backend.utils.cache :refer :all]
   [clojure.test :refer :all]
   [clojure.java.io :as io]))

(defn cache-fixture [test-fn]
  (when (.exists (io/file "test-cache.txt"))
    (.delete (io/file "test-cache.txt")))
  (test-fn)
  (when (.exists (io/file "test-cache.txt"))
    (.delete (io/file "test-cache.txt"))))

(use-fixtures :each cache-fixture)

;; caminho feliz
(deftest cache-creator-test
  (testing "Testando a função de criar cache"
    (testing "cache-creator"
      (is (nil? (cache-creator "teste" "test-cache.txt"))))))

(deftest cachefile-exist-test
  (testing "Testando a função de verificar se arquivo existe"
    (testing "cachefile-exist?"
      (spit "test-cache.txt" "conteudo")
      (is (true? (cachefile-exist? "test-cache.txt"))))))

;; caminho triste
(deftest cache-creator-fail-test
  (testing "Testando a função de criar cache com caminho inválido"
    (testing "cache-creator"
      (is (string? (cache-creator "teste" "/caminho/inexistente/arquivo.txt"))))))

(deftest cachefile-exist-fail-test
  (testing "Testando a função de verificar arquivo inexistente"
    (testing "cachefile-exist?"
      (is (false? (cachefile-exist? "arquivo-inexistente.txt"))))))
