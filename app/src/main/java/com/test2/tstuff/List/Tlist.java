package com.test2.tstuff.List;

public class Tlist {

    private String Tlist;
    private int quantity;

    public Tlist(String Tlist, int quantity) {
        this.Tlist = Tlist;
        this.quantity = quantity;
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
}
