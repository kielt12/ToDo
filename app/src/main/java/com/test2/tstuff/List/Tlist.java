package com.test2.tstuff.List;

public class Tlist {

    private String Tlist;
    private int quantity;
    private int ID;

    public Tlist(String Tlist, int quantity, int ID) {
        this.Tlist = Tlist;
        this.quantity = quantity;
        this.ID = ID;
    }

    public Tlist() {
    }

    public String getTlist() {
        return Tlist;
    }

    public void setTlist(String tlist) {
        Tlist = tlist;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
