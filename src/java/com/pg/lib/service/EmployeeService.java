/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.service;

import com.pg.lib.utility.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author 111525
 */
public class EmployeeService {
    
    private static Connection conn ;
    private static ResultSet rs ;
    private static PreparedStatement ps ;
    
    public static String queryName(String userid){
        String name = "" ;
            try{
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("" +
                        "SELECT prefixdesc, pwfname, pwlname " +
                        "FROM v_pwemployee " +
                        "WHERE pwemployee = ? ");
                ps.setString(1, userid);
                rs = ps.executeQuery();
                    while(rs.next()){
                        name = rs.getString("prefixdesc") + " " + rs.getString("pwfname") + " " + rs.getString("pwlname");
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                   rs.close();
                   ps.close();
                   ConnectDB.closeConnection(conn);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        return name;
    }
    
    public static String getRole(String userid){
        String role = "" ;
            try{
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT authorize FROM pgrole WHERE userid = ? and project = 'ORDER_UNIFORM'");
                ps.setString(1, userid);
                rs = ps.executeQuery();
                    while(rs.next()){
                        role = rs.getString("authorize");
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try{
                    rs.close();
                    ps.close();
                    ConnectDB.closeConnection(conn);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        return role;
    }
         
}
