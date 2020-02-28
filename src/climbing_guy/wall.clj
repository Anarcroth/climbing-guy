(ns climbing-guy.wall
  (:require [quil.core :as q]
            [climbing-guy.screen :as s]))

(defn rand-x-pos []
  (+ (get s/scr :height)
     (rand-int (- (+ (get s/scr :height) 50) (get s/scr :height)))))

(defn gen-holds
  ([] (gen-holds 5))
  ([nx]
   (if (>= nx (get s/scr :height))
     '()
     (let [x (rand-x-pos)
           y (- (get s/scr :height) nx)
           r 10
           hold {:x x :y y :r r}]
       (cons hold (lazy-seq (gen-holds (+ 13 nx))))))))

(defn create-hold
  [hold]
  (q/rect (get hold :x) (get hold :y) (get hold :r) (get hold :r)))

(def get-holds (gen-holds))
