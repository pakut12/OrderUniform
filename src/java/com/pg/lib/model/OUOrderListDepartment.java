package com.pg.lib.model;

public class OUOrderListDepartment {

    private int depart_id;
    private int companyID;
    private int materialID;
    private String materialfullname;
    private String size;
    private String quantity;
    private String matCode;

    public OUOrderListDepartment() {
        super();
    }

    public OUOrderListDepartment(int depart_id, int companyID, int materialID, String materialfullname, String size, String quantity, String matCode) {
        this.depart_id = depart_id;
        this.companyID = companyID;
        this.materialID = materialID;
        this.materialfullname = materialfullname;
        this.size = size;
        this.quantity = quantity;
        this.matCode = matCode;
    }

    public String getmatCode() {
        return matCode;
    }

    public void setmatCode(String matCode) {
        this.matCode = matCode;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(int depart_id) {
        this.depart_id = depart_id;
    }

    public int getMaterialID() {
        return materialID;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public String getMaterialfullname() {
        return materialfullname;
    }

    public void setMaterialfullname(String materialfullname) {
        this.materialfullname = materialfullname;
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
}
