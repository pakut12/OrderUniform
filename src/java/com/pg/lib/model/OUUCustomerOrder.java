/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.model;

import java.util.List;

/**
 *
 * @author 111525
 */
public class OUUCustomerOrder {
    
    private int id ;
    private String code ;
    private String name ;
    private int companyid ; 
    private List<OUUItemOrder> item;

    public OUUCustomerOrder(int id, String code, String name, int companyid, List<OUUItemOrder> item) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.companyid = companyid;
        this.item = item;
    }
    
    public OUUCustomerOrder(){
        super();
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCompanyid() {
        return companyid;
    }

    public void setCompanyid(int companyid) {
        this.companyid = companyid;
    }

    public List<OUUItemOrder> getItem() {
        return item;
    }

    public void setItem(List<OUUItemOrder> item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
