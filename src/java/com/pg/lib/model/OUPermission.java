package com.pg.lib.model;

import java.util.ArrayList;

public class OUPermission {
    private String role;
    private ArrayList url;
    
    public OUPermission(String role, ArrayList url){
        this.role = role;
        this.url = url;
    }
    
    public OUPermission(){
        super();
    }
    
    public String getRole(){
        return this.role;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public ArrayList getUrl(){
        return this.url;
    }
    
    public void setUrl(ArrayList url){
        this.url = url;
    }
}
