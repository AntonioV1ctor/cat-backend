(ns cat_backend.utils.cache
  (:require [clojure.java.io :as io]))

(defn cache-creator [content name-file]
  (try (spit  name-file content :append true )
       (catch Exception e (str "An error occurred while trying to create a cache file:"(.getMessage e)))))

(defn cache-read [name-file]
  (slurp "cache.txt"))

(defn cachefile-exist? [fileName]
  (.exists (io/file fileName)))

;; (cache-read "cache.txt")
;; (cache-creator "Olá José\n" "cache.txt" )
;; (cachefile-exist? "cache.txt")
