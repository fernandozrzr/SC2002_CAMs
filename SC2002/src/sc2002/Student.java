package sc2002;

import java.util.ArrayList;

public class Student extends User {
	protected ArrayList<Enquiries> myEnquiries;
	protected ArrayList<Camp> registeredCamps;
	
	public Student(String name, String userID, String faculty){
		super(name, userID, faculty);
	}
	
	public ArrayList<Enquiries> GetMyEnquiries() {
		return myEnquiries;
    }

	public void AddMyEnquiries(Enquiries enquiry){
		myEnquiries.add(enquiry);
	}
	
	public ArrayList<Camp> GetRegisteredCamps() {
        	return registeredCamps;
    	}

	public void AddRegisteredCamps(Camp camp){
		registeredCamps.add(camp);
	}
}
