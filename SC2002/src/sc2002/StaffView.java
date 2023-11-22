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
		System.out.println("\nWelcome staff "+ camsApp.currentUser.getName());

		
		System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////        Staff Menu        ///////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////");

        
        System.out.println("Camp");
        System.out.println("\t(1) Create/Edit/View Camp");
        System.out.println("\t(2) Delete Camp");
        
        System.out.println("Suggestion");
        System.out.println("\t(3) View Suggestion");
        System.out.println("\t(4) Approve Suggestion");
        
        System.out.println("Enquiries");
        System.out.println("\t(5) View Enquiries");
        System.out.println("\t(6) Reply Enquiries");
        
        System.out.println("Other");
        System.out.println("\t(7) Generate List");
        System.out.println("\t(8) Generate Report");
        System.out.println("\t(9) Change Report");
        System.out.println("\t(10) Log Out");
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.print("Enter your Choice: ");
	}
	
	//Input Hash map of enquires to display all
	public void DisplayAllEnquiries(HashMap<Student, ArrayList<Enquiries>> map) {
		for (Map.Entry<Student, ArrayList<Enquiries>> entry : map.entrySet()) {
	        Student name = entry.getKey();
	        ArrayList<Enquiries> enquiriesList = entry.getValue();
	        
	        System.out.println("CCM: " + name);
	        for (Enquiries enq : enquiriesList) {
	            System.out.println("EnquiryId: " + enq.GetEnquiryId()+"Enquiry: " + enq.GetEnquiry()+ "Ask By: " + enq.GetAskBy()+ "Reply: " + enq.GetReplyBy()+ "Ask By: " + enq.GetStatus()+ "Status: " + enq.GetStatus());
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
	            System.out.println("SuggestionID: " + suggestion.GetID() + "Suggestion: " + suggestion.GetSuggestion() + " Status: "+ suggestion.IsStatus());
	        }
	    }
	}

	




}
