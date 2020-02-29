(ns climbing-guy.wall
  (:require [quil.core :as q]
            [climbing-guy.screen :as s]))


(def initial-x-pos
  "This is the initial x position for holds."
  100)
(def hold-density
  "A general density of holds."
  100)

(defn rand-x-pos []
  "Gives random x position.
  Used for placing a hold on the X axis in a random spot, with a initial offset of `initia-x-pos`."
  (+ initial-x-pos (rand-int hold-density)))

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
