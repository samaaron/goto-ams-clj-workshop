(ns goto-ams-workshop.tron.bots
  (:require [tron.core :as tron]))

(defn empty-look
  "A mock look function which just checks for the arena
   boundaries."
  [pos]
  (when-not (tron/valid-pos? pos) :wall))

(defn mock-look
  "Returns a mock look function which checks for the arena
   boundaries and the specified occupied positions."
  [& occupied-poses]
  (let [occupied-poses (set occupied-poses)]
    (fn [pos]
      (or (occupied-poses pos)
        (when-not (tron/valid-pos? pos) :wall)))))

(defn buzz
  "To infinity and beyond!"
  [look {[x y] :pos}]
  {:pos [(inc x) y]})

(defn right [[x y]]
  [(inc x) y])

(defn down [[x y]]
  [x (inc y)])

(defn down-or-right
  [look {pos :pos}]
  (if (look (down pos))
    {:pos (right pos)}
    {:pos (down pos)}))

(def dirs #{[0 1] [0 -1] [-1 0] [1 0]})
(defn addv [a b] (mapv + a b))
(defn subv [a b] (mapv - a b))
(defn are-neighbours? [a b]
  (contains? dirs (subv a b)))

(defn neighbours
  "returns a seq or coll of the 4 neighbours of the current
   pos, irrespective of whether these positions are valid o
   free."
  [pos]
  {:post [(every? are-neighbours? %)]}
  :TODO)

(defn valid-neighbours
  "returns a seq or coll of the neighbours of the current
   pos that are free to go (ie look returns nil for those)."
  [look pos]
  {:post [(not-any? look %) (every? are-neighbours? %)]}
  :TODO)

(defn valid-path
  "returns a seq of the all the next positions that are free
   to go in the given direction until one occupied one is
   reached."
  [look pos dir]
  {:post [(not-any? look %) (every? (fn [[a b]] (= dir (subv b a))) (partition 2 1 %))]}
  :TODO)

;; launch bots
(tron/run buzz down-or-right)
