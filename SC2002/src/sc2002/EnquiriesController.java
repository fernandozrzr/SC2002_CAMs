// EnquiriesController.java
package sc2002;

import java.util.ArrayList;
import java.util.HashMap;

public class EnquiriesController {
    private static EnquiriesController instance = null;
    private HashMap<Student,ArrayList<Enquiries>> enquiries;

    public static EnquiriesController GetInstance() {
        if (instance == null)
            instance = new EnquiriesController();

        return instance;
    }

    private EnquiriesController() {
        // Initialize enquiries list
        enquiries = new HashMap<Student,ArrayList<Enquiries>>();
    }

    public ArrayList<Enquiries> GetEnquiries() {
        return enquiries;
    }

    // Add an enquiry to the enquiries list
    public boolean AddEnquiry(Enquiries enquiry) {
        return this.enquiries.add(enquiry);
    }

    // Fetch and display enquiries for a specific student
    public void ViewMyEnquiries(Student student) {
        ArrayList<Enquiries> studentEnquiries = student.getMyEnquiries();
        for (Enquiries enquiry : studentEnquiries) {
            System.out.println(enquiry);
        }
    }
}
