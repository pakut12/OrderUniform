package com.pg.lib.model;

//เหตุที่มี WithID เพราะตอนแรกไม่ได้คิดจะใช้ ID แต่ทำไปซักพักแล้วรู้สึกว่าใช้ เลยสร้างขึ้นมาใหม่ ไม่มีอะไร

import java.io.Serializable;

public class OUDepartmentDetailWithID implements Serializable {
    
    private int depart_id ; 
    private String depart_seq;
    private String depart_agency;
    private String depart_division;
    private String depart_name;
    private int company_id;
    
    public OUDepartmentDetailWithID(){
        super();
    }

    public OUDepartmentDetailWithID(int depart_id, String depart_seq, String depart_agency, String depart_division, String depart_name, int company_id) {
        this.depart_id = depart_id;
        this.depart_seq = depart_seq;
        this.depart_agency = depart_agency;
        this.depart_division = depart_division;
        this.depart_name = depart_name;
        this.company_id = company_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getDepart_agency() {
        return depart_agency;
    }

    public void setDepart_agency(String depart_agency) {
        this.depart_agency = depart_agency;
    }

    public String getDepart_division() {
        return depart_division;
    }

    public void setDepart_division(String depart_division) {
        this.depart_division = depart_division;
    }

    public int getDepart_id() {
        return depart_id;
    }

    public void setDepart_id(int depart_id) {
        this.depart_id = depart_id;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public String getDepart_seq() {
        return depart_seq;
    }

    public void setDepart_seq(String depart_seq) {
        this.depart_seq = depart_seq;
    }
    
    
}
