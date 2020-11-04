(def parks_info
  {
    :Bertramka {
      :WC true
      :Bike true
      :Rolerblades false
      :Sport_Ground false
      :Playground false
      :MHD {
              :Tram #{4, 7, 9, 10, 58, 59}
              :Bus nil
              :Metro nil}
      :GPS "50°4’15.00’’ N, 14°23’42.00’’ E"
      :Parking {
                :Type "undeground"
                :Where "OC Smichov"}
      :Trail_Type "cobblestone"
      :Owner "Městská část Praha 5 a spol. Comenius"
      :Hours { ;;User will ask "When open", machine ask "what month" then machine gives either on or off hrs
              :On_Season ["April", "May", "June", "July", "August", "September", "October"]
              :Off_Season ["November", "December", "January", "February", "March"]
              :On_Hours "9-18"
              :Off_Hours "9:30-16"}
      :Website "https://www.praha.eu/jnp/cz/co_delat_v_praze/parky/bertramka/index.html"}})

;;Predicates
(defn WC? [park]
  (:WC (park parks_info)))

(defn bike? [park]
  (:Bike (park parks_info)))

(defn rolerblades? [park]
  (:Rolerblades (park parks_info)))

(defn sport_ground? [park]
  (:Sport_Ground (park parks_info)))

(defn playground? [park]
  (:Playground (park parks_info)))

(defn parking? [park]
  (not (nil? (:Parking (park parks_info)))))

(defn food? [park]
  (not (nil? (:Food (park parks_info)))))

;;Get info
(defn parking_info [park]
  (when (parking? park)
    [(:Type (:Parking (park parks_info))), (:Where (:Parking (park parks_info)))]))

(defn trail_type [park]
  (:Trail_Type (park parks_info)))

(defn gps_coordinates [park]
  (:GPS (park parks_info)))

(defn owner [park]
  (:Owner (park parks_info))))

(defn on_hours [park]
  (:On_Hours (:Hours (park parks_info))))
(defn off_hours [park]
  (:Off_Hours (:Hours (park parks_info))))

(defn on_season [park]
  (:On_Season (:Hours (park parks_info))))
(defn off_season [park]
  (:Off_Season (:Hours (park parks_info))))

(defn tram [park] (:Tram (:MHD (park parks_info))))
(defn tram_line? [park tram_no] (contains? (tram park) tram_no))

(defn bus [park] (:Bus (:MHD (park parks_info))))
(defn bus_line? [park bus_no] (contains? (bus park) bus_no))

(defn metro [park] (:Metro (:MHD (park parks_info))))
(defn metro_line? [park metro_line] (contains? (metro park) metro_line))

;;Bot
(defn process_input_question [input]
  (println "Thats a question."))

(defn process_input_statement [input]
  (println "Thats a statement."))

(defn process_input_main [input]
  (if (= (re-find (re-pattern "\\Q?\\E") input) nil)
    (process_input_statement input)
    (process_input_question input)))

(defn dialougue_loop []
  (loop [user_in (read-line)]
    (process_input_main user_in)
    (when (not (= user_in "Goodbye"))
      (recur (read-line)))))
