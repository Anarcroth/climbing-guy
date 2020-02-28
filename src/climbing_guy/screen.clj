(ns climbing-guy.screen
  (:import java.awt.Toolkit))

(defn get-default-screen []
  (let [scr (.getScreenSize (Toolkit/getDefaultToolkit))]
    {:width (int (.getWidth scr))
     :hegiht (int (.getHeight scr))}))

(defn screen
  ([] (get-default-screen))
  ([width height] {:width width :height height}))

(def scr (screen 1292 800))
