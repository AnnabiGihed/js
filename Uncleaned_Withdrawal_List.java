package com.Models.BIATNET_V1;

import java.util.List;

public class Uncleaned_Withdrawal_List 
{
	int ID;
    List<String> Informations;

    public int Get_ID() {
        return ID;
    }
    public void Set_ID(int ID) {
        this.ID = ID;
    }
    public List<String> Get_Informations() {
        return Informations;
    }
    public void Set_Informations(List<String> Informations) {
        this.Informations = Informations;
    }
    @Override
    public String toString() {
        return "uncleaned_List{" + "ID=" + ID + ", Informations=" + Informations + '}';
    }
}
