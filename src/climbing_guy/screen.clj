(ns climbing-guy.screen
  (:import java.awt.Toolkit))

(defn screen []
  (.getScreenSize (Toolkit/getDefaultToolkit)))

(defn getScreenSize []
  [(int (.getWidth (screen)))
   (int (.getHeight (screen)))])
