package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Models.BIATNET_V1.Agency;
import com.Models.BIATNET_V1.Currency;

public class Currency_Parser
{
	int i=0;
	private List<Currency> Currency_List;
	public Currency_Parser()
	{
		
	}       
	public Currency_Parser(List<String> Request_Resualt)
	{
		Set_Currency_List(Request_Resualt);
		Currency_List=Sort_List();
	}
	public List<Currency> Get_Currency_List()
	{
		return Currency_List;
	}
	/*
	 * This Methode Extract One Currency Information From String and put it under the following form
	 * ID,Name,Buy Rate,Sell Rate
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
        	System.out.println("Currency Request returned Null");
        }
        return Individual_Data;
    }
        
	/*
	 * This Method Put The Recived Currency Data From Extract_Individual_Data in Currency Class
	 * */
	private Currency Set_Data_In_To_Class(List<String> Currency_Ind_Data)
	{
		Currency Current_Currency=new Currency();
        Iterator<String> Data_Iterator=Currency_Ind_Data.iterator();
        Current_Currency.Set_ID(Get_Last_Currency_ID());
        Current_Currency.Set_Name(Data_Iterator.next().replaceAll(" ", ""));
        Current_Currency.Set_Name(Current_Currency.Get_Name().replaceAll("\n", ""));
        String Buy_Rate=Data_Iterator.next();
        try
        {
        	Current_Currency.Set_Buy_Rate(Double.parseDouble(Buy_Rate));
        }
        catch(NumberFormatException ex)
        {
        	Current_Currency.Set_Buy_Rate(0.0);
        }
        String Sell_Rate=Data_Iterator.next();
        try
        {
        	Current_Currency.Set_Sell_Rate(Double.parseDouble(Sell_Rate));
        }
        catch(NumberFormatException ex)
        {
        	Current_Currency.Set_Sell_Rate(0.0);
        }
        i++;
        return Current_Currency;
    }

	/*
	 * This Method Get the Cleaned Currency Information and create agency List with it
	 * */
	private void Set_Currency_List(List<String> Currency_List_Cleaned_Information)
    {
		Currency_List=new ArrayList<Currency>();
        Iterator<String> Currency_List_Itearator=Currency_List_Cleaned_Information.iterator();
        while(Currency_List_Itearator.hasNext())
        {
        	String Currency_Data=Currency_List_Itearator.next();
        	Currency_List.add(Set_Data_In_To_Class(Extract_Individual_Data(Currency_Data)));
        }  
    }
	
    private void Out_On_Screan_Currency_Information(List<Currency> Currency_List)
	{
		Iterator<Currency> Currency_List_Itearator=Currency_List.iterator();
        while(Currency_List_Itearator.hasNext())
        {
        	System.out.println(Currency_List_Itearator.next().toString());
        }
    }

    private int Get_Last_Currency_ID()
    {
    	int ID=1;
    	if(Currency_List.isEmpty())
    	{
    		ID=1;
    	}
    	else
    	{
    		Iterator<Currency> Currency_Iterator=Currency_List.iterator();
    		while(Currency_Iterator.hasNext())
    		{
    			Currency Current_Agency=Currency_Iterator.next();
    			ID=Current_Agency.Get_ID();
    		}
    		ID=ID+1;
    	}
    	return ID;
    }

    private List<Currency> Sort_List()
    {
    	List<Currency> Ordred_Currency_List=new ArrayList<Currency>();
    	List<String> Order_List=new ArrayList<String>();
		Order_List.add("SAR");
		Order_List.add("CAD");
		Order_List.add("DKK");
		Order_List.add("AED");
		Order_List.add("USD");
		Order_List.add("GBP");
		Order_List.add("JPY");
		Order_List.add("KWD");
		Order_List.add("NOK");
		Order_List.add("QAR");
		Order_List.add("SEK");
		Order_List.add("CHF");
		Order_List.add("EUR");
		Order_List.add("BHD");
		Order_List.add("LYD");
    	Iterator<String> Order_List_Iterator=Order_List.iterator();
        while(Order_List_Iterator.hasNext())
        {
        	String Current_Order_String=Order_List_Iterator.next();
        	Iterator<Currency> Unordred_Currency_List_Iterator=Currency_List.iterator();
        	while(Unordred_Currency_List_Iterator.hasNext())
        	{	
        		Currency Current_Currency=Unordred_Currency_List_Iterator.next();
        		if(Current_Currency.Get_Name().equals(Current_Order_String))
        		{
        			Ordred_Currency_List.add(Current_Currency);
        		}
        	}
        }
        return Ordred_Currency_List;
    }
}
