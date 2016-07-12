package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Models.BIATNET_V1.Distributor;

public class Distributor_Parser 
{
	int i=0;
	private List<Distributor> Distributors_List;
    List<String> Request_Resualt;
	public Distributor_Parser()
	{
		
	}     
	public Distributor_Parser(List<String> Request_Resualt)
	{
		this.Request_Resualt=Request_Resualt;
		Set_Distributors_List(Request_Resualt);
		Governors_Manager Manager=new Governors_Manager();
		Collections.sort(Distributors_List,new Distributor_Comparator());
		Manager.Insert_Distributors_In_Governors(Distributors_List);
		
	}
	public List<Distributor> Get_Distributors_List()
	{
		return Distributors_List;
	}
	/*
	 * This methode Extract One Distributor Information From String and put it under the following form
	 * Libelle,Adresse,Governor,LONGITUDE,LATITUDE
	 * */    
	private List<String> Extract_Individual_Data(String Distributor_Information)
	{
		Matcher matcher=null;
		ArrayList<String> Individual_Data = new ArrayList<>();
        Pattern regex = Pattern.compile("\"([^\"]*)\"");
        matcher = regex.matcher(Distributor_Information);
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
	 * This Method Put the recived Distributor Data From Extract_Individual_Data in Distributor Class
	 * */
	private Distributor Set_Data_In_To_Class(List<String> Distributor_Ind_Data)
	{
		Distributor Current_Distributor=new Distributor();
        Iterator<String> Data_Iterator=Distributor_Ind_Data.iterator();
        Current_Distributor.Set_ID(Get_Last_Distributor_ID());
        Current_Distributor.Set_Libelle(Data_Iterator.next());
        Current_Distributor.Set_Address(Data_Iterator.next());
        Current_Distributor.Set_Governor(Data_Iterator.next());
        String Longtitude=Data_Iterator.next();
        try
        {
        	Current_Distributor.Set_Longitude(Double.parseDouble(Longtitude));
        	
        }
        catch(NumberFormatException ex)
        {
        	Current_Distributor.Set_Longitude(0.0);
        }
        String Latitude=Data_Iterator.next();
        try
        {
        	Current_Distributor.Set_Latitude(Double.parseDouble(Latitude));
        }
        catch(NumberFormatException ex)
        {
        	Current_Distributor.Set_Latitude(0.0);
        }
        i++;
        return Current_Distributor;
    }
	/*
	 * This Method Get the Cleaned Distributor Information and create Distributor List with it
	 * */
	private void Set_Distributors_List(List<String> Distributors_Cleaned_Information)
    {
		Distributors_List=new ArrayList<Distributor>();
        Iterator<String> Distributor_Itearator=Distributors_Cleaned_Information.iterator();
        while(Distributor_Itearator.hasNext())
        {
        	String Distributor_Data=Distributor_Itearator.next();
            Distributors_List.add(Set_Data_In_To_Class(Extract_Individual_Data(Distributor_Data)));
        }  
    }
    private void Out_On_Screan_Distributors_Information()
	{
		Iterator<Distributor> Distributor_Itearator=Distributors_List.iterator();
        while(Distributor_Itearator.hasNext())
        {
        	System.out.println(Distributor_Itearator.next().toString());
        }
    }
    private int Get_Last_Distributor_ID()
    {
    	int ID=1;
    	if(Distributors_List.isEmpty())
    	{
    		return ID;
    	}
    	else
    	{
    		Iterator<Distributor> Distributor_Iterator=Distributors_List.iterator();
    		while(Distributor_Iterator.hasNext())
    		{
    			Distributor Current_Distributor=Distributor_Iterator.next();
    			ID=Current_Distributor.Get_ID();
    		}
    		return ID+1;
    	}
    	
    }
}
class Distributor_Comparator implements Comparator<Distributor>
{
    @Override
    public int compare(Distributor o1, Distributor o2)
    {
        return o1.Get_Libelle().compareTo(o2.Get_Libelle());
    }
}
