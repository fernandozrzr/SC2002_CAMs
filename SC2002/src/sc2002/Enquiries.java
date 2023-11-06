package sc2002;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enquiries {
	private Map<Student, List<String>> enquiriesList;
	
	public Enquiries() {
		enquiriesList = new HashMap<>();
	}
	
	public void AddEnquiries(Student student, String enquiry) {
		List<String> enquiries = enquiriesList.get(student);
		if(enquiries == null) {
			enquiries = new ArrayList<>();
			enquiriesList.put(student, enquiries);
		}
		enquiries.add(enquiry);
	}
	
	public void DeleteEnquiries(Student student, String enquiry) {
		List<String> enquiries = enquiriesList.get(student);
		if(enquiries != null) {
			enquiries.remove(enquiry);
			if(enquiries.isEmpty()) {
				enquiriesList.remove(student);
			}
		}
	}
	
	public void EditEnquiries(Student student, String oldEnquiry, String newEnquiry) {
		List<String> enquiries = enquiriesList.get(student);
		if(enquiries != null) {
			int index = enquiries.indexOf(oldEnquiry);
			
			if (index != -1) {
				enquiries.indexOf(index, newEnquiry);
			}
		}
	}
	
	public void ViewEnquiries() {
		StringBuilder result = new StringBuilder();
		for (Map.Entry<Student, List<String>> entry : enquiriesList.entrySet()){
			Student student = entry.getKey();
			result.append("Student: ").append(student.getName()).append("\n");
			result.append("Enquiries: ").append(entry.getValue()).append("\n");
		}
		return result.toString();
	}
	
	public String ReplyEnquiries(User user, Student student) {
		if (user instanceof Staff) {
			List<String> enquiries = enquiriesList.get(student);
			if (enquiries != null && !enquiries.isEmpty()) {
				System.out.println("Enquiries")
			}
		}
	}
	
}
