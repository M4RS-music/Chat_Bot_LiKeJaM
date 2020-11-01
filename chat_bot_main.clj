(def parks_info
  {
    :Bertramka {
      :WC true
      :Bike true
      :Rolerblades false
      :Sport_Ground false
      :Playground false
      :MHD {
              :Tram [4, 7, 9, 10, 58, 59]
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

(defn process_input [input]
  (println "Machine says:" input))

(defn dialougue_loop []
  (loop [user_in (read-line)]
    (process_input user_in)
    (when (not (= user_in "Goodbye"))
      (recur (read-line)))))
