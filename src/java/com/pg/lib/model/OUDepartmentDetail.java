/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.model;

/**
 *
 * @author 111525
 */
public class OUDepartmentDetail {
    
    private String depart_seq;
    private String depart_agency;
    private String depart_division;
    private String depart_name;
    private String depart_company;
            
    public OUDepartmentDetail(){
        super();
    }
    
    public OUDepartmentDetail(
                               String depart_seq,
                               String depart_agency,
                               String depart_division,
                               String depart_name,
                               String depart_company
                              ){
        this.depart_seq = depart_seq;
        this.depart_agency  = depart_agency;
        this.depart_division = depart_division;
        this.depart_name = depart_name;
        this.depart_company = depart_company;
    }

    public String getDepart_agency() {
        return depart_agency;
    }

    public String getDepart_company() {
        return depart_company;
    }

    public String getDepart_division() {
        return depart_division;
    }

    public String getDepart_name() {
        return depart_name;
    }

    public String getDepart_seq() {
        return depart_seq;
    }

    public void setDepart_agency(String depart_agency) {
        this.depart_agency = depart_agency;
    }

    public void setDepart_company(String depart_company) {
        this.depart_company = depart_company;
    }

    public void setDepart_division(String depart_division) {
        this.depart_division = depart_division;
    }

    public void setDepart_name(String depart_name) {
        this.depart_name = depart_name;
    }

    public void setDepart_seq(String depart_seq) {
        this.depart_seq = depart_seq;
    }
    
}
