// CCMController.java
package sc2002;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class CCMController {
    private static CCMController instance = null;
    private int ids = 0;
    private ArrayList<CCM> ccm;
    private CCMView view = null;
    private ArrayList<Camp> eligibleCamps = null;
    Scanner sc = new Scanner(System.in);
    private CampController campManager = null;
	private CCMView  CCMViewManager = null;
    public static CCMController GetInstance() {
        if (instance == null)
            instance = new CCMController();
        return instance;
    }


    public CCMController() {
        this.campManager = CampController.GetInstance();
		this.CCMViewManager = CCMView.GetInstance();
        view = new CCMView();
        eligibleCamps = new ArrayList<Camp>();
        ccm = new ArrayList<CCM>();
    }

    public ArrayList<CCM> GetCCM() {
        return ccm;
    }

    public void DeleteSuggestion(int campID, CCM ccm, int suggestionID) {
        // Delete a suggestion from the camp's suggestions list
        Suggestions s = ccm.FindSuggestion(suggestionID);
        ccm.DeleteMySuggestion(s);
        CampController.GetInstance().RemoveSuggestion(campID, ccm, s);
    }

    public void AddSuggestion(int campID, CCM ccm, String suggestion) {
        // Add a suggestion to the camp's suggestions list
        Suggestions _suggestion = new Suggestions(ids++, suggestion, false);
        ccm.AddMySuggestion(_suggestion);
        CampController.GetInstance().AddSuggestion(campID, ccm, _suggestion);
        // Increment points for the CCM
        ccm.SetPoints(ccm.GetPoints()+1);
    }

    public void EditSuggestion(CCM ccm, int suggestionID, String edited) {
        // Edit a suggestion in the camp's suggestions list
        Suggestions s = ccm.FindSuggestion(suggestionID);
        s.SetSuggestion(edited);
    }

    public void ReplyEnquiry(int campID, int index, String reply, CCM replyBy) 
    {
        // Add a reply to a particular enquiry, and leave the replyBy person, and increment the point of the replyBy ccm
        Camp camp = CampController.GetInstance().GetCamps().get(campID);

        if(camp == null)
        {
            System.out.println("This Camp " + campID + " does not exist.");
            return;
        }

        int count = 0;
        Enquiries e = null;

        for (Map.Entry<Student, ArrayList<Enquiries>> entry : camp.GetEnquiries().entrySet()) 
        {
            ArrayList<Enquiries> studentEnquiries = entry.getValue();
            for (Enquiries enquiry : studentEnquiries) 
            {
                if(count == index++)
                {
                    e = enquiry;
                    break;
                }
            }

            if(e != null)
                break;
        }
        
        if (e != null && e.GetStatus() == STATUS.OPEN) {
            e.SetReply(reply);
            e.SetStatus(STATUS.CLOSED);
            e.SetReplyBy(replyBy.GetName());

            // Increment points for the CCM
            replyBy.SetPoints(replyBy.GetPoints() + 1);
        }
        // CampController.GetInstance().ReplyEnquiry(campID, student, enquiry, reply, replyBy);
    }

    public int GetPoints(CCM ccm) {
        // return the number of points the CampComitteeMember has
        return ccm.GetPoints();
    }

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
	        	try (BufferedWriter writer = new BufferedWriter(new FileWriter(selectedCamp.GetCampName()+"_CCM_List.csv"))) {
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

    private void WriteToFile(String fileName, String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Report generated successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Add an enquiry
    public void AddEnquiry(int campID, String enquiry, String reply, String replyBy, Student askBy) 
    {
    	Enquiries newEnquiry = new Enquiries(campID, enquiry, reply, replyBy, askBy);
    	askBy.AddMyEnquiry(newEnquiry);
    	CampController.GetInstance().AddEnquiry(campID, askBy, newEnquiry);
    }
    
    // Delete an enquiry
    public void DeleteEnquiry(Student askBy, int enquiryID) 
    {
    	if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == STATUS.CLOSED)
			System.out.println("Enquiry cannot be deleted as it is already processed.");
		else {
			Enquiries tempEnquiry = askBy.FindEnquiry(enquiryID);
			askBy.RemoveMyEnquiry(tempEnquiry);
			CampController.GetInstance().RemoveEnquiry(tempEnquiry.GetCampID(), askBy, tempEnquiry);
		}
    }
    
    // Edit an enquiry
    public void EditEnquiry(Student askBy, int enquiryID, String newEnquiry) 
    {
		if (askBy.FindEnquiry(enquiryID) == null)
			System.out.println("Enquiry not found.");
		else if (askBy.FindEnquiry(enquiryID).GetStatus() == STATUS.CLOSED)
			System.out.println("Enquiry cannot be edited as it is already processed.");
		else {
			askBy.EditMyEnquiry(enquiryID, newEnquiry);
		}
    }

    private void InitEligibleCamps()
    {
        eligibleCamps.clear();

        for(Camp camp : CampController.GetInstance().GetCamps())
        {
            if(camp == null) continue;
            
            if(camp.IsVisible() && camp.GetUserGrp().equals(camsApp.currentUser.GetFaculty()) )
                eligibleCamps.add(camp);
        }
    }
    
    
    ///////////////////////////////////////////////////         Main Loop Stuff         ///////////////////////////////////////////////////
    public void CcmMenu()
    {
        InitEligibleCamps();
        int choice = -1;

        while(choice != 0)
        {
            view.DisplayMainMenu();
            try
            {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch(InputMismatchException e) 
            {
                System.out.println("Invalid Input!");
                break;
            }

            switch (choice) {
            	case 0: 
            		camsApp.currentUser=null;
            		break;
                case 1:
                    ProfileMenu();
                    break;
                case 2:
                    CampMenu();
                    break;
                case 3:
                    EnquiriesMenu();
                    break;
                case 4:
                    SuggestionMenu();
                case 5:
                    System.out.println("Please enter a user group: \n"
                        + "Student\\CCM\\All");
                    String userGrp = sc.nextLine();
                    GenerateList(((CCM)camsApp.currentUser).GetccmID(),userGrp);
                break;
            
                default:
                    break;
            }
        }
        
    }
    public int getCampIDInput() {
		int campID=-1;

		//display all camp 
		CCMViewManager.DisplayAllCamps(false);
		//get input 
		System.out.println("please select a camp： ");
		campID= Integer.parseInt(sc.nextLine());
		return campID - 1;
	}
    private void CampMenu()
    {
        String[] choices = {"view all", "view registered",  "view details", "register", "withdraw", "exit"};
        String choice = "";
        boolean exit = false;
        
        while(!exit)
        {
            //Printing out the options and checking if validity
            do
            {
                System.out.println();
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("///////////////////////        Camp Menu        ////////////////////////");
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("\"" + choices[0] + "\" to view all camps");
                System.out.println("\"" + choices[1] + "\" to view registered camps");
                System.out.println("\"" + choices[2] + "\" to view a camp's details");
                System.out.println("\"" + choices[3] + "\" to register for a camp");
                System.out.println("\"" + choices[4] + "\" to withdraw from a camp");
                System.out.println("\"" + choices[5] + "\" to exit this page");
                System.out.print("Please enter your choice as stated above: ");

                try
                {
                    choice = sc.nextLine();
                    choice = choice.toLowerCase();
                }
                catch(InputMismatchException e) 
                {
                    System.out.println("Invalid Input!");
                    break;
                }
                
            }while(!Utility.IsValidChoice(choice, choices));

            System.out.println("/////////////////////////////////////////////////////////////////////////");
            System.out.println();

            //Execute function based on choice
            switch (choice) 
            {
                case "view all":
                {
                    view.DisplayAllCamps(CampView.GetFilter());
                    break;
                }

                case "view registered":
                {
                    Student user = null;

                    if(camsApp.currentUser instanceof Student)
                    {
                        user = (Student)(camsApp.currentUser);
                    }
                    else
                    {
                        System.out.println("Invalid request, User is not a Student");
                        break;
                    }

                    view.DisplayMyCamps(user.GetRegisteredCamps());
                    System.out.println("Registered for Camp " + 
                    CampController.GetInstance().GetCamps().get(((CCM)camsApp.currentUser).GetccmID()).GetCampName() 
                    + " as a committee member.");

                    break;
                }
                
                case "view details":
                {
                    System.out.print("Enter the ID of the camp you want to view details of: ");
                    int campID = -1;

                    //Error Checking
                    try
                    {
                        campID = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    view.DisplayCampDetails(campID, (Student)camsApp.currentUser);

                    break;
                }
                
                case "register":
                {
                    // Show all camps with their respective slots available beside and option to register 
                    int campIndex = -1;
                	System.out.print("Enter the ID of the camp to register: ");

                    try
                    {
                        campIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    //Invalid Choice so exit out of case
                    if(campIndex < 0 || campIndex > eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    if(!eligibleCamps.get(campIndex).CanRegister())
                    {
                        System.out.println("The registeration date has passed.");
                        break;
                    }
                    
                    if(CampController.GetInstance().AlreadyRegistered(campIndex, camsApp.currentUser.GetName()))
                    {
                        System.out.println("You are already registered in this camp.");
                        break;
                    }
                    if(CampController.GetInstance().AlreadyCommittee(campIndex, camsApp.currentUser.GetName()))
                    {
                        System.out.println("You are already registered in this camp.");
                        break;
                    }

                    int type = -1;
                    System.out.print("Would you like to join as an (1) Attendee or (2) Camp Committee Memmber: ");

                    try
                    {
                        type = Integer.parseInt(sc.nextLine());
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    switch(type)
                    {
                        case 1:
                            if(camsApp.currentUser instanceof Student) 
                            {
                            	((Student) camsApp.currentUser).AddRegisteredCamps(eligibleCamps.get(campIndex));
                                CampController.GetInstance().AddAttendee(eligibleCamps.get(campIndex).GetCampID(), (Student)camsApp.currentUser);
                            }
                            else
                                System.out.println("Invalid request, User is not a Student");
                            break;
                        case 2:
                            if(camsApp.currentUser instanceof CCM) 
                            {
                            	CCM s = (CCM)camsApp.currentUser;
                                if(s.GetccmID() != -1)
                                {
                                    System.out.println("You are already either a committee member in this camp or another camp.");
                                    break;  
                                }
                            	s.ccmID = eligibleCamps.get(campIndex).GetCampID();
                                
                            	CCM currentUser = new CCM(s.GetName(), s.GetUserID(), s.GetFaculty(), s.GetMyEnquiries(), s.GetRegisteredCamps(), s.GetccmID());
                            	camsApp.currentUser = currentUser;
                            	CampController.GetInstance().AddCommitteeMember(currentUser.ccmID, currentUser);
                                exit = true;
                                camsApp.domain = 0;
                            }
                            else
                                System.out.println("Invalid request, User is not a Student");
                            break;
                        default:
                            System.out.println("You have entered an invalid choice");
                    }
                    break;
                }

                case "withdraw":
                {
                    int campIndex = -1;
                	System.out.print("Enter the ID of the camp to withdraw: ");

                    try
                    {
                        campIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    //Invalid Choice so exit out of case
                    if(campIndex < 0 || campIndex > eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }
                    
                    if(camsApp.currentUser instanceof CCM)
                    {
                        CCM s = (CCM)camsApp.currentUser;
                        if(s.GetccmID() == eligibleCamps.get(campIndex).GetCampID())
                        {
                            System.out.println("You are not able to withdraw from the camp as you are a committee member of this camp.");
                            break;
                        }
                        CampController.GetInstance().RemoveAttendee(eligibleCamps.get(campIndex).GetCampID(), s);
                        s.RemoveRegisteredCamps(eligibleCamps.get(campIndex));
                        System.out.println("You have successfully withdrawn from the camp and will not be allowed to join back");
                    }
                    else
                    {
                        System.out.println("Invalid request, User is not a Student");
                    }

                    break;
                }

                case "exit":
                {
                    exit = true;
                    break;
                }
            
                default:
                    break;
            }
        }
    }

    private void EnquiriesMenu()
    {
        String[] choices = {"view", "view camp enquiry", "edit",  "delete", "submit", "reply", "exit"};
        String choice = "";
        boolean exit = false;
        
        while(!exit)
        {
            //Printing out the options and checking if validity
            do
            {
                System.out.println();
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("/////////////////////        Enquiries Menu        //////////////////////");
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("\"" + choices[0] + "\" to view enquires submitted");
                System.out.println("\"" + choices[1] + "\" to view camps' enquiries");
                System.out.println("\"" + choices[2] + "\" to edit an enquriy");
                System.out.println("\"" + choices[3] + "\" to delete an enquiry");
                System.out.println("\"" + choices[4] + "\" to submit an enquiry");
                System.out.println("\"" + choices[5] + "\" to reply an enquiry");
                System.out.println("\"" + choices[6] + "\" to exit this page");
                System.out.print("Please enter your choice as stated above: ");

                try
                {
                    choice = sc.nextLine();
                    choice = choice.toLowerCase();
                }
                catch(InputMismatchException e) 
                {
                    System.out.println("Invalid Input!");
                    break;
                }
                
            }while(!Utility.IsValidChoice(choice, choices));

            System.out.println("/////////////////////////////////////////////////////////////////////////");
            System.out.println();

            switch (choice) {
                case "view":
                {
                    if(camsApp.currentUser instanceof Student)
                        view.DisplayMyEnquiries(((Student)camsApp.currentUser).GetMyEnquiries());
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "view camp enquiry":
                {
                    view.DisplayAllEnquiries(CampController.GetInstance().GetCamps().get(((CCM)camsApp.currentUser).GetccmID()), camsApp.currentUser);
                    break;
                }

                case "edit":
                {
                    int enquiryIndex = -1;
                	System.out.print("Enter the ID of the enquiry to edit: ");

                    try
                    {
                        enquiryIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(enquiryIndex < 0 || enquiryIndex > ((Student)camsApp.currentUser).GetMyEnquiries().size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    String enquiry = "";
                    try
                    {
                        enquiry = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof Student)
                    {
                        EditEnquiry((Student)camsApp.currentUser, enquiryIndex, enquiry);
                        System.out.println("Enquiry edited successfully");
                    }
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "delete":
                {
                    int enquiryIndex = -1;
                	System.out.print("Enter the ID of the enquiry to delete: ");

                    try
                    {
                        enquiryIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(enquiryIndex < 0 || enquiryIndex > ((Student)camsApp.currentUser).GetMyEnquiries().size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    if(camsApp.currentUser instanceof Student)
                    {
                        DeleteEnquiry((Student)camsApp.currentUser, enquiryIndex);
                        System.out.println("Enquiry deleted successfully");
                    }
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "submit":
                {
                    int campIndex = -1;
                    view.DisplayAllCamps(false);
                	System.out.print("Enter the ID of the camp you want to enquire about: ");

                    try
                    {
                        campIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(campIndex < 0 || campIndex > eligibleCamps.size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    String enquiry = "";
                    try
                    {
                        enquiry = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof Student)
                    {
                        AddEnquiry(eligibleCamps.get(campIndex).GetCampID(), enquiry, null, null, (Student)camsApp.currentUser);
                        System.out.println("Enquiry successfully submitted");
                    }
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "reply":
                {
                    int enquiryIndex = -1;
                	System.out.print("Enter the ID of the enquiry to reply: ");

                    try
                    {
                        enquiryIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(enquiryIndex < 0 || enquiryIndex > ((Student)camsApp.currentUser).GetMyEnquiries().size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    String reply = "";
                	System.out.print("Enter the reply: ");

                    try
                    {
                        reply = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof CCM)
                    {
                        CCM c = (CCM)camsApp.currentUser;
                        ReplyEnquiry(c.GetccmID(), enquiryIndex, reply, c);
                        System.out.println("Enquiry Replied successfully");
                    }
                    else
                        System.out.println("Invalid request, User is not a Student");
                    break;
                }

                case "exit":
                {
                    exit = true;
                    break;
                }
            
                default:
                    break;
            }
        }
    }

    private void ProfileMenu()
    {
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println();
        System.out.println("Name: " + camsApp.currentUser.GetName());
        System.out.println("ID: " + camsApp.currentUser.GetUserID());
        System.out.println("Faculty: " + camsApp.currentUser.GetFaculty());
        System.out.println("You are a committee member of " + CampController.GetInstance().GetCamps().get(((CCM)camsApp.currentUser).ccmID).GetCampName() + " Camp");
        System.out.println();
    }

    private void SuggestionMenu()
    {
        String[] choices = {"view", "edit", "delete", "submit", "exit"};
        String choice = "";
        boolean exit = false;
        
        while(!exit)
        {
            //Printing out the options and checking if validity
            do
            {
                System.out.println();
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("////////////////////        Suggestions Menu        /////////////////////");
                System.out.println("/////////////////////////////////////////////////////////////////////////");
                System.out.println("\"" + choices[0] + "\" to view suggestions submitted");
                System.out.println("\"" + choices[1] + "\" to edit a submitted suggestion");
                System.out.println("\"" + choices[2] + "\" to delete a submitted suggestion");
                System.out.println("\"" + choices[3] + "\" to submit a suggestion");
                System.out.println("\"" + choices[4] + "\" to exit this page");
                System.out.print("Please enter your choice as stated above: ");

                try
                {
                    choice = sc.nextLine();
                    choice = choice.toLowerCase();
                }
                catch(InputMismatchException e) 
                {
                    System.out.println("Invalid Input!");
                    break;
                }
                
            }while(!Utility.IsValidChoice(choice, choices));

            System.out.println("/////////////////////////////////////////////////////////////////////////");
            System.out.println();

            switch (choice) {
                case "view":
                {
                    if(camsApp.currentUser instanceof CCM)
                        view.DisplayAllSuggestions((CCM)camsApp.currentUser);
                    else
                        System.out.println("Invalid request, User is not a CCM");
                    break;
                }

                case "edit":
                {
                    int sIndex = -1;
                	System.out.print("Enter the ID of the suggestion to edit: ");

                    try
                    {
                        sIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(sIndex < 0 || sIndex > ((CCM)camsApp.currentUser).GetMySuggestions().size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    String s = "";
                    try
                    {
                        s = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof CCM)
                    {
                        EditSuggestion((CCM)camsApp.currentUser, sIndex, s);
                        System.out.println("Suggestion edited successfully");
                    }
                    else
                        System.out.println("Invalid request, User is not a CCM");
                    break;
                }

                case "delete":
                {
                    int sIndex = -1;
                	System.out.print("Enter the ID of the suggestion to delete: ");

                    try
                    {
                        sIndex = Integer.parseInt(sc.nextLine()) - 1;
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(sIndex < 0 || sIndex > ((CCM)camsApp.currentUser).GetMySuggestions().size())
                    {
                        System.out.println("You have entered an invalid choice. \n");
                        break;
                    }

                    if(camsApp.currentUser instanceof CCM)
                    {
                        DeleteSuggestion(((CCM)camsApp.currentUser).GetccmID(), (CCM)camsApp.currentUser, sIndex);
                        System.out.println("Suggestion deleted successfully");
                    }
                    else
                        System.out.println("Invalid request, User is not a CCM");
                    break;
                }

                case "submit":
                {
                    System.out.println("Enter your suggestion to the camp: ");
                    String s = "";
                    try
                    {
                        s = sc.nextLine();
                    }
                    catch (InputMismatchException e) 
                    {
                        System.out.println("Invalid Input!");
                        break;
                    }

                    if(camsApp.currentUser instanceof CCM)
                    {
                        AddSuggestion(((CCM)camsApp.currentUser).GetccmID(), (CCM)camsApp.currentUser, s);
                        System.out.println("Suggestion successfully submitted");
                    }
                    else
                        System.out.println("Invalid request, User is not a CCM");
                    break;
                }

                case "exit":
                {
                    exit = true;
                    break;
                }
            
                default:
                    break;
            }
        }
    }
}