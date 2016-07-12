package com.Models.BIATNET_V1;

import java.util.ArrayList;
import java.util.List;

public class Customer 
{
    private String ID,Unknown_ID,Phone_Number,status,Password,Full_Name;
    private int Virement,Download;
    private List<Account> Accounts=new ArrayList<Account>();
    private List<Card> Cards=new ArrayList<Card>();
    private List<Transfer_History> Transfers_History=new ArrayList<Transfer_History>();
    
    public String Get_ID() {
        return ID;
    }

    public void Set_ID(String ID) {
        this.ID = ID;
    }

    public String Get_Unknown_ID() {
        return Unknown_ID;
    }

    public void Set_Unknown_ID(String Unknown_ID) {
        this.Unknown_ID = Unknown_ID;
    }
    
    public String Get_Phone_Number() {
        return Phone_Number;
    }

    public void Set_Phone_Number(String Phone_Number) {
        this.Phone_Number = Phone_Number;
    }

    public String Get_Status() {
        return status;
    }

    public void Set_Status(String status) {
        this.status = status;
    }

    public String Get_Password() {
        return Password;
    }

    public void Set_Password(String Password) {
        this.Password = Password;
    }

    public String Get_Full_Name() {
        return Full_Name;
    }

    public void Set_Full_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }

    public int Get_Virement() {
        return Virement;
    }

    public void Set_Virement(int Virement) {
        this.Virement = Virement;
    }

    public int Get_Download() {
        return Download;
    }

    public void Set_Download(int Download) {
        this.Download = Download;
    }
    
    public List<Account> Get_Accounts() {
        return Accounts;
    }

    public void Set_Accounts(List<Account> Accounts) {
        this.Accounts = Accounts;
    }

    public List<Card> Get_Cards() {
        return Cards;
    }

    public void Set_Cards(List<Card> Cards) {
        this.Cards = Cards;
    }
    public void Set_Transfers_History(List<Transfer_History> Transfers_History)
    {
    	this.Transfers_History=Transfers_History;
    }
    public List<Transfer_History> Get_Transfers_History()
    {
    	return this.Transfers_History;
    }


	@Override
	public String toString() {
		return "Customer [ID=" + ID + ", Unknown_ID=" + Unknown_ID + ", Phone_Number=" + Phone_Number + ", status="
				+ status + ", Password=" + Password + ", Full_Name=" + Full_Name + ", Virement=" + Virement
				+ ", Download=" + Download + ", Accounts=" + Accounts + ", Cards=" + Cards + "]";
	}
    
}
