package sc2002;

import java.util.ArrayList;

public class Student extends User {
	protected ArrayList<Enquiries> myEnquiries;
	protected ArrayList<Camp> registeredCamps;
	
	public Student(String name, String userID, String faculty){
		super(name, userID, faculty);
	}
	
	public ArrayList<Enquiries> getMyEnquiries() {
        	return myEnquiries;
    	}

	public void viewMyEnquiries() {
		for (Enquiries enquiry : myEnquiries) {
            		System.out.println(enquiry); 
        	}
	}
	
	public void viewRegisteredCamp() {
		for (Camp camp : registeredCamps) {
            		System.out.println(camp);
        	}
	}
	
	public ArrayList<Camp> GetRegisteredCamps() {
        	return registeredCamps;
    	}
}
