package com.pg.lib.service;

import com.pg.lib.model.OUMaterialDetail;
import com.pg.lib.utility.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaterialService {

    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement ps;

     public boolean deleteMaterial(String hmat_id) {
        boolean result = false;
        String SQLText = "delete from ou_header_material where hmat_id = ?";
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(SQLText);
            ps.setString(1, hmat_id);
            int i = ps.executeUpdate();
            if (i > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
     
    public boolean createNewMaterial(String code, String spec, String desc, String company) {
        boolean result = false;
        String SQLText = generateSQLText(code, spec, desc, company);
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(SQLText);
            int i = ps.executeUpdate();
            if (i > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;

    }

    public List<OUMaterialDetail> getDataMaterial() {
        List<OUMaterialDetail> arrlist = new ArrayList<OUMaterialDetail>();
        String sqlText = "SELECT hmat_id,hmat_code,hmat_teamorder, hmat_gender, hmat_category, hmat_type, hmat_pattern, hmat_rno, hmat_color,hmat_desc , b.comp_code, b.comp_name, c.group_name " +
                "FROM ou_header_material a " +
                "LEFT JOIN ou_company b ON a.company_id = b.comp_id " +
                "LEFT JOIN ou_material_group c ON a.group_id = c.group_id " +
                "WHERE a.hmat_status = 'new' ";
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sqlText);
            rs = ps.executeQuery();
            while (rs.next()) {
                OUMaterialDetail obj = new OUMaterialDetail();
                    obj.setHmat_id(rs.getString("hmat_id"));
                    obj.setHmat_code(rs.getString("hmat_code"));
                    obj.setHmat_team(rs.getString("hmat_teamorder"));
                    obj.setHmat_desc(rs.getString("hmat_desc"));
                    obj.setHmat_gender(rs.getString("hmat_gender"));
                    obj.setHmat_category(rs.getString("hmat_category"));
                    obj.setHmat_type(rs.getString("hmat_type"));
                    obj.setHmat_rno(rs.getString("hmat_rno"));
                    obj.setHmat_pattern(rs.getString("hmat_pattern"));
                    obj.setHmat_color(rs.getString("hmat_color"));
                    obj.setCom_code(rs.getString("comp_code"));
                    obj.setCom_name(rs.getString("comp_name"));
                    obj.setGroup_name(rs.getString("group_name"));
                arrlist.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return arrlist;
    }

    private int getLatestPK() {

        int pk = 0;
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(" SELECT max(hmat_id) as lastestpk FROM ou_header_material ");
            rs = ps.executeQuery();
            while (rs.next()) {
                pk = rs.getInt("lastestpk");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return pk;

    }

    private String generateSQLText(String code, String spec, String desc, String company) {

        //Check lastest Primary key
        int lastestpk = getLatestPK();

        /* Split detail of material and insert into arr[]
         * arr[0] = TeamOrder
         * arr[1] = Gender (value M,W)
         * arr[2] = Catogory 
         * arr[3] = Type 
         * arr[4] = pattern
         * arr[5] = RunningNumber
         * arr[6] = Color
         */
        String materialSpec[] = spec.split("-");

        //Get Date/Time and set format
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();

        //Find the company ID 
        String comapanyID = CompanyService.getIDCompany(company);

        lastestpk = lastestpk + 1;
        String text = " Insert into ou_header_material ( " +
                "hmat_id, " +
                "hmat_code, " +
                "hmat_teamorder, " +
                "hmat_gender, " +
                "hmat_category, " +
                "hmat_type, " +
                "hmat_pattern, " +
                "hmat_rno, " +
                "hmat_color, " +
                "group_id, " +
                "company_id, " +
                "hmat_status, " +
                "hmat_createdate, " +
                "hmat_modifydate, " +
                "hmat_desc) " +
                "VALUES (" +
                lastestpk + ", '" +
                code + "', '" +
                materialSpec[0] + "', '" +
                materialSpec[1] + "', '" +
                materialSpec[2] + "', '" +
                materialSpec[3] + "', '" +
                materialSpec[4] + "', '" +
                materialSpec[5] + "', '" +
                materialSpec[6] + "', " +
                "'', " +
                Integer.parseInt(comapanyID) + ", " +
                "'new', " +
                "to_timestamp('" + formatter.format(date) + "', 'DD.MM.YYYY HH24:MI:SSFF3'), " +
                "'', '" +
                desc+"') ";
        return text;
    }
}
