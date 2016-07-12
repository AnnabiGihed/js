package com.Models.BIATNET_V1;
public class Agency 
{
	 private int ID;
	 private String Name,Address,Governor,Phone_Number,Fax_Number;
	 private double Longitude,Latitude;
	 
	 public Agency()
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
	    
	 public String Get_Phone_Number() 
	 {
		 return Phone_Number;
	 }
	 public void Set_Phone_Number(String Phone_Number) 
	 {
		 this.Phone_Number = Phone_Number;
	 }
	 public String Get_Fax_Number() 
	 {
		 return Fax_Number;
	 }
	 public void Set_Fax_Number(String Fax_Number) 
	 {
		 this.Fax_Number = Fax_Number;
	 }   
	 public String Get_Name() 
	 {
		 return Name;
	 }
	 public void Set_Name(String Name) 
	 {
		 this.Name =Name;
	 }
	 public String Get_Address() 
	 {
		 return Address;
	 }
	 public void Set_Address(String Address) 
	 {
		 this.Address = Address;
	 }
	 public String Get_Governor() 
	 {
		 return Governor;
	 }
	 public void Set_Governor(String Governor) 
	 {
		 this.Governor = Governor;
	 }
	 public double Get_Longitude() 
	 {
		 return Longitude;
	 }
	 public void Set_Longitude(double Longitude) 
	 {
		 this.Longitude = Longitude;
	 }
	 public double Get_Latitude() 
	 { 
		 return Latitude;
	 }
	 public void Set_Latitude(double Latitude) 
	 {
		 this.Latitude = Latitude;
	 }
	 @Override
	 public String toString() 
	 {
		 return "Agency{" + "Id=" + ID + ", Name=" + Name + ", Address=" + Address + ", Governor=" + Governor + ", Phone_Number=" + Phone_Number + ", Fax_Number=" + Fax_Number + ", Longitude=" + Longitude + ", Latitude=" + Latitude + '}';
	 }
	 public String firstLetterCaps ( String data )
	 {
		 String firstLetter = data.substring(0,1).toUpperCase();
		 String restLetters = data.substring(1).toLowerCase();
		 return firstLetter + restLetters;
	 }
}
