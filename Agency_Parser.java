package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Models.BIATNET_V1.Agency;


public class Agency_Parser 
{
	int i=0;
	private List<Agency> Agencies_List;
	public Agency_Parser()
	{
		
	}
        
	public Agency_Parser(List<String> Request_Resualt)
	{
		Governors_Manager Manager=new Governors_Manager();
		Set_Agencies_List(Request_Resualt);
		Collections.sort(Agencies_List,new Agency_Comparator());
		Manager.Insert_Agencies_List_In_Governors(Agencies_List);
	}
	public List<Agency> Get_Agency_List()
	{
		return Agencies_List;
	}
	/*
	 * This methode Extract One Agency Information From String and put it under the following form
	 * Name,Adresse,Phone Number,Fax Number,Governor,LONGITUDE,LATITUDE
	 * */    
	private List<String> Extract_Individual_Data(String Agency_Information)
	{
		Matcher matcher=null;
		ArrayList<String> Individual_Data = new ArrayList<>();
        Pattern regex = Pattern.compile("\"([^\"]*)\"");
        matcher = regex.matcher(Agency_Information);
        try
        {
        	while(matcher.find())
        	{
        		Individual_Data.add(matcher.group(1));
        	}
        }
        catch(NullPointerException ex)
        {
        	System.out.println("Empty String");
        }
        return Individual_Data;
    }      
	/*
	 * This Method Put the recived Agency Data From Extract_Individual_Data in Agency Class
	 * */
	private Agency Set_Data_In_To_Class(List<String> Agency_Ind_Data)
	{
		Agency Current_Agency=new Agency();
        Iterator<String> Data_Iterator=Agency_Ind_Data.iterator();
        Current_Agency.Set_ID(Get_Last_Agency_ID());
        String Name=Data_Iterator.next().replace("AGENCE ", "");
        Name=Name.replace("AG. ", "");
        Current_Agency.Set_Name(Name);
        Current_Agency.Set_Address(Data_Iterator.next());
        Current_Agency.Set_Phone_Number(Data_Iterator.next());
        Current_Agency.Set_Fax_Number(Data_Iterator.next());
        Current_Agency.Set_Governor(Data_Iterator.next());
        String Longtitude=Data_Iterator.next();
        try
        {
        	Current_Agency.Set_Longitude(Double.parseDouble(Longtitude));
        	
        }
        catch(NumberFormatException ex)
        {
        	Current_Agency.Set_Longitude(0.0);
        }
        String Latitude=Data_Iterator.next();
        try
        {
        	Current_Agency.Set_Latitude(Double.parseDouble(Latitude));
        }
        catch(NumberFormatException ex)
        {
        	Current_Agency.Set_Latitude(0.0);
        }
        i++;
        return Current_Agency;
    }
	/*
	 * This Method Get the Cleaned Agency Information and create agency List with it
	 * */
	private void Set_Agencies_List(List<String> Agencies_List_Cleaned_Information)
    {
		Agencies_List=new ArrayList<Agency>();
        Iterator<String> Agencies_List_Itearator=Agencies_List_Cleaned_Information.iterator();
        while(Agencies_List_Itearator.hasNext())
        {
        	String Agency_Data=Agencies_List_Itearator.next();
            Agencies_List.add(Set_Data_In_To_Class(Extract_Individual_Data(Agency_Data)));
        }  
    }
    private void Out_On_Screan_Agencies_Information()
	{
		Iterator<Agency> Agencies_List_Itearator=Agencies_List.iterator();
        while(Agencies_List_Itearator.hasNext())
        {
        	System.out.println(Agencies_List_Itearator.next().toString());
        }
    }
    private int Get_Last_Agency_ID()
    {
    	int ID=1;
    	if(Agencies_List.isEmpty())
    	{
    		ID=1;
    	}
    	else
    	{
    		Iterator<Agency> Agency_Iterator=Agencies_List.iterator();
    		while(Agency_Iterator.hasNext())
    		{
    			Agency Current_Agency=Agency_Iterator.next();
    			ID=Current_Agency.Get_ID();
    		}
    		ID=ID+1;
    	}
    	return ID;
    } 
}

class Agency_Comparator implements Comparator<Agency>
{
    @Override
    public int compare(Agency o1, Agency o2)
    {
        return o1.Get_Name().compareTo(o2.Get_Name());
    }
}
