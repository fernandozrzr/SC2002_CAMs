// EnquiriesController.java
package sc2002;

import java.util.ArrayList;

public class EnquiriesController {
    private static EnquiriesController instance = null;
    private ArrayList<Enquiries> enquiries;

    public static EnquiriesController getInstance() {
        if (instance == null)
            instance = new EnquiriesController();

        return instance;
    }

    private EnquiriesController() {
        // Initialize enquiries list
        enquiries = new ArrayList<>();
    }

    public ArrayList<Enquiries> getEnquiries() {
        return enquiries;
    }

    // Add an enquiry to the enquiries list
    public boolean addEnquiry(Enquiries enquiry) {
        return this.enquiries.add(enquiry);
    }

    // Fetch and display enquiries for a specific student
    public void viewMyEnquiries(Student student) {
        ArrayList<Enquiries> studentEnquiries = student.getMyEnquiries();
        for (Enquiries enquiry : studentEnquiries) {
            System.out.println(enquiry);
        }
    }
}