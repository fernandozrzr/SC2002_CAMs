package sc2002;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map.Entry;
import java.util.Scanner;
/**
 *  Controller class which controls all the logic related to Staff
 * 
 * @author Fernando Leong
 * @version 1.0
 * @since 25/11/2023
 */

public class StaffController {
	/**
	 * Scanner for methods below
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * CampController instance
	 */
	private CampController campManager = null;
	/**
	 * StaffView instance
	 */
	private StaffView staffViewManager = null;
	/**
	 * StaffController instance
	 */
	private static StaffController instance = null;
	 
	/**
	 * Static instance for StaffController
	 */
	public static StaffController GetInstance() {
        if (instance == null)
            instance = new StaffController();
        return instance;
    }
	/**
	 * Default constructor for StaffController
	 * Gets instance of controllers
	 */
	public StaffController() {
		this.campManager = CampController.GetInstance();
		this.staffViewManager = StaffView.GetInstance();
    }
	/**
	 * Staff Main Menu
	 */
	public void StaffMainLoop() {
			
		
		int choice;
			//staff main loop 
			do {
				choice=-1;
				do {			
					try {
						//print the menu
						staffViewManager.Menu();
						choice = Integer.parseInt(sc.nextLine());
					}catch (NumberFormatException e) {
						System.out.println("Invalid input");
					}
				}while(choice<1 || choice >11);
				
				switch(choice) {
				case 1: //create/edit/view all camp 
					StaffMenu1();
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
	
	/**
	 * Staff: Create/Edit/View Camp Menu
	 */
	public void StaffMenu1() {
		
			String sel = null;
			System.out.println("/////////////////////////////////////////////////////////////////////////");
	        System.out.println("//////////////////////        Create/Edit/View Camp       ///////////////////////");
	        System.out.println("/////////////////////////////////////////////////////////////////////////");

	        
	        System.out.println("Please enter your choice: ");
	        System.out.println("\t \"view\" to View All Camp");
	        System.out.println("\t \"view my camp\" to View My Camp");
	        System.out.println("\t \"create\" to Create Camp");
	        System.out.println("\t \"edit\" to Edit Camp");
	        System.out.println("\t \"exit\" to Exit");
	        
				
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
				//System.out.println("Exiting Staff camp menu 1...");
				break;
			default:
				System.out.println("Please enter correct selection!");
				break;
			}

	}
	/**
	 * Get CampID 
	 * @return campID 
	 */
	public int getCampIDInput() {
		int campID=-1;

		//display all camp 
		staffViewManager.DisplayAllCamps(false);
		//get input 
		System.out.println("please select a camp： ");
		campID= Integer.parseInt(sc.nextLine());
		return campID - 1;
	}
	/**
	 * Create Camp menu
	 *
	 */
	public void CreateCampMgr() {
	
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		String date = null;
		String closeDate = null;
		String userGrp =null;
		int totalSlots=-1;
		int CommittessSlots=-1;

		System.out.println("Camp Name: ");
		String campName = sc.nextLine();
		
		//camp date 
		do {
			try {
				System.out.println("Camp Date:(dd/mm/yyyy)");
				date = sc.nextLine();
	            Date inputDate = sdf1.parse(date);
			}catch (ParseException e) {
	            System.out.println("Invalid date format. Please enter in dd/mm/yyyy format.");
	            date=null;
	        }catch(InputMismatchException e) {
	            System.out.println("Invalid date format. Please enter in dd/mm/yyyy format.");
	            date=null;
	        }
		}while(date==null);

		//camp close date
		do {
			try {
				System.out.println("Camp Close Date:(dd/mm/yyyy)");
				closeDate = sc.nextLine();
	            Date inputDate = sdf1.parse(closeDate);
			}catch (ParseException e) {
	            System.out.println("Invalid date format. Please enter in dd/mm/yyyy format.");
	            closeDate=null;
	        }catch(InputMismatchException e) {
	            System.out.println("Invalid date format. Please enter in dd/mm/yyyy format.");
	            closeDate=null;
	        }
		}while(closeDate==null);

		//userGrp
		String[] choices= {"SCSE","ADM","NBS","SSS","EEE"};
		do {
			System.out.println("Camp User Group: (SCSE/ADM/NBS/SSS/EEE)");
			userGrp = sc.nextLine();
			userGrp = userGrp.toUpperCase();
			if(Utility.IsValidChoice(userGrp,choices)==false) {
	            System.out.println("Invalid Input!");
				userGrp=null;
			}
		}while(userGrp==null);
		
		System.out.println("Camp Location: ");
		String location = sc.nextLine();
		System.out.println("Camp Description: ");
		String description = sc.nextLine();

		//////////////// Camp member slots////////////////////
		Boolean validInput = false;
		System.out.println("No. of camp member slots: ");
		totalSlots = -1;
		do {
            try {
                totalSlots = Integer.parseInt(sc.nextLine());
                if (totalSlots<0) {
                    System.out.println("Please enter a non-negative number.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (!validInput);
		validInput = false;
		
		//////////////// CCM Slots ////////////////////
		System.out.println("No. of camp committee member slots: (Max 10)");
		CommittessSlots = -1;
		
		do {
            try {
            	CommittessSlots = Integer.parseInt(sc.nextLine());
                if (CommittessSlots > 10 || CommittessSlots<0) {
                    System.out.println("Please enter a number from 0-10.");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        } while (!validInput);
		//////////////// Set visibility ////////////////////
		boolean visibility = false;
		validInput = false;
		 do {
	            try {
	                System.out.println("Camp Visibility: (true/false)");
	                String userInput = sc.nextLine().toLowerCase(); // Convert input to lowercase for case-insensitive comparison

	                if (userInput.equals("true") || userInput.equals("false")) {
	                    visibility = Boolean.parseBoolean(userInput);
	                    validInput = true;
	                } else {
	                    System.out.println("Please enter 'true' or 'false'.");
	                }
	            } catch (Exception e) {
	                System.out.println("Invalid input. Please enter 'true' or 'false'.");
	                sc.nextLine(); // Clear the scanner buffer
	            }
	        } while (!validInput);

		
		Camp newcamp = CreateCamp(campName, date, closeDate, userGrp, location, description, camsApp.currentUser.name, totalSlots, CommittessSlots, visibility);
		Staff staff = (Staff)camsApp.currentUser;
		staff.AddMyCamps(newcamp);
		System.out.println("camp created!");
		//sc.nextLine();
	}
	
	/**
	 * View all camps
	 * 
	 */
	public void ViewAllCamp()
    {
		staffViewManager.DisplayAllCamps(CampView.GetFilter());
    }
	/**
	 * View my created camps
	 * 
	 */
	public void ViewMyCamps()
    {	
		
		Staff staff = (Staff)camsApp.currentUser;
		
		staffViewManager.DisplayMyCamps(staff.GetMyCamps());;
    }
	
	/**
	 * Create a new camp by calling campManager.AddCamp()
	 * 
     * @param campName Name of the camp
     * @param date Date that the camp is held
     * @param registerCloseDate Registration Closing Date
     * @param userGrp Faculty of this camp
     * @param location Where the camp is held
     * @param desc Description of the camp 
     * @param staffInCharge Name of staff that created the camp
     * @param totalSlots  Number of slots available for students to join
     * @param committeeSlots Number of committee member slots available for students to join (Max 10)
     * @param visibility Whether the camp can be seen by students
     * @return newCamp object created using the parameters
	 */
	public Camp CreateCamp(String campName, String date, String closeDate, String userGrp, String location, String description, String staffIC, int totalSlots, int CommitteeSlots, boolean Visibility)
    {
		Camp newCamp=campManager.AddCamp(campName, date, closeDate, userGrp.toUpperCase(), location, description, staffIC, totalSlots, CommitteeSlots, Visibility);
		//returns camp to so that can be added to Staff Class
		return newCamp;
    }
	/**
	 * Edit camp based on campID
     * @param campID ID of the camp
	 */
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
	        int choice = Integer.parseInt(sc.nextLine());
	        
	        switch (choice) {
	            case 1:
	                System.out.println("Enter Camp Name: ");
	                String campName = sc.nextLine();
	                editcamp.SetCampName(campName);
	                campManager.SortCampsByName(camps);
	                campManager.ReassignCampIDs(camps);
	                break;
	            case 2:
	                System.out.println("Enter Date: ");
	                String date = sc.nextLine();
	                editcamp.SetDate(date);
	                break;
	            case 3:
	                System.out.println("Enter Register Close Date: ");
	                String registerCloseDate = sc.nextLine();
	                editcamp.SetRegisterCloseDate(registerCloseDate);
	                break;
	            case 4:
	                System.out.println("Enter User Group: ");
	                String userGroup = sc.nextLine();
	                editcamp.SetUserGrp(userGroup);
	                break;
	            case 5:
	                System.out.println("Enter Location: ");
	                String location = sc.nextLine();
	                editcamp.SetLocation(location);
	                break;
	            case 6:
	                System.out.println("Enter Description: ");
	                String description = sc.nextLine();
	                editcamp.SetDescription(description);
	                break;
	            case 7:
	                System.out.println("Enter Staff in Charge: ");
	                String staffInCharge = sc.nextLine();
	                editcamp.SetStaffInCharge(staffInCharge);
	                break;
	            case 8:
	                System.out.println("Enter Total Slots: ");
	                int totalSlots = Integer.parseInt(sc.nextLine());
	                editcamp.SetTotalSlots(totalSlots);
	                break;
	            case 9:
	                System.out.println("Enter Committee Slots: ");
	                int committeeSlots = Integer.parseInt(sc.nextLine());
	                editcamp.SetCommitteeSlots(committeeSlots);
	                break;
	            case 10:
	            	System.out.println("Set Visibility: true or false?");
	                boolean newVisibility = Boolean.parseBoolean(sc.nextLine());
	                ToggleVisibility(editcamp.GetCampID(), newVisibility);
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
	/**
	 * Delete camp based on campID
     * @param campID ID of the camp 
	 */
	public void DeleteCamp(int campID)
    {
		ArrayList<Camp> camps = campManager.GetCamps();
		Camp remove = null;
    	boolean campExists= false;
    	for (Camp camp : camps) {
			if(camp == null) continue;
    		if (camp.GetCampID() == campID) { // Assuming CampID is a field in the Camp class
    			campExists = true;
    			remove = camp;
    			break;
    		}
    	}	
    	if(campExists) {
    		if (camps.get(campID).GetAttendees().isEmpty() && camps.get(campID).GetCommitteeList().isEmpty()) {
    			campManager.DeleteCamp(campID);
    			Staff staff = (Staff)camsApp.currentUser;
    			staff.RemoveCamps(remove);
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
	/**
	 * Toggle visibility based on campID
     * @param campID ID of the camp
     * @param state true or false
	 */
	public void ToggleVisibility(int campID, boolean state)
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
	/**
	 * View all suggestions of a camp based on CampID
	 * 
     * @param CampID ID of the camp
     * @return true or false
	 */
	public boolean ViewSuggestions(int CampID)
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
    		HashMap<CCM, ArrayList<Suggestions>> map = selectedCamp.GetSuggestion();
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
	/**
	 * Approve suggestion based on CampID and SuggestionID
	 * 
     * @param CampID ID of the camp
     * @param SuggestionID ID of the suggestion
     * @param status true or false
	 */
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
    		HashMap<CCM, ArrayList<Suggestions>> map = selectedCamp.GetSuggestion();
    		for (Entry<CCM, ArrayList<Suggestions>> entry : map.entrySet()) {
    		    ArrayList<Suggestions> suggestions = entry.getValue();
    		    for (Suggestions sgst : suggestions) {
    		        if (sgst.GetID()==(SuggestionID)) {
    		            // Access the reply variable
    		        	sgst.SetStatus(status);
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
	
	/**
	 *  View all enquires of a camp based on CampID
	 * 
     * @param CampID ID of the camp
     * @return true if enquires found. Otherwise, false
	 */
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
    		HashMap<Student, ArrayList<Enquiries>> map = selectedCamp.GetEnquiries();
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
	
	/**
	 *  Reply to enquires based on CamppID and EnquiryID
	 * 
     * @param CampID ID of the camp
     * @param EnquiryID ID of the enquiry
     * @param reply Reply to the enquiry
	 */
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
    		HashMap<Student, ArrayList<Enquiries>> map = selectedCamp.GetEnquiries();
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
	
	/**
	 * Generate csv of participants
	 * 
     * @param CampID ID of the camp
     * @param participantType Student or CCM or All : To generate list of selected participants
	 */
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
	        	
	        	String filename = selectedCamp.GetCampName()+"_Student_List.csv";
	 	        File file = new File(filename);
	 	        int index = 1;

	 	        // Check if the file already exists
	 	        while (file.exists()) {
	 	            // If the file exists, create a new filename with an index
	 	            filename = selectedCamp.GetCampName()+"_Student_List (" + index + ").csv";
	 	            file = new File(filename);
	 	            index++;
	 	        }
	        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
		            // Write header for CSV file
		            writer.write("CampID, CampName, ParticipantName, Role");
		            writer.newLine();

		            // Iterate through participants and write to CSV
		            for (Student student : filterlist) {
		                String campName = selectedCamp.GetCampName();
		                String name = student.GetName();
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
	        	
	        	String filename = selectedCamp.GetCampName()+"_CCM_List.csv";
	 	        File file = new File(filename);
	 	        int index = 1;

	 	        // Check if the file already exists
	 	        while (file.exists()) {
	 	            // If the file exists, create a new filename with an index
	 	            filename = selectedCamp.GetCampName()+"_CCM_List (" + index + ").csv";
	 	            file = new File(filename);
	 	            index++;
	 	        }

	        	
	        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            // Write header for CSV file
	        		writer.write("CampID, CampName, ParticipantName, Role");
	        		writer.newLine();

	            // Iterate through participants and write to CSV
	        		for (CCM ccm : flterlist) {
	        			String campName = selectedCamp.GetCampName();
	        			String name = ccm.GetName();
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

	/**
	 * Generate csv report of CCM based on camp
	 * Print CCM name and their points
     * @param CampID ID of the camp
	 */
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
	        
	        String filename = campName + " CCM Report.csv";
	        File file = new File(filename);
	        int index = 1;

	        // Check if the file already exists
	        while (file.exists()) {
	            // If the file exists, create a new filename with an index
	            filename = campName + "_CCM Report (" + index + ").csv";
	            file = new File(filename);
	            index++;
	        }
	        
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
	            // Write header for CSV file
	            writer.write("ID, Name, Faculty, Points");
	            writer.newLine();

	            // Iterate through each CCM in the list and write to CSV
	            for (CCM ccm : ccmList) {
	            	String userid = ccm.GetUserID();
	            	String faculty = ccm.GetFaculty();
	                String name = ccm.GetName();
	                int points = ccm.GetPoints();

	                // Write data to CSV
	                writer.write(userid + "," +  name + "," + faculty + "," + points);
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
