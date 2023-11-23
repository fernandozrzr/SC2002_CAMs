package sc2002;

import java.util.ArrayList;

public class Student extends User {
	protected ArrayList<Enquiries> myEnquiries;
	protected ArrayList<Camp> registeredCamps;
	protected int ccmID;
	
	public Student(String name, String userID, String faculty)
	{
		super(name, userID, faculty);
		ccmID = -1;
		myEnquiries = new ArrayList<Enquiries>();
		registeredCamps = new ArrayList<Camp>();
	}

	public Student(String name, String userID, String faculty, ArrayList<Enquiries> myEnquiries, ArrayList<Camp> registeredCamps)
	{
		super(name, userID, faculty);
		ccmID = -1;
		this.myEnquiries = myEnquiries;
		this.registeredCamps = registeredCamps;
	}
	
	// For camps
	public ArrayList<Camp> GetRegisteredCamps() 
	{
        	return registeredCamps;
	}

	public void AddRegisteredCamps(Camp camp)
	{
		registeredCamps.add(camp);
	}
	
	public void RemoveRegisteredCamps(Camp camp)
	{
		registeredCamps.remove(camp);
	}
	
	// For enquiries
	public ArrayList<Enquiries> GetMyEnquiries() 
	{
		return myEnquiries;
    	}

	public void AddMyEnquiry(Enquiries enquiry)
	{
		myEnquiries.add(enquiry);
	}
	
	public void RemoveMyEnquiry(Enquiries enquiry)
	{
		myEnquiries.remove(enquiry);
	}
	
	public void EditMyEnquiry(int index, String newEnquiry) 
	{
		myEnquiries.get(index).SetEnquiry(newEnquiry);
	}
	
	public Enquiries FindEnquiry(int enquiryID) 
	{
		for (int i = 0; i < myEnquiries.size(); ++i) {
			if (i == enquiryID) 
				return myEnquiries.get(i);
		}
		
		System.out.println("Enquiry not found.");
		return null;
	}

	public int GetccmID()
	{
		return ccmID;
	}
	public Boolean dateClash(String date)
	{	
		for(Camp camp :registeredCamps) {
			//System.out.println("reg date: "+camp.GetDate());
			if(camp.GetDate()==date)
				return true;
		}
		return false;
	}
	
}
