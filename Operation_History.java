package com.Models.BIATNET_V1;

import java.util.List;

public class Operation_History 
{
	private String Account_Owner,Account_Identifier,Currency;
    private String Erreur;
    private List<Operation> Operation_List;
    public String Get_Account_Owner() {
        return Account_Owner;
    }
    public void Set_Account_Owner(String Account_Owner) {
        this.Account_Owner = Account_Owner;
    }
    public String Get_Account_Identifier() {
        return Account_Identifier;
    }
    public void Set_Account_Identifier(String Account_Identifier) {
        this.Account_Identifier = Account_Identifier;
    }
    public String Get_Currency() {
        return Currency;
    }
    public void Set_Currency(String Currency) {
        this.Currency = Currency;
    }
    public List<Operation> Get_Operation_List() {
        return Operation_List;
    }
    public void Set_Operation_List(List<Operation> Operation_List) {
        this.Operation_List = Operation_List;
    }
    public String Get_Erreur() {
        return Erreur;
    }
    public void Set_Erreur(String Erreur) {
        this.Erreur = Erreur;
    }
	@Override
	public String toString() {
		return "Operation_History [Account_Owner=" + Account_Owner + ", Account_Identifier=" + Account_Identifier
				+ ", Currency=" + Currency + ", Erreur=" + Erreur + ", Operation_List=" + Operation_List + "]";
	}
	
}
