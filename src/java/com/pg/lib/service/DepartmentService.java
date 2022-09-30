package com.pg.lib.service;

import com.pg.lib.model.OUDepartmentDetail;
import com.pg.lib.model.OUUploadDepartment;
import com.pg.lib.utility.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DepartmentService {
    
    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement ps;
    
    public boolean addDepartment(List<OUUploadDepartment> arr){
        
        boolean insertResult = false;
            try {
                String sqltext = generateSQLText(arr);
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqltext);
                int i = ps.executeUpdate();
                    if(i > 0){
                        insertResult = true;
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                  ps.close();
                  ConnectDB.closeConnection(conn);
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        return insertResult;
        
    }
    
    public List<OUDepartmentDetail> getDataUpload(String companyID){
        
        List<OUDepartmentDetail> result = new ArrayList<OUDepartmentDetail>();
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(" SELECT " +
                                           " depart_seq , " +
                                           " depart_agency, " +
                                           " depart_division, " +
                                           " depart_name, " +
                                           " b.comp_name as depart_company " +
                                           " FROM ou_upload_department a INNER JOIN ou_company b" +
                                           " ON a.company_id = b.comp_id" +
                                           " WHERE a.depart_status = ? " +
                                           " AND a.company_id = ? " +
                                           " ORDER BY depart_seq ");
                ps.setString(1, "new");
                ps.setString(2, companyID);
                rs = ps.executeQuery();
                    while(rs.next()){
                        OUDepartmentDetail objDepart = new OUDepartmentDetail();
                            objDepart.setDepart_seq(rs.getString("depart_seq"));
                            objDepart.setDepart_agency(rs.getString("depart_agency"));
                            objDepart.setDepart_division(rs.getString("depart_division"));
                            objDepart.setDepart_name(rs.getString("depart_name"));
                            objDepart.setDepart_company(rs.getString("depart_company"));
                        result.add(objDepart);
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
        return result;
        
    }
    
    private String generateSQLText(List<OUUploadDepartment> arr){
        
        String temp = "";
        int seq = 0;
        int latestPK = getLatestPrimarykey();
        long millis = System.currentTimeMillis();  
        java.sql.Date date = new java.sql.Date(millis); 
        
            temp += "INSERT all ";
            for(int i = 0 ; i<= arr.size()-1 ; i++){
                temp += "INTO ou_upload_department VALUES ";
                latestPK = latestPK + 1;
                seq = i + 1;
                temp += "("+latestPK+",'"
                        + seq+"','"
                        + arr.get(i).getAgency() + "','"
                        + arr.get(i).getDivision() + "','"
                        + arr.get(i).getDepartment() + "',"
                        + arr.get(i).getCompany() + ","
                        + "'new',"
                        + "TO_DATE('"+date+"','yyyy-mm-dd'),"
                        + "'') ";
            }
            temp += "select * from dual";
        return temp;
        
    }
    
    private int getLatestPrimarykey(){
        
        int latestPK = 0;
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT max(depart_id) as latestIndex from ou_upload_department ");
                rs = ps.executeQuery();
                    while(rs.next()){
                       latestPK = rs.getInt("latestIndex");
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    ps.close();
                    rs.close();
                    ConnectDB.closeConnection(conn);
                } catch ( SQLException e){
                    e.printStackTrace();
                }
            }
        return latestPK;
        
    }
}
