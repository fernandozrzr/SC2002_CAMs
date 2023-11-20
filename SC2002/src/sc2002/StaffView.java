package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import CAMS.User;

public class StaffView implements  CampView{
	
	private static StaffView instance = null;
	public StaffView() {
		super();
	}
	public static StaffView GetInstance() {
		if(instance == null)
            instance =  new StaffView();

        return instance;
	}
	
	public void menu() {
		System.out.println("Welcome staff "+ camsApp.currentUser.getName());
		System.out.println("Please enter your choice: \n"
				+ "camp\n"
				+ "1. create/edit/view all camp \n"
				+ "2. Delete camp\n\n"
				+ "suggestion\n"
				+ "3. View suggestion\n"
				+ "4. Approve sggestion\n\n"
				+ "enquiries\n"
				+ "5. View Enquiries\n"
				+ "6. Reply Enquiries\n\n"
				+ "other\n"
				+ "7. Generate list\n"
				+ "8. Generate report\n"
				+ "9. Change Password\n"
				+ "10. Log Out\n");
	}
	
	//Input Hash map of enquires to display all
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
	//Input staff array of camps to display all
	public void DisplayMyCamps(ArrayList<Camp> camp) {
		for (Camp campname : camp) {
			 System.out.println("CampID: "+ campname.GetCampID() + " Camp Name: " + campname.GetCampName());
		}
	}
	//Input Hash map of suggestions to display all
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
