package com.Servlets.BIATNET_V1;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Models.BIATNET_V1.Governor;
import com.Utility.BIATNET_V1.InvocationSingleton;

import java.lang.reflect.Method;
import java.net.SocketException;
import java.lang.reflect.InvocationTargetException;

public class Mobile_Banking_Servlet extends HttpServlet implements Servlet 
{
	String Method_Name=null;
	String Class_Name=null;
	String Recived_Class=null;
	public static List<Governor> Governors=new ArrayList<Governor>();
	int i=0;
	public String Response_Caller="";
	private static final long serialVersionUID = 1L;
    public Mobile_Banking_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		response.getOutputStream().println("Mobile Banking Servlet Has Been Called, And it's ready to Answer your needs : "+timeStamp);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setCharacterEncoding("utf-8");
		System.out.println("-------------------------Beging Of One Response-------------------");
		String Message_To_Sent_To_User="";
		try
		{
			try
			{	
				Client_Response Client=new Client_Response();
				String Reading_Request_Resualt=Reading_Client_Message(request);
				Field Variable = Client.getClass().getField(Method_Name);
				Message_To_Sent_To_User=(String)Variable.get(Client);
				System.out.println("--------------------Beging Of First Message Sent to user---------------------------");
				response.getOutputStream().write(Message_To_Sent_To_User.getBytes());
				System.out.println("Message Sent to user :"+Message_To_Sent_To_User);
				System.out.println("--------------------------End Of First Message Sent to user------------------------");
				response.getOutputStream().flush();
				System.out.println("--------------------Beging Of Second Message Sent to user---------------------------");
				response.getOutputStream().write(Reading_Request_Resualt.getBytes());
				System.out.println("Message Sent to user :"+Reading_Request_Resualt);
				System.out.println("--------------------------End Of Second Message Sent to user------------------------");
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
			catch(IllegalAccessException|NoSuchFieldException|IllegalAccessError ex)
			{
				System.out.println(ex.toString());
			}	
		}
		catch (IOException ex)
		{
			System.out.println("error : "+ex);
	    }
		System.out.println("-------------------------End Of One Response-------------------");
		doGet(request, response);
		
	}
	private String Reading_Client_Message(HttpServletRequest request) throws IOException
	{
		List<String> Parsed_Cliend_Request=new ArrayList<String>();
		int length = request.getContentLength();
        byte[] input = new byte[length];
        ServletInputStream sin = request.getInputStream();
        int c, count = 0 ;
        while ((c = sin.read(input, count, input.length-count)) != -1) {
            count +=c;
        }
        sin.close();
        String recievedString = new String(input);
        System.out.println("recivedString "+recievedString );
        String Recived_String="";
        for(int i=1;i<recievedString.length()-1;i++)
        {
        	if(recievedString.charAt(i)!='"')
        	{
        		if(recievedString.charAt(i)!='\\')
        		Recived_String=Recived_String+recievedString.charAt(i);
        	}
        }
        System.out.println(Recived_String);
        String[] tokens=Recived_String.split("/");
		for (String t : tokens)
        {
			Parsed_Cliend_Request.add(t);
        }
        return Identifing_Client_Needs(Parsed_Cliend_Request);
	}
	private String Identifing_Client_Needs(List<String> Parsed_Client_Request)
	{
		
		try
		{
			Recived_Class=null;
			int i=0;
			Iterator<String> Parsed_Client_Request_Iterator=Parsed_Client_Request.iterator();
			while(Parsed_Client_Request_Iterator.hasNext())
			{
				String Current_String=Parsed_Client_Request_Iterator.next();
				if(i==0)
				{
					if(Current_String.contains(","))
					{
						Recived_Class=Current_String.substring(0,Current_String.lastIndexOf(','));
						System.out.println("Recived_Class : "+Recived_Class);
						Class_Name=Current_String.substring(Current_String.lastIndexOf(',')+1,Current_String.length());
						System.out.println("Recived_Class : "+Class_Name);
					}
					else
					{
						Class_Name=Current_String;
					}
					
				}
				else
				{	
					Method_Name=Current_String;
				}
				i++;
			}
			if(Recived_Class==null)
			{
				Class<?> Called_Class = Class.forName(Class_Name);
				Constructor<?> Called_Class_Constructor = Called_Class.getConstructor();
				Object object = Called_Class_Constructor.newInstance();
				String Resualt=null;
				Method  method = Called_Class.getDeclaredMethod (Method_Name);
				Resualt=(String)method.invoke(object);
				return Resualt;
			}
			else
			{
				Class<?> Called_Class = Class.forName(Class_Name);
				Constructor<?> Called_Class_Constructor = Called_Class.getConstructor();
				Object object = Called_Class_Constructor.newInstance();
				String Resualt=null;
				Method  method = Called_Class.getDeclaredMethod (Method_Name,String.class);
				Resualt=(String)method.invoke(object,Recived_Class);
				return Resualt;
			}
		}
		catch(SecurityException|NoSuchMethodException|InvocationTargetException ex)
		{
			ex.printStackTrace();
			return null;
		}
		catch(IllegalAccessException|ClassNotFoundException|InstantiationException ex)
		{
			System.out.println("i'm the cause "+ex);
			return null;
		}
	}
}
 
class Client_Response
{
	public final String Preparing_Agencies_List_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Agencies_List_Response\n";
	public final String Preparing_Dabs_List_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Dabs_List_Response\n";
	public final String Preparing_Currency_List_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Currency_List_Response\n";
	public final String Preparing_Customer_Info_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Account_Info_Response\n";
	public final String Preparing_Check_Note_Creation="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Check_Book_Creation_Response\n";
	public final String Preparing_Withdrawal_History_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Withdrawal_History_Response\n";
	public final String Preparing_Operation_History_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Operation_History_Response\n";
	public final String Preparing_Add_Beneficiary_Request_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Add_Beneficiary_Response\n";
	public final String Preparing_Remove_Beneficiary_Request_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Remove_Beneficiary_Response\n";
	public final String Preparing_Beneficiary_List_Request_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Beneficiary_List_Response\n";
	public final String Preparing_Transfer_Inter_Creation="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Transfer_Inter_Creation_Response\n";
	public final String Preparing_Transfer_Inter_Validation="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Transfer_Inter_Validation_Response\n";
	public final String Preparing_Transfer_Intra_Creation="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Transfer_Intra_Creation_Response\n";
	public final String Preparing_Transfer_Intra_Validation="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Transfer_Intra_Validation_Response\n";
	public final String Preparing_Transfer_History_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Transfer_History_Response\n";
	public final String Preparing_Remboursement_Constant_Duree_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Sim_Credit_Response\n";
	public final String Preparing_Remboursement_Constant_Montant_To_Send="com.app.taha.pfe.Server_Configuration.Server_Response_Manager/Sim_Credit_Response\n";
}
