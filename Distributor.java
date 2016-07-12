package com.Models.BIATNET_V1;

public class Distributor  
{
    private int ID;
    private String Libelle,Address,Governor;
    private double Longitude,Latitude;

    public Distributor()
    {

    }
    public int Get_ID() {
        return ID;
    }

    public void Set_ID(int ID) {
        this.ID = ID;
    }
    public String Get_Libelle() {
        return Libelle;
    }

    public void Set_Libelle(String Libelle) {
        this.Libelle = Libelle;
    }

    public String Get_Address() {
        return Address;
    }

    public void Set_Address(String Address) {
        this.Address = Address;
    }

    public String Get_Governor() {
        return Governor;
    }

    public void Set_Governor(String Governor) {
        this.Governor = Governor;
    }

    public double Get_Longitude() {
        return Longitude;
    }

    public void Set_Longitude(double Longitude) {
        this.Longitude = Longitude;
    }

    public double Get_Latitude() {
        return Latitude;
    }

    public void Set_Latitude(double Latitude) {
        this.Latitude = Latitude;
    }

    @Override
    public String toString() {
        return "Distributor{" + "ID=" + ID + ", Libelle=" + Libelle + ", Address=" + Address + ", Governor=" + Governor + ", Longitude=" + Longitude + ", Latitude=" + Latitude + '}';
    }
}
