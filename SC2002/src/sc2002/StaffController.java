package sc2002;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;


public class StaffController {
	
	private CampController campManager = null;
	private StaffView staffViewManager = null;
	private static StaffController instance = null;
	 
	public static StaffController GetInstance() {
        if (instance == null)
            instance = new StaffController();
        return instance;
    }
	
	public StaffController() {
		this.campManager = CampController.GetInstance();
		this.staffViewManager = StaffView.GetInstance();
    }
		
	public void StaffMainLoop() {
			
		Scanner sc = new Scanner(System.in);
		int choice;
			//staff main loop 
			do {
				choice=-1;
				do {			
					try {
						//print the menu
						staffViewManager.menu();
						choice = Integer.parseInt(sc.nextLine());
					}catch (InputMismatchException e) {
						System.out.println("Invalid input");
					}
				}while(choice<1 || choice >11);
				
				switch(choice) {
				case 1: //create/edit/view all camp 
					Staffmenu1();
					break;
				case 2: //delete camp
					DeleteCamp(getCampIDInput());
					break;
				case 3: //view suggestion
					ViewSuggestions(getCampIDInput());
					break;
				case 4: //approve suggestion 
					int campID =getCampIDInput();
					Boolean exist = ViewSuggestions(campID);
					if(exist) {
						System.out.println("Please select a suggestion: ");
						int sugID = Integer.parseInt(sc.nextLine());
						System.out.println("(A)Approve / (D)Decline ?");
						String AD = sc.nextLine().toLowerCase();
						switch(AD) {
						case "a":
							ApproveSuggestions(campID,sugID,true);
							break;
						case "d":
							ApproveSuggestions(campID,sugID,false);
							break;
						}
					}
					
					break;
				case 5: // view enquires 
					ViewEnquiries(getCampIDInput());
					break;
				case 6: // reply enquires
					campID= getCampIDInput();
					Boolean exist1 = ViewEnquiries(campID);
					if(exist1) {
						System.out.println("Please select an enquiries: ");
						int enID = Integer.parseInt(sc.nextLine());
						System.out.println("Please enter your reply: ");
						String reply = sc.nextLine();
						ReplyEnquiries(campID,enID,reply);
					}
					break;
				case 7: //generate list
					campID = getCampIDInput();
					System.out.println("Please enter a user group: \n"
							+ "Student\\CCM\\All");
					String userGrp = sc.nextLine();
					GenerateList(campID,userGrp);
					break;
				case 8: //generate report
					GenerateReport(getCampIDInput());
					break;
				case 9:
					Auth.ChangePassword(camsApp.currentUser);
					break;
				case 10: //logout
					System.out.println("Exiting StaffMainLoop...");
					camsApp.currentUser=null;
					break;
				}
				
			}while(choice !=10);
			
		}
	
	
	public void Staffmenu1() {
		
			String sel = null;
			System.out.println("/////////////////////////////////////////////////////////////////////////");
	        System.out.println("//////////////////////        Create/Edit/View Camp       ///////////////////////");
	        System.out.println("/////////////////////////////////////////////////////////////////////////");

	        
	        System.out.println("Please enter your choice: ");
	        System.out.println("\t \\\"view\\\" to View All Camp");
	        System.out.println("\t \\\"view my camp\\\" to View My Camp");
	        System.out.println("\t \\\"create\\\" to Create Camp");
	        System.out.println("\t \\\"edit\\\" to Edit Camp");
	        System.out.println("\t \\\"exit\\\" to Exit");
	        
				Scanner sc= new Scanner(System.in);
				try {
					sel = sc.nextLine();
					sel = sel.toLowerCase();
				}catch(InputMismatchException e) {
					System.out.println("Please correct selection!");
				}
			
			
			switch(sel) {
			case "view":
				System.out.println("View All Camp");
				ViewAllCamp();
				break;
			case "create":
				System.out.println("Create a Camp");
				CreateCampMgr();
				break;
			case "edit":
				System.out.println("Edit a Camp");
				EditCamp(getCampIDInput());
				break;
			case "view my camp":
				ViewMyCamps();
			case "exit":
				System.out.println("Exiting Staff camp menu 1...");
				break;
			default:
				System.out.println("Please enter correct selection!");
				break;
			}

	}
	
