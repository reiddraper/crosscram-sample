(ns crosscram-sample.bot-b
  (:require [crosscram.board :as board])
  (:require [clojure.core.match :as match])
  (:require [clojure.set :as set])
  )

;; random-moves
;; https://github.com/reiddraper/crosscram/blob/b00453ff0fa5428859108df86a1b0de389759a0c/src/crosscram/bots.clj#L19
(defn bot [game]
  (let [rows (:rows game)
        columns (:columns game)]
    (loop [moves (match/match (:next-player game)
                              :horizontal (board/generate-horizontal rows columns)
                              :vertical (board/generate-vertical rows columns))]
      (let [m (set/difference (set moves) (set (map :move (:history game))))
            random-move (rand-nth (vec m))]
        (if (apply board/location-empty? (:board game) random-move)
          random-move
          (recur (remove #{random-move} m)))))))
