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
    
    public String GetSuggestion(){
        return suggestion;
    }
    public void SetSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public boolean IsStatus(){
        return status;
    }
    
    public int GetID()
    {
        return id;
    }
    public void setStatus(boolean status){
        this.status = status;
        return;
    }
}