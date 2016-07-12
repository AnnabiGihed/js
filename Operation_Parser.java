package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Models.BIATNET_V1.Uncleaned_Operation_List;
import com.Models.BIATNET_V1.Operation;
import com.Models.BIATNET_V1.Operation_History;

public class Operation_Parser 
{
	private List<String> First_Part,Last_Part;
    private Operation_History Operation_History=new Operation_History();
    
    public Operation_Parser(String Server_Response) 
    {
        Create_Operation_History(Server_Response);
    }
    private void Create_Operation_History(String Serveur_Resposne)
    {
        int i=0;
        List<Operation> Operation_List=new ArrayList<>();
        Operation_History=new Operation_History();
        Operation Current_Operation;
        try
        {
        	Iterator<Uncleaned_Operation_List> Operation_Cleaned_String_List_Iterator=Put_Server_Response_Into_List(Serveur_Resposne).iterator();
        	Add_First_Part_To_Operation_History(Operation_History,Operation_List);
        	Add_Last_Part_To_Operation_History(Operation_History, Operation_List);
        	while(Operation_Cleaned_String_List_Iterator.hasNext())
        	{
        		if(i==0)
        		{
        			Current_Operation=new Operation();
        			Uncleaned_Operation_List One_Uncleaned_List=Operation_Cleaned_String_List_Iterator.next();
        			List<String> Informations=One_Uncleaned_List.Get_Informations();
        			Iterator<String> Informations_Iterator=Informations.iterator();
        			Current_Operation.Set_Name(Extract_Individual_Data(Informations_Iterator.next()).get(0).trim());
        			Informations_Iterator.next();
        			Informations_Iterator.next();
        			Informations_Iterator.next();
        			Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(Informations_Iterator.next()).iterator();
        			Current_Operation.Set_Date_Valeur(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Type(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Reference(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Date(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Amount(Individual_Data_Iterator.next().trim());
        			Operation_List.add(Current_Operation);
        			i++;
        		}
        		else
        		{
        			Current_Operation=new Operation();
        			Uncleaned_Operation_List One_Uncleaned_List=Operation_Cleaned_String_List_Iterator.next();
        			List<String> Informations=One_Uncleaned_List.Get_Informations();
        			Iterator<String> Informations_Iterator=Informations.iterator();
        			Current_Operation.Set_Name(Extract_Individual_Data(Informations_Iterator.next()).get(0).trim());
        			Informations_Iterator.next();
        			Informations_Iterator.next();
        			Informations_Iterator.next();
        			Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(Informations_Iterator.next()).iterator();
        			Current_Operation.Set_Date_Valeur(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Type(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Reference(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Date(Individual_Data_Iterator.next().trim());
        			Current_Operation.Set_Amount(Individual_Data_Iterator.next().trim());
        			Operation_List.add(Current_Operation);
        		}
        	}
        	Operation_History.Set_Operation_List(Operation_List);
            System.out.println(Operation_History.toString());
        }
        catch(NoSuchElementException ex)
        {
        	System.out.println("Empty Server Response : "+ ex );
        }
    }
    private List<String> Extract_Individual_Data(String Current_Line)
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
        catch(NullPointerException|NoSuchElementException ex)
        {
        	System.out.println("Empty Server Response : "+ ex );
        }
        return Individual_Data;
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
    		System.out.println("Empty Server Response : "+ ex );
    		return null;
    	}
    }
    private List<Uncleaned_Operation_List> Put_Server_Response_Into_List(String Server_Response)
    {
        int i=0,j=0,ID=0;
        First_Part=new ArrayList<>();
        Last_Part=new ArrayList<>();
        List<Uncleaned_Operation_List>Request_Resualt_List=new ArrayList<>();
        List<String>One_Operation_List=new ArrayList<>();
        try
        {
        	String[] Tokens=Remove_Server_Response_Header(Server_Response).split(",\"");
        	for (String One_Line : Tokens)
        	{
        		if(i>3 && i<Tokens.length-3)
        		{
        			if(j<5)
        			{
        				One_Line="\""+One_Line;
        				One_Operation_List.add(One_Line);
        				j++;
        			}
        			if(j==5)
        			{
        				Uncleaned_Operation_List Current_Uncleaned_Line=new Uncleaned_Operation_List();
        				Current_Uncleaned_Line.Set_ID(ID);
        				Current_Uncleaned_Line.Set_Informations(One_Operation_List);
        				Request_Resualt_List.add(Current_Uncleaned_Line);
        				One_Operation_List=new ArrayList<>();
        				ID++;
        				j=0;
        			}
        		}  
        		else 
        		{
        			if(i<Tokens.length-3)
        			{
        				One_Line="\""+One_Line;
        				First_Part.add(One_Line);
        			}
        			else
        			{
        				One_Line="\""+One_Line;
        				Last_Part.add(One_Line);
        			}
        		}
        		i++;
        	}
        	return Request_Resualt_List;
        }
        catch(NoSuchElementException ex)
        {
        	System.out.println("Empty Server Response : "+ ex );
        	return Request_Resualt_List;
        }
    }
    private void Add_First_Part_To_Operation_History(Operation_History Current_Operation_History,List<Operation> Current_Operation_List)
    {   
    	try
    	{
    		Iterator<String> First_Part_Iterator=First_Part.iterator();
    		First_Part_Iterator.next();
    		Extract_Operation_History_Main_Information(Current_Operation_History,First_Part_Iterator.next());
    		First_Part_Iterator.next();
    		Extract_Operation_From_First_Part(Current_Operation_List,First_Part_Iterator.next());
    	}
    	catch(NoSuchElementException ex)
    	{
    		System.out.println("Empty Server Response : "+ ex );
    	}
    }
    private void Extract_Operation_From_First_Part(List<Operation> Current_Operation_List,String First_Part_Operation)
    {
    	try
    	{
    		Operation Current_Operation=new Operation();
    		Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(First_Part_Operation).iterator();
    		Current_Operation.Set_Date_Valeur(Individual_Data_Iterator.next().trim());
    		Current_Operation.Set_Type(Individual_Data_Iterator.next().trim());
    		Current_Operation.Set_Reference(Individual_Data_Iterator.next().trim());
    		Current_Operation.Set_Date(Individual_Data_Iterator.next().trim());
    		Current_Operation.Set_Amount(Individual_Data_Iterator.next().trim());
    		Current_Operation_List.add(Current_Operation);
    	}
    	catch(NoSuchElementException ex)
    	{
    		System.out.println("Empty Server Response : "+ ex );
    	}
    }
    private void Extract_Operation_History_Main_Information(Operation_History Current_Operation_History,String History_Main_Information)
    {
    	try
    	{
    		Iterator<String> History_Main_Information_Iterator=Extract_Individual_Data(History_Main_Information).iterator();
    		History_Main_Information_Iterator.next();
    		Current_Operation_History.Set_Account_Owner(History_Main_Information_Iterator.next().trim());
    		Current_Operation_History.Set_Account_Identifier(History_Main_Information_Iterator.next().trim());
    	}
    	catch(NoSuchElementException ex)
    	{
    		System.out.println("Empty Server Response : "+ ex );
    	}
    }
    private void Add_Last_Part_To_Operation_History(Operation_History Current_Operation_History,List<Operation> Current_Operation_List)
    {
        Iterator<String> Last_Part_Iterator=Last_Part.iterator();
        Last_Part_Iterator.next();
        String Devise=Extract_Individual_Data(Last_Part_Iterator.next()).get(0).trim();
        String Erreur=Extract_Individual_Data(Last_Part_Iterator.next()).get(0).trim();
        Current_Operation_History.Set_Currency(Devise);
        Current_Operation_History.Set_Erreur(Erreur);
    }
    public Operation_History Get_Operation_History()
    {
    	return Operation_History;
    }
}
