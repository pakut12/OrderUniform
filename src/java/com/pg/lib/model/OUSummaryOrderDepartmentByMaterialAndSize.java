package com.pg.lib.model;


public class OUSummaryOrderDepartmentByMaterialAndSize {
        private int materialid ;
        private String materialname; 
        private String materialdesc;
        private String materialsize;
        private String summary;

    public OUSummaryOrderDepartmentByMaterialAndSize(){
        super();
    }    
        
    public OUSummaryOrderDepartmentByMaterialAndSize(int materialid, String materialname, String materialdesc, String materialsize, String summary) {
        this.materialid = materialid;
        this.materialname = materialname;
        this.materialdesc = materialdesc;
        this.materialsize = materialsize;
        this.summary = summary;
    }
    
    public String getMaterialdesc() {
        return materialdesc;
    }

    public void setMaterialdesc(String materialdesc) {
        this.materialdesc = materialdesc;
    }

    public int getMaterialid() {
        return materialid;
    }

    public void setMaterialid(int materialid) {
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
        
        
}
