package com.pg.lib.model;

public class OUMaterialDetail {

    private String hmat_code;
    private String hmat_gender;
    private String hmat_category;
    private String hmat_type;
    private String hmat_pattern;
    private String hmat_rno;
    private String hmat_color;
    private String com_code;
    private String com_name;
    private String group_name;
    private String hmat_desc;
    
    public OUMaterialDetail(){
        super();
    }

    public OUMaterialDetail(String hmat_code, String hmat_gender, String hmat_category, String hmat_type, String hmat_pattern, String hmat_rno, String hmat_color, String com_code, String com_name, String group_name, String hmat_desc) {
        this.hmat_code = hmat_code;
        this.hmat_gender = hmat_gender;
        this.hmat_category = hmat_category;
        this.hmat_type = hmat_type;
        this.hmat_pattern = hmat_pattern;
        this.hmat_rno = hmat_rno;
        this.hmat_color = hmat_color;
        this.com_code = com_code;
        this.com_name = com_name;
        this.group_name = group_name;
        this.hmat_desc = hmat_desc;
    }

    public String getCom_code() {
        return com_code;
    }

    public void setCom_code(String com_code) {
        this.com_code = com_code;
    }

    public String getCom_name() {
        return com_name;
    }

    public void setCom_name(String com_name) {
        this.com_name = com_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getHmat_category() {
        return hmat_category;
    }

    public void setHmat_category(String hmat_category) {
        this.hmat_category = hmat_category;
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

    public String getHmat_gender() {
        return hmat_gender;
    }

    public void setHmat_gender(String hmat_gender) {
        this.hmat_gender = hmat_gender;
    }

    public String getHmat_pattern() {
        return hmat_pattern;
    }

    public void setHmat_pattern(String hmat_pattern) {
        this.hmat_pattern = hmat_pattern;
    }

    public String getHmat_rno() {
        return hmat_rno;
    }

    public void setHmat_rno(String hmat_rno) {
        this.hmat_rno = hmat_rno;
    }

    public String getHmat_type() {
        return hmat_type;
    }

    public void setHmat_type(String hmat_type) {
        this.hmat_type = hmat_type;
    }

    
    
    
}
