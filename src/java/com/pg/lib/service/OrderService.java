

package com.pg.lib.service;

import com.pg.lib.model.OUCustomerDetailWithID;
import com.pg.lib.model.OUDepartmentDetailWithID;
import com.pg.lib.model.OUMaterialDetailWithID;
import com.pg.lib.utility.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;



public class OrderService {
    
        private static Connection conn;
        private static PreparedStatement ps;
        private static ResultSet rs ;
        
    public List<TreeMap> getCompanyList(String conntentType){
        List<TreeMap> arr = new ArrayList<TreeMap>();
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT comp_id, " +
                                            "comp_name " +
                                            "FROM ou_company " +
                                            "WHERE content_type = '"+conntentType+"' " +
                                            "order by comp_id asc ");
                rs = ps.executeQuery();
                    while(rs.next()){
                        TreeMap<String,String> treemap = new TreeMap<String, String>();
                        treemap.put(rs.getString("comp_id"),rs.getString("comp_name"));
                        arr.add(treemap);
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        return arr;
    }
    
    public List<String> getDetailOfCompany(String CompanyID){
        List<String> arr = new ArrayList<String>();
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT * FROM ou_company WHERE comp_id = ? ");
                ps.setString(1, CompanyID);
                rs = ps.executeQuery();
                    while(rs.next()){
                        arr.add(String.valueOf(rs.getInt("comp_id")));
                        arr.add(rs.getString("comp_code"));
                        arr.add(rs.getString("comp_name"));
                        arr.add(rs.getString("content_type"));
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        return arr;
    }
    
    public List<OUMaterialDetailWithID> getMaterialOfCompany(List<String> companydetail){
        List<OUMaterialDetailWithID> listmat = new ArrayList<OUMaterialDetailWithID>();
        String SQLText = "SELECT * FROM ou_header_material WHERE company_id = '"+companydetail.get(0)+"' and hmat_status = 'new' ORDER BY HMAT_ID asc ";
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(SQLText);
                rs = ps.executeQuery();
                    while(rs.next()){
                        OUMaterialDetailWithID obj = new OUMaterialDetailWithID();
                            obj.setHmat_id(rs.getInt("hmat_id"));
                            obj.setHmat_code(rs.getString("hmat_code"));
                            obj.setHmat_desc(rs.getString("hmat_desc"));
                            obj.setHmat_color(rs.getString("hmat_color"));
                            obj.setCompany_id(rs.getInt("company_id"));
                            obj.setGroup_id(rs.getInt("group_id"));
                        listmat.add(obj);
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                    e.printStackTrace();
                } 
            }
        return listmat;
    }
    
    public List<OUCustomerDetailWithID> getCustomerOfComapany(List<String> companydetail){
        List<OUCustomerDetailWithID> listcustomer = new ArrayList<OUCustomerDetailWithID>();
        String sqlText = "SELECT * FROM ou_upload_customer WHERE company_id = "+companydetail.get(0)+" and cus_status = 'new' order by cus_id ";
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqlText);
                rs = ps.executeQuery();
                    while(rs.next()){
                        OUCustomerDetailWithID obj = new OUCustomerDetailWithID();
                            obj.setCus_id(rs.getInt("cus_id"));
                            obj.setCus_no(rs.getString("cus_no"));
                            obj.setCus_seq(rs.getString("cus_seq"));
                            obj.setCus_prename(rs.getString("cus_prename"));
                            obj.setCus_fname(rs.getString("cus_fname"));
                            obj.setCus_department(rs.getString("cus_department"));
                            obj.setCompany_id(rs.getInt("company_id"));
                        listcustomer.add(obj);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                   rs.close();
                   ps.close();
                   ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                   e.printStackTrace();   
                }
            }
        return listcustomer;
    }
    
    public List<OUDepartmentDetailWithID> getDepartmentOfCompany(List<String> companydetail){
        List<OUDepartmentDetailWithID> listdepart = new ArrayList<OUDepartmentDetailWithID>();
        String sqlText = "SELECT * FROM ou_upload_department WHERE company_id = "+companydetail.get(0)+" and depart_status = 'new' ";
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqlText);
                rs = ps.executeQuery();
                    while(rs.next()){
                        OUDepartmentDetailWithID obj = new OUDepartmentDetailWithID();
                            obj.setDepart_id(rs.getInt("depart_id"));
                            obj.setDepart_seq(rs.getString("depart_seq"));
                            obj.setDepart_agency(rs.getString("depart_agency"));
                            obj.setDepart_division(rs.getString("depart_division"));
                            obj.setDepart_name(rs.getString("depart_name"));
                            obj.setCompany_id(rs.getInt("company_id"));
                        listdepart.add(obj);
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        return listdepart;
    }
    
}
