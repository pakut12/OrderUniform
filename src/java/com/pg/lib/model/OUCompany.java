package com.pg.lib.model;

public class OUCompany {
    
    private String code;
    private String name;
    private String content_type;
    
    public OUCompany(){
        super();
    }
    
    public OUCompany(String code, String name, String content_type){
        this.code = code;
        this.name = name;
        this.content_type = content_type;
    }
    
    public String getCode(){
        return this.code;
    }
    
    public void setCode(String code){
        this.code = code;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }
    
    
}
