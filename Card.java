package com.Models.BIATNET_V1;
public class Card 
{
    String Number,Owner,Type,ID_Cout;

    public String Get_Number() {
        return Number;
    }

    public void Set_Number(String Number) {
        this.Number = Number;
    }

    public String Get_Owner() {
        return Owner;
    }

    public void Set_Owner(String Owner) {
        this.Owner = Owner;
    }

    public String Get_Type() {
        return Type;
    }

    public void Set_Type(String Type) {
        this.Type = Type;
    }

    public String Get_ID_Cout() {
        return ID_Cout;
    }

    public void Set_ID_Cout(String ID_Cout) {
        this.ID_Cout = ID_Cout;
    }

    @Override
    public String toString() {
        return "Card{" + "Number=" + Number + ", Owner=" + Owner + ", Type=" + Type + ", ID_Cout=" + ID_Cout + '}';
    }
    
    
}
