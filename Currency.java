package com.Models.BIATNET_V1;

public class Currency 
{
	private int ID;
    private String Name;
    private Double Buy_Rate,Sell_Rate;
    public Currency()
    {
    }
    public int Get_ID() 
    {
        return ID;
    }
    public void Set_ID(int ID) 
    {
        this.ID = ID;
    }
    public void Set_Name(String Name)
    {
        this.Name=Name;
    }
    public String Get_Name()
    {
        return this.Name;
    }
    public Double Get_Buy_Rate() 
    {
        return Buy_Rate;
    }
    public void Set_Buy_Rate(Double Buy_Rate) 
    {
        this.Buy_Rate = Buy_Rate;
    }
    public Double Get_Sell_Rate() 
    {
        return Sell_Rate;
    }
    public void Set_Sell_Rate(Double Sell_Rate) 
    {
	this.Sell_Rate = Sell_Rate;
    }
	@Override
	public String toString() {
		return "Currency [ID=" + ID + ", Name=" + Name + ", Buy_Rate=" + Buy_Rate + ", Sell_Rate=" + Sell_Rate + "]";
	}
    
    
}
