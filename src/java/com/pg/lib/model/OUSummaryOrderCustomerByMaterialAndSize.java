package com.pg.lib.model;


public class OUSummaryOrderCustomerByMaterialAndSize {
    private String materialid;
    private String materialname;
    private String materialdesc;
    private String materialsize;
    private String quantitySummary;
    
    public OUSummaryOrderCustomerByMaterialAndSize(){
        super();
    }

    public OUSummaryOrderCustomerByMaterialAndSize(String materialid, String materialname,String materialdesc, String materialsize, String quantitySummary) {
        this.materialid = materialid;
        this.materialname = materialname;
        this.materialdesc = materialdesc;
        this.materialsize = materialsize;
        this.quantitySummary = quantitySummary;
    }

    public String getMaterialid() {
        return materialid;
    }

    public void setMaterialid(String materialid) {
        this.materialid = materialid;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getMaterialsize() {
        return materialsize;
    }

    public void setMaterialsize(String materialsize) {
        this.materialsize = materialsize;
    }

    public String getQuantitySummary() {
        return quantitySummary;
    }

    public void setQuantitySummary(String quantitySummary) {
        this.quantitySummary = quantitySummary;
    }

    public String getMaterialdesc() {
        return materialdesc;
    }

    public void setMaterialdesc(String materialdesc) {
        this.materialdesc = materialdesc;
    }
    
}
