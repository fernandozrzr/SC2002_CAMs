package sc2002;

import java.util.ArrayList;

public class Staff extends User {
	
  private ArrayList<String> createdCamps;
	
	public Staff() {
		super();
		createdCamps = new ArrayList<>();
	}
	
	public ArrayList<String> getMyCamps() {
		return createdCamps;
	}
	
	public void addMyCamps(String campname) {
		createdCamps.add(campname);
	}
	public void removeCamps(String campname) {
		 String campToRemove = null;

		    // Search for the camp with the specified name
		    for (String camp : createdCamps) {
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
