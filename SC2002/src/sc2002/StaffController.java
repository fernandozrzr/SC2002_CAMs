package sc2002;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 


public class StaffController {
	
	private CampController campManager;
	private StaffView staffViewManager;
	private static StaffController instance = null;
	 
	public static StaffController GetInstance() {
        if (instance == null)
            instance = new StaffController();
        return instance;
    }
	
	public StaffController() {
		this.campManager = CampController.GetInstance();
		this.staffViewManager = new StaffView();
    }
		
	public void StaffMainLoop() {
			
			int choice=-1;
			//staff main loop 
			do {
				do {
					//print the menu
					staffViewManager.menu();
					Scanner sc = new Scanner(System.in);
					try {
						choice = sc.nextInt();
					}
					catch(InputMismatchException e){
						}
				}while(choice<1 || choice >13);
				
				switch(choice) {
				case 1: //create/edit/view all camp 
					Staffmenu1();
					break;
				case 12:
					Auth.ChangePassword(camsApp.currentUser);
					break;
				case 13: //logout
					System.out.println("Exiting StaffMainLoop...");
					break;
				}
				
			}while(choice !=13);
			
		}
	
	
	public void Staffmenu1() {
		
		boolean loop= true;
		boolean exit =false;
		String sel = null;
		
		do {
			System.out.println("Please enter your choice: \n"
					+ "\"view\" to view all camp\n"
					+ "\"create\" to create new camp\n"
					+ "\"edit\" to edit a camp\n"
					+ "\"exit\" to exit this page\n");
			do {
				Scanner sc= new Scanner(System.in);
				try {
					sel = sc.next();
					sel = sel.toLowerCase();
					loop= false;
				}catch(InputMismatchException e) {
					System.out.println("Please correct selection!");
				}				
			}while(loop == true);
			
			switch(sel) {
			case "view":
				System.out.println("View All Camp");
				ViewAllCamp();
				break;
			case "create":
				System.out.println("Create a Camp");
				break;
			case "edit":
				System.out.println("Edit a Camp");
				break;
			case "exit":
				System.out.println("Exiting Staff camp menu 1...");
				exit = true;
				break;
			default:
				System.out.println("Please enter correct selection!");
				break;
			}
		}while(exit = false);	
	}
	
	public void CreateCampMgr() {
		
		boolean Visibility= true;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("camp name: ");
		String campName = sc.next();
		System.out.println("camp close date: ");
		String closeDate = sc.next();
		System.out.println("camp user group: ");
		String userGrp = sc.next();
		System.out.println("camp location: ");
		String location = sc.next();
		System.out.println("camp description: ");
		String description = sc.next();
		System.out.println("camp staff in charge: ");
		String staffIC = sc.next();
		System.out.println("no. of camp member slots: ");
		int totalSlots = sc.nextInt();
		System.out.println("no. of camp committee member slots: ");
		int CommittessSlots = sc.nextInt();
		String visibility = null;
		do {
			System.out.println("camp visibility: (T\\F)");
			visibility = sc.next().toLowerCase();
			
			switch(visibility) {
			case "t":
				Visibility = true;
				break;
			case "f":
				Visibility = false;
				break;
			default: 
				visibility = null;
				break;
			}
			
		}while(visibility==null);
		
		String date = LocalDateTime.now().toString();
		CreateCamp(campName, date, closeDate, userGrp, location, description, staffIC, totalSlots, CommittessSlots, Visibility);
	
	}
	
