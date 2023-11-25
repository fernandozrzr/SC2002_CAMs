package sc2002;

import java.util.ArrayList;
/**
 * Staff Data Structure (Model)
 * 
 * @author Fernando Leong
 * @version 1.0
 * @since 25/11/2023
 */
public class Staff extends User {
	/**
	 * List of camps created
	 */
	private ArrayList<Camp> createdCamps;
	/**
	 * Default Constructor of Staff
     * Initializes list of createdCamps
	 * 
	 */
	public Staff(String name, String userID, String faculty) {
		super(name, userID, faculty);
		createdCamps = new ArrayList<>();
	}
	/**
	 * Gets the list of createdCamps
	 * 
	 * @return ArrayList of createdCamps
	 */
	public ArrayList<Camp> GetMyCamps() {
		return createdCamps;
	}
	/**
	 * Add a camp to list of createdCamps
	 * @param campname Camp name to be added
	 */
	public void AddMyCamps(Camp campname) {
		createdCamps.add(campname);
		
	}
	/**
	 * Remove a camp from list of createdCamps
	 * @param campName Camp name to be remove
	 */
	public void RemoveCamps(Camp campName) {
		 	Camp campToRemove = null;
		 	String remove=campName.GetCampName();
		    // Search for the camp with the specified name
		    for (Camp camp : createdCamps) {
		    	if(camp!=null)
		    		if (camp.GetCampName().equals(remove)) {
		            campToRemove = camp;
		            break; // Stop searching once found
		        }
		    }
		    // Remove the camp if found
		    if (campToRemove != null) {
		        createdCamps.remove(campToRemove);
		    }
		    else {
		    	return;
		    }
	}
}
