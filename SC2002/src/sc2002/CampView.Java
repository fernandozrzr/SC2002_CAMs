package sc2002;

import java.util.ArrayList;

public interface CampView 
{
    public default void DisplayAllCamps()
    {
        ArrayList<Camp> camps = CampController.GetInstance().GetCamps();
        for(int i = 0; i < camps.size(); ++i)
            System.out.println("(" + i + 1 + ") " + camps.get(i).GetCampName());
    }

    public void DisplayMyCamps(ArrayList<Camp> camps);
   
}