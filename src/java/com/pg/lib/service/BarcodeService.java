package com.pg.lib.service;

import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.utility.ConnectDB;

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
public class BarcodeService {

    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement ps;

    public HashMap<String, String> getBarcodeFindByMaterialCode(List<OUTransactionCustomerDetail> data) {
        String sql = createSQLText(data);
        HashMap<String, String> barcode = new HashMap<String, String>();
        try {
            conn = ConnectDB.getConnectionPGCA();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                barcode.put(rs.getString("mat_no"), rs.getString("ean_upc"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ConnectDB.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return barcode;
    }

    private String createSQLText(List<OUTransactionCustomerDetail> data) {

        String strWhereClause = " SELECT mat_no, ean_upc FROM sap_mara_mat_all WHERE mat_no in ( ";
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1) {
                strWhereClause += "\'" + data.get(i).getMatfullname().replace("000", "") + "\' )";
            } else {
                strWhereClause += "\'" + data.get(i).getMatfullname().replace("000", "") + "\',";
            }
        }
        System.out.println(strWhereClause);
        return strWhereClause;
    }
}
