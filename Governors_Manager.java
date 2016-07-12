package com.Utility.BIATNET_V1;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.Models.BIATNET_V1.Agency;
import com.Models.BIATNET_V1.Distributor;
import com.Models.BIATNET_V1.Governor;
import com.Servlets.BIATNET_V1.Mobile_Banking_Servlet;

public class Governors_Manager 
{
	public void Out_On_Screan_Governors_Information()
    {
    	Iterator<Governor> Governors_List_Test_Iterator = Mobile_Banking_Servlet.Governors.iterator();
        while (Governors_List_Test_Iterator.hasNext())
        {
            Governor Current_Governor = Governors_List_Test_Iterator.next();
            Iterator<Agency> Agencies_List_Test_Iterator=Current_Governor.Get_Agencies_List().iterator();
            while(Agencies_List_Test_Iterator.hasNext())
            {
                Agency Current_Agency = Agencies_List_Test_Iterator.next();
            }
            Iterator<Distributor> Distributors_List_Iterator=Current_Governor.Get_Distributor_List().iterator();
            while(Distributors_List_Iterator.hasNext())
            {
                Distributor Current_Distributor = Distributors_List_Iterator.next();
            }
        }
    }
	private int Get_Last_Governor_ID()
    {
    	int ID=1;
    	if(Mobile_Banking_Servlet.Governors.isEmpty())
    	{
    		return ID;
    	}
    	else
    	{
    		Iterator<Governor> Governor_Iterator=Mobile_Banking_Servlet.Governors.iterator();
    		while(Governor_Iterator.hasNext())
    		{
    			Governor Current_Governor=Governor_Iterator.next();
    			ID=Current_Governor.Get_ID()+1;
    		}
    		return ID;
    	}
    }	
	/*
     * This Methode Will Insert Agencies In There Corresponding Governor
     */
	public void Insert_Agencies_List_In_Governors(List<Agency> Agencies_List)
    {
        Get_All_Existing_Governors_From_Agencies_List(Agencies_List);
        Clear_All_Governors_Agencies_List();
        Iterator<Governor> Governors_List_Iterator = Mobile_Banking_Servlet.Governors.iterator();
        while (Governors_List_Iterator.hasNext())
        {
            Governor Current_Governor = Governors_List_Iterator.next();
            Iterator<Agency> Agencies_List_Iterator=Agencies_List.iterator();
            while(Agencies_List_Iterator.hasNext())
            {
                Agency Current_Agency = Agencies_List_Iterator.next();
                if(Current_Agency.Get_Governor().equals(Current_Governor.Get_Name()))
                {
                    Current_Governor.Add_Agency(Current_Agency);
                }
            }
        }
    }
	/*
	 * This Methode Check If A Given Governor Already Exist In Governors List
	 * If Exist It Will Return True
	 * If Not It Will Return False
	 */
    private Boolean Check_Governor_Existence(String Governor_Name)
    {
        Boolean Exist=false;
        if(Mobile_Banking_Servlet.Governors.isEmpty())
        {
            Exist=false;
        }
        else
        {
            Iterator<Governor> Governors_List_Iterator =Mobile_Banking_Servlet.Governors.iterator();
            while (Governors_List_Iterator.hasNext()) {
                Governor Current_Governor = Governors_List_Iterator.next();
                if (Governor_Name.equals(Current_Governor.Get_Name()))
                {
                    Exist=true;
                }
            }
        }
        return Exist;
    }
    /*
	 * This Methode Take Agencies List and Extract Out Of It All The Exisiting Governors
	 * And Put It In An ArrayList<Governor>
	 */
	private void Get_All_Existing_Governors_From_Agencies_List(List<Agency> Agencies_List)
    {
        Iterator<Agency> Agencies_List_Iterator=Agencies_List.iterator();
        while(Agencies_List_Iterator.hasNext())
        {
            Agency Current_Agency=Agencies_List_Iterator.next();
            if(Check_Governor_Existence(Current_Agency.Get_Governor())==false)
            {
            	Mobile_Banking_Servlet.Governors.add(new Governor(Get_Last_Governor_ID(),Current_Agency.Get_Governor()));
            }
        }
    }
	/*
	 * This Methode Take Distributors List and Extract Out Of It All The Exisiting Governors
	 * And Put It In An ArrayList<Governor>
	 */
	private void Get_All_Existing_Governors_From_Distributors_List(List<Distributor> Distributors_List)
    {
        Iterator<Distributor> Distributor_List_Iterator=Distributors_List.iterator();
        while(Distributor_List_Iterator.hasNext())
        {
            Distributor Current_Distributor=Distributor_List_Iterator.next();
            if(Check_Governor_Existence(Current_Distributor.Get_Governor())==false)
            {
                Mobile_Banking_Servlet.Governors.add(new Governor(Get_Last_Governor_ID(),Current_Distributor.Get_Governor()));
            }
        }
    }
	/*
     * This Methode Will Insert Distributors In There Corresponding Governor
     */
    public void Insert_Distributors_In_Governors(List<Distributor> Distributors_List)
    {
        Get_All_Existing_Governors_From_Distributors_List(Distributors_List);
        Clear_All_Governors_Distributor_List();
        Iterator<Governor> Governors_List_Iterator = Mobile_Banking_Servlet.Governors.iterator();
        while (Governors_List_Iterator.hasNext())
        {
            Governor Current_Governor = Governors_List_Iterator.next();
            Iterator<Distributor> Distributors_List_Iterator=Distributors_List.iterator();
            while(Distributors_List_Iterator.hasNext())
            {
                Distributor Current_Distributor = Distributors_List_Iterator.next();
                if(Current_Distributor.Get_Governor().equals(Current_Governor.Get_Name()))
                {
                    Current_Governor.Add_Distributor(Current_Distributor);
                }
            }
        }
    }
    public void Clear_All_Governors_Agencies_List()
    {
    	Iterator<Governor> Governors_Iterator=Mobile_Banking_Servlet.Governors.iterator();
    	while(Governors_Iterator.hasNext())
    	{
    		Governor Current_Governor=Governors_Iterator.next();
    		Current_Governor.Reset_Agencies_List();
    	}
    }
    public void Clear_All_Governors_Distributor_List()
    {
    	Iterator<Governor> Governors_Iterator=Mobile_Banking_Servlet.Governors.iterator();
    	while(Governors_Iterator.hasNext())
    	{
    		Governor Current_Governor=Governors_Iterator.next();
    		Current_Governor.Reset_Distributor_List();
    	}
    }
    public void Sort_Governors()
    {
    	Collections.sort(Mobile_Banking_Servlet.Governors,new Governor_Comparator());
    }
}
class Governor_Comparator implements Comparator<Governor>
{
    @Override
    public int compare(Governor o1, Governor o2)
    {
        return o1.Get_Name().compareTo(o2.Get_Name());
    }
}