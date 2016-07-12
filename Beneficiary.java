package com.Models.BIATNET_V1;

public class Beneficiary 
{
	String Useless,Identifier,NickName,Note,Beneficiary_Account_Identifier,BIATNet_Customer_ID;
	public Beneficiary() 
	{
		this.Useless="Useless";
	}
	public String Get_Useless() {
		return Useless;
	}
	public void Set_Useless(String useless) {
		Useless = useless;
	}
	public String Get_Identifier() {
		return Identifier;
	}
	public void Set_Identifier(String identifier) {
		Identifier = identifier;
	}
	public String Get_NickName() {
		return NickName;
	}
	public void Set_NickName(String NickName) {
		this.NickName = NickName;
	}
	public String Get_Note() {
		return Note;
	}
	public void Set_Note(String note) {
		Note = note;
	}
	public String Get_Beneficiary_Account_Identifier() {
		return Beneficiary_Account_Identifier;
	}
	public void Set_Beneficiary_Account_Identifier(String beneficiary_Account_Identifier) {
		Beneficiary_Account_Identifier = beneficiary_Account_Identifier;
	}
	public String Get_BIATNet_Customer_ID() {
		return BIATNet_Customer_ID;
	}
	public void Set_BIATNet_Customer_ID(String bIATNet_Customer_ID) {
		BIATNet_Customer_ID = bIATNet_Customer_ID;
	}

    @Override
    public String toString() {
        return "Beneficiary{" + "Useless=" + Useless + ", Identifier=" + Identifier + ", NickName=" + NickName + ", Note=" + Note + ", Beneficiary_Account_Identifier=" + Beneficiary_Account_Identifier + ", BIATNet_Customer_ID=" + BIATNet_Customer_ID + '}';
    }
}
