package com.pg.lib.service;

import com.pg.lib.model.OUPermission;
import com.pg.lib.utility.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class AuthorizedService {
    
    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement ps;
    
    public static OUPermission definePermission(String role){
        ArrayList<String> oPermission = new ArrayList();
            try{
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT * FROM pgpermission WHERE project = 'ORDER_UNIFORM' and authorize = ? ");
                ps.setString(1,role);
                rs = ps.executeQuery();
                while(rs.next()) {
                    oPermission.add(rs.getString("url"));
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
        return new OUPermission(role, oPermission);
    }
}

