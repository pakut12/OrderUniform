/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.model;

/**
 *
 * @author 111525
 */
public class OUCustomerDetail {
    
    private String cus_no;
    private String cus_seq;
    private String cus_prename;
    private String cus_fname;
    private String cus_department;
    private String cus_company;
    
    public OUCustomerDetail(){
        super();
    }
    
    public OUCustomerDetail(
                            String cus_no,
                            String cus_seq,
                            String cus_prename,
                            String cus_fname,
                            String cus_department,
                            String cus_company
                            ){
        this.cus_no = cus_no;
        this.cus_seq = cus_seq;
        this.cus_prename = cus_prename;
        this.cus_fname = cus_fname;
        this.cus_department = cus_department;
        this.cus_company = cus_company;
    }
    
    public String getCusNo(){
        return this.cus_no;
    }
    
    public void setCusNo(String cus_no){
        this.cus_no = cus_no;
    }
    
    public String getCusSeq(){
        return this.cus_seq;
    }
    
    public void setCusSeq(String cus_seq){
        this.cus_seq = cus_seq;
    }
    
    public String getCusPreName(){
        return this.cus_prename;
    }
    
    public void setCusPreName(String cus_prename){
        this.cus_prename = cus_prename;
    }
    
    public String getCusFName(){
        return this.cus_fname;
    }
    
    public void setCusFName(String cus_fname){
        this.cus_fname = cus_fname;
    }
    
    public String getCusDepartmet(){
        return this.cus_department;
    }
    
    public void setCusDepartment(String cus_department){
        this.cus_department = cus_department;
    }
    
    public String getCusCompany(){
        return this.cus_company;
    }
    
    public void setCusCompany(String cus_company){
        this.cus_company = cus_company;
    }
}
