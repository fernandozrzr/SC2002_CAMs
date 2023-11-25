package sc2002;

import java.util.ArrayList;

/**
 * Represents a Student who is a type of User with additional functionalities.
 * Extends the User class.
 * 
 * @author Kiersten Yeo
 * @version 1.0
 * @since 25/11/2023
 */
public class Student extends User {
	/**
	 * List of enquiries made by the student
	 */
	protected ArrayList<Enquiries> myEnquiries;
	/**
	 * List of camps the student registered for
	 */
	protected ArrayList<Camp> registeredCamps;
	/**
	 * ID of the camp the student is a committee member of
	 */
	protected int ccmID;
	
	/**
     	* Constructor of Student object
     	* Initialises the various variables attached to this student
     	* 
     	* @param name Name of the student
     	* @param userID User ID of the student
     	* @param faculty Faculty to which the student belongs
     	*/
	public Student(String name, String userID, String faculty)
	{
		super(name, userID, faculty);
		ccmID = -1;
		myEnquiries = new ArrayList<Enquiries>();
		registeredCamps = new ArrayList<Camp>();
	}

	/**
     	* Constructor of Student object
     	* Initialises the various variables attached to this student
     	* 
     	* @param name Name of the student
     	* @param userID User ID of the student
     	* @param faculty Faculty to which the student belongs
     	* @param myEnquiries List of enquiries made by the student
     	* @param registeredCamps List of camps registered by the student
     	*/
	public Student(String name, String userID, String faculty, ArrayList<Enquiries> myEnquiries, ArrayList<Camp> registeredCamps)
	{
		super(name, userID, faculty);
		ccmID = -1;
		this.myEnquiries = myEnquiries;
		this.registeredCamps = registeredCamps;
	}
	
	/**
     	* Gets the list of camps the student registered for
     	* 
     	* @return List of camps the student registered for
     	*/
	public ArrayList<Camp> GetRegisteredCamps() 
	{
        	return registeredCamps;
	}

	/**
     	* Adds a camp to the list of camps the student registered for
     	* 
    	* @param camp(of type Camp) Camp the student registers for
    	*/
	public void AddRegisteredCamps(Camp camp)
	{
		registeredCamps.add(camp);
	}
	
	/**
     	* Removes a camp from the list of camps the student registered for
     	* 
     	* @param camp(of type Camp) Camp the student drops out of
     	*/
	public void RemoveRegisteredCamps(Camp camp)
	{
		registeredCamps.remove(camp);
	}
	
	/**
     	* Gets the list of enquiries the student submitted
     	* 
     	* @return List of enquiries the student submitted
     	*/
	public ArrayList<Enquiries> GetMyEnquiries() 
	{
		return myEnquiries;
    	}
	/**
     	* Adds an enquiry to the list of enquiries the student submitted
     	* 
     	* @param enquiry(of type Enquiry) Enquiry the student submits
     	*/
	public void AddMyEnquiry(Enquiries enquiry)
	{
		myEnquiries.add(enquiry);
	}
	/**
     	* Removes an enquiry from the list of enquiries the student submitted
     	* 
     	* @param enquiry(of type Enquiry) Enquiry the student removes
     	*/
	public void RemoveMyEnquiry(Enquiries enquiry)
	{
		myEnquiries.remove(enquiry);
	}
	/**
     	* Edits an enquiry in the list of enquiries the student submitted
     	* 
     	* @param index(of type int) Index of the enquiry the student wants to edit
     	* @param newEnquiry(of type String) New enquiry the student wants to edit the old enquiry to
     	*/
	public void EditMyEnquiry(int index, String newEnquiry) 
	{
		myEnquiries.get(index).SetEnquiry(newEnquiry);
	}
	/**
     	* Finds an enquiry in the list of enquiries the student submitted
     	* 
     	* @param enquiryID index(of type int) Index of the enquiry the student wants to find
     	* @return Enquiry the student wants to find (if not found, return error message)
     	*/
	public Enquiries FindEnquiry(int enquiryID) 
	{
		for (int i = 0; i < myEnquiries.size(); ++i) {
			if (i == enquiryID) 
				return myEnquiries.get(i);
		}
		
		System.out.println("Enquiry not found.");
		return null;
	}
	/**
     	* Gets the ID of the camp the student is registered as a committee member of
     	* 
     	* @return ID of the camp the student is registered as a committee member of
     	*/
	public int GetccmID()
	{
		return ccmID;
	}
	/**
     	* Checks if the date of a camp the student wants to register for clashes with any registered camps
     	* 
		* @param date date to check if there is a clash
     	* @return true if there is a date clash, false if there are no date clashes
     	*/
	public boolean dateClash(String date)
	{	
		for(Camp camp :registeredCamps) {
			if(camp.GetDate().equals(date))
				return true;
		}
		return false;
	}
	
}
