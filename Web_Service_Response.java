package com.Models.BIATNET_V1;

public class Web_Service_Response 
{
	private String Echeance,Nombre_De_Jours,Encours,Amortissement,Interests;

	public String Get_Echeance() {
		return Echeance;
	}

	public void Set_Echeance(String echeance) {
		Echeance = echeance;
	}

	public String Get_Nombre_De_Jours() {
		return Nombre_De_Jours;
	}

	public void Set_Nombre_De_Jours(String nombre_De_Jours) {
		Nombre_De_Jours = nombre_De_Jours;
	}

	public String Get_Encours() {
		return Encours;
	}

	public void Set_Encours(String encours) {
		Encours = encours;
	}

	public String Get_Amortissement() {
		return Amortissement;
	}

	public void Set_Amortissement(String amortissement) {
		Amortissement = amortissement;
	}

	public String Get_Interests() {
		return Interests;
	}

	public void Set_Interests(String interests) {
		Interests = interests;
	}

	@Override
	public String toString() {
		return "Web_Service_Response [Echeance=" + Echeance + ", Nombre_De_Jours=" + Nombre_De_Jours + ", Encours="
				+ Encours + ", Amortissement=" + Amortissement + ", Interests=" + Interests + "]";
	}
	
}