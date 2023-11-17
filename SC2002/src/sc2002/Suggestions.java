//suggestions.java
package sc2002;

public class Suggestions {
    private int id;
    private String suggestion;
    private boolean status;
    

    public Suggestions(int id, String suggestion, boolean status) {
        this.suggestion = suggestion;
        this.status = false;
        this.id = id;
    }
    
    // Getter and setter methods for the attributes
    
    public String getSuggestion(){
        return suggestion;
    }
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public boolean isStatus(){
        return status;
    }

    public int GetID()
    {
        return id;
    }
}