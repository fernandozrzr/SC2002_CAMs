package sc2002;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public interface CampView 
{
	Scanner sc = new Scanner(System.in);
    public default void DisplayAllCamps(Boolean filter){	
    	
    	Boolean success = false;
    	Scanner sc = new Scanner(System.in);
    	
    	if (filter) {
    		System.out.println("Filter by:");
    	    System.out.println("1. Camp Name");
    	    System.out.println("2. Date");
    	    System.out.println("3. Register Close Date");
    	    System.out.println("4. Location");
    	    System.out.print("Enter your choice (1-4): ");

    	    
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
    	    	for(int i = 0; i < filtered.size(); ++i)
    			{
    			    if(filtered.get(i) == null) continue;
    			    System.out.println("(" + (i + 1) + ") " + filtered.get(i).GetCampName());
    			}
    	    }
    	
    	}
    	else
    	{
			ArrayList<Camp> camps = CampController.GetInstance().GetCamps();
			for(int i = 0; i < camps.size(); ++i)
			{
			    if(camps.get(i) == null) continue;
			    System.out.println("(" + (i + 1) + ") " + camps.get(i).GetCampName());
			}
    	}
    }

    public default void DisplayCampDetails(int campID, Student student) 
    {
        ArrayList<Camp> campList = CampController.GetInstance().GetCamps();

        if(campID < 0 || campID > campList.size())
        {
            System.out.println("Not a valid CampID");
            return;
        }

        Camp camp = campList.get(campID);
        if(camp == null)
        {
            System.out.println("Not a valid CampID");
            return;
        }

        System.out.println("Camp Name: " + camp.GetCampName());
        System.out.println("Date: " + camp.GetDate());
        System.out.println("Registration Dateline: " + camp.GetRegisterCloseDate());
        System.out.println("Camp Group: " + camp.GetUserGrp());
        System.out.println("Location: " + camp.GetLocation());
        System.out.println("Description: " + camp.GetDescription());
        System.out.println("Staff In Charge: " + camp.GetStaffInCharge());

        int slotsLeft = camp.GetTotalSlots() - camp.GetAttendees().size();
        System.out.println("Total Slots Available: " + slotsLeft + "/" + camp.GetTotalSlots());
        slotsLeft = camp.GetCommitteeSlots() - camp.GetCommitteeList().size();
        System.out.println("Committee Member Slots Available: " + slotsLeft + "/" + camp.GetCommitteeSlots());
        System.out.println();
    }
	
	public static Boolean getfilter() {
		Boolean filter = false; // Default value
    	System.out.println("Do you want to filter?");
    	System.out.println("Enter true or false:");
    	
    	try {
    	    String input = sc.nextLine();
    	    filter = Boolean.parseBoolean(input.toLowerCase());
    	    
    	} catch (Exception e) {
    	    System.out.println("Please enter a valid selection (true/false).");
    	}
    	return filter;
	}
    public void DisplayMyCamps(ArrayList<Camp> camps);
   
}