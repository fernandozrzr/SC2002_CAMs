package sc2002;

import java.util.ArrayList;

public class Student extends User {
	public static ArrayList<Enquiries> myEnquiries;
	private ArrayList<Camp> registeredCamps;
	public Student() {
		ArrayList<Enquiries> myEnquiries = new ArrayList<>();
		ArrayList<Camp> registeredCamps = new ArrayList<>();
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
}
