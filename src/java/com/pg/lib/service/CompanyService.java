/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.service;

import com.pg.lib.model.OUCompany;
import com.pg.lib.model.OUDepartmentDetail;
import com.pg.lib.utility.ConnectDB;
import java.lang.Integer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author 111525
 */
public class CompanyService {
    
    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement ps;
        
    public static HashMap<Integer, OUCompany> getCompanyDetail(String content){
        HashMap<Integer, OUCompany> hm = new HashMap<Integer, OUCompany>();
        String additionwhere = "" ;
        int i = 0;
        
            if(content.equals("customer")){
                additionwhere = "and content_type = '"+content.trim()+"' ";
            } else if (content.equals("department")) {
                additionwhere = "and content_type = '"+content.trim()+"' ";
            } else {
                additionwhere = "";
            }
        
                try {
                    conn = ConnectDB.getConnection();
                    ps = conn.prepareStatement("SELECT comp_code, comp_name, content_type FROM ou_company WHERE comp_code != 'C99' "+additionwhere);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            OUCompany objCompany = new OUCompany();
                            objCompany.setCode(rs.getString("comp_code"));
                            objCompany.setName(rs.getString("comp_name"));
                            objCompany.setContent_type(rs.getString("content_type"));
                            hm.put(i,objCompany);
                            i++;
                        }

                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    try {
                        ps.close();
                        rs.close();
                        ConnectDB.closeConnection(conn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        return hm;
    }
    
    public static String getIDCompany(String compcode){
        String result = "";
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT comp_id FROM ou_company where comp_code = ? ");
                ps.setString(1, compcode);
                rs = ps.executeQuery();
                    while(rs.next()){
                       result = String.valueOf(rs.getInt("comp_id"));
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    rs.close();
                    ConnectDB.closeConnection(conn);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return result;
    }
  
    public List<OUDepartmentDetail> getDataALL(){
        
        List<OUDepartmentDetail> listDepartment = new ArrayList<OUDepartmentDetail>();
            try {
                String sqltext = "SELECT depart_seq, " +
                                 "depart_agency, " +
                                 "depart_division, " +
                                 "depart_name, " +
                                 "b.comp_name as companyname " +
                                 "FROM ou_upload_department a INNER JOIN ou_company b " +
                                 "ON a.company_id = b.comp_id " +
                                 "WHERE a.depart_status = 'new' ";
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqltext);
                rs = ps.executeQuery();
                    while(rs.next()){
                        OUDepartmentDetail objdepart = new OUDepartmentDetail();
                            objdepart.setDepart_seq(rs.getString("depart_seq"));
                            objdepart.setDepart_agency(rs.getString("depart_agency"));
                            objdepart.setDepart_division(rs.getString("depart_division"));
                            objdepart.setDepart_name(rs.getString("depart_name"));
                            objdepart.setDepart_company(rs.getString("companyname"));
                        listDepartment.add(objdepart);
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
            
        return listDepartment;
    }
            
    public static String addNewCompany(String companyname,String contenttype){
        int latestPK = getLatestPKCompany() ; 
        String returnString = "";
        String companyCode = "C"+String.valueOf(latestPK+1);
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("INSERT INTO ou_company " +
                                           "(comp_id,comp_code,comp_name,content_type) " +
                                           "VALUES (?,?,?,?)");
                ps.setInt(1, latestPK+1);
                ps.setString(2, companyCode);
                ps.setString(3, companyname.trim());
                ps.setString(4, contenttype.trim());
                int result = ps.executeUpdate();
                    if(result >= 1){
                        returnString = "Success";
                    } else {
                        returnString = "Failure";
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    rs.close();
                    ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        return returnString;
    }
    
    private static int getLatestPKCompany(){
        int latestPK = 0;
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT max(comp_id) as last FROM ou_company ");
                rs = ps.executeQuery();
                    while(rs.next()){
                        latestPK = rs.getInt("last");
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                  ps.close();
                  rs.close();
                  ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        return latestPK;
    }
}
