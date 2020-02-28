(ns climbing-guy.climber
  (:require [quil.core :as q]))

(def climber
  (atom {:head [100 740 20 20]
         :body [100 740 100 780]
         :left-arm [100 755 80 748]
         :right-arm [100 755 120 748]
         :left-leg [100 780 80 800]
         :right-leg [100 780 120 800]}))

(defn draw-climber-part
  [dfn body-part]
  (dfn ((@climber body-part) 0) ((@climber body-part) 1) ((@climber body-part) 2) ((@climber body-part) 3)))

(defn draw-climber
  []
  (draw-climber-part q/line :body)
  (draw-climber-part q/line :left-arm)
  (draw-climber-part q/line :right-arm)
  (draw-climber-part q/line :left-leg)
  (draw-climber-part q/line :right-leg)
  (draw-climber-part q/ellipse :head))
