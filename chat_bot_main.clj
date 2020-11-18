;;Requirements
(require '[clojure.string :as str])

;;Data Structures
(def parks_info
  {
	  :Bertramka {
		       :Food false
		       :WC true
		       :Dogs true
		       :Interest "Cultural monument, classical music concerts, social events, W. A. Mozart Museum"
		       :Bike true
		       :Rolerblades false
		       :Sport_Ground false
		       :Playground false
		       :MHD {
    				    :Tram #{4, 7, 9, 10, 58, 59}
    				    :Bus nil
    				    :Metro nil
				    }
		       :GPS "50°4’15.00’’ N, 14°23’42.00’’ E"
		       :Parking {
      					:Type "undeground"
      					:Where "OC Smichov"
					}
		       :Trail_Type "cobblestone"
		       :Owner "Městská část Praha 5 a spol. Comenius"
		       :Hours { ;;User will ask "When open", machine ask "what month" then machine gives either on or off hrs
				      :On_Season ["April", "May", "June", "July", "August", "September", "October"]
				      :Off_Season ["November", "December", "January", "February", "March"]
				      :On_Hours "9-18"
				      :Off_Hours "9:30-16"
				      }
		       :Website "https://www.praha.eu/jnp/cz/co_delat_v_praze/parky/bertramka/bertramka_text.html"
		       }

	  :Frantiskanska_zahrada {
					 :Food true
					 :WC true
					 :Dogs false
					 :Interest "Pleasant sitting in the very center of the city, suitable for children and seniors, in the vicinity of the church of P. Marie Sněžné"
					 :Bike true
					 :Rolerblades false
					 :Sport_Ground false
					 :Playground true
					 :MHD {
						      :Tram #{3, 9, 14, 24, 51, 52, 54, 55, 56, 58}
						      :Bus nil
						      :Metro #{ "A", "B"}
						      }
					 :GPS "50°4’58.8’’ N, 14°25’24.8’’ E"
					 :Parking nil
					 :Trail_Type "cobblestone, a special surface in the children's corner"
					 :Owner "Capital City of Prague"
					 :Hours { ;;User will ask "When open", machine ask "what month" then machine gives either on or off hrs
							:On_Season ["April", "May", "June", "July", "August", "September", "October", "November", "December", "January", "February", "March"]
							:Off_Season nil
							:On_Hours "closes for the night"
							:Off_Hours nil}
					 :Website "https://www.praha.eu/jnp/cz/co_delat_v_praze/parky/frantiskanska_zahrada/frantiskanska_text.html"
					 }

	  :Obora_hvezda {
				:Food true
				:WC true
				:Dogs true
				:Interest "Suitable for families with children and tourists, memorable trees, nature trail, natural attractions"
				:Bike true
				:Rolerblades false
				:Sport_Ground false
				:Playground true
				:MHD {
					     :Tram #{15, 22, 25, 57}
					     :Bus #{179, 184, 191, 510}
					     :Metro nil
					     }
				:GPS "50°5’2.798’’ N, 14°19’45.529’’ E"
				:Parking {
						 :Type "on street"
						 :Where "at Libock Gate, or freely on U Star street"
						 }
				:Trail_Type "paved and forest"
				:Owner "National Literature Memorial and Capital City of Prague"
				:Hours { ;;User will ask "When open", machine ask "what month" then machine gives either on or off hrs
					       :On_Season ["May", "June", "July", "August", "September", "November", "December", "January", "February", "March"]
					       :Off_Season ["April", "October"]
					       :On_Hours "10-17"
					       :Off_Hours "10-18"}
				:Website "https://www.praha.eu/jnp/cz/co_delat_v_praze/parky/hvezda/obora_hvezda.html"
				}

	  :Kampa {
			 :Food true
			 :WC true
			 :Dogs true
			 :Interest "View of the Vltava River, gallery Sovovy Mlýny, Čertovka, Charles Bridge, Werich house, memorable trees"
			 :Bike true
			 :Rolerblades true
			 :Sport_Ground false
			 :Playground true
			 :MHD {
				      :Tram #{6, 9, 12, 20, 22, 23, 57, 58, 59}
				      :Bus nil
				      :Metro nil
				      }
			 :GPS "50°5’16.87’’ N, 14°24’14.58’’ E"
			 :Parking nil
			 :Trail_Type "asphalt"
			 :Owner "Prague City District 1"
			 :Hours { ;;User will ask "When open", machine ask "what month" then machine gives either on or off hrs
					:On_Season ["May", "June", "July", "August", "September", "November", "December", "January", "February", "March", "April", "October"]
					:Off_Season nil
					:On_Hours "0-0"
					:Off_Hours nil}
			 :Website "https://www.praha.eu/jnp/cz/co_delat_v_praze/parky/kampa/kampa_text.html"
			 }

	  :Kinskeho_zahrada {
				    :Food true
				    :WC true
				    :Dogs true
				    :Interest "Pleasant place to walk with children, rest zone in the middle of the city, museum, ornamental ponds, natural attraction - Petrin rock garden"
				    :Bike false
				    :Rolerblades false
				    :Sport_Ground false
				    :Playground true
				    :MHD {
						 :Tram nil
						 :Bus #{176}
						 :Metro nil
						 }
				    :GPS "50°4’44.26’’ N, 14°24’0.21’’ E"
				    :Parking {
						     :Type "undeground"
						     :Where "OC Smíchov"
						     }
				    :Trail_Type "cobblestone"
				    :Owner "Capital City of Prague"
				    :Hours { ;;User will ask "When open", machine ask "what month" then machine gives either on or off hrs
						   :On_Season ["May", "June", "July", "August", "September", "November", "December", "January", "February", "March", "April", "October"]
						   :Off_Season nil
						   :On_Hours "0-0"
						   :Off_Hours nil
						   }
				    :Website "https://www.praha.eu/jnp/cz/co_delat_v_praze/parky/kinskeho_zahrada/kinskeho_text.html"
				    }
	  }
	)

;;Responses

(def responses ;;for multiple dp items (parking, MHD, hours) create a vector that allows for the process_input
               ;;function to insert the datapoints in between the strings. (see :parking_yes for eg.)
  {
    :food_yes {
      :1 "Yes there are food vendors at this park."
      :2 "Yes food is available there."
    }
    :food_no {
      :1 "No there is no food at this park."
      :2 "There are not any food vendors there."
    }
    :parking_yes {
      :1 ["Yes there is ", "parking", "available at", "."]
      :2 ["Sure! ", "parking", "is available at", "."]
    }
    :parking_no {
      :1 "There is no information on parking for this park."
      :2 "No there is not parking."
    }
    })

;;Predicates
(defn food? [park]
  (:Food (park parks_info)))

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
  (:Food (park parks_info)))

(defn dogs? [park]
  (:Dogs (park parks_info))))

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

;;UIE
(defn string_to_vector [string]
  (str/split (str/replace string (re-pattern "^0-9A-Za-z") "") #" "))

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
