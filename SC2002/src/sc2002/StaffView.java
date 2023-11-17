package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StaffView implements  CampView{
	public StaffView() {
		super();
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
