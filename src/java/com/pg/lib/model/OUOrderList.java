package com.pg.lib.model;


public class OUOrderList {
    
        private int customerId;
        private int companyId;
        private int materialId;
        private String materialfullname;
        private String size;
        private String quantity;
        
    public OUOrderList(){
        super();
    }

    public OUOrderList(int customerId, int companyId, int materialId, String materialfullname, String size, String quantity) {
        this.customerId = customerId;
        this.companyId = companyId;
        this.materialId = materialId;
        this.materialfullname = materialfullname;
        this.size = size;
        this.quantity = quantity;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
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
