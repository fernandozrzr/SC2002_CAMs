package sc2002;

import java.util.ArrayList;

public class Staff extends User {
	
  private ArrayList<Camp> createdCamps;
	
	public Staff() {
		super();
		createdCamps = new ArrayList<>();
	}
	
	public ArrayList<Camp> GetMyCamps() {
		return createdCamps;
	}
	
	public void AddMyCamps(Camp campname) {
		createdCamps.add(campname);
	}
	public void removeCamps(Camp campname) {
		 	Camp campToRemove = null;

		    // Search for the camp with the specified name
		    for (Camp camp : createdCamps) {
		        if (camp.equals(campname)) {
		            campToRemove = camp;
		            break; // Stop searching once found
		        }
		    }
		    // Remove the camp if found
		    if (campToRemove != null) {
		        createdCamps.remove(campToRemove);
		    }
		    else {
		    	System.out.println("Camp not found!");
		    }
	}
}
