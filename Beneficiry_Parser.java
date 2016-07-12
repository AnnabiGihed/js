package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.Models.BIATNET_V1.Beneficiary;

public class Beneficiry_Parser 
{
private List<Beneficiary> Beneficiaries_List=new ArrayList<>();
    
    public Beneficiry_Parser(String Server_Response) 
    {
        Create_Beneficiaries_List(Server_Response);
    }   
    private List<String> Extract_Individual_Data(String Current_Line)
    {
    	try
    	{
    		Matcher matcher;
    		ArrayList<String> Individual_Data = new ArrayList<>();
    		Pattern regex = Pattern.compile("\"([^\"]*)\"");
    		matcher = regex.matcher(Current_Line);
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
    	catch(NoSuchElementException ex)
    	{
    		return null;
    	}
    }
    private String Remove_Server_Response_Header(String Server_Response)
    {
    	try
    	{
    		int i=0;
    		int j=0;
    		while(j!=2)
    		{
    			if(Server_Response.charAt(i)==44)
    			{
    				j++;
    			}
    			i++;
    		}
    		Server_Response=Server_Response.substring(i-1,Server_Response.length());
    		return Server_Response;
    	}
    	catch(NoSuchElementException ex)
    	{
    		return null;
    	}	
    }
    private  List<String> Put_Server_Response_Into_List(String Server_Response)
    {
    	try
    	{
    		int i=0;
    		List<String>Request_Resualt_List=new ArrayList<String>();
    		String[] Tokens=Remove_Server_Response_Header(Server_Response).split(",\"");
    		for (String One_Line : Tokens)
    		{
    			if(i>0)
    			{
    				One_Line="\""+One_Line;
    				Request_Resualt_List.add(One_Line);
    			}
    			i++;
    		}
    		return Request_Resualt_List;
    	}
    	catch(NoSuchElementException ex)
    	{
    		return null;
    	}
    }
    private List<Beneficiary> Create_Beneficiaries_List(String Server_Response)
    {
    	try
    	{
    		Beneficiaries_List=new ArrayList<>();
    		Beneficiary Current_Beneficiary;
    		Iterator<String> Cleaned_Server_Response_Iterator=Put_Server_Response_Into_List(Server_Response).iterator();
    		while(Cleaned_Server_Response_Iterator.hasNext())
    		{
            
    			Current_Beneficiary=new Beneficiary();
    			String Current_Server_Response_Line=Cleaned_Server_Response_Iterator.next();
    			System.out.println(Current_Server_Response_Line);
    			Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(Current_Server_Response_Line).iterator();       
    			Current_Beneficiary.Set_Identifier(Individual_Data_Iterator.next().trim());
    			Current_Beneficiary.Set_NickName(Individual_Data_Iterator.next().trim());
    			Current_Beneficiary.Set_Note(Individual_Data_Iterator.next().trim());
    			Current_Beneficiary.Set_Beneficiary_Account_Identifier(Individual_Data_Iterator.next().trim());
    			Beneficiaries_List.add(Current_Beneficiary);
    		}
    		return Beneficiaries_List;
    	}
    	catch(NoSuchElementException ex)
    	{
    		return null;
    	}
    }
    public List<Beneficiary> Get_Beneficiaries_List()
    {
        return Beneficiaries_List;
    }
}
