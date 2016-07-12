package com.Utility.BIATNET_V1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import org.w3c.dom.NodeList;

import com.Models.BIATNET_V1.Web_Service_Response;

public class Web_Service 
{
	private List<Web_Service_Response> Web_Service_Response_List;
	public Web_Service(SOAPMessage Soap_Request)
	{
		try {
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "http://intranetapp/SI_SERVICES/Credit.asmx";
			SOAPMessage soapResponse = soapConnection.call(Soap_Request, url);
			Web_Service_Response_List=Manage_Response(soapResponse);
			soapConnection.close();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
	}
	private List<Web_Service_Response> Manage_Response(SOAPMessage Response)
	{
		SOAPBody sb;
		List<Web_Service_Response> Response_List=new ArrayList<>();
		Web_Service_Response Current_Response;
		try {
			sb = Response.getSOAPBody();
			NodeList Echeance_List=sb.getElementsByTagName("Echeance");
			NodeList NombreDeJours_List=sb.getElementsByTagName("NombreDeJours");
			NodeList Encours_List=sb.getElementsByTagName("Encours");
			NodeList Amortissement_List=sb.getElementsByTagName("Amortissement");
			NodeList Interets_List=sb.getElementsByTagName("Interets");
			int Size=Echeance_List.getLength();
			for(int i=0;i<Size;i++)
			{
				Current_Response=new Web_Service_Response();
				Current_Response.Set_Echeance(Echeance_List.item(i).getTextContent());
				Current_Response.Set_Nombre_De_Jours(NombreDeJours_List.item(i).getTextContent());
				Current_Response.Set_Encours(Encours_List.item(i).getTextContent());
				Current_Response.Set_Amortissement(Amortissement_List.item(i).getTextContent());
				Current_Response.Set_Interests(Interets_List.item(i).getTextContent());
				Response_List.add(Current_Response);
			}
			return Response_List;
		} 
		catch (SOAPException e) 
		{
			return null;
		}
		
	}
	public List<Web_Service_Response> Get_Web_Service_Response_List() {
		return Web_Service_Response_List;
	}
	/*private static SOAPMessage createSOAPRequest() throws Exception 
	{
		MessageFactory messageFactory = MessageFactory.newInstance();
		SOAPMessage soapMessage = messageFactory.createMessage();
		SOAPPart soapPart = soapMessage.getSOAPPart();
		
		String serverURI = "http://BIATSI/SI_SERVICES/Credit/";
		
		// SOAP Envelope
		SOAPEnvelope envelope = soapPart.getEnvelope();
		envelope.addNamespaceDeclaration("",serverURI);
		
		// SOAP Body
		//SOAPBody soapBody = envelope.getBody();
		SOAPBody soapBody = envelope.getBody();
		SOAPElement soapBodyElem = soapBody.addChildElement("RemboursementConstantDuree","","http://BIATSI/SI_SERVICES/Credit");
		SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("Montant_de_la_tranche","","http://BIATSI/SI_SERVICES/Credit");
		soapBodyElem1.addTextNode("5000");
		SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("Duree","","http://BIATSI/SI_SERVICES/Credit");
		soapBodyElem2.addTextNode("3");
		SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("Taux","","http://BIATSI/SI_SERVICES/Credit");
		soapBodyElem3.addTextNode("5");
		SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("Periodicite","","http://BIATSI/SI_SERVICES/Credit");
		soapBodyElem4.addTextNode("1");
		SOAPElement soapBodyElem5 = soapBodyElem.addChildElement("DateDeblocage","","http://BIATSI/SI_SERVICES/Credit");
		soapBodyElem5.addTextNode("2016-05-18T00:00:00");
		SOAPElement soapBodyElem6 = soapBodyElem.addChildElement("PremiereEcheance","","http://BIATSI/SI_SERVICES/Credit");
		soapBodyElem6.addTextNode("2016-05-18T00:00:00");
		MimeHeaders headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", serverURI  + "RemboursementConstantDuree");
		soapMessage.saveChanges();
		return soapMessage;
	}*/
}