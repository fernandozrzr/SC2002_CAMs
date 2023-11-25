package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * StaffView which handles the interface that the Staff will see
 * 
 * @author Fernando Leong
 * @version 1.0
 * @since 25/11/2023
 */

public class StaffView implements  CampView{
	/**
	 * StaffView instance
	 */
	private static StaffView instance = null;
	/**
	 * StaffView default constructor
	 */
	public StaffView() {
		super();
	}
	/**
	 * Static instance for StaffView
	 * 
	 * @return instance of Staffview
	 */
	public static StaffView GetInstance() {
		if(instance == null)
            instance =  new StaffView();

        return instance;
	}
	/**
	 * Print Staff Main Menu
	 */
	public void Menu() {
		System.out.println("\nWelcome staff "+ camsApp.currentUser.GetName());
		
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
	
	/**
	 * Display HashMap of enquires
	 * @param map enquires of students
	 */
	public void DisplayAllEnquiries(HashMap<Student, ArrayList<Enquiries>> map) {
		for (Map.Entry<Student, ArrayList<Enquiries>> entry : map.entrySet()) {
	        Student name = entry.getKey();
	        ArrayList<Enquiries> enquiriesList = entry.getValue();
	        
	        System.out.println("Student: " + name.GetName());
	        for (Enquiries enq : enquiriesList) {
	            System.out.println("\nEnquiryId: " + enq.GetEnquiryId()+" Enquiry: " + enq.GetEnquiry()+ "\nReply: " + enq.GetReply()+ ", Replied By: " + enq.GetReplyBy()+ "\nStatus: " + enq.GetStatus());
	        }
	    }
	}
	/**
	 * Display Staff created camps
	 * @param camp list of created camps
	 */
	public void DisplayMyCamps(ArrayList<Camp> camp) {
		for (Camp campname : camp) {
			 System.out.println("CampID: "+ campname.GetCampID() + ", Camp Name: " + campname.GetCampName());
		}
	}
	
	/**
	 * Display HashMap of suggestions
	 * @param map suggestions of CCM
	 */
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
