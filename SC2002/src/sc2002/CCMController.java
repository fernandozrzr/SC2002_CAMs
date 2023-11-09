package sc2002;

import java.util.ArrayList;

public class CCMController {
    private static CCMController instance = null;
    private ArrayList<CCM> ccm;
    
    public static CCMController GetInstance()
    {
        if(instance == null)
            instance =  new CCMController();
        return instance;
    }

    private CCMController()
    {
        ccm = new ArrayList<CCM>();
    }

    public ArrayList<CCM> getCCM()
    {
        return ccm;
    }

    public void DeleteSuggestions(String ccm, String suggestion) {
        //CampController.GetInstance();
    }

    public void SubmitSuggestions(String suggestion, CCM ccm) {
        
    }

    public void ReplyEnquiries() {

    }
    
    public int GetPoints() {
        return 0;
    }

    public String GenerateList() {
        return "null";
    }

    public void SaveReportToFile() {

    }
}
