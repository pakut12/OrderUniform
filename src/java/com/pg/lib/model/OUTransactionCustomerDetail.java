package com.pg.lib.model;


public class OUTransactionCustomerDetail {
    private int docID;
    private int transactionID;
    private int customerID;
    private int companyID;
    private int materialID;
    private String docName;
    private String prename;
    private String fname;
    private String customerCode;
    private String departmentname;
    private String materialname;
    private String materialdesc;
    private String color;
    private String desc;
    private String size;
    private String quantity;
    private String companyname;
    private String matfullname;
    private String barcode;
    
    public OUTransactionCustomerDetail(){
        super();
    }

    public OUTransactionCustomerDetail(int docID, int transactionID, int customerID, int companyID, int materialID, String docName, String prename, String fname, String customerCode, String departmentname, String materialname, String materialdesc, String color, String desc, String size, String quantity, String companyname, String matfullname, String barcode) {
        this.docID = docID;
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.companyID = companyID;
        this.materialID = materialID;
        this.docName = docName;
        this.prename = prename;
        this.fname = fname;
        this.customerCode = customerCode;
        this.departmentname = departmentname;
        this.materialname = materialname;
        this.materialdesc = materialdesc;
        this.color = color;
        this.desc = desc;
        this.size = size;
        this.quantity = quantity;
        this.companyname = companyname;
        this.matfullname = matfullname;
        this.barcode = barcode;
    }

    
    
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMatfullname() {
        return matfullname;
    }

    public void setMatfullname(String matfullname) {
        this.matfullname = matfullname;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getMaterialdesc() {
        return materialdesc;
    }

    public void setMaterialdesc(String materialdesc) {
        this.materialdesc = materialdesc;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    
    
    
}
