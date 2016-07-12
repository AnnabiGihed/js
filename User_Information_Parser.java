package com.Utility.BIATNET_V1;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.Models.BIATNET_V1.Account;
import com.Models.BIATNET_V1.Card;
import com.Models.BIATNET_V1.Check_Book;
import com.Models.BIATNET_V1.Customer;
import com.Models.BIATNET_V1.Transfer_History;

public class User_Information_Parser 
{
    private Customer Current_Customer;
    public User_Information_Parser(List<String> Server_Response) 
    {
        Current_Customer=new Customer();
        Create_Customer(Server_Response);
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
       catch (NoSuchElementException e) 
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
       catch (NoSuchElementException e) 
       {
    	   return null;
       }
    }
    private Customer Create_Customer(List<String> Server_Response)
    {
    	List<Account> Current_Customer_Accounts=new ArrayList<Account>();
        try 
        {
			
			Account Current_Account;
			int i=0;
			Iterator<String> Cleaned_Server_Response_Iterator=Server_Response.iterator();
			while(Cleaned_Server_Response_Iterator.hasNext())
			{
			    if(i==0)
			    {
			        Current_Account=new Account();
			        String Current_Server_Response_Line=Cleaned_Server_Response_Iterator.next();
			        Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(Current_Server_Response_Line).iterator();
			        
			        Current_Customer.Set_ID(Individual_Data_Iterator.next().trim());
			        Current_Customer.Set_Phone_Number(Individual_Data_Iterator.next().trim());
			        
			        Current_Account.Set_Identifier(Individual_Data_Iterator.next().trim());
			        Current_Account=Extract_Account_Balance(Current_Account);
			        Current_Account.Set_Check_Books(Extract_Check_Book_History(Current_Account.Get_Identifier()));
			        Current_Customer.Set_Status(Individual_Data_Iterator.next().trim());
			        Current_Customer.Set_Password(Individual_Data_Iterator.next().trim());
			        Current_Customer.Set_Full_Name(Individual_Data_Iterator.next().trim());
			        Current_Account.Set_Fonction(Individual_Data_Iterator.next().trim());
			        
			        Current_Customer.Set_Unknown_ID(Individual_Data_Iterator.next().trim());
			        Current_Customer.Set_Cards(Extract_Customer_Cards_List(Current_Customer.Get_Unknown_ID()));
			        Current_Account.Set_Account_CTOS(Individual_Data_Iterator.next().trim());
			        String Options=Individual_Data_Iterator.next().trim();
			        Current_Customer.Set_Transfers_History(Extract_Transfer_History_Data(Current_Customer.Get_Unknown_ID()));
			        if(Options.indexOf("2") >= 0)
			        {
			            Current_Customer.Set_Virement(1);
			        }
			        else
			        {
			            Current_Customer.Set_Virement(0);
			        }								
			        if(Options.indexOf("1") >= 0)
			        {
			            Current_Customer.Set_Download(1);
			        }
			        else
			        {
			            Current_Customer.Set_Download(0);
			        }
			        Current_Account.Set_Owner(Individual_Data_Iterator.next().trim());
			        Current_Customer_Accounts.add(Current_Account);
			        i++;
			    }
			    else
			    {
			        Current_Account=new Account();
			        String Current_Server_Response_Line=Cleaned_Server_Response_Iterator.next();
			        Iterator<String> Individual_Data_Iterator=Extract_Individual_Data(Current_Server_Response_Line).iterator();
			        
			        Individual_Data_Iterator.next();
			        Individual_Data_Iterator.next();
			        
			        Current_Account.Set_Identifier(Individual_Data_Iterator.next().trim());
			        
			        Current_Account=Extract_Account_Balance(Current_Account);
			        Current_Account.Set_Check_Books(Extract_Check_Book_History(Current_Account.Get_Identifier()));

			        Individual_Data_Iterator.next();
			        Individual_Data_Iterator.next();
			        Individual_Data_Iterator.next();
			        
			        Current_Account.Set_Fonction(Individual_Data_Iterator.next().trim());
			        
			        Individual_Data_Iterator.next();
			        
			        Current_Account.Set_Account_CTOS(Individual_Data_Iterator.next().trim());
			        
			        Individual_Data_Iterator.next();
			        
			        Current_Account.Set_Owner(Individual_Data_Iterator.next().trim());
			        
			        Current_Customer_Accounts.add(Current_Account);
			    }
			}
			Current_Customer.Set_Accounts(Current_Customer_Accounts);
			return Current_Customer;
		} catch (NoSuchElementException e) 
        {
			return null;
		}
    }
    private List<String> Extract_Individual_Data(String Current_Line)
    {
    	try {
			Matcher matcher=null;
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
		} catch (NoSuchElementException e) 
    	{
			return null;
		}
    }
    private Account Extract_Account_Balance(Account Current_Account)
    {
    	Account Current_Account1;
    	Current_Account1=Current_Account;
        InvocationSingleton Invocation=new InvocationSingleton();
        String Request_Response=null;
		try 
		{
			Invocation.getOfsUserInfos("CHQ");
			Request_Response = Invocation.getSoldeComptes(Current_Account1.Get_Identifier());
			Iterator<String> Balance_Server_Response_Iterator=Put_Server_Response_Into_List(Request_Response).iterator();
	        Iterator<String> Current_Balance_Iterator=Extract_Individual_Data(Balance_Server_Response_Iterator.next()).iterator();
	        Current_Balance_Iterator.next();
	        Current_Balance_Iterator.next();
	        Current_Account1.Set_Last_Operation_Date(Current_Balance_Iterator.next().trim());
	        Current_Account1.Set_Countable_Balance(Current_Balance_Iterator.next().trim());
	        Current_Account1.Set_Available_Balance(Current_Balance_Iterator.next().trim());
	        Current_Account1.Set_Devise(Current_Balance_Iterator.next().trim());
	        return Current_Account1;
		} 
    	catch (RemoteException | NoSuchElementException e) 
		{
			e.printStackTrace();
			return null;
		}
    }
    private List<Check_Book> Extract_Check_Book_History(String Identifier)
    {
        List<Check_Book> Current_Account_Check_Book_List=new ArrayList<Check_Book>();
        Check_Book Current_Check_Book;
        InvocationSingleton Invocation=new InvocationSingleton();
        String Request_Response=null;
        try
        {
        	Invocation.getOfsUserInfos("CHQ");
            Request_Response=Invocation.SuiviCommandeChequier(Identifier);
            Iterator<String> Request_Response_Iterator=Put_Server_Response_Into_List(Request_Response).iterator();
            while(Request_Response_Iterator.hasNext())
            {
            	Current_Check_Book=new Check_Book();
                String Current_Line=Request_Response_Iterator.next();
                List<String> Current_Data=Extract_Individual_Data(Current_Line);
                if(Current_Data.size()==1)
                {
                	Current_Check_Book.Set_Status("Aucune demande en cours");
                }
                else
                {
                   	Iterator<String> Current_Data_Iterator=Current_Data.iterator();
                   	Current_Check_Book.Set_ID(Current_Data_Iterator.next().trim());
                   	Current_Data_Iterator.next();
                   	Current_Check_Book.Set_Date_Request(Current_Data_Iterator.next().trim());
                   	Current_Check_Book.Set_Number_Of_Pages(Integer.parseInt(Current_Data_Iterator.next().trim()));
                   	Current_Check_Book.Set_Barred(Current_Data_Iterator.next().trim());
                   	Current_Check_Book.Set_Status(Current_Data_Iterator.next().trim()); 
                }
            	Current_Account_Check_Book_List.add(Current_Check_Book);
            }
            return Current_Account_Check_Book_List; 
        }
        catch(RemoteException | NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
        
    }
    private List<Card> Extract_Customer_Cards_List(String Unknown_ID)
    {
    	InvocationSingleton Invocation=new InvocationSingleton();
    	List<Card> Current_Customuer_Cards_List=new ArrayList<Card>();
        Card Current_Card;
        String Request_Response=null;
    	try
    	{
    		Invocation.getOfsUserInfos("CHQ");
    		Request_Response=Invocation.getListecartes(Unknown_ID);
    		Iterator<String> Request_Response_Iterator=Put_Server_Response_Into_List(Request_Response).iterator();
        	//Iterator<String> Request_Response_Iterator=Put_Server_Response_Into_List(List_Carte_Server_Response).iterator();
            while(Request_Response_Iterator.hasNext())
            {
                try 
                {
					Current_Card=new Card();
					Iterator<String> Current_Card_Data_Iterator=Extract_Individual_Data(Request_Response_Iterator.next()).iterator();
					Current_Card.Set_Number(Current_Card_Data_Iterator.next().trim());
					Current_Card.Set_Owner(Current_Card_Data_Iterator.next().trim());
					Current_Card.Set_Type(Current_Card_Data_Iterator.next().trim());
					Current_Card.Set_ID_Cout(Current_Card_Data_Iterator.next().trim());
					Current_Customuer_Cards_List.add(Current_Card);
				}
                catch (NoSuchElementException e) 
                {
					Current_Card=new Card();
				}
            }
            return Current_Customuer_Cards_List;
    	}
    	catch(RemoteException | NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
    }
    private List<Transfer_History> Extract_Transfer_History_Data(String Unknown_ID)
	{
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Transfer_History_Parser Parser=new Transfer_History_Parser(Put_Server_Response_Into_List(Invocation.SuiviVirements(Unknown_ID)));
			return Parser.Get_Transfer_History_List();
		} 
    	catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}	
	}
    public Customer Get_Gustomer()
    {
    	return Current_Customer;
    }
}
