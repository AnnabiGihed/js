package com.Models.BIATNET_V1;

public class Operation 
{
	String Type,Name,Date_Valeur,Reference,Date,Amount;
    public String Get_Type() {
        return Type;
    }
    public void Set_Type(String Type) {
        this.Type = Type;
    }
    public String Get_Name() {
        return Name;
    }
    public void Set_Name(String Name) {
        this.Name = Name;
    }
    public String Get_Date_Valeur() {
        return Date_Valeur;
    }
    public void Set_Date_Valeur(String Date_Valeur) {
        this.Date_Valeur = Date_Valeur;
    }
    public String Get_Reference() {
        return Reference;
    }
    public void Set_Reference(String Reference) {
        this.Reference = Reference;
    }
    public String Get_Date() {
        return Date;
    }
    public void Set_Date(String Date) {
        this.Date = Date;
    }
    public String Get_Amount() {
        return Amount;
    }
    public void Set_Amount(String Amount) {
        this.Amount = Amount;
    }
    @Override
    public String toString() {
        return "Operation{" + "Type=" + Type + ", Name=" + Name + ", Date_Valeur=" + Date_Valeur + ", Reference=" + Reference + ", Date=" + Date + ", Amount=" + Amount + '}';
    }
    
}
