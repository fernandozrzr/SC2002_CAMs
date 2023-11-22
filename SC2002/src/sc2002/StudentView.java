package sc2002;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class StudentView implements CampView
{
    public StudentView()
    {
    	super();
    }

    ///////////////////////////////////////////////////         Implmenting interface functions         ///////////////////////////////////////////////////
    @Override
    public void DisplayAllCamps(){	
    	Boolean filter = false; // Default value
    	Boolean success = false;
    	System.out.println("Filter?");
    	System.out.println("Enter true or false:");
    	
    	Scanner sc = new Scanner(System.in);
    	try {
    	    String input = sc.nextLine();
    	    filter = Boolean.parseBoolean(input.toLowerCase());
    	} catch (Exception e) {
    	    System.out.println("Please enter a valid selection (true/false).");
    	}
    	if (filter) {
    		System.out.println("Filter by:");
    	    System.out.println("1. Camp Name");
    	    System.out.println("2. Date");
    	    System.out.println("3. Register Close Date");
    	    System.out.println("4. Location");
    	    System.out.print("Enter your choice (1-4): ");

    	    Scanner scanner = new Scanner(System.in);
    	    int choice = scanner.nextInt();

    	    ArrayList<Camp> camps = CampController.GetInstance().GetCamps();
    	    ArrayList<Camp> filtered= new ArrayList<>();
    	    
    	    switch (choice) {
    	        case 1:
    	            // Filter by camp name
    	            System.out.println("Enter the filter camp name:");
    	            scanner = new Scanner(System.in);
    	            String filterName = scanner.nextLine().toLowerCase();

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
    	            // Filter by register close date
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
    	            scanner = new Scanner(System.in);
    	            String filterLocation = scanner.nextLine().toLowerCase();
    	            
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
    	            
    	            if (filtered.get(i).IsVisible() && camsApp.currentUser.getFaculty().equals(filtered.get(i).GetUserGrp()))
    	            {
    	            	System.out.println("(" + (x + 1) + ") " + filtered.get(i).GetCampName());
    	                int slotsLeft = filtered.get(i).GetTotalSlots() - filtered.get(i).GetAttendees().size();
    	                System.out.println("\tTotal Slots Available: " + slotsLeft + "/" + filtered.get(i).GetTotalSlots());
    	                slotsLeft = filtered.get(i).GetCommitteeSlots() - filtered.get(i).GetCommitteeMembers().size();
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
            
            if (allCamps.get(i).IsVisible() && camsApp.currentUser.getFaculty().equals(allCamps.get(i).GetUserGrp()))
            {
            	System.out.println("(" + (x + 1) + ") " + allCamps.get(i).GetCampName());
                int slotsLeft = allCamps.get(i).GetTotalSlots() - allCamps.get(i).GetAttendees().size();
                System.out.println("\tTotal Slots Available: " + slotsLeft + "/" + allCamps.get(i).GetTotalSlots());
                slotsLeft = allCamps.get(i).GetCommitteeSlots() - allCamps.get(i).GetCommitteeMembers().size();
                System.out.println("\tCommittee Member Slots Available: " + slotsLeft + "/" + allCamps.get(i).GetCommitteeSlots());
            	++x;
            }
        }
    	}
        
    }

    @Override
    public void DisplayMyCamps(ArrayList<Camp> camp) 
    {
        if(camp.size() == 0)
        {
            System.out.println("You are not registerd in any camps");
            return;
        }

    	for (Camp campTemp : camp) 
            System.out.println("Registered for Camp " + campTemp.GetCampName() + " as an attendee.");
    }

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
