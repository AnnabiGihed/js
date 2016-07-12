package com.Models.BIATNET_V1;

import java.util.ArrayList;
import java.util.List;

public class Account 
{
    String Identifier,Fonction,Account_CTOS,Owner,Last_Operation_Date,Devise,Countable_Balance,Available_Balance;
    List<Check_Book> Check_Books=new ArrayList<Check_Book>();

    public Account() 
    {
        
    }
    
    public String Get_Identifier() {
        return Identifier;
    }

    public void Set_Identifier(String Identifier) {
        this.Identifier = Identifier;
    }

    public String Get_Fonction() {
        return Fonction;
    }

    public void Set_Fonction(String Fonction) {
        this.Fonction = Fonction;
    }

    public String Get_Account_CTOS() {
        return Account_CTOS;
    }

    public void Set_Account_CTOS(String Account_CTOS) {
        this.Account_CTOS = Account_CTOS;
    }

    public String Get_Owner() {
        return Owner;
    }

    public void Set_Owner(String Owner) {
        this.Owner = Owner;
    }

    public String Get_Last_Operation_Date() {
        return Last_Operation_Date;
    }

    public void Set_Last_Operation_Date(String Last_Operation_Date) {
        this.Last_Operation_Date = Last_Operation_Date;
    }

    public String Get_Devise() {
        return Devise;
    }

    public void Set_Devise(String Devise) {
        this.Devise = Devise;
    }

    public String Get_Countable_Balance() {
        return Countable_Balance;
    }

    public void Set_Countable_Balance(String Countable_Balance) {
        this.Countable_Balance = Countable_Balance;
    }

    public String Get_Available_Balance() {
        return Available_Balance;
    }

    public void Set_Available_Balance(String Available_Balance) {
        this.Available_Balance = Available_Balance;
    }

    public List<Check_Book> Get_Check_Books() {
        return Check_Books;
    }

    public void Set_Check_Books(List<Check_Book> Check_Books) {
        this.Check_Books = Check_Books;
    }
    
    @Override
    public String toString() {
        return "\n Account{" + "Account_Identifier=" + Identifier + ", Fonction=" + Fonction + ", Account_CTOS=" + Account_CTOS + ", Owner=" + Owner + ", Last_Operation_Date=" + Last_Operation_Date + ", Devise=" + Devise + ", Countable_Balance=" + Countable_Balance + ", Available_Balance=" + Available_Balance + ", Check_Books=" + Check_Books + '}';
    }
    
}
