package com.pg.lib.service;

import com.pg.lib.model.OUCustomerDetail;
import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.model.OUUploadCustomer;
import com.pg.lib.utility.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerService {

    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;
    

    public ArrayList<OUTransactionCustomerDetail> GroupCustomerDepart(String doc_id) {
        TransactionCustomerService s_trancustomer = new TransactionCustomerService();
        List<OUTransactionCustomerDetail> listdepart = s_trancustomer.getDetailTransactionByDocumentId(doc_id);
        ArrayList<OUTransactionCustomerDetail> arr = new ArrayList<OUTransactionCustomerDetail>();
        String no = "";
        for (int n = 0; n < listdepart.size(); n++) {
            if (!no.equals(listdepart.get(n).getDepartmentname())) {
                OUTransactionCustomerDetail list = new OUTransactionCustomerDetail();
                list.setDepartmentname(listdepart.get(n).getDepartmentname());
                arr.add(list);
            }
            no = listdepart.get(n).getDepartmentname();
        }
        return arr;
    }

    public ArrayList<OUTransactionCustomerDetail> GroupCustomerCode(String doc_id) {
        TransactionCustomerService s_trancustomer = new TransactionCustomerService();
        List<OUTransactionCustomerDetail> listcus_no = s_trancustomer.getDetailTransactionByDocumentId(doc_id);
        ArrayList<OUTransactionCustomerDetail> arr = new ArrayList<OUTransactionCustomerDetail>();
        String no = "";
        for (int n = 0; n < listcus_no.size(); n++) {
            if (!no.equals(listcus_no.get(n).getCustomerCode())) {
                OUTransactionCustomerDetail list = new OUTransactionCustomerDetail();
                list.setCustomerCode(listcus_no.get(n).getCustomerCode());
                list.setDepartmentname(listcus_no.get(n).getDepartmentname());
                list.setFname(listcus_no.get(n).getFname());
                list.setDocName(listcus_no.get(n).getDocName());
                arr.add(list);
            }
            no = listcus_no.get(n).getCustomerCode();
        }
        return arr;
    }

    public boolean addCustomer(List<OUUploadCustomer> arr) {
        boolean insertResult = false;
        try {
            String sqlText = generateSQLText(arr);
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sqlText);
            int i = ps.executeUpdate();
            if (i > 0) {
                insertResult = true;
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

        return insertResult;
    }

    public List<OUCustomerDetail> getDataUpload(String compID) {

        List<OUCustomerDetail> listcus = new ArrayList<OUCustomerDetail>();
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement("SELECT cus_seq, " +
                    "cus_no, " +
                    "cus_prename, " +
                    "cus_fname, " +
                    "cus_department, " +
                    "b.comp_name as comp_name " +
                    "FROM ou_upload_customer a INNER JOIN ou_company b " +
                    "ON a.company_id = b.comp_id " +
                    "WHERE " +
                    "a.cus_status = ? AND " +
                    "a.company_id = ? " +
                    "ORDER BY cus_seq asc ");
            ps.setString(1, "new");
            ps.setString(2, compID);
            rs = ps.executeQuery();
            while (rs.next()) {
                OUCustomerDetail objCustomer = new OUCustomerDetail();
                objCustomer.setCusSeq(rs.getString("cus_seq"));
                objCustomer.setCusNo(rs.getString("cus_no"));
                objCustomer.setCusPreName(rs.getString("cus_prename"));
                objCustomer.setCusFName(rs.getString("cus_fname"));
                objCustomer.setCusDepartment(rs.getString("cus_department"));
                objCustomer.setCusCompany(rs.getString("comp_name"));
                listcus.add(objCustomer);
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
        return listcus;

    }

    public List<OUCustomerDetail> getDataALL() {

        List<OUCustomerDetail> listcus = new ArrayList<OUCustomerDetail>();
        try {
            String sqlText = "SELECT cus_no, " +
                    "cus_seq, " +
                    "cus_prename, " +
                    "cus_fname, " +
                    "cus_department, " +
                    "b.comp_name as compname " +
                    "FROM ou_upload_customer a INNER JOIN ou_company b ON a.company_id = b.comp_id " +
                    "WHERE a.cus_status = 'new' ";
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sqlText);
            rs = ps.executeQuery();
            while (rs.next()) {
                OUCustomerDetail obj = new OUCustomerDetail();
                obj.setCusNo(rs.getString("cus_no"));
                obj.setCusSeq(rs.getString("cus_seq"));
                obj.setCusPreName(rs.getString("cus_prename"));
                obj.setCusFName(rs.getString("cus_fname"));
                obj.setCusDepartment(rs.getString("cus_department"));
                obj.setCusCompany(rs.getString("compname"));
                listcus.add(obj);
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

        return listcus;
    }

    private String generateSQLText(List<OUUploadCustomer> arr) {
        String temp = "";
        int latestPrimaryKey = getLatestPrimarykey();
        int seq = 0;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        temp = "INSERT all";
        for (int i = 0; i <= arr.size() - 1; i++) {
            temp += " INTO ou_upload_customer VALUES ";
            latestPrimaryKey = latestPrimaryKey + 1;
            seq = i + 1;
            temp += "('" + latestPrimaryKey + "','" + arr.get(i).getCustomerID() + "','" + seq + "','" + arr.get(i).getPrename() + "','" + arr.get(i).getFullname() + "','" + arr.get(i).getDepartment() + "'," + arr.get(i).getCompany() + "," + "'new'," + "TO_DATE('" + date + "','yyyy-mm-dd')," + "'') ";
        }
        temp += "select * from dual";

        return temp;
    }

    private int getLatestPrimarykey() {
        int pk = 0;
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement("SELECT max(cus_id) as lastestid FROM ou_upload_customer");
            rs = ps.executeQuery();
            while (rs.next()) {
                pk = (rs.getInt("lastestid"));
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

    private int getLatestSeq() {
        int seq = 0;
        int latestPrimaryKey = getLatestPrimarykey();
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement("SELECT cus_seq as lastestseq FROM ou_upload_customer where cus_id = ?");
            ps.setInt(1, latestPrimaryKey);
            rs = ps.executeQuery();
            while (rs.next()) {
                seq = (rs.getInt("lastestseq"));
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
        return seq;
    }

    private String InsertDataOneRow() {

        return "";
    }
}
