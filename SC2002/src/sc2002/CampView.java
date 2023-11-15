package sc2002;

import java.util.ArrayList;

public interface CampView 
{
    public default ArrayList<Integer> DisplayAllCamps()
    {
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        ArrayList<Camp> camps = CampController.GetInstance().GetCamps();
        for(int i = 0; i < camps.size(); ++i)
        {
            System.out.println("(" + (i + 1) + ") " + camps.get(i).GetCampName());
            indexes.add(camps.get(i).GetCampID());
        }

        //Returns the index of the camps displayed so if any functions needs access the camp can use
        return indexes;
    }
	
    public void DisplayMyCamps(ArrayList<Camp> camps);
   
}