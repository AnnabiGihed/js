package com.Models.BIATNET_V1;

import java.util.List;

public class Withdrawal_History 
{
    private String Withdrawal_Beginning_Date,Balance_Amount_Before_Withdrawal;
    private String Account_Owner,Account_Identifier,Currency;
    private String Withdrawal_End_Date,Balance_Amount_After_Withdrawal;
    private String Balance_Available_Date,Available_Balance_Amount;
    private String Erreur;
    private List<Withdrawal> Withdrawal_List;
    
    public String Get_Withdrawal_Beginning_Date() {
        return Withdrawal_Beginning_Date;
    }
    public void Set_Withdrawal_Beginning_Date(String Withdrawal_Beginning_Date) {
        this.Withdrawal_Beginning_Date = Withdrawal_Beginning_Date;
    }
    public String Get_Balance_Amount_Before_Withdrawal() {
        return Balance_Amount_Before_Withdrawal;
    }
    public void Set_Balance_Amount_Before_Withdrawal(String Balance_Amount_Before_Withdrawal) {
        this.Balance_Amount_Before_Withdrawal = Balance_Amount_Before_Withdrawal;
    }
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
    public String Get_Withdrawal_End_Date() {
        return Withdrawal_End_Date;
    }
    public void Set_Withdrawal_End_Date(String Withdrawal_End_Date) {
        this.Withdrawal_End_Date = Withdrawal_End_Date;
    }
    public String Get_Balance_Amount_After_Withdrawal() {
        return Balance_Amount_After_Withdrawal;
    }
    public void Set_Balance_Amount_After_Withdrawal(String Balance_Amount_After_Withdrawal) {
        this.Balance_Amount_After_Withdrawal = Balance_Amount_After_Withdrawal;
    }
    public String Get_Balance_Available_Date() {
        return Balance_Available_Date;
    }
    public void Set_Balance_Available_Date(String Balance_Available_Date) {
        this.Balance_Available_Date = Balance_Available_Date;
    }
    public String Get_Available_Balance_Amount() {
        return Available_Balance_Amount;
    }
    public void Set_Available_Balance_Amount(String Available_Balance_Amount) {
        this.Available_Balance_Amount = Available_Balance_Amount;
    }
    public List<Withdrawal> Get_Withdrawal_List() {
        return Withdrawal_List;
    }
    public void Set_Withdrawal_List(List<Withdrawal> Withdrawal_List) {
        this.Withdrawal_List = Withdrawal_List;
    }
    public String Get_Erreur() {
        return Erreur;
    }
    public void Set_Erreur(String Erreur) {
        this.Erreur = Erreur;
    }
	@Override
	public String toString() {
		return "Withdrawal_History [Withdrawal_Beginning_Date=" + Withdrawal_Beginning_Date
				+ ", Balance_Amount_Before_Withdrawal=" + Balance_Amount_Before_Withdrawal + ", Account_Owner="
				+ Account_Owner + ", Account_Identifier=" + Account_Identifier + ", Currency=" + Currency
				+ ", Withdrawal_End_Date=" + Withdrawal_End_Date + ", Balance_Amount_After_Withdrawal="
				+ Balance_Amount_After_Withdrawal + ", Balance_Available_Date=" + Balance_Available_Date
				+ ", Available_Balance_Amount=" + Available_Balance_Amount + ", Erreur=" + Erreur + ", Withdrawal_List="
				+ Withdrawal_List + "]";
	}
    
}