	public int getCampIDInput() {
		int campID=-1;
		Scanner sc = new Scanner(System.in);
				//display all camp 
				staffViewManager.DisplayAllCamps(false);
				//get input 
				System.out.println("please select a camp： ");
				campID= Integer.parseInt(sc.nextLine());
		return campID - 1;
	}
	
	public void CreateCampMgr() {
		
		boolean Visibility= true;
		//String visibility = null;
		
		Scanner sc = new Scanner(System.in);
		
		//input validation????
		
		System.out.println("Camp Name: ");
		String campName = sc.nextLine();
		
		//change to date datatype?
		System.out.println("Camp Date:(dd/mm/yyyy)");
		String date = sc.nextLine();

		System.out.println("Camp Close Date:(dd/mm/yyyy)");
		String closeDate = sc.nextLine();
		
		System.out.println("Camp User Group: (SCSE/ADM/NBS/SSS/EEE)");
		String userGrp = sc.nextLine();
		System.out.println("Camp Location: ");
		String location = sc.nextLine();
		System.out.println("Camp Description: ");
		String description = sc.nextLine();
		
		System.out.println("No. of camp member slots: ");
		int totalSlots = Integer.parseInt(sc.nextLine());
		System.out.println("No. of camp committee member slots: (Max 10)");
		int CommittessSlots = Integer.parseInt(sc.nextLine());
		try {
			System.out.println("Camp Visibility: (true\\false)");
			Visibility = Boolean.parseBoolean(sc.nextLine());
		}catch(InputMismatchException e) {
			
		}
		
		CreateCamp(campName, date, closeDate, userGrp, location, description, camsApp.currentUser.name, totalSlots, CommittessSlots, Visibility);
		System.out.println("camp created!");
		//sc.nextLine();
	}
	
	// View all camps
	public void ViewAllCamp()
    {
		staffViewManager.DisplayAllCamps(CampView.getfilter());
    }
	public void ViewMyCamps()
    {	
		
		String myname = camsApp.currentUser.getName();
		ArrayList<Camp> camps = campManager.GetCamps();

		ArrayList<Camp> filteredCamps = new ArrayList<>();

		for (Camp camp : camps) {
		    if (camp != null && camp.GetStaffInCharge().equals(myname)) {
		        filteredCamps.add(camp);
		    }
		}
		staffViewManager.DisplayMyCamps(filteredCamps);;
    }
	
