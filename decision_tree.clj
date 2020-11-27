;;This will contain the set of conditions that process_input functions will use
;;determine what question is being asked
(defn normalize_string [string]
  (re-find #".*[A-Za-z]" (str/lower-case string)))

;;sets of keyword synonyms
(def set_food #{"food", "eat", "drinks", "beverages", "concessions"})

;;gr => generate reply
(defn gr_food [park]
  (println "reply for foods in park: ") park)

(defn detect_keywords [user_in_arr park]
  (let [stop (dec (count user_in_arr))]
    (loop [i 0]
      (let [current (nth user_in_arr i)]
        (cond
          (contains? set_food (normalize_string current))
            (gr_food park)
          ))
      (when (< i stop)
        (recur (inc i))))))
