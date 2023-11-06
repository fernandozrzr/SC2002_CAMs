package sc2002;

public class CCMController {
    private List<Suggestions> mySuggestions;
    public void editSuggestions(String oldSuggestion, String newSuggestion) {
        for (Suggestions suggestion : mySuggestions) {
            if (suggestion.getSuggestion().equals(oldSuggestion)) {
                suggestion.setSuggestion(newSuggestion);
                break; // Assuming you want to edit only one suggestion
            }
        }
    }

    public void deleteSuggestions(String suggestion) {
        Suggestions suggestionToDelete = null;
        for (Suggestions s : mySuggestions) {
            if (s.getSuggestion().equals(suggestion)) {
                suggestionToDelete = s;
                break;
            }
        }
        if (suggestionToDelete != null) {
            mySuggestions.remove(suggestionToDelete);
        }
    }

    public void submitSuggestions(String suggestion, CCM ccm) {
        Suggestions newSuggestion = new Suggestions(suggestion, "CCM", false);
        mySuggestions.add(newSuggestion);
        ccm.mypoints++;
    }

    public void replyEnquiries() {
        if (enquiries.isEmpty()) {
            System.out.println("No enquiries to reply to.");
            return;
        }

        System.out.println("Enquiries List:");
        Enumeration<Student> students = enquiries.keys();
        Scanner scanner = new Scanner(System.in);

        while (students.hasMoreElements()) {
            Student student = students.nextElement();
            String enquiry = enquiries.get(student);

            // Display student's name and enquiry
            System.out.println("Student: " + student.getName());
            System.out.println("Enquiry: " + enquiry);

            // Prompt the CCM for a reply
            System.out.print("Enter your reply: ");
            String reply = scanner.nextLine();

            // Store the reply (You can implement your storage logic here)
            // For example, you can store the reply in a data structure or database
            // This code is just an example to demonstrate how to capture input
            System.out.println("Reply: " + reply);
            System.out.println("------------------------");
        }

        // Close the scanner
        scanner.close();
    }
    
    public int getPoints() {
        return ccm.getPoints();
    }

    public String generateList(Camp camp, String roleFilter, String format) {
        // Get the list of students attending the camp
        List<Student> attendees = camp.getAttendees();
        
        // Prepare the content for the report
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Camp Name: ").append(camp.getCampName()).append("\n");
        reportContent.append("Date: ").append(camp.getDate()).append("\n");
        reportContent.append("Location: ").append(camp.getLocation()).append("\n");
        reportContent.append("Description: ").append(camp.getDescription()).append("\n");
        reportContent.append("Roles Filter: ").append(roleFilter).append("\n");
        reportContent.append("\nParticipants:\n");
        
        // Filter and add participants based on the role filter
        for (Student student : attendees) {
            if (roleFilter.equals("attendee")) {
                if (student.getRole().equals("attendee")) {
                    reportContent.append("Name: ").append(student.getName()).append(" (Attendee)\n");
                }
            } else if (roleFilter.equals("camp committee")) {
                if (student.getRole().equals("camp committee")) {
                    reportContent.append("Name: ").append(student.getName()).append(" (Camp Committee)\n");
                }
            } else {
                reportContent.append("Name: ").append(student.getName()).append(" (Role: ").append(student.getRole()).append(")\n");
            }
        }

        // Save the report to a file
        if (format.equalsIgnoreCase("txt")) {
            String fileName = camp.getCampName() + "_Report.txt";
            saveReportToFile(fileName, reportContent.toString());
        } else if (format.equalsIgnoreCase("csv")) {
            String fileName = camp.getCampName() + "_Report.csv";
            // Implement CSV file generation
            // You can use a library like OpenCSV to generate CSV files
            saveReportToFile(fileName, reportContent.toString());
        }

        return reportContent.toString();
    }

    private void saveReportToFile(String fileName, String content) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(content);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
