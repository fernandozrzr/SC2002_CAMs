package sc2002;

import java.util.ArrayList;

public class EnquiriesController {
    private static EnquiriesController instance = null;
    
    private ArrayList<Enquiries> enquiries;

    public static EnquiriesController GetInstance()
    {
        if(instance == null)
            instance =  new EnquiriesController();

        return instance;

    }private EnquiriesController()
    {
        enquiries = new ArrayList<Enquiries>();
    }

    public ArrayList<Enquiries> GetEnquiries()
    {
        return enquires;
    }
    public ArrayList<Enquiries> GetEnquiries()
    {
        return enquiries;
    }

    public void AddEnquiries(Student student, String enquiry) {	
        enquiries.AddEnquiries(student,enquiry);
    }
	
	public void ViewEnquiries() {

	}
}
