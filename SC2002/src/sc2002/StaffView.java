package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import CAMS.User;

public class StaffView implements  CampView{
	
	public StaffView() {
		super();
		
	}
	
	public void menu() {
		System.out.println("Welcome staff "+ camsApp.currentUser.getName());
		System.out.println("Please enter your choice: \n"
				+ "camp\n"
				+ "1. create/edit/view all camp \n"
				+ "4. Delete camp\n"
				+ "5. Toggle visibility\n\n"
				+ "suggestion\n"
				+ "6. View suggestion\n"
				+ "7. Approve sggestion\n\n"
				+ "enquiries\n"
				+ "8. View Enquiries\n"
				+ "9. Reply Enquiries\n\n"
				+ "other\n"
				+ "10. Generate list\n"
				+ "11. Generate report\n"
				+ "12. Change Password\n"
				+ "13. Log Out\n");
	}

	

	public void DisplayAllEnquiries(HashMap<Student, ArrayList<Enquiries>> map) {
		for (Map.Entry<Student, ArrayList<Enquiries>> entry : map.entrySet()) {
	        Student name = entry.getKey();
	        ArrayList<Enquiries> enquiriesList = entry.getValue();
	        
	        System.out.println("CCM: " + name);
	        for (Enquiries suggestion : enquiriesList) {
	            System.out.println("  " + suggestion);
	        }
	    }
	}

	public void DisplayMyCamps(ArrayList<Camp> camp) {
		for (Camp campname : camp) {
			 System.out.println(campname.GetCampName());
		}
	}
	public void DisplayAllSuggestions(HashMap<CCM, ArrayList<Suggestions>> map) {
	    for (Map.Entry<CCM, ArrayList<Suggestions>> entry : map.entrySet()) {
	        CCM name = entry.getKey();
	        ArrayList<Suggestions> suggestionsList = entry.getValue();
	        
	        System.out.println("CCM: " + name);
	        for (Suggestions suggestion : suggestionsList) {
	            System.out.println("  " + suggestion);
	        }
	    }
	}




}
