package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.Models.BIATNET_V1.Withdrawal;
import com.Models.BIATNET_V1.Uncleaned_Withdrawal_List;
import com.Models.BIATNET_V1.Withdrawal_History;

public class Withdrawal_Parser 
{
    private List<String> First_Part,Last_Part;
    private Withdrawal_History Withdrawal_History=new Withdrawal_History();
    
    public Withdrawal_Parser(String Server_Response) 
    {
        Create_Withdrawal_History(Server_Response);
    }   
    private void Create_Withdrawal_History(String Serveur_Resposne)
    {
        int i=0;
        List<Withdrawal> Withdrawal_List=new ArrayList<>();
        Withdrawal_History=new Withdrawal_History();
        Withdrawal Current_Withdrawal;
        try
        {
        	Iterator<Uncleaned_Withdrawal_List> Withdrawal_Cleaned_String_List_Iterator=Put_Server_Response_Into_List(Serveur_Resposne).iterator();
        	Add_First_Part_To_Withdrawal_History(Withdrawal_History,Withdrawal_List);
        	Add_Last_Part_To_Withdrawal_History(Withdrawal_History, Withdrawal_List);
        	while(Withdrawal_Cleaned_String_List_Iterator.hasNext())
        	{
        		if(i==0)
        		{
        			Current_Withdrawal=new Withdrawal();
        			Uncleaned_Withdrawal_List One_Uncleaned_List=Withdrawal_Cleaned_String_List_Iterator.next();
        			List<String> Informations=One_Uncleaned_List.Get_Informations();
        			Iterator<String> Informations_Iterator=Informations.iterator();
        			Current_Withdrawal.Set_Name(Informations_Iterator.next().trim());
        			Informations_Iterator.next();
        			Informations_Iterator.next();
        			Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(Informations_Iterator.next()).iterator();
        			Current_Withdrawal.Set_Date_Valeur(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Type(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Reference(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Date(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Amount(Individual_Data_Iterator.next().trim());
        			Withdrawal_List.add(Current_Withdrawal);
        			i++;
        		}
        		else
        		{
        			Current_Withdrawal=new Withdrawal();
        			Uncleaned_Withdrawal_List One_Uncleaned_List=Withdrawal_Cleaned_String_List_Iterator.next();
        			List<String> Informations=One_Uncleaned_List.Get_Informations();
        			Iterator<String> Informations_Iterator=Informations.iterator();
        			Current_Withdrawal.Set_Name(Informations_Iterator.next().trim());
        			Informations_Iterator.next();
        			Informations_Iterator.next();
        			Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(Informations_Iterator.next()).iterator();
        			Current_Withdrawal.Set_Date_Valeur(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Type(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Reference(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Date(Individual_Data_Iterator.next().trim());
        			Current_Withdrawal.Set_Amount(Individual_Data_Iterator.next().trim());
        			Withdrawal_List.add(Current_Withdrawal);
        		}
        	}
        	Withdrawal_History.Set_Withdrawal_List(Withdrawal_List);
        	System.out.println(Withdrawal_History.toString());
        }
        catch(NoSuchElementException ex)
        {
        	System.out.println(ex);
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
        catch(NullPointerException ex)
        {
            System.out.println("Empty String");
        }
        return Individual_Data;
    }
    private String Remove_Server_Response_Header(String Server_Response)
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
    private List<Uncleaned_Withdrawal_List> Put_Server_Response_Into_List(String Server_Response)
    {
    	int i=0,j=0,ID=0;
		First_Part=new ArrayList<>();
		Last_Part=new ArrayList<>();
		List<String>One_Withdrawal_List=new ArrayList<>();
    	List<Uncleaned_Withdrawal_List>Request_Resualt_List=new ArrayList<>();
    	try
    	{
    		String[] Tokens=Remove_Server_Response_Header(Server_Response).split(",\"");
    		for (String One_Line : Tokens)
    		{
    			if(i>2 && i<Tokens.length-8)
    			{
    				if(j<4)
    				{
    					One_Line="\""+One_Line;
    					One_Withdrawal_List.add(One_Line);
    					j++;
    				}
    				if(j==4)
    				{
    					Uncleaned_Withdrawal_List Current_Uncleaned_Line=new Uncleaned_Withdrawal_List();
    					Current_Uncleaned_Line.Set_ID(ID);
    					Current_Uncleaned_Line.Set_Informations(One_Withdrawal_List);
    					Request_Resualt_List.add(Current_Uncleaned_Line);
    					One_Withdrawal_List=new ArrayList<>();
    					ID++;
    					j=0;
    				}
    			}  
    			else 
    			{
    				if(i<Tokens.length-8)
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
    		return Request_Resualt_List;
    	}
    }
    private void Add_First_Part_To_Withdrawal_History(Withdrawal_History Current_Withdrawal_History,List<Withdrawal> Current_Withdrawal_List)
    {   
        Iterator<String> First_Part_Iterator=First_Part.iterator();
        First_Part_Iterator.next();
        Extract_Withdrawal_History_Main_Information(Current_Withdrawal_History,First_Part_Iterator.next());
        Extract_Withdrawal_From_First_Part(Current_Withdrawal_List,First_Part_Iterator.next());
    }
    private void Extract_Withdrawal_From_First_Part(List<Withdrawal> Current_Operation,String First_Part_Withdrawal)
    {
        Withdrawal Current_Withdrawal=new Withdrawal();
        Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(First_Part_Withdrawal).iterator();
        Current_Withdrawal.Set_Date_Valeur(Individual_Data_Iterator.next().trim());
        Current_Withdrawal.Set_Type(Individual_Data_Iterator.next().trim());
        Current_Withdrawal.Set_Reference(Individual_Data_Iterator.next().trim());
        Current_Withdrawal.Set_Date(Individual_Data_Iterator.next().trim());
        Current_Withdrawal.Set_Amount(Individual_Data_Iterator.next().trim());
        Current_Operation.add(Current_Withdrawal);
    }
    private void Extract_Withdrawal_History_Main_Information(Withdrawal_History Current_Withdrawal_History,String History_Main_Information)
    {
        Iterator<String> History_Main_Information_Iterator=Extract_Individual_Data(History_Main_Information).iterator();
        Current_Withdrawal_History.Set_Withdrawal_Beginning_Date(History_Main_Information_Iterator.next().trim());
        Current_Withdrawal_History.Set_Balance_Amount_Before_Withdrawal(History_Main_Information_Iterator.next().trim());
    }
    private void Add_Last_Part_To_Withdrawal_History(Withdrawal_History Current_Withdrawal_History,List<Withdrawal> Current_Withdrawal_List)
    {
        Iterator<String> Last_Part_Iterator=Last_Part.iterator();
        System.out.println(Last_Part);
        Last_Part_Iterator.next();
        String Account_Information=Last_Part_Iterator.next();
        List<String> Account_Information_List=Extract_Individual_Data(Account_Information);
        System.out.println(Account_Information_List.get(1).trim());
        System.out.println(Account_Information_List.get(2).trim());
        Current_Withdrawal_History.Set_Account_Owner(Account_Information_List.get(1).trim());
        Current_Withdrawal_History.Set_Account_Identifier(Account_Information_List.get(2).trim());
        Current_Withdrawal_History.Set_Currency(Extract_Individual_Data(Last_Part_Iterator.next()).get(1).trim());
        Current_Withdrawal_History.Set_Erreur(Extract_Individual_Data(Last_Part_Iterator.next()).get(0).trim());
        Current_Withdrawal_History.Set_Withdrawal_End_Date(Extract_Individual_Data(Last_Part_Iterator.next()).get(0).trim());
        Current_Withdrawal_History.Set_Balance_Available_Date(Extract_Individual_Data(Last_Part_Iterator.next()).get(0).trim());
        Current_Withdrawal_History.Set_Balance_Amount_After_Withdrawal(Extract_Individual_Data(Last_Part_Iterator.next()).get(0).trim());
        Current_Withdrawal_History.Set_Available_Balance_Amount(Extract_Individual_Data(Last_Part_Iterator.next()).get(0).trim());
    }
    public Withdrawal_History Get_Withdrawal_History()
    {
    	return Withdrawal_History;
    }
}
