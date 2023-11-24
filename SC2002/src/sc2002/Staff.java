package sc2002;

import java.util.ArrayList;

public class Staff extends User {
	
  private ArrayList<Camp> createdCamps;
	
	public Staff(String name, String userID, String faculty) {
		super(name, userID, faculty);
		createdCamps = new ArrayList<>();
	}
	
	public ArrayList<Camp> GetMyCamps() {
		return createdCamps;
	}
	public void ViewCreatedCamps()
	{
		for (Camp camp : createdCamps) {
			if(camp == null) continue;
	       System.out.println("CampID: "+ camp.GetCampID()+" Camp Name: "+ camp.GetCampName());
		}
	}
	
	public void AddMyCamps(Camp campname) {
		createdCamps.add(campname);
		
	}
	public void RemoveCamps(Camp campName) {
		 	Camp campToRemove = null;

		    // Search for the camp with the specified name
		    for (Camp camp : createdCamps) {
		        if (camp.equals(campName)) {
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
