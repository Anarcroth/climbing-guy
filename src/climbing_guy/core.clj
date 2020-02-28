(ns climbing-guy.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [climbing-guy.screen :as s]
            [climbing-guy.wall :as w]
            [climbing-guy.climber :as c]))

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
  (doseq [hold w/get-holds]
    (w/create-hold hold))
  (c/draw-climber))

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
