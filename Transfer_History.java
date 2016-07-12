package com.Models.BIATNET_V1;

public class Transfer_History 
{
    String Date,Amount,Transfer_ID,Status,Account_CTOS;
    String Beneficiary_NickName,Beneficiary_ID;

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

    public String Get_Transfer_ID() {
        return Transfer_ID;
    }

    public void Set_Transfer_ID(String Transfer_ID) {
        this.Transfer_ID = Transfer_ID;
    }

    public String Get_Status() {
        return Status;
    }

    public void Set_Status(String Status) {
        this.Status = Status;
    }

    public String Get_Account_CTOS() {
        return Account_CTOS;
    }

    public void Set_Account_CTOS(String Account_CTOS) {
        this.Account_CTOS = Account_CTOS;
    }

    public String Get_Beneficiary_NickName() {
        return Beneficiary_NickName;
    }

    public void Set_Beneficiary_NickName(String Beneficiary_NickName) {
        this.Beneficiary_NickName = Beneficiary_NickName;
    }

    public String Get_Beneficiary_ID() {
        return Beneficiary_ID;
    }

    public void Set_Beneficiary_ID(String Beneficiary_ID) {
        this.Beneficiary_ID = Beneficiary_ID;
    }

    @Override
    public String toString() {
        return "Transfer_History{" + "Date=" + Date + ", Amount=" + Amount + ", Transfer_ID=" + Transfer_ID + ", Status=" + Status + ", Account_CTOS=" + Account_CTOS + ", Beneficiary_NickName=" + Beneficiary_NickName + ", Beneficiary_ID=" + Beneficiary_ID + '}';
    }
}
