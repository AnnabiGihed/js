package com.Models.BIATNET_V1;

public class Transfer
{
    String Sender_Account_ID,Reciver_Account_ID,Currency,Phone_Number,BIATNet_Account_ID,Password,Status,Amount,Transfer_ID,User_Code_Validation;

    public String Get_Sender_Account_ID() {
        return Sender_Account_ID;
    }
    public void Set_Sender_Account_ID(String Sender_Account_ID) {
        this.Sender_Account_ID = Sender_Account_ID;
    }
    public String Get_Reciver_Account_ID() {
        return Reciver_Account_ID;
    }
    public void Set_Reciver_Account_ID(String Reciver_Account_ID) {
        this.Reciver_Account_ID = Reciver_Account_ID;
    }
    public String Get_Currency() {
        return Currency;
    }
    public void Set_Currency(String Currency) {
        this.Currency = Currency;
    }
    public String Get_Phone_Number() {
        return Phone_Number;
    }
    public void Set_Phone_Number(String Phone_Number) {
        this.Phone_Number = Phone_Number;
    }
    public String Get_BIATNet_Account_ID() {
        return BIATNet_Account_ID;
    }
    public void Set_BIATNet_Account_ID(String BIATNet_Account_ID) {
        this.BIATNet_Account_ID = BIATNet_Account_ID;
    }
    public String Get_Password() {
        return Password;
    }
    public void Set_Password(String Password) {
        this.Password = Password;
    }
    public String Get_Status() {
        return Status;
    }
    public void Set_Status(String Status) {
        this.Status = Status;
    }
    public String Get_Amount() {
        return Amount;
    }
    public void Set_Amount(String Amount)
    {
        this.Amount = Amount;
    }
    public String Get_Transfer_ID() {
        return Transfer_ID;
    }
    public void Set_Transfer_ID(String Transfer_ID) {
        this.Transfer_ID = Transfer_ID;
    }
    public String Get_User_Code_Validation() {
        return User_Code_Validation;
    }
    public void Set_User_Code_Validation(String User_Code_Validation) {
        this.User_Code_Validation = User_Code_Validation;
    }
}
