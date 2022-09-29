package com.pg.lib.model;


public class OUUItemOrder {
    
        private int matid ;
        private String matcode;
        private String size;
        private String qty;

    public OUUItemOrder(int matid, String matcode, String size, String qty) {
        this.matid = matid;
        this.matcode = matcode;
        this.size = size;
        this.qty = qty;
    }

    public OUUItemOrder(){
        super();
    }

    public String getMatcode() {
        return matcode;
    }

    public void setMatcode(String matcode) {
        this.matcode = matcode;
    }

    public int getMatid() {
        return matid;
    }

    public void setMatid(int matid) {
        this.matid = matid;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    
}
