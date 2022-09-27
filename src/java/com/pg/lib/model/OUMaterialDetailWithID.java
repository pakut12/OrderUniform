package com.pg.lib.model;

//เหตุที่มี WithID เพราะตอนแรกไม่ได้คิดจะใช้ ID แต่ทำไปซักพักแล้วรู้สึกว่าใช้ เลยสร้างขึ้นมาใหม่ ไม่มีอะไร

import java.io.Serializable;

public class OUMaterialDetailWithID implements Serializable {
    
    private int hmat_id ;
    private String hmat_code ;
    private String hmat_desc ;
    private String hmat_color ;
    private int company_id ;
    private int group_id;
    
    public OUMaterialDetailWithID(){
        super();
    }

    public OUMaterialDetailWithID(int hmat_id, String hmat_code, String hmat_desc, String hmat_color, int company_id, int group_id) {
        this.hmat_id = hmat_id;
        this.hmat_code = hmat_code;
        this.hmat_desc = hmat_desc;
        this.hmat_color = hmat_color;
        this.company_id = company_id;
        this.group_id = group_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getHmat_code() {
        return hmat_code;
    }

    public void setHmat_code(String hmat_code) {
        this.hmat_code = hmat_code;
    }

    public String getHmat_color() {
        return hmat_color;
    }

    public void setHmat_color(String hmat_color) {
        this.hmat_color = hmat_color;
    }

    public String getHmat_desc() {
        return hmat_desc;
    }

    public void setHmat_desc(String hmat_desc) {
        this.hmat_desc = hmat_desc;
    }

    public int getHmat_id() {
        return hmat_id;
    }

    public void setHmat_id(int hmat_id) {
        this.hmat_id = hmat_id;
    }

    

}
