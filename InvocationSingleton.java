package com.Utility.BIATNET_V1;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class InvocationSingleton {
	public InvocationSingleton()
	{
		System.out.println("I have been called");
	}
	private String doEnquiery(String enquiery) throws RemoteException {

		if (enquiery == "" || enquiery == null) {
			return "";
		}

		t24webservicesimpl.T24WebServicesImplServiceStub stub = new t24webservicesimpl.T24WebServicesImplServiceStub();

		t24webservicesimpl.T24WebServicesImplServiceStub.CallOfsE callofse = (t24webservicesimpl.T24WebServicesImplServiceStub.CallOfsE) new t24webservicesimpl.T24WebServicesImplServiceStub.CallOfsE();

		t24webservicesimpl.T24WebServicesImplServiceStub.CallOfs callofs = (t24webservicesimpl.T24WebServicesImplServiceStub.CallOfs) new t24webservicesimpl.T24WebServicesImplServiceStub.CallOfs();

		callofs.setOfsRequest(enquiery);

		t24webservicesimpl.T24WebServicesImplServiceStub.CallOfsResponse callofsr = new t24webservicesimpl.T24WebServicesImplServiceStub.CallOfsResponse();

		t24webservicesimpl.T24WebServicesImplServiceStub.CallOfsResponseE callofsre = new t24webservicesimpl.T24WebServicesImplServiceStub.CallOfsResponseE();

		callofsre.setCallOfsResponse(callofsr);

		callofse.setCallOfs(callofs);

		callofsre = stub.callOfs(callofse);

		callofsr = callofsre.getCallOfsResponse();

		return callofsr.getOfsResponse();
	}
	public String parseTWSMessage(String tws_message) 
	{
		try
		{
			String[] splits = tws_message.split("=");

			String message = "";

			if (splits.length > 0) {
				message = splits[splits.length - 1];
			}
			return message;
		}
		catch(NullPointerException e)
		{
			return null;
		}
	}
	public String getOfsUserInfos(String param) throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getParameter("userOFS") + "/" + Utility.getParameter("pwdOFS") + "/"+ Utility.getParameter("company") + ",GET.PARAM.OFS.USER.MB.ENQ,@ID:EQ=" + param;

		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getAccountIBInfos(String identifiant) throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",GET.INFO.MB.USER.ENQ,@ID:EQ=" + identifiant;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getListecartes(String identifiant) throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",BIAT.MB.CHOIX.CARTE.ENQ,CLIENT:EQ=" + identifiant;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getListeAgences()
			throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",BIAT.MB.LIST.AGENCES.ENQ";
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getListeDAB()
			throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",BIAT.MB.LIST.DAB.ENQ";
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getDevisesTable() throws RemoteException 
	{
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company")+ ",BIAT.MB.GET.CURRENCY.ENQ";
		return this.doEnquiery(queryString);
		
		//return "SICAV PROSPERITY 107.710 SICAV PATRIMOINE OBLI 103.381 SICAV TRESOR 102.796 SICAV OPPORTUNITY 105.729 FCPBIAT-EPARGNE ACTIONS 126.911 BIATCAPITAL-CROISSANCE 90.082 BIATCAPITAL-EQUILIBRE 97.146 BIATCAPITAL-PRUDENCE 99.539 BTA5%OCT2015 ACH 99.59%VTE 100.09% BTA5.25%MARS16 ACH 99.80%VTE100.30%Z12344.269R4.379R1.543R1.576R29.633R30.257R4.359R4.453R1.602R1.631R2.593R2.644R16.440R16.780R5.678R5.799R27.132R27.736R4.397R4.496R2.513R2.566R17.958R18.313R2.212R2.255R4.247R4.342R0.197R0.202RZ";
	}
	public String CommandeChequier(String compte, String nb, String barre,String client, String user) throws RemoteException 
	{
		String queryString = "BIAT.IB.DEM.CHQ,BIAT.IB.SAISIE.MB/I/PROCESS,"+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",,COMPTE::="+ compte + ",NB.CHEQUE::=" + nb + ",BARRE::=" + barre+ ",CLIENT::=" + client + ",USER::=" + user;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getSoldeComptes(String compte) throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",BIAT.MB.SOLDE.COMPTES.ENQ,@ID:EQ=" + compte;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String SuiviCommandeChequier(String compte) throws RemoteException {

		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company")+ ",BIAT.MDP.GEST.CHEQ.SUIV.CMD.ENQ.MB,ACCOUNT.NO:EQ=" + compte;
		return this.doEnquiery(queryString);
	}
	public String getOperation(String compte, String datedebut, String datedebutmax,String montantmin, String montantmax, String sensope,String refope, String libope) throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",BIAT.MB.EXTR.OPE.CHOIX.ENQ,ACCOUNT:EQ=" + compte+ ",BOOKING.DATE:GE=" + datedebut + ",TYPE.ACCES:EQ=MOBILE";
		if (datedebutmax != "") 
		{
			queryString += ",BOOKING.DATE.MAX:LE=" + datedebutmax;
		}
		if (montantmin != "") 
		{
			queryString += ",MONTANT.MIN:GE=" + montantmin;
		}
		if (montantmax != "") 
		{
			queryString += ",MONTANT.MAX:LE=" + montantmax;
		}
		if (sensope != "") 
		{
			queryString += ",SENS.OPE:EQ=" + sensope;
		}
		if (refope != "") 
		{
			queryString += ",REF.SEL:EQ=" + refope;
		}
		if (libope != "") 
		{
			queryString += ",LIB.OPER:EQ=" + libope;
		}
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getExtraitCompte(String compte, String datedebut)throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",BIAT.MB.EXTRAIT.COMPTE.ENQ,ACCOUNT:EQ=" + compte+ ",BOOKING.DATE:GE=" + datedebut + ",TYPE.ACCES:EQ=MOBILE";
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String getListecartesPre(String client) throws RemoteException {
		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",BIAT.MB.LIST.CARTE.PREPAYE,NUM.CLIENT:EQ="+ client;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String virementInter(String emetteur, String beneficiaire, String montant,String devise, String password, String telephone, String idibuser,String statut, String IdBiatVirement, String codeotp)throws RemoteException 
	{					
			montant = montant.replaceAll(",", ".");
			String queryString = "BIAT.IB.VIREMENT,BIAT.SAISIE.MB/I/PROCESS,"+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + "," + IdBiatVirement+ ",COMPTE::=" + emetteur + ",COMPTE.BEN::=" + beneficiaire+ ",MONTANT::=" + montant + ",DEVISE::=" + devise+ ",PASSWORD::=" + password + ",TELEPHONE::=" + telephone+ ",ID.IB.USER::=" + idibuser + ",STATUT::=" + statut+ ",CODE.OTP::=" + codeotp;
			return this.parseTWSMessage(this.doEnquiery(queryString));
	}	
	public String virementInter1(String emetteur, String beneficiaire, String montant,String devise, String password, String telephone, String idibuser,String statut) throws RemoteException 
	{
		montant = montant.replaceAll(",", ".");
		String queryString = "BIAT.IB.VIREMENT,BIAT.SAISIE.MB/I/PROCESS,"+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + ",,COMPTE::="+ emetteur + ",COMPTE.BEN::=" + beneficiaire + ",MONTANT::="+ montant + ",DEVISE::=" + devise + ",PASSWORD::=" + password+ ",TELEPHONE::=" + telephone + ",ID.IB.USER::=" + idibuser+ ",STATUT::=" + statut;
		return this.doEnquiery(queryString);
	}
	public String virementIntra(String emetteur, String beneficiaire, String montant,String devise, String password, String telephone, String idibuser,String codeotp, String statut, String IdBiatVirement)throws RemoteException 
	{
		montant = montant.replaceAll(",", ".");
		String queryString = "BIAT.IB.VIREMENT,BIAT.SAISIE.MM.CLT.MB/I/PROCESS,"+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company")+ ","+ IdBiatVirement+ ","+ "COMPTE::="+ emetteur+ ",COMPTE.2::="+ beneficiaire+ ",MONTANT::="+ montant+ ",DEVISE::="+ devise+ ",PASSWORD::="+ password+ ",TELEPHONE::="+ telephone+ ",ID.IB.USER::="+ idibuser+ ",CODE.OTP::="+ codeotp+ ",STATUT::=" + statut;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String virementIntra1(String emetteur, String beneficiaire, String montant,String devise, String password, String telephone, String idibuser,String statut) throws RemoteException 
	{
		montant = montant.replaceAll(",", ".");
		String queryString = "BIAT.IB.VIREMENT,BIAT.SAISIE.MM.CLT.MB/I/PROCESS,"+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company")+ ",,COMPTE::="+ emetteur+ ",COMPTE.2::="+ beneficiaire+ ",MONTANT::="+ montant+ ",DEVISE::="+ devise+ ",PASSWORD::="+ password+ ",TELEPHONE::="+ telephone+ ",ID.IB.USER::="+ idibuser+ ",STATUT::=" + statut;
		return this.doEnquiery(queryString);
	}
	public String SuiviVirements(String idibuser) throws RemoteException {

		String queryString = "ENQUIRY.SELECT,," + Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company")+ ",BIAT.MB.HIST.VIR.ENQ,ID.IB.USER:EQ=" + idibuser;
		return this.doEnquiery(queryString);
	}
	public String getListeBenefIB(String client) throws RemoteException 
	{
		String queryString = "ENQUIRY.SELECT,,"	+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company")+ ",BIAT.MB.LISTE.BENEFICIAIRE.ENQ,STATUT:EQ=ACTIF,OWNING.CUSTOMER:EQ="+ client;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}
	public String addBeneficiary(String compte, String nickname, String shortname, String customer,String ownercustomer) throws RemoteException 
	{
		String queryString = "BENEFICIARY,BIAT.IB.SAISIE.MB/I/PROCESS,"+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("companyhammamet") + ",,NICKNAME::="+ nickname + ",CUSTOMER.REF::=" + shortname+ ",BEN.SHORT.NAME::=" + customer + ",OWNING.CUSTOMER::="+ ownercustomer;
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}	
	public String removeBeneficiary( String Id) throws RemoteException 
	{
		String queryString = "BENEFICIARY,BIAT.IB.DEL.MB/I/PROCESS,"+ Utility.getUserOFS() + "/" + Utility.getPwdOFS() + "/"+ Utility.getParameter("company") + "," + Id + ",";
		return this.parseTWSMessage(this.doEnquiery(queryString));
	}	
}