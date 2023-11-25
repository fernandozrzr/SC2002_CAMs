package sc2002;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * StudentView which handles the interface that the Student will see
 * 
 * @author Kiersten Yeo
 * @version 1.0
 * @since 25/11/2023
 */
public class StudentView implements CampView
{
	/**
	 * StudentView default constructor
	 */
    public StudentView()
    {
    	super();
    }

    ///////////////////////////////////////////////////         Implmenting interface functions         ///////////////////////////////////////////////////
    
    /**
     * Displays all camps visible to the student.
     * 
     * @param filter Whether the user wants to filter the camps
     */
    @Override
    public void DisplayAllCamps(Boolean filter){	
    	
    	Boolean success = false;
    	
    	if (filter) {
    		System.out.println("Filter by:");
    	    System.out.println("1. Camp Name");
    	    System.out.println("2. Date");
    	    System.out.println("3. Register Close Date");
    	    System.out.println("4. Location");
    	    System.out.print("Enter your choice (1-4): ");

    	    Scanner sc = new Scanner(System.in);
    	    int choice = sc.nextInt();

    	    ArrayList<Camp> camps = CampController.GetInstance().GetCamps();
    	    ArrayList<Camp> filtered= new ArrayList<>();
    	    
    	    switch (choice) {
    	        case 1:
    	            // Filter by camp name
    	            System.out.println("Enter the filter camp name:");
    	            sc = new Scanner(System.in);
    	            String filterName = sc.nextLine().toLowerCase();

    	            for (Camp camp : camps) {
    	                if (camp.GetCampName().toLowerCase().contains(filterName)) {
    	                	filtered.add(camp);
    	                }
    	            }
    	            success = true;
    	            break;

    	        case 2:
    	        	// Filter by camp date
    	            System.out.println("Enter Date:(dd/mm/yyyy)");
    	            String date = sc.nextLine();

    	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    	            try {
    	                Date inputDate = sdf.parse(date);

    	                for (Camp camp : camps) {
    	                    Date campDate = sdf.parse(camp.GetDate());

    	                    if (campDate.before(inputDate)) {
    	                        filtered.add(camp);
    	                    }
    	                }
    	                success = true;
    	            } catch (ParseException e) {
    	                System.out.println("Invalid date format. Please enter in dd/mm/yyyy format.");
    	            }
    	            break;

    	        case 3:
    	            // Filter by register close dateF
    	        	System.out.println("Enter Date:(dd/mm/yyyy)");
    	            String closeDate = sc.nextLine();

    	            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

    	            try {
    	                Date inputDate = sdf1.parse(closeDate);

    	                for (Camp camp : camps) {
    	                    Date campDate = sdf1.parse(camp.GetRegisterCloseDate());

    	                    if (campDate.before(inputDate)) {
    	                        filtered.add(camp);
    	                    }
    	                }
    	                success = true;
    	            } catch (ParseException e) {
    	                System.out.println("Invalid date format. Please enter in dd/mm/yyyy format.");
    	            }
    	            break;

    	        case 4:
    	            // Filter by location
    	            System.out.println("Enter the filter location:");
    	            String filterLocation = sc.nextLine().toLowerCase();
    	            
    	            for (Camp camp : camps) {
    	                if (camp.GetLocation().toLowerCase().equals(filterLocation)) {
    	                	filtered.add(camp);
    	                	System.out.println(camp.GetCampName());
    	                }
    	            }
    	            success = true;
    	            break;

    	        default:
    	            System.out.println("Invalid choice.");
    	            break;
    	    }
    	    if(success) {
    	    	int x = 0;
    	    	for(int i = 0; i < filtered.size(); ++i)
    	        {
    	            if(filtered.get(i) == null) continue;
    	            
    	            if (filtered.get(i).IsVisible() && camsApp.currentUser.GetFaculty().equals(filtered.get(i).GetUserGrp()))
    	            {
    	            	System.out.println("(" + (x + 1) + ") " + filtered.get(i).GetCampName());
    	                int slotsLeft = filtered.get(i).GetTotalSlots() - filtered.get(i).GetAttendees().size() - filtered.get(i).GetCommitteeList().size();
    	                System.out.println("\tTotal Slots Available: " + slotsLeft + "/" + filtered.get(i).GetTotalSlots());
    	                slotsLeft = filtered.get(i).GetCommitteeSlots() - filtered.get(i).GetCommitteeList().size();
    	                System.out.println("\tCommittee Member Slots Available: " + slotsLeft + "/" + filtered.get(i).GetCommitteeSlots());
    	            	++x;
    	            }
    	        }
    	    }
    	
    	}
    	else
        
    	{
    	ArrayList<Camp> allCamps = CampController.GetInstance().GetCamps();
        
        int x = 0;
        
        for(int i = 0; i < allCamps.size(); ++i)
        {
            if(allCamps.get(i) == null) continue;
            
            if (allCamps.get(i).IsVisible() && camsApp.currentUser.GetFaculty().equals(allCamps.get(i).GetUserGrp()))
            {
            	System.out.println("(" + (x + 1) + ") " + allCamps.get(i).GetCampName());
                int slotsLeft = allCamps.get(i).GetTotalSlots() - allCamps.get(i).GetAttendees().size();
                System.out.println("\tTotal Slots Available: " + slotsLeft + "/" + allCamps.get(i).GetTotalSlots());
                slotsLeft = allCamps.get(i).GetCommitteeSlots() - allCamps.get(i).GetCommitteeList().size();
                System.out.println("\tCommittee Member Slots Available: " + slotsLeft + "/" + allCamps.get(i).GetCommitteeSlots());
            	++x;
            }
        }
    	}
        
    }
    /**
	 * Displays the list of camps the student registered for.
	 * 
     * @param camps List of camps to display
	 */
    @Override
    public void DisplayMyCamps(ArrayList<Camp> camp) 
    {
        if(camp.size() == 0)
        {
            System.out.println("You are not registered in any camps");
            return;
        }

    	for (Camp campTemp : camp) 
            System.out.println("Registered for Camp " + campTemp.GetCampName() + " as an attendee.");
    }
    /**
     * Displays all enquiries submitted by a Student.
     * 
     * @param enquiries List of enquiries submitted by the student
     */
    public void DisplayMyEnquiries(ArrayList<Enquiries> enquiries) 
    {
        ArrayList<Camp> campList = CampController.GetInstance().GetCamps();
        int count = 1;

        if(enquiries.size() == 0)
            System.out.println("You have not made any enquiries");

        for(Enquiries enquiry : enquiries)
        {
            System.out.println("(" + (count++) + ") " + enquiry.GetEnquiry() + " [" + enquiry.GetStatus() + ", " + campList.get(enquiry.GetCampID()).GetCampName() + "]");
            if(enquiry.GetStatus() == STATUS.CLOSED)
                System.out.println("\t\t" + "Answer: " + enquiry.GetReply());
        }
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////         Main Loop Stuff         /////////////////////////////////////////////////////////////////////
    public void DisplayMainMenu()
    {
        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.println("//////////////////////        Student Menu        ///////////////////////");
        System.out.println("/////////////////////////////////////////////////////////////////////////");

        System.out.println("(0) Log Out");
        System.out.println("Profile");
        System.out.println("\t(1) View Profile");
        System.out.println("Camps");
        System.out.println("\t(2) View / Register / Withdraw");
        System.out.println("Enquiries");
        System.out.println("\t(3) View / Edit / Delete / Submit");

        System.out.println("/////////////////////////////////////////////////////////////////////////");
        System.out.print("Enter your Choice: ");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
