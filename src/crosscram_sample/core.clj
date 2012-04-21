(ns crosscram-sample.core
  (:require [crosscram.core :as crosscram])
  (:require [crosscram-sample.bot-a :as bot-a])
  (:require [crosscram-sample.bot-b :as bot-b]))

(defn -main [num-games]
  (let [num-games (Integer/parseInt num-games)
        game (crosscram/new-game 9 9 :horizontal)
        winners (apply merge-with +
                       (map (fn [k] {k 1})
                            (map :winner
                                 (for [_ (range num-games)]
                                   (crosscram/play-symmetric game
                                                             bot-a/bot
                                                             bot-b/bot
                                                             10)))))]
    (println (str "Winner Stats:" winners))))
