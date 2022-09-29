package com.pg.lib.model;


public class OUSummaryOrderByDepartment {
        private int departid;
        private String agency;
        private String division;
        private String departname;
        private String summary;
        
        public OUSummaryOrderByDepartment(){
            super();
        }

    public OUSummaryOrderByDepartment(int departid, String agency, String division, String departname, String summary) {
        this.departid = departid;
        this.agency = agency;
        this.division = division;
        this.departname = departname;
        this.summary = summary;
    }
        
    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
        
        
}
