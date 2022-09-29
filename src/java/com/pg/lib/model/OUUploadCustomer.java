/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.model;

/**
 *
 * @author 111525
 */
public class OUUploadCustomer {
    

    private String customer_id;
    private String pre_name;
    private String full_name;
    private String department;
    private String company;
    
    public OUUploadCustomer(){
        super();
    }
    
    public OUUploadCustomer(
                            String customer_id,
                            String pre_name,
                            String full_name,
                            String department,
                            String company){
        this.customer_id = customer_id;
        this.pre_name = pre_name;
        this.full_name = full_name;
        this.department = department;
        this.company = company;
    }
    

    public String getCustomerID(){
        return this.customer_id;
    }
    
    public void setCustomerID(String customerid){
        this.customer_id = customerid;
    }
    
    public String getPrename(){
        return this.pre_name;
    }
    
    public void setPrename(String prename){
        this.pre_name = prename;
    }
    
    public String getFullname(){
        return this.full_name;
    }
    
    public void setFullname(String fullname){
        this.full_name = fullname;
    }
    
    public String getDepartment(){
        return this.department;
    }
    
    public void setDepartment(String department){
        this.department = department;
    }
    
    public String getCompany(){
        return this.company;
    }
    
    public void setCompany(String company){
        this.company = company;
    }
}