	public void ViewAllCamp()
    {
		staffViewManager.DisplayAllCamps();
    }
	public Camp CreateCamp(String campName, String date, String closedate, String userGrp, String location, String description, String staffIC, int totalSlots, int CommitteeSlots, boolean Visibility)
    {
		Camp newcamp=campManager.AddCamp(campName, date, closedate, userGrp, location, description, staffIC, totalSlots, CommitteeSlots, Visibility);
		//returns camp to so that can be added to Staff Class
		return newcamp;
    }
	public void EditCamp(int campID)
    {
		//not done
    }
	public void DeleteCamp(int campID)
    {
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	for (Camp camp : camps) {
    		if (camp.GetCampID() == campID) { // Assuming CampID is a field in the Camp class
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		campManager.DeleteCamp(campID);
    	}
    	else {
    		System.out.println("CampID does not exist.");
    	}
    }
	public void ToggeVisibility(int campID, boolean state)
    {	
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	for (Camp camp : camps) {
    		if (camp.GetCampID() == campID) { // Assuming CampID is a field in the Camp class
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		campManager.ToggleVisibility(campID, state);
    	}
    	else {
    		System.out.println("CampID does not exist.");
    	}
		
    }
	public void ViewSuggestions(int CampID)
    {	
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	Camp selectedCamp = null;
    	for (Camp camp : camps) {
    		if (camp.GetCampID() == CampID) { // Assuming CampID is a field in the Camp class
    			selectedCamp = camp;
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		HashMap<CCM, ArrayList<Suggestions>> map = selectedCamp.getSuggestion();
    		staffViewManager.DisplayAllSuggestions(map);
    	}
    	else {
    		System.out.println("CampID does not exist.");
    	}
		
    }
	public void ApproveSuggestions(int index)
    {
		//not done
    }
	public void ViewEnquiries(int CampID)
    {	ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	Camp selectedCamp = null;
    	for (Camp camp : camps) {
    		if (camp.GetCampID() == CampID) { // Assuming CampID is a field in the Camp class
    			selectedCamp = camp;
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		HashMap<Student, ArrayList<Enquiries>> map = selectedCamp.getEnquiries();
    		staffViewManager.DisplayAllEnquiries(map);
    	}
    	else {
    		System.out.println("CampID does not exist.");
    	}
		
    }
	public void ReplyEnquiries(int CampID, int EnquiryID ,String reply)
    {
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	Camp selectedCamp = null;
    	for (Camp camp : camps) {
    		if (camp.GetCampID() == CampID) { // Assuming CampID is a field in the Camp class
    			selectedCamp = camp;
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		HashMap<Student, ArrayList<Enquiries>> map = selectedCamp.getEnquiries();
    		for (HashMap.Entry<Student, ArrayList<Enquiries>> entry : map.entrySet()) {
    		    ArrayList<Enquiries> enquiries = entry.getValue();

    		    // Iterate through the enquiries for the current enquiryID
    		    for (Enquiries enquiry : enquiries) {
    		        if (enquiry.getEnquiryId()==(EnquiryID)) {
    		            // Access the reply variable
    		        	enquiry.setReply(reply);
    		            // Do something with the reply variable
    		            System.out.println("Reply Set");
    		            // You found the enquiry with the desired enquiryID, so break out of loops
    		            return;
    		        }
    		    }
    		}}
    	else {
    		System.out.println("CampID or EnquiryID does not exist.");
    	}
    }
	public void GenerateList(int CampID, String participantType) {
	    ArrayList<Camp> camps = campManager.GetCamps(); // Assuming GetCamps() returns an ArrayList
	    boolean error = true;
	    boolean campExists = false;
	    Camp selectedCamp = null;

	    // Find the camp with the provided CampID
	    for (Camp camp : camps) {
	        if (camp.GetCampID() == CampID) {
	            selectedCamp = camp;
	            campExists = true;
	            break;
	        }
	    }

	    if (campExists) {
	        // Get participants based on the specified participantType
	        if (participantType.equalsIgnoreCase("Student")||participantType.equalsIgnoreCase("All")) {
	        	ArrayList<Student> filterlist = selectedCamp.GetAttendees();
	        	try (BufferedWriter writer = new BufferedWriter(new FileWriter("Participant_List.csv"))) {
		            // Write header for CSV file
		            writer.write("CampID, CampName, ParticipantName, Role");
		            writer.newLine();

		            // Iterate through participants and write to CSV
		            for (Student student : filterlist) {
		                String campName = selectedCamp.GetCampName();
		                String name = student.getName();
		                String role = "Attendee";

		                // Write data to CSV
		                writer.write(CampID + "," + campName + "," + name + "," + role);
		                writer.newLine();
		            }

		            System.out.println("Data exported to CSV successfully!");
		            error =false;
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	        }  
	        if (participantType.equalsIgnoreCase("CCM")||participantType.equalsIgnoreCase("All")) {
	        	ArrayList<CCM> flterlist = selectedCamp.GetCommitteeList();
	        	try (BufferedWriter writer = new BufferedWriter(new FileWriter("Participant_List.csv"))) {
	            // Write header for CSV file
	        		writer.write("CampID, CampName, ParticipantName, Role");
	        		writer.newLine();

	            // Iterate through participants and write to CSV
	        		for (CCM ccm : flterlist) {
	        			String campName = selectedCamp.GetCampName();
	        			String name = ccm.getName();
	        			String role = "CCM";

	                // Write data to CSV
	        			writer.write(CampID + "," + campName + "," + name + "," + role);
	        			writer.newLine();
	            }

	            System.out.println("Data exported to CSV successfully!");
	            error =false;
	        } 	catch (IOException e) {
	            	e.printStackTrace();
	        	}}
	        if (error) {
	        	System.out.println("Invalid participant type.");
	        }
	        
	        return; // Stop execution if the participant type is invalid
	        
	        }
	        
	     else {
	        System.out.println("CampID does not exist.");
	        // Handle the situation where CampID does not exist
	        // This could include logging an error, notifying the user, etc.
	    }
	}
	
	public void GenerateReport(int CampID) {
		ArrayList<Camp> camps = campManager.GetCamps(); // Assuming GetCamps() returns an ArrayList

	    boolean campExists = false;
	    Camp selectedCamp = null;

	    // Find the camp with the provided CampID
	    for (Camp camp : camps) {
	        if (camp.GetCampID() == CampID) { // Assuming CampID is a field in the Camp class
	            selectedCamp = camp;
	            campExists = true;
	            break;
	        }
	    }

	    if (campExists) {
	        ArrayList<CCM> ccmList = selectedCamp.GetCommitteeList();
	        String campName = selectedCamp.GetCampName();
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campName+" CCM Report.csv"))) {
	            // Write header for CSV file
	            writer.write("Name,Points");
	            writer.newLine();

	            // Iterate through each CCM in the list and write to CSV
	            for (CCM ccm : ccmList) {
	                String name = ccm.getName();
	                int points = ccm.GetPoints();

	                // Write data to CSV
	                writer.write(name + "," + points);
	                writer.newLine();
	            }

	            System.out.println("Data exported to CSV successfully!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("CampID does not exist.");
	        // Handle the situation where CampID does not exist
	        // This could include logging an error, notifying the user, etc.
	    }
	}
	
}
