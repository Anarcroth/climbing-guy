(ns climbing-guy.core
  (:import java.awt.Toolkit)
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn screen []
  (.getScreenSize (Toolkit/getDefaultToolkit)))

(defn getScreenSize []
  [(int (.getWidth (screen)))
   (int (.getHeight (screen)))])

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

(def get-holds (gen-holds))

(defn create-hold
  [hold]
  (q/rect (get hold :x) (get hold :y) (get hold :r) (get hold :r)))

(def climber (atom {:head [100 740 20 20]
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

(defn setup []
  (q/frame-rate 1)
  (q/color-mode :hsb)
  ; setup function returns initial state. It contains
  ; circle color and position.
  {:color 0
   :angle 0})

(defn update-state [state]
  ; Update sketch state by changing circle color and position.
  {:color (mod (+ (:color state) 0.7) 255)
   :angle (+ (:angle state) 0.1)})

(defn draw-state [state]
  (q/background 240)
  (q/fill (:color state) 255 255)
  (doseq [hold get-holds]
    (create-hold hold))
  (draw-climber))

(defn -main
  [& args]
  (q/sketch
   :title "Climbing-guy"
   :size [1292 800]
   :setup setup
   :update update-state
   :draw draw-state
   :features [:keep-on-top :exit-on-close]
   :middleware [m/fun-mode]))