	// Create a camp
	public Camp CreateCamp(String campName, String date, String closedate, String userGrp, String location, String description, String staffIC, int totalSlots, int CommitteeSlots, boolean Visibility)
    {
		Camp newcamp=campManager.AddCamp(campName, date, closedate, userGrp.toUpperCase(), location, description, staffIC, totalSlots, CommitteeSlots, Visibility);
		//returns camp to so that can be added to Staff Class
		return newcamp;
    }
	// Edit camp based on selection
	public void EditCamp(int campID)
    {	
		Camp editcamp = null;
		ArrayList<Camp> camps = campManager.GetCamps();
    	for (Camp camp : camps) {
    		if (camp.GetCampID() == campID) { // Assuming CampID is a field in the Camp class
    			editcamp = camp;
    			break;
    		}
    	}
    	if(editcamp!= null) {
    	
			Scanner scanner = new Scanner(System.in);
			System.out.println("\nMenu:");
	        System.out.println("1. Set Camp Name");
	        System.out.println("2. Set Date");
	        System.out.println("3. Set Register Close Date");
	        System.out.println("4. Set User Group");
	        System.out.println("5. Set Location");
	        System.out.println("6. Set Description");
	        System.out.println("7. Set Staff in Charge");
	        System.out.println("8. Set Total Slots");
	        System.out.println("9. Set Committee Slots");
	        System.out.println("10. Toggle Visibility");
	        System.out.println("11. Exit");
	        System.out.print("Enter your choice: ");
	        int choice = Integer.parseInt(scanner.nextLine());
	        
	        switch (choice) {
	            case 1:
	                System.out.println("Enter Camp Name: ");
	                String campName = scanner.nextLine();
	                editcamp.SetCampName(campName);
	                campManager.sortCampsByName(camps);
	                campManager.reassignCampIDs(camps);
	                break;
	            case 2:
	                System.out.println("Enter Date: ");
	                String date = scanner.nextLine();
	                editcamp.SetDate(date);
	                break;
	            case 3:
	                System.out.println("Enter Register Close Date: ");
	                String registerCloseDate = scanner.nextLine();
	                editcamp.SetRegisterCloseDate(registerCloseDate);
	                break;
	            case 4:
	                System.out.println("Enter User Group: ");
	                String userGroup = scanner.nextLine();
	                editcamp.SetUserGrp(userGroup);
	                break;
	            case 5:
	                System.out.println("Enter Location: ");
	                String location = scanner.nextLine();
	                editcamp.SetLocation(location);
	                break;
	            case 6:
	                System.out.println("Enter Description: ");
	                String description = scanner.nextLine();
	                editcamp.SetDescription(description);
	                break;
	            case 7:
	                System.out.println("Enter Staff in Charge: ");
	                String staffInCharge = scanner.nextLine();
	                editcamp.SetStaffInCharge(staffInCharge);
	                break;
	            case 8:
	                System.out.println("Enter Total Slots: ");
	                int totalSlots = Integer.parseInt(scanner.nextLine());
	                editcamp.SetTotalSlots(totalSlots);
	                break;
	            case 9:
	                System.out.println("Enter Committee Slots: ");
	                int committeeSlots = Integer.parseInt(scanner.nextLine());
	                editcamp.SetCommitteeSlots(committeeSlots);
	                break;
	            case 10:
	            	System.out.println("Set Visibility: true or false?");
	                boolean newVisibility = Boolean.parseBoolean(scanner.nextLine());
	                ToggeVisibility(editcamp.GetCampID(), newVisibility);
	                break;
	            case 11:
	                System.out.println("Exiting menu.");
	                return;
	            default:
	                System.out.println("Invalid choice. Please enter a number from 1 to 11.");
	                break;
	        }
	        }
    	else {
    		System.out.println("CampId does not exist");
    	}
        
    }
	// Delete camp
	public void DeleteCamp(int campID)
    {
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	for (Camp camp : camps) {
			if(camp == null) continue;
    		if (camp.GetCampID() == campID) { // Assuming CampID is a field in the Camp class
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		if (camps.get(campID).GetAttendees().isEmpty() && camps.get(campID).GetCommitteeList().isEmpty()) {
    			campManager.DeleteCamp(campID);
    			System.out.println("Camp Deleted.");
    		}
    		else {
    			System.out.println("Unable to delete camp as there are attendees.");
    		}
    		
    	}
    	else {
    		System.out.println("CampID does not exist.");
    	}
    }
	// Toggle camp visibility
	public void ToggeVisibility(int campID, boolean state)
    {	
		Camp editcamp=null;
    
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	for (Camp camp : camps) {
			if(camp == null) continue;
    		if (camp.GetCampID() == campID) { // Assuming CampID is a field in the Camp class
    			campExists = true;
    			editcamp = camp;
    			break;
    		}
    	}	
    	if(campExists) {
			//if camp exists, toggle
    		if (camps.get(campID).GetAttendees().isEmpty() && camps.get(campID).GetCommitteeList().isEmpty()) {
    			editcamp.SetVisibility(state);
    			System.out.println("Camp visibility toggled.");
    		}
    		else {
    			System.out.println("Unable to toggle camp visibility as there are attendees.");
    		}
    		
    	}
    	else {
    		System.out.println("CampID does not exist.");
    	}
		
    }
	// View all suggestions of a camp based on CampID
	public Boolean ViewSuggestions(int CampID)
    {	
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	Camp selectedCamp = null;
    	for (Camp camp : camps) {
			if(camp == null) continue;
    		if (camp.GetCampID() == CampID) { // Assuming CampID is a field in the Camp class
    			selectedCamp = camp;
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		HashMap<CCM, ArrayList<Suggestions>> map = selectedCamp.getSuggestion();
    		if (map.isEmpty()) {
    			System.out.println("CampID does not have any suggestions.");
    			return false;
    		}
    		else {
    			
    			staffViewManager.DisplayAllSuggestions(map);
    			return true;
    		}
    	}
    	else {
    		System.out.println("CampID does not exist.");
    		return false;
    	}
		
    }
	// Approve suggestion based on CamppID and SuggestionID
	public void ApproveSuggestions(int CampID, int SuggestionID ,Boolean status)
    {
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	Camp selectedCamp = null;
    	for (Camp camp : camps) {
			if(camp == null) continue;
    		if (camp.GetCampID() == CampID) { // Assuming CampID is a field in the Camp class
    			selectedCamp = camp;
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		HashMap<CCM, ArrayList<Suggestions>> map = selectedCamp.getSuggestion();
    		for (Entry<CCM, ArrayList<Suggestions>> entry : map.entrySet()) {
    		    ArrayList<Suggestions> suggestions = entry.getValue();
    		    for (Suggestions sgst : suggestions) {
    		        if (sgst.GetID()==(SuggestionID)) {
    		            // Access the reply variable
    		        	sgst.setStatus(status);
    		            // Do something with the reply variable
    		        	if(status)
    		        		System.out.println("Suggestion approved!");
    		        	else
    		        		System.out.println("Suggestion unapproved!");
    		            return;
    		        }
    		    }
    		}}
    	else {
    		System.out.println("CampID or Suggestion does not exist.");
    	}
    }
	// View all enquires of a camp based on CampID
	public Boolean ViewEnquiries(int CampID)
    {	ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	Camp selectedCamp = null;
    	for (Camp camp : camps) {
			if(camp == null) continue;
    		if (camp.GetCampID() == CampID) { // Assuming CampID is a field in the Camp class
    			selectedCamp = camp;
    			campExists = true;
    			break;
    		}
    	}	
    	if(campExists) {
    		HashMap<Student, ArrayList<Enquiries>> map = selectedCamp.getEnquiries();
    		if(map.isEmpty()) {
    			System.out.println("CampID does not have any enquiries.");
    			return false;
    		}else {
    			staffViewManager.DisplayAllEnquiries(map);
    			return true;
    		}
    		
    	}
    	else {
    		System.out.println("CampID does not exist.");
    		return false;
    	}
		
    }
	// Reply to enquires based on CamppID and EnquiryID
	public void ReplyEnquiries(int CampID, int EnquiryID ,String reply)
    {
		ArrayList<Camp> camps = campManager.GetCamps();
    	boolean campExists= false;
    	Camp selectedCamp = null;
    	for (Camp camp : camps) {
			if(camp == null) continue;
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
    		        if (enquiry.GetEnquiryId()==(EnquiryID)) {
    		            // Access the reply variable
    		        	enquiry.SetReply(reply);
    		            // Do something with the reply variable
    		            System.out.println("Reply Set");
    		            // You found the enquiry with the desired enquiryID, so break out of loops
    		            return;
    		        }
    		    }
    		}}
    	else {
    		System.out.println("CampID or Enquiry does not exist.");
    	}
    }
	// Generate list of of participants: input participantType as -> Student or CCM or ALL
	public void GenerateList(int CampID, String participantType) {
	    ArrayList<Camp> camps = campManager.GetCamps(); // Assuming GetCamps() returns an ArrayList
	    boolean error = true;
	    boolean campExists = false;
	    Camp selectedCamp = null;

	    // Find the camp with the provided CampID
	    for (Camp camp : camps) {
			if(camp == null) continue;
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
	        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedCamp.GetCampName()+"_Student_List.csv"))) {
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
	        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedCamp.GetCampName()+"_CCM_List.csv"))) {
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
	// Generate Report of CCM based on camp
	// Print CCM name and their points
	public void GenerateReport(int CampID) {
		ArrayList<Camp> camps = campManager.GetCamps(); // Assuming GetCamps() returns an ArrayList

	    boolean campExists = false;
	    Camp selectedCamp = null;

	    // Find the camp with the provided CampID
	    for (Camp camp : camps) {
			if(camp == null) continue;
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
	            	String userid = ccm.getUserID();
	            	String faculty = ccm.getFaculty();
	                String name = ccm.getName();
	                int points = ccm.GetPoints();

	                // Write data to CSV
	                writer.write(userid + name + faculty + points);
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
