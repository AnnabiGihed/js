package Response_Classes;

import java.lang.reflect.Type;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import com.Models.BIATNET_V1.Beneficiary;
import com.Models.BIATNET_V1.Customer;
import com.Models.BIATNET_V1.Governor;
import com.Models.BIATNET_V1.Operation_History;
import com.Models.BIATNET_V1.Loans;
import com.Models.BIATNET_V1.Transfer;
import com.Models.BIATNET_V1.Withdrawal_History;
import com.Servlets.BIATNET_V1.Mobile_Banking_Servlet;
import com.Utility.BIATNET_V1.Agency_Parser;
import com.Utility.BIATNET_V1.Beneficiry_Parser;
import com.Utility.BIATNET_V1.Currency_Parser;
import com.Utility.BIATNET_V1.Distributor_Parser;
import com.Utility.BIATNET_V1.Governors_Manager;
import com.Utility.BIATNET_V1.InvocationSingleton;
import com.Utility.BIATNET_V1.Operation_Parser;
import com.Utility.BIATNET_V1.Transfer_History_Parser;
import com.Utility.BIATNET_V1.User_Information_Parser;
import com.Utility.BIATNET_V1.Web_Service;
import com.Utility.BIATNET_V1.Withdrawal_Parser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;

public class Client_Request_Manager 
{
	public Client_Request_Manager()
	{
	}
	public String Preparing_Agencies_List_To_Send()
	{
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Agency_Parser Parser=new Agency_Parser(Put_Server_Response_Into_List(Invocation.getListeAgences()));
			Governors_Manager Gov_Parser=new Governors_Manager();
			Gov_Parser.Sort_Governors();
			String json=new Gson().toJson(Mobile_Banking_Servlet.Governors);
			return json;
		} 
    	catch (RemoteException | NullPointerException e) {
			e.printStackTrace();
			return "Application internal Error";
		}	
	}
	public String Preparing_Dabs_List_To_Send()
	{
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Distributor_Parser Parser=new Distributor_Parser(Put_Server_Response_Into_List(Invocation.getListeDAB()));
			Type listType = new TypeToken<List<Governor>>() {}.getType();
	        Governors_Manager Gov_Parser=new Governors_Manager();
	        Gov_Parser.Sort_Governors();
	        String json=new Gson().toJson(Mobile_Banking_Servlet.Governors);
	        return json;
		} catch (RemoteException | JsonSyntaxException | NullPointerException e) {
			e.printStackTrace();
			return "Application internal Error";
		}
		
	}
	public String Preparing_Currency_List_To_Send()
	{
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Currency_Parser Parser=new Currency_Parser(Put_Server_Response_Into_List(Invocation.getDevisesTable()));
        	String json=new Gson().toJson(Parser.Get_Currency_List());
        	return json;
		} 
		catch (RemoteException | NullPointerException e) 
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Customer_Info_To_Send()
	{
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			User_Information_Parser Parser=new User_Information_Parser(Put_Server_Response_Into_List(Invocation.getAccountIBInfos("IU1308852938")));
        	String json=new Gson().toJson(Parser.Get_Gustomer());
        	return json;
		} 
		catch (RemoteException | NullPointerException e) 
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Check_Note_Creation(String Param)throws MalformedJsonException
	{
		Type Type = new TypeToken<Customer>() {}.getType();
        Customer Current_Customer = new Gson().fromJson(Param, Type);
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			String Barre=Current_Customer.Get_Accounts().get(0).Get_Check_Books().get(0).Get_Barred().toLowerCase();
			String firstLetter = Barre.substring(0,1).toUpperCase();
			String restLetters =Barre.substring(1).toLowerCase();
			Barre=firstLetter + restLetters;
			Current_Customer.Get_Accounts().get(0).Get_Check_Books().get(0).Set_Barred(Barre);
			String Check_Book_Creation_Response_Message= Invocation.CommandeChequier(Current_Customer.Get_Accounts().get(0).Get_Identifier(), String.valueOf(Current_Customer.Get_Accounts().get(0).Get_Check_Books().get(0).Get_Number_Of_Pages()), Current_Customer.Get_Accounts().get(0).Get_Check_Books().get(0).Get_Barred(), Current_Customer.Get_ID(), Current_Customer.Get_Unknown_ID());
			Type listType = new TypeToken<String>() {}.getType();
			String json=new Gson().toJson(Check_Book_Creation_Response_Message,listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Withdrawal_History_To_Send(String Param)throws MalformedJsonException
	{
		String Date=null;
		String Account=null; 
		Type Type = new TypeToken<List<String>>() {}.getType();
        List<String> Recived_Param = new Gson().fromJson(Param, Type);
        Account=Recived_Param.get(0);
        Date=Recived_Param.get(1);
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Withdrawal_Parser Parser=new Withdrawal_Parser(Invocation.getExtraitCompte(Account, Date));
			Type listType = new TypeToken<Withdrawal_History>() {}.getType();
			String json=new Gson().toJson(Parser.Get_Withdrawal_History(),listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Operation_History_To_Send(String Param)throws MalformedJsonException
	{
		String Account=null; 
		String Date_Debut=null;
		String Date_Debut_Max=null;
		String Min_Amount=null;
		String Max_Amount=null;
		String Sensop=null;
		String Refop=null;
		String Libop=null;
		Type Type = new TypeToken<List<String>>() {}.getType();
        List<String> Recived_Param = new Gson().fromJson(Param, Type);
        System.out.println(Recived_Param.toString());
        Account=Recived_Param.get(0);
        Date_Debut=Recived_Param.get(1);
        if(Recived_Param.get(2)==null)
        {
        	Date_Debut_Max="";
        }
        else
        {
        	Date_Debut_Max=Recived_Param.get(2);
        }
        if(Recived_Param.get(3)==null)
        {
        	Min_Amount="";
        }
        else
        {
        	Min_Amount=Recived_Param.get(3);
        }
        if(Recived_Param.get(4)==null)
        {
        	Max_Amount="";
        }
        else
        {
        	Max_Amount=Recived_Param.get(4);
        }
        if(Recived_Param.get(5)==null)
        {
        	Sensop="";
        }
        else
        {
        	Sensop=Recived_Param.get(5);
        }
        if(Recived_Param.get(6)==null)
        {
        	Refop="";
        }
        else
        {
        	Refop=Recived_Param.get(6);
        }
        if(Recived_Param.get(7)==null)
        {
        	Libop="";
        }
        else
        {
        	Libop=Recived_Param.get(7);
        }
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Operation_Parser Parser=new Operation_Parser(Invocation.getOperation(Account, Date_Debut,Date_Debut_Max,Min_Amount,Max_Amount,Sensop,Refop,Libop));
			Type listType = new TypeToken<Operation_History>() {}.getType();
			String json=new Gson().toJson(Parser.Get_Operation_History(),listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Add_Beneficiary_Request_To_Send(String Param)throws MalformedJsonException
	{	
		try
		{	
			Param=Param.replaceAll(",", "\",\"");
			Param=Param.replaceAll("\\{", "{\"");
			Param=Param.replaceAll("\\}", "\"}");
			Param=Param.replaceAll(":", "\":\"");
			Type Current_Type = new TypeToken<Beneficiary>() {}.getType();
	        Beneficiary Current_Beneficiary = new Gson().fromJson(Param, Current_Type);
	        InvocationSingleton Invocation;
	        Invocation=new InvocationSingleton();
	        String Add_Beneficiary_Response_Message=Invocation.addBeneficiary(Current_Beneficiary.Get_Useless(), Current_Beneficiary.Get_NickName(),Current_Beneficiary.Get_Note(),Current_Beneficiary.Get_Beneficiary_Account_Identifier(),Current_Beneficiary.Get_BIATNet_Customer_ID());
	        Type listType = new TypeToken<String>() {}.getType();
			String json=new Gson().toJson(Add_Beneficiary_Response_Message,listType);
        	return json;
		}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Remove_Beneficiary_Request_To_Send(String Param)throws MalformedJsonException
	{
		InvocationSingleton Invocation;
		try
		{
			Invocation=new InvocationSingleton();
			Type Type = new TypeToken<String>() {}.getType();
	        String Current_Beneficiary_ID = new Gson().fromJson(Param, Type);
	        return Invocation.removeBeneficiary(Current_Beneficiary_ID);
		}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Beneficiary_List_Request_To_Send(String Param)throws MalformedJsonException
	{

		Type Type = new TypeToken<String>() {}.getType();
        String Client_ID = new Gson().fromJson(Param, Type);
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Beneficiry_Parser Parser=new Beneficiry_Parser(Invocation.getListeBenefIB(Client_ID));
			Type listType = new TypeToken<List<Beneficiary>>() {}.getType();
			String json=new Gson().toJson(Parser.Get_Beneficiaries_List(),listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Transfer_Inter_Creation(String Param)throws MalformedJsonException
	{
		Type Type = new TypeToken<Transfer>() {}.getType();
        Transfer Current_Transfer = new Gson().fromJson(Param, Type);
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			String Virement_Inter1_Response=Invocation.virementInter1
					(
							Current_Transfer.Get_Sender_Account_ID(),
							Current_Transfer.Get_Reciver_Account_ID(),
							Current_Transfer.Get_Amount(),
							Current_Transfer.Get_Currency(),
							Current_Transfer.Get_Password(),
							Current_Transfer.Get_Phone_Number(),
							Current_Transfer.Get_BIATNet_Account_ID(),
							Current_Transfer.Get_Status()
					);
				   
			String[] Tokens=Virement_Inter1_Response.split("/");
	        String Transfer_ID=Tokens[0].substring(Tokens[0].indexOf(86));
	        Current_Transfer.Set_Transfer_ID(Transfer_ID);
			Type listType = new TypeToken<Transfer>() {}.getType();
			String json=new Gson().toJson(Current_Transfer,listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Transfer_Inter_Validation(String Param)throws MalformedJsonException
	{
		Type Type = new TypeToken<Transfer>() {}.getType();
        Transfer Current_Transfer = new Gson().fromJson(Param, Type);
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			String Virement_Inter_Request=Invocation.virementInter
					(
							Current_Transfer.Get_Sender_Account_ID(),
							Current_Transfer.Get_Reciver_Account_ID(),
							Current_Transfer.Get_Amount(),
							Current_Transfer.Get_Currency(),
							Current_Transfer.Get_Password(),
							Current_Transfer.Get_Phone_Number(),
							Current_Transfer.Get_BIATNet_Account_ID(),
							"ATTENTE",
							Current_Transfer.Get_Transfer_ID(),
							Current_Transfer.Get_User_Code_Validation()
					);
			Type listType = new TypeToken<String>() {}.getType();
			String json=new Gson().toJson(Virement_Inter_Request,listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Transfer_Intra_Creation(String Param)throws MalformedJsonException
	{
		Type Type = new TypeToken<Transfer>() {}.getType();
        Transfer Current_Transfer = new Gson().fromJson(Param, Type);
        System.out.println("Recived param For Transfer Creation :");
		System.out.println(Current_Transfer.toString());
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			String Virement_Intra_Response=Invocation.virementIntra1
					(
							Current_Transfer.Get_Sender_Account_ID(),
							Current_Transfer.Get_Reciver_Account_ID(),
							Current_Transfer.Get_Amount(),
							Current_Transfer.Get_Currency(),
							Current_Transfer.Get_Password(),
							Current_Transfer.Get_Phone_Number(),
							Current_Transfer.Get_BIATNet_Account_ID(),
							"NONE"
					);
				   
			String[] Tokens=Virement_Intra_Response.split("/");
	        String Transfer_ID=Tokens[0].substring(Tokens[0].indexOf(86));
	        Current_Transfer.Set_Transfer_ID(Transfer_ID);
			Type listType = new TypeToken<Transfer>() {}.getType();
			String json=new Gson().toJson(Current_Transfer,listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	public String Preparing_Transfer_Intra_Validation(String Param)throws MalformedJsonException
	{
		Type Type = new TypeToken<Transfer>() {}.getType();
        Transfer Current_Transfer = new Gson().fromJson(Param, Type);
		InvocationSingleton Invocation;
		System.out.println("Recived param For Transfer Validation :");
		System.out.println(Current_Transfer.toString());
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			String Virement_Intra_Request=Invocation.virementIntra
					(
							Current_Transfer.Get_Sender_Account_ID(),
							Current_Transfer.Get_Reciver_Account_ID(),
							Current_Transfer.Get_Amount(),
							Current_Transfer.Get_Currency(),
							Current_Transfer.Get_Password(),
							Current_Transfer.Get_Phone_Number(),
							Current_Transfer.Get_BIATNet_Account_ID(),
							Current_Transfer.Get_User_Code_Validation(),
							"ATTENTE",
							Current_Transfer.Get_Transfer_ID()
					);
			Type listType = new TypeToken<String>() {}.getType();
			String json=new Gson().toJson(Virement_Intra_Request,listType);
        	return json;
    	}
		catch(RemoteException | JsonSyntaxException | NullPointerException e)
		{
			e.printStackTrace();
			return "Application internal Error";
		}
	}
	
	public String Preparing_Transfer_History_To_Send(String Param)throws MalformedJsonException
	{
		Type Type = new TypeToken<String>() {}.getType();
        String Client_ID = new Gson().fromJson(Param, Type);
		InvocationSingleton Invocation;
		Invocation=new InvocationSingleton();
		try
    	{
			Invocation.getOfsUserInfos("CHQ");
			Transfer_History_Parser Parser=new Transfer_History_Parser(Put_Server_Response_Into_List(Invocation.SuiviVirements(Client_ID)));
			String json=new Gson().toJson(Parser.Get_Transfer_History_List());
			return json;
		} 
    	catch (RemoteException | JsonSyntaxException | NullPointerException e) {
			e.printStackTrace();
			return "Application internal Error";
		}	
	}
	public String Preparing_Remboursement_Degressif_Montant_To_Send(String Param)throws MalformedJsonException
	{
        try 
        {
        	Type Type = new TypeToken<Loans>() {}.getType();
            Loans Simulation_Param = new Gson().fromJson(Param, Type);
            
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage Soap_Message = messageFactory.createMessage();
			SOAPPart soapPart = Soap_Message.getSOAPPart();
			
			String serverURI = "http://BIATSI/SI_SERVICES/Credit/";
			
			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration("",serverURI);
			
			// SOAP Body
			//SOAPBody soapBody = envelope.getBody();
			SOAPBody soapBody = envelope.getBody();
			SOAPElement Remboursement_Degressif_Montant = soapBody.addChildElement("RemboursementDegressifMontant","","http://BIATSI/SI_SERVICES/Credit");
			
			SOAPElement Montant_de_la_tranche = Remboursement_Degressif_Montant.addChildElement("Montant_de_la_tranche","","http://BIATSI/SI_SERVICES/Credit");
			Montant_de_la_tranche.addTextNode(Simulation_Param.Get_Montant_de_la_tranche());
			
			SOAPElement Montant_Remboursement = Remboursement_Degressif_Montant.addChildElement("Montant_Remboursement","","http://BIATSI/SI_SERVICES/Credit");
			Montant_Remboursement.addTextNode(Simulation_Param.Get_Montant_Remboursement());
			
			SOAPElement Taux = Remboursement_Degressif_Montant.addChildElement("Taux","","http://BIATSI/SI_SERVICES/Credit");
			Taux.addTextNode(Simulation_Param.Get_Taux());
			
			SOAPElement Periodicite_Principal= Remboursement_Degressif_Montant.addChildElement("Periodicite_Principal","","http://BIATSI/SI_SERVICES/Credit");
			Periodicite_Principal.addTextNode(Simulation_Param.Get_Periodicite_Principal());
			
			SOAPElement Periodicite_Interets = Remboursement_Degressif_Montant.addChildElement("Periodicite_Interets","","http://BIATSI/SI_SERVICES/Credit");
			Periodicite_Interets.addTextNode(Simulation_Param.Get_Periodicite_Interets());
			
			SOAPElement DateDeblocage = Remboursement_Degressif_Montant.addChildElement("DateDeblocage","","http://BIATSI/SI_SERVICES/Credit");
			DateDeblocage.addTextNode(Simulation_Param.Get_DateDeblocage());
			
			SOAPElement PremiereEcheancePrincipal = Remboursement_Degressif_Montant.addChildElement("PremiereEcheancePrincipal","","http://BIATSI/SI_SERVICES/Credit");
			PremiereEcheancePrincipal.addTextNode(Simulation_Param.Get_PremiereEcheancePrincipal());
			
			SOAPElement PremiereEcheanceInterets = Remboursement_Degressif_Montant.addChildElement("PremiereEcheanceInterets","","http://BIATSI/SI_SERVICES/Credit");
			PremiereEcheanceInterets.addTextNode(Simulation_Param.Get_PremiereEcheanceInterets());
			
			MimeHeaders headers = Soap_Message.getMimeHeaders();
			headers.addHeader("SOAPAction", serverURI  + "RemboursementDegressifMontant");
			Soap_Message.saveChanges();
			Web_Service Web_Service_Caller=new Web_Service(Soap_Message);
			String json=new Gson().toJson(Web_Service_Caller.Get_Web_Service_Response_List());
			return json;
		} 
        catch (SOAPException | JsonSyntaxException | NullPointerException e) 
        {
        	e.printStackTrace();
        	return "Application internal Error";
		}
	}
	public String Preparing_Remboursement_Constant_Montant_To_Send(String Param)throws MalformedJsonException
	{
        try 
        {
        	Type Type = new TypeToken<Loans>() {}.getType();
            Loans Simulation_Param = new Gson().fromJson(Param, Type);
            
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage Soap_Message = messageFactory.createMessage();
			SOAPPart soapPart = Soap_Message.getSOAPPart();
			
			String serverURI = "http://BIATSI/SI_SERVICES/Credit/";
			
			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration("",serverURI);
			
			// SOAP Body
			//SOAPBody soapBody = envelope.getBody();
			SOAPBody soapBody = envelope.getBody();
			SOAPElement Remboursement_Constant_Montant = soapBody.addChildElement("RemboursementConstantMontant","","http://BIATSI/SI_SERVICES/Credit");
			
			SOAPElement Montant_de_la_tranche = Remboursement_Constant_Montant.addChildElement("Montant_de_la_tranche","","http://BIATSI/SI_SERVICES/Credit");
			Montant_de_la_tranche.addTextNode(Simulation_Param.Get_Montant_de_la_tranche());
			
			SOAPElement Montant_Remboursement = Remboursement_Constant_Montant.addChildElement("Montant_Remboursement","","http://BIATSI/SI_SERVICES/Credit");
			Montant_Remboursement.addTextNode(Simulation_Param.Get_Montant_Remboursement());
			
			SOAPElement Taux = Remboursement_Constant_Montant.addChildElement("Taux","","http://BIATSI/SI_SERVICES/Credit");
			Taux.addTextNode(Simulation_Param.Get_Taux());
			
			SOAPElement Periodicite_Principal= Remboursement_Constant_Montant.addChildElement("Periodicite_Principal","","http://BIATSI/SI_SERVICES/Credit");
			Periodicite_Principal.addTextNode(Simulation_Param.Get_Periodicite_Principal());
			
			SOAPElement Periodicite_Interets = Remboursement_Constant_Montant.addChildElement("Periodicite_Interets","","http://BIATSI/SI_SERVICES/Credit");
			Periodicite_Interets.addTextNode(Simulation_Param.Get_Periodicite_Interets());
			
			SOAPElement DateDeblocage = Remboursement_Constant_Montant.addChildElement("DateDeblocage","","http://BIATSI/SI_SERVICES/Credit");
			DateDeblocage.addTextNode(Simulation_Param.Get_DateDeblocage());
			
			SOAPElement PremiereEcheancePrincipal = Remboursement_Constant_Montant.addChildElement("PremiereEcheancePrincipal","","http://BIATSI/SI_SERVICES/Credit");
			PremiereEcheancePrincipal.addTextNode(Simulation_Param.Get_PremiereEcheancePrincipal());
			
			SOAPElement PremiereEcheanceInterets = Remboursement_Constant_Montant.addChildElement("PremiereEcheanceInterets","","http://BIATSI/SI_SERVICES/Credit");
			PremiereEcheanceInterets.addTextNode(Simulation_Param.Get_PremiereEcheanceInterets());
			
			MimeHeaders headers = Soap_Message.getMimeHeaders();
			headers.addHeader("SOAPAction", serverURI  + "RemboursementConstantMontant");
			Soap_Message.saveChanges();
			System.out.println(Soap_Message.toString());
			Web_Service Web_Service_Caller=new Web_Service(Soap_Message);
			String json=new Gson().toJson(Web_Service_Caller.Get_Web_Service_Response_List());
			return json;
		} 
        catch (SOAPException | JsonSyntaxException | NullPointerException e) 
        {
        	e.printStackTrace();
        	return "Application internal Error";
		}
	}
	public String Preparing_Remboursement_Degressif_Duree_To_Send(String Param)throws MalformedJsonException
	{
        try 
        {
        	Type Type = new TypeToken<Loans>() {}.getType();
            Loans Simulation_Param = new Gson().fromJson(Param, Type);
            
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage Soap_Message = messageFactory.createMessage();
			SOAPPart soapPart = Soap_Message.getSOAPPart();
			
			String serverURI = "http://BIATSI/SI_SERVICES/Credit/";
			
			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration("",serverURI);
			
			// SOAP Body
			//SOAPBody soapBody = envelope.getBody();
			SOAPBody soapBody = envelope.getBody();
			SOAPElement Remboursement_Degressif_Duree = soapBody.addChildElement("RemboursementDegressifDuree","","http://BIATSI/SI_SERVICES/Credit");
			
			SOAPElement Montant_de_la_tranche = Remboursement_Degressif_Duree.addChildElement("Montant_de_la_tranche","","http://BIATSI/SI_SERVICES/Credit");
			Montant_de_la_tranche.addTextNode(Simulation_Param.Get_Montant_de_la_tranche());
			
			SOAPElement Duree_Tranche = Remboursement_Degressif_Duree.addChildElement("Duree_Tranche","","http://BIATSI/SI_SERVICES/Credit");
			Duree_Tranche.addTextNode(Simulation_Param.Get_Duree_Tranche());
			
			SOAPElement Taux = Remboursement_Degressif_Duree.addChildElement("Taux","","http://BIATSI/SI_SERVICES/Credit");
			Taux.addTextNode(Simulation_Param.Get_Taux());
			
			SOAPElement Periodicite_Principal= Remboursement_Degressif_Duree.addChildElement("Periodicite_Principal","","http://BIATSI/SI_SERVICES/Credit");
			Periodicite_Principal.addTextNode(Simulation_Param.Get_Periodicite_Principal());
			
			SOAPElement Periodicite_Interets = Remboursement_Degressif_Duree.addChildElement("Periodicite_Interets","","http://BIATSI/SI_SERVICES/Credit");
			Periodicite_Interets.addTextNode(Simulation_Param.Get_Periodicite_Interets());
			
			SOAPElement DateDeblocage = Remboursement_Degressif_Duree.addChildElement("DateDeblocage","","http://BIATSI/SI_SERVICES/Credit");
			DateDeblocage.addTextNode(Simulation_Param.Get_DateDeblocage());
			
			SOAPElement PremiereEcheancePrincipal = Remboursement_Degressif_Duree.addChildElement("PremiereEcheancePrincipal","","http://BIATSI/SI_SERVICES/Credit");
			PremiereEcheancePrincipal.addTextNode(Simulation_Param.Get_PremiereEcheancePrincipal());
			
			SOAPElement PremiereEcheanceInterets = Remboursement_Degressif_Duree.addChildElement("PremiereEcheanceInterets","","http://BIATSI/SI_SERVICES/Credit");
			PremiereEcheanceInterets.addTextNode(Simulation_Param.Get_PremiereEcheanceInterets());
			
			MimeHeaders headers = Soap_Message.getMimeHeaders();
			headers.addHeader("SOAPAction", serverURI  + "RemboursementDegressifDuree");
			Soap_Message.saveChanges();
			System.out.println(Soap_Message.toString());
			Web_Service Web_Service_Caller=new Web_Service(Soap_Message);
			String json=new Gson().toJson(Web_Service_Caller.Get_Web_Service_Response_List());
			return json;
		} 
        catch (SOAPException | JsonSyntaxException | NullPointerException e) 
        {
        	e.printStackTrace();
        	return "Application internal Error";
		}
	}
	public String Preparing_Remboursement_Constant_Duree_To_Send(String Param)throws MalformedJsonException
	{
		
        try 
        {
        	Type Type = new TypeToken<Loans>() {}.getType();
            Loans Simulation_Param = new Gson().fromJson(Param, Type);
            
			MessageFactory messageFactory = MessageFactory.newInstance();
			SOAPMessage Soap_Message = messageFactory.createMessage();
			SOAPPart soapPart = Soap_Message.getSOAPPart();
			
			String serverURI = "http://BIATSI/SI_SERVICES/Credit/";
			// SOAP Envelope
			SOAPEnvelope envelope = soapPart.getEnvelope();
			envelope.addNamespaceDeclaration("",serverURI);
			
			// SOAP Body
			//SOAPBody soapBody = envelope.getBody();
			SOAPBody soapBody = envelope.getBody();
			SOAPElement Remboursement_Constant_Duree = soapBody.addChildElement("RemboursementConstantDuree","","http://BIATSI/SI_SERVICES/Credit");
			
			SOAPElement Montant_de_la_tranche = Remboursement_Constant_Duree.addChildElement("Montant_de_la_tranche","","http://BIATSI/SI_SERVICES/Credit");
			Montant_de_la_tranche.addTextNode(Simulation_Param.Get_Montant_de_la_tranche());
			
			SOAPElement Duree = Remboursement_Constant_Duree.addChildElement("Duree","","http://BIATSI/SI_SERVICES/Credit");
			Duree.addTextNode(Simulation_Param.Get_Duree());
			
			SOAPElement Taux = Remboursement_Constant_Duree.addChildElement("Taux","","http://BIATSI/SI_SERVICES/Credit");
			Taux.addTextNode(Simulation_Param.Get_Taux());
			
			SOAPElement Periodicite= Remboursement_Constant_Duree.addChildElement("Periodicite","","http://BIATSI/SI_SERVICES/Credit");
			Periodicite.addTextNode(Simulation_Param.Get_Periodicite());
			
			SOAPElement DateDeblocage = Remboursement_Constant_Duree.addChildElement("DateDeblocage","","http://BIATSI/SI_SERVICES/Credit");
			DateDeblocage.addTextNode(Simulation_Param.Get_DateDeblocage());
			
			SOAPElement PremiereEcheance = Remboursement_Constant_Duree.addChildElement("PremiereEcheance","","http://BIATSI/SI_SERVICES/Credit");
			PremiereEcheance.addTextNode(Simulation_Param.Get_PremiereEcheance());
			
			MimeHeaders headers = Soap_Message.getMimeHeaders();
			headers.addHeader("SOAPAction", serverURI  + "RemboursementConstantDuree");
			Soap_Message.saveChanges();
			
			
			Web_Service Web_Service_Caller=new Web_Service(Soap_Message);
			String json=new Gson().toJson(Web_Service_Caller.Get_Web_Service_Response_List());
			return json;
		} 
        catch (SOAPException | JsonSyntaxException | NullPointerException e) 
        {
        	e.printStackTrace();
        	return "Application internal Error";
		}
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
	private List<String> Put_Server_Response_Into_List(String Server_Response)
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
}
