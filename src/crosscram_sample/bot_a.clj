(ns crosscram-sample.bot-a
  (:require [crosscram.board :as board])
  (:require [clojure.core.match :as match])
  (:require [clojure.set :as set])
  )

;; brute-force-moves
;; https://github.com/reiddraper/crosscram/blob/b00453ff0fa5428859108df86a1b0de389759a0c/src/crosscram/bots.clj#L8
(defn bot [game]
  (let [rows (:rows game)
        columns (:columns game)]
    (loop [moves (match/match (:next-player game)
                              :horizontal (board/generate-horizontal rows columns)
                              :vertical (board/generate-vertical rows columns))]

      (if (apply board/location-empty? (:board game) (first moves))
        (first moves)
        (recur (rest moves))))))
