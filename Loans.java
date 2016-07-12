package com.Models.BIATNET_V1;

public class Loans 
{
	private String Montant_de_la_tranche,Montant_Remboursement,Taux;        
	private String Periodicite_Interets,DateDeblocage;
	private String Duree_Tranche,Duree;
	private String Periodicite_Principal,Periodicite;
	private String PremiereEcheancePrincipal,PremiereEcheanceInterets,PremiereEcheance;
	public String Get_Montant_de_la_tranche() {
		return Montant_de_la_tranche;
	}
	public void Set_Montant_de_la_tranche(String montant_de_la_tranche) {
		Montant_de_la_tranche = montant_de_la_tranche;
	}
	public String Get_Montant_Remboursement() {
		return Montant_Remboursement;
	}
	public void Set_Montant_Remboursement(String montant_Remboursement) {
		Montant_Remboursement = montant_Remboursement;
	}
	public String Get_Taux() {
		return Taux;
	}
	public void Set_Taux(String taux) {
		Taux = taux;
	}
	public String Get_Periodicite_Interets() {
		return Periodicite_Interets;
	}
	public void Set_Periodicite_Interets(String periodicite_Interets) {
		Periodicite_Interets = periodicite_Interets;
	}
	public String Get_DateDeblocage() {
		return DateDeblocage;
	}
	public void Set_DateDeblocage(String dateDeblocage) {
		DateDeblocage = dateDeblocage;
	}
	public String Get_Duree_Tranche() {
		return Duree_Tranche;
	}
	public void Set_Duree_Tranche(String duree_Tranche) {
		Duree_Tranche = duree_Tranche;
	}
	public String Get_Duree() {
		return Duree;
	}
	public void Set_Duree(String duree) {
		Duree = duree;
	}
	public String Get_Periodicite_Principal() {
		return Periodicite_Principal;
	}
	public void Set_Periodicite_Principal(String periodicite_Principal) {
		Periodicite_Principal = periodicite_Principal;
	}
	public String Get_Periodicite() {
		return Periodicite;
	}
	public void Set_Periodicite(String periodicite) {
		Periodicite = periodicite;
	}
	public String Get_PremiereEcheancePrincipal() {
		return PremiereEcheancePrincipal;
	}
	public void Set_PremiereEcheancePrincipal(String premiereEcheancePrincipal) {
		PremiereEcheancePrincipal = premiereEcheancePrincipal;
	}
	public String Get_PremiereEcheanceInterets() {
		return PremiereEcheanceInterets;
	}
	public void Set_PremiereEcheanceInterets(String premiereEcheanceInterets) {
		PremiereEcheanceInterets = premiereEcheanceInterets;
	}
	public String Get_PremiereEcheance() {
		return PremiereEcheance;
	}
	public void Set_PremiereEcheance(String premiereEcheance) {
		PremiereEcheance = premiereEcheance;
	}
	@Override
	public String toString() {
		return "Loans [Montant_de_la_tranche=" + Montant_de_la_tranche + ", Montant_Remboursement="
				+ Montant_Remboursement + ", Taux=" + Taux + ", Periodicite_Interets=" + Periodicite_Interets
				+ ", DateDeblocage=" + DateDeblocage + ", Duree_Tranche=" + Duree_Tranche + ", Duree=" + Duree
				+ ", Periodicite_Principal=" + Periodicite_Principal + ", Periodicite=" + Periodicite
				+ ", PremiereEcheancePrincipal=" + PremiereEcheancePrincipal + ", PremiereEcheanceInterets="
				+ PremiereEcheanceInterets + ", PremiereEcheance=" + PremiereEcheance + "]";
	}
	
	
	
}
