package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Models.BIATNET_V1.Transfer_History;

public class Transfer_History_Parser 
{
    private List<Transfer_History> Transfer_History_List=new ArrayList<Transfer_History>();

    
    public Transfer_History_Parser(List<String> Server_Response) 
    {
        Transfer_History_List=Set_Transfer_History_List(Server_Response);
    }
    private List<String> Extract_Individual_Data(String Transfer_Information)
    {
    	Matcher matcher=null;
    	ArrayList<String> Individual_Data = new ArrayList<>();
        Pattern regex = Pattern.compile("\"([^\"]*)\"");
        matcher = regex.matcher(Transfer_Information);
        try
        {
            while(matcher.find())
            {
                Individual_Data.add(matcher.group(1).trim());
            }
        }
        catch(NullPointerException ex)
        {
            System.out.println("Empty String");
        }
        return Individual_Data;
    }
    private Transfer_History Set_Data_In_To_Class(List<String> Agency_Ind_Data)
    {
    	try
    	{
    		Transfer_History Current_Transfer_History=new Transfer_History();
    		Iterator<String> Current_Transfer_History_Iterator=Agency_Ind_Data.iterator();
    		Current_Transfer_History.Set_Date(Current_Transfer_History_Iterator.next());
    		Current_Transfer_History.Set_Account_CTOS(Current_Transfer_History_Iterator.next());
    		Current_Transfer_History.Set_Amount(Current_Transfer_History_Iterator.next());
    		Current_Transfer_History.Set_Beneficiary_NickName(Current_Transfer_History_Iterator.next());
    		Current_Transfer_History.Set_Beneficiary_ID(Current_Transfer_History_Iterator.next());
    		Current_Transfer_History.Set_Transfer_ID(Current_Transfer_History_Iterator.next());
    		Current_Transfer_History.Set_Status(Current_Transfer_History_Iterator.next());
    		return Current_Transfer_History;
    	}
    	catch(NoSuchElementException ex)
    	{
    		return null;
    	}
    }
    private List<Transfer_History> Set_Transfer_History_List(List<String> Transfer_History_Information)
    {
    	List<Transfer_History> Transfer_History_List=new ArrayList<Transfer_History>();
        Iterator<String> Transfer_History_List_Iterator=Transfer_History_Information.iterator();
        while(Transfer_History_List_Iterator.hasNext())
        {
        	String Current_Transfer_History=Transfer_History_List_Iterator.next();
        	Transfer_History_List.add(Set_Data_In_To_Class(Extract_Individual_Data(Current_Transfer_History)));
        }
        return Transfer_History_List;
    }
    public List<Transfer_History> Get_Transfer_History_List() {
        return Transfer_History_List;
    }
}
