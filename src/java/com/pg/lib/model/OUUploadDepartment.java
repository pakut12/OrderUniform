package com.pg.lib.model;

public class OUUploadDepartment {
        private String agency ;
        private String division ;
        private String department ;
        private String company;
    
    public OUUploadDepartment(){
        super();
    }
    
    public OUUploadDepartment( String agency,
                               String division,
                               String department,
                               String company ){
        this.agency = agency;
        this.division = division;
        this.department = department;
        this.company = company;
    }
    
    public String getAgency(){
        return this.agency;
    }
    
    public void setAgency(String agency){
        this.agency = agency;
    }
    
    public String getDivision(){
        return this.division;
    }
    
    public void setDivision(String division){
        this.division = division;
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
