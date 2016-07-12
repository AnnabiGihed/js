package com.Models.BIATNET_V1;

import java.util.ArrayList;
import java.util.List;

public class Governor
{
	private int ID;
	private String Name;
	private List<Distributor> Distributor_List=new ArrayList<Distributor>();
    private List<Agency> Agencies_List=new ArrayList<Agency>();
	
	public Governor()
    {
    }
	public Governor(int ID,String Name) 
	{
        this.Name = Name;
        this.ID=ID;
    }
	public int Get_ID() 
	{
		return ID;
	}
	public void Set_ID(int iD) 
	{
		ID = iD;
	}
    public String Get_Name() {
        return this.Name;
    }
    public void Set_Name(String Name)
    {
        this.Name = firstLetterCaps(Name);
    }
    public List<Agency> Get_Agencies_List() {
        return Agencies_List;
    }
    public void Add_Agency(Agency New_Agency)
    {
        Agencies_List.add(New_Agency);
    }
    public void Add_Distributor(Distributor New_Distributor)
    {
    	Distributor_List.add(New_Distributor);
    }
    public List<Distributor> Get_Distributor_List()
    {
    	return Distributor_List;
    }
    public void Reset_Agencies_List()
    {
    	this.Agencies_List=new ArrayList<Agency>();
    }
    public void Reset_Distributor_List()
    {
    	this.Distributor_List=new ArrayList<Distributor>();
    }
    public String firstLetterCaps ( String data )
    {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }
	@Override
	public String toString() {
		return "Governor [ID=" + ID + ", Name=" + Name + ", Distributor_List=" + Distributor_List + ", Agencies_List="
				+ Agencies_List + "]";
	}
}
