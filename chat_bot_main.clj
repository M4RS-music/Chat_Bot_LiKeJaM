(defn process_input [input]
  (println "Machine says:" input))

(defn dialougue_loop []
  (loop [user_in (read-line)]
    (process_input user_in)
    (when (not (= user_in "Goodbye"))
      (recur (read-line)))))
