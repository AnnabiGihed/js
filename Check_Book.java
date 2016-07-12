package com.Models.BIATNET_V1;
public class Check_Book 
{

    String ID,Date_Request,Barred,Status;
    int Number_Of_Pages;
    
    public Check_Book() {
    }

    public String Get_ID() {
        return ID;
    }

    public void Set_ID(String ID) {
        this.ID = ID;
    }

    public String Get_Date_Request() {
        return Date_Request;
    }

    public void Set_Date_Request(String Date_Request) {
        this.Date_Request = Date_Request;
    }

    public String Get_Barred() {
        return Barred;
    }

    public void Set_Barred(String Barred) {
        this.Barred = Cap_First_Letter(Barred);
    }

    public String Get_Status() {
        return Status;
    }

    public void Set_Status(String Status) {
        this.Status = Status;
    }

    public int Get_Number_Of_Pages() {
        return Number_Of_Pages;
    }

    public void Set_Number_Of_Pages(int Number_Of_Pages) {
        this.Number_Of_Pages = Number_Of_Pages;
    }
    private String Cap_First_Letter (String data)
    {
	String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }

    @Override
    public String toString() {
        return "Check_Book{" + "ID=" + ID + ", Date_Request=" + Date_Request + ", Barred=" + Barred + ", Status=" + Status + ", Number_Of_Pages=" + Number_Of_Pages + '}';
    }
    
}
