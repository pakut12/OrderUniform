package com.pg.lib.model;

public class OUSummaryOrderByCustomer {

    private String customerid;
    private String customerno;
    private String customerprename;
    private String customername;
    private String quantity;
    private String departmentname;

    public OUSummaryOrderByCustomer() {
        super();
    }

    public OUSummaryOrderByCustomer(String customerid, String customerno, String customerprename, String customername, String quantity,String departmentname) {
        this.customerid = customerid;
        this.customerno = customerno;
        this.customerprename = customerprename;
        this.customername = customername;
        this.quantity = quantity;
        this.departmentname = departmentname;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getCustomerno() {
        return customerno;
    }

    public void setCustomerno(String customerno) {
        this.customerno = customerno;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerprename() {
        return customerprename;
    }

    public void setCustomerprename(String customerprename) {
        this.customerprename = customerprename;
    }
}
