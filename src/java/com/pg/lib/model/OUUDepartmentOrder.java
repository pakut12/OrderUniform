
package com.pg.lib.model;

import java.util.List;


public class OUUDepartmentOrder {
    
    private int id ;
    private String agency;
    private String division;
    private String department;
    private int company_id;
    private List<OUUItemOrder> item;
            
    public OUUDepartmentOrder(){
        super();
    }

    public OUUDepartmentOrder(int id, String agency, String division, String department, int company_id, List<OUUItemOrder> item) {
        this.id = id;
        this.agency = agency;
        this.division = division;
        this.department = department;
        this.company_id = company_id;
        this.item = item;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OUUItemOrder> getItem() {
        return item;
    }

    public void setItem(List<OUUItemOrder> item) {
        this.item = item;
    }
    
    
}
