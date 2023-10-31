package sc2002;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List; 

public class CampCommiteeMember extends Student {
    private int myPoints;

    public CampCommiteeMember() {
        myPoints = 0;
    }

    public void generateList(String filePath, String attendeeFilter, String campFilter, List<String> attendees, List<String> camps) {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Write CSV header
            writer.append("Attendee,Camp\n");

            // Write data to the CSV file based on the filters
            for (int i = 0; i < attendees.size(); i++) {
                String attendee = attendees.get(i);
                String camp = camps.get(i);

                if (attendeeFilter==attendee&& campFilter==camp) {
                    writer.append(attendee).append(",").append(camp).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void viewPoints() {
    	System.out.println("You have: "+ this.myPoints + " points.");
    }
}
