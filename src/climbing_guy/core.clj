(ns climbing-guy.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [climbing-guy.screen :as s]
            [climbing-guy.wall :as w]
            [climbing-guy.climber :as c]))

(defn setup []
  (q/frame-rate 1)
  (q/color-mode :rgb))

(defn update-state [state])

(defn draw-state [state]
  (q/background 240)
  (q/fill 150 255 255)
  (doseq [hold w/get-holds]
    (w/create-hold hold))
  (c/draw-climber))

(defn -main
  [& args]
  (q/sketch
   :title "Climbing-guy"
   :size [(get s/scr :width) (get s/scr :height)]
   :setup setup
   :update update-state
   :draw draw-state
   :features [:keep-on-top :exit-on-close]
   :middleware [m/fun-mode]))
