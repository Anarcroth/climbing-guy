(ns climbing-guy.wall
  (:require [quil.core :as q]))

(defn rand-x-pos [] (+ 800 (rand-int (- 850 800))))

(defn gen-holds
  ([] (gen-holds 5))
  ([nx]
   (if (>= nx 800)
     '()
     (let [x (rand-x-pos)
           y (- 800 nx)
           r 10
           hold {:x x :y y :r r}]
       (cons hold (lazy-seq (gen-holds (+ 13 nx))))))))

(defn create-hold
  [hold]
  (q/rect (get hold :x) (get hold :y) (get hold :r) (get hold :r)))

(def get-holds (gen-holds))
