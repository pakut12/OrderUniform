package com.pg.lib.model;

//เหตุที่มี WithID เพราะตอนแรกไม่ได้คิดจะใช้ ID แต่ทำไปซักพักแล้วรู้สึกว่าใช้ เลยสร้างขึ้นมาใหม่ ไม่มีอะไร

import java.io.Serializable;

public class OUCustomerDetailWithID implements Serializable {
    
        private int cus_id;
        private String cus_no;
        private String cus_seq;
        private String cus_prename;
        private String cus_fname;
        private String cus_department;
        private int company_id;
    
    public OUCustomerDetailWithID(){
        super();
    }    
        
    public OUCustomerDetailWithID(int cus_id, String cus_no, String cus_seq, String cus_prename, String cus_fname, String cus_department, int company_id) {
        this.cus_id = cus_id;
        this.cus_no = cus_no;
        this.cus_seq = cus_seq;
        this.cus_prename = cus_prename;
        this.cus_fname = cus_fname;
        this.cus_department = cus_department;
        this.company_id = company_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCus_department() {
        return cus_department;
    }

    public void setCus_department(String cus_department) {
        this.cus_department = cus_department;
    }

    public String getCus_fname() {
        return cus_fname;
    }

    public void setCus_fname(String cus_fname) {
        this.cus_fname = cus_fname;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_no() {
        return cus_no;
    }

    public void setCus_no(String cus_no) {
        this.cus_no = cus_no;
    }

    public String getCus_prename() {
        return cus_prename;
    }

    public void setCus_prename(String cus_prename) {
        this.cus_prename = cus_prename;
    }

    public String getCus_seq() {
        return cus_seq;
    }

    public void setCus_seq(String cus_seq) {
        this.cus_seq = cus_seq;
    }
        
        
}
