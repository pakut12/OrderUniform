package com.pg.lib.model;


public class OUTransactionDepartmentDetail {
    
        private int docID;
        private int transactionID;
        private int departID;
        private int companyID;
        private int materialID;
        private String docName;
        private String agency;
        private String division;
        private String departmentname;
        private String materialname;
        private String materialcolor;
        private String materialdesc;
        private String materialsize;
        private String materialquantity;
        private String companyname;
        private String materialfullname;
        private String barcode;
        
    public OUTransactionDepartmentDetail(){
        super();
    }

    public OUTransactionDepartmentDetail(int docID, int transactionID, int departID, int companyID, int materialID, String docName, String agency, String division, String departmentname, String materialname, String materialcolor, String materialdesc, String materialsize, String materialquantity, String companyname, String materialfullname, String barcode) {
        this.docID = docID;
        this.transactionID = transactionID;
        this.departID = departID;
        this.companyID = companyID;
        this.materialID = materialID;
        this.docName = docName;
        this.agency = agency;
        this.division = division;
        this.departmentname = departmentname;
        this.materialname = materialname;
        this.materialcolor = materialcolor;
        this.materialdesc = materialdesc;
        this.materialsize = materialsize;
        this.materialquantity = materialquantity;
        this.companyname = companyname;
        this.materialfullname = materialfullname;
        this.barcode = barcode;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public int getDepartID() {
        return departID;
    }

    public void setDepartID(int departID) {
        this.departID = departID;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getDocID() {
        return docID;
    }

    public void setDocID(int docID) {
        this.docID = docID;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getMaterialcolor() {
        return materialcolor;
    }

    public void setMaterialcolor(String materialcolor) {
        this.materialcolor = materialcolor;
    }

    public String getMaterialdesc() {
        return materialdesc;
    }

    public void setMaterialdesc(String materialdesc) {
        this.materialdesc = materialdesc;
    }

    public String getMaterialfullname() {
        return materialfullname;
    }

    public void setMaterialfullname(String materialfullname) {
        this.materialfullname = materialfullname;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getMaterialquantity() {
        return materialquantity;
    }

    public void setMaterialquantity(String materialquantity) {
        this.materialquantity = materialquantity;
    }

    public String getMaterialsize() {
        return materialsize;
    }

    public void setMaterialsize(String materialsize) {
        this.materialsize = materialsize;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

}