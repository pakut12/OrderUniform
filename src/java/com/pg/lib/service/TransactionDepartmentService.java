package com.pg.lib.service;

import com.pg.lib.model.OUOrderListDepartment;
import com.pg.lib.model.OUSummaryOrderByDepartment;
import com.pg.lib.model.OUSummaryOrderDepartmentByMaterialAndSize;
import com.pg.lib.model.OUTransactionDepartmentDetail;
import com.pg.lib.model.OUUDepartmentOrder;
import com.pg.lib.utility.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class TransactionDepartmentService {

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement ps;

    public int createNewTransaction(List<OUOrderListDepartment> orderList, HashMap<String, String> detailfile) {
        int lastestpk = generateHeaderTransaction(detailfile);
        String nativeSQL = generateSQLText(orderList, lastestpk);
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(nativeSQL);
            int resultinsert = ps.executeUpdate();
            if (resultinsert > 0) {
                return lastestpk;
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
        return 0;
    }

    public boolean updateStatusItems(List<OUOrderListDepartment> orderList) {
        String sqlupdatestatusitems = generateSQLUpdateStatusItems(orderList);
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sqlupdatestatusitems);
            int result = ps.executeUpdate();
            if (result > 0) {
                return true;
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
        return false;
    }

    public boolean updateStatusDepartment(List<OUOrderListDepartment> orderList) {
        String sqlupdatestatusdepart = generateSQLUpdateStatusDepartment(orderList);
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sqlupdatestatusdepart);
            int result = ps.executeUpdate();
            if (result > 0) {
                return true;
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
        return false;
    }

    public List<OUOrderListDepartment> processData(List<OUUDepartmentOrder> transactionDetail) {

        List<OUOrderListDepartment> result = new ArrayList<OUOrderListDepartment>();
        String matCode, matSize, matQuatity, matFullname;
        int matID, departID, comID;

        for (int i = 0; i <= transactionDetail.size() - 1; i++) {
            for (int j = 0; j <= transactionDetail.get(i).getItem().size() - 1; j++) {
                if (transactionDetail.get(i).getItem().get(j).getSize().equals("-") &&
                        transactionDetail.get(i).getItem().get(j).getQty().equals("-")) {
                    continue;
                } else {
                    OUOrderListDepartment obj = new OUOrderListDepartment();

                    departID = transactionDetail.get(i).getId();
                    comID = transactionDetail.get(i).getCompany_id();
                    matID = transactionDetail.get(i).getItem().get(j).getMatid();
                    matSize = transactionDetail.get(i).getItem().get(j).getSize();
                    matQuatity = transactionDetail.get(i).getItem().get(j).getQty();
                    matCode = transactionDetail.get(i).getItem().get(j).getMatcode();

                    matFullname = generateMaterialCodeFullName(matCode, matSize);

                    obj.setDepart_id(departID);
                    obj.setCompanyID(comID);
                    obj.setMaterialID(matID);
                    obj.setMaterialfullname(matFullname);
                    obj.setSize(matSize);
                    obj.setQuantity(matQuatity);

                    result.add(obj);
                }
            }
        }
        return result;
    }

    public List<OUTransactionDepartmentDetail> getDetailTransactionByDocumentId(String doc_id) {
        List<OUTransactionDepartmentDetail> listreturn = new ArrayList<OUTransactionDepartmentDetail>();
        String plainSQL = "SELECT " +
                "a.h_id as docID, " +
                "a.h_name as docName, " +
                "b.tran_depart_id as transactionID, " +
                "c.depart_id as departmentID, " +
                "d.hmat_id as materialID, " +
                "e.comp_id as companyID, " +
                "b.tran_depart_size as matsize, " +
                "b.tran_depart_quantity as matquantity, " +
                "c.depart_agency as agency, " +
                "c.depart_division as division, " +
                "c.depart_name as departname, " +
                "d.hmat_code as materialname, " +
                "d.hmat_color as materialcolor, " +
                "d.hmat_desc as materialdesc, " +
                "e.comp_name as companyname, " +
                "b.tran_depart_matcode as matcodefullname, " +
                "b.tran_depart_barcode as barcode " +
                "FROM ou_header_transaction_depart a " +
                "LEFT JOIN ou_transaction_department b ON a.h_id = b.header_depart_id " +
                "LEFT JOIN ou_upload_department c ON b.depart_id = c.depart_id " +
                "LEFT JOIN ou_header_material d ON b.hmat_id = d.hmat_id " +
                "LEFT JOIN ou_company e ON b.comp_id = e.comp_id " +
                "WHERE a.h_id = ? " +
                "ORDER BY b.tran_depart_id asc ";
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(plainSQL);
            ps.setString(1, doc_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                OUTransactionDepartmentDetail objtran = new OUTransactionDepartmentDetail();
                objtran.setDocID(rs.getInt("docID"));
                objtran.setTransactionID(rs.getInt("transactionID"));
                objtran.setDepartID(rs.getInt("departmentID"));
                objtran.setMaterialID(rs.getInt("materialID"));
                objtran.setCompanyID(rs.getInt("companyID"));
                objtran.setDocName(rs.getString("docName"));
                objtran.setMaterialsize(rs.getString("matsize"));
                objtran.setMaterialquantity(rs.getString("matquantity"));
                objtran.setAgency(rs.getString("agency"));
                objtran.setDivision(rs.getString("division"));
                objtran.setDepartmentname(rs.getString("departname"));
                objtran.setMaterialname(rs.getString("materialname"));
                objtran.setMaterialcolor(rs.getString("materialcolor"));
                objtran.setMaterialdesc(rs.getString("materialdesc"));
                objtran.setCompanyname(rs.getString("companyname"));
                objtran.setMaterialfullname(rs.getString("matcodefullname"));
                objtran.setBarcode(rs.getString("barcode"));
                listreturn.add(objtran);
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
        return listreturn;
    }

    public List<OUSummaryOrderByDepartment> summaryOrderByDepartment(String doc_id) {
        List<OUSummaryOrderByDepartment> arrreturn = new ArrayList<OUSummaryOrderByDepartment>();
        String plainSQL = "SELECT " +
                "c.depart_id as departid, " +
                "c.depart_agency as agency, " +
                "c.depart_division as division, " +
                "c.depart_name as departname, " +
                "sum(tran_depart_quantity) as summary " +
                "FROM ou_header_transaction_depart a " +
                "left join ou_transaction_department b on a.h_id = b.header_depart_id " +
                "left join ou_upload_department c on b.depart_id = c.depart_id " +
                "left join ou_header_material d on b.hmat_id = d.hmat_id " +
                "WHERE a.h_id = ? " +
                "Group by c.depart_id, c.depart_agency, c.depart_division, c.depart_name " +
                "Order by c.depart_id ";
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(plainSQL);
            ps.setString(1, doc_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                OUSummaryOrderByDepartment obj = new OUSummaryOrderByDepartment();
                obj.setDepartid(rs.getInt("departid"));
                obj.setAgency(rs.getString("agency"));
                obj.setDivision(rs.getString("division"));
                obj.setDepartname(rs.getString("departname"));
                obj.setSummary(rs.getString("summary"));
                arrreturn.add(obj);
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
        return arrreturn;
    }

    public List<OUSummaryOrderDepartmentByMaterialAndSize> summaryOrderByMaterialandSize(String doc_id) {
        List<OUSummaryOrderDepartmentByMaterialAndSize> listsummary = new ArrayList<OUSummaryOrderDepartmentByMaterialAndSize>();
        String sqltext = "SELECT " +
                "a.hmat_id as materialid, " +
                "b.hmat_code as materialname, " +
                "b.hmat_desc as materialdesc, " +
                "a.tran_depart_size as materialsize, " +
                "sum(a.tran_depart_quantity) as summary " +
                "FROM ou_transaction_department a " +
                "LEFT JOIN ou_header_material b ON a.hmat_id = b.hmat_id " +
                "WHERE a.header_depart_id = ? " +
                "GROUP BY a.hmat_id, b.hmat_code, b.hmat_desc, a.tran_depart_size " +
                "ORDER BY a.hmat_id ";
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sqltext);
            ps.setString(1, doc_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                OUSummaryOrderDepartmentByMaterialAndSize object = new OUSummaryOrderDepartmentByMaterialAndSize();
                object.setMaterialid(rs.getInt("materialid"));
                object.setMaterialname(rs.getString("materialname"));
                object.setMaterialdesc(rs.getString("materialdesc"));
                object.setMaterialsize(rs.getString("materialsize"));
                object.setSummary(rs.getString("summary"));
                listsummary.add(object);
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
        return listsummary;
    }

    public HashMap<String, Integer> calculateTotalBySize(List<OUSummaryOrderDepartmentByMaterialAndSize> summarybysize) {
        String tempMaterialname = "";
        int tempQuantity = 0;
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        for (int index = 0; index <= summarybysize.size() - 1; index++) {
            if (!summarybysize.get(index).getMaterialname().equals(tempMaterialname)) {
                tempMaterialname = summarybysize.get(index).getMaterialname();
                tempQuantity = Integer.parseInt(summarybysize.get(index).getSummary());
            } else {
                tempQuantity += Integer.parseInt(summarybysize.get(index).getSummary());
            }
            result.put(tempMaterialname, tempQuantity);
        }
        return result;
    }

    public List<TreeMap> getHeaderTransaction() {
        List<TreeMap> arrlist = new ArrayList<TreeMap>();
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(" SELECT h_id, h_name, h_filename, h_create_date FROM ou_header_transaction_depart where h_id != 99 ");
            rs = ps.executeQuery();
            while (rs.next()) {
                String datetime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("h_create_date"));
                TreeMap<String, String> objtree = new TreeMap<String, String>();
                objtree.put("h_id", String.valueOf(rs.getInt("h_id")));
                objtree.put("h_name", rs.getString("h_name"));
                objtree.put("h_filename", rs.getString("h_filename"));
                objtree.put("h_create_date", datetime);
                arrlist.add(objtree);
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

    private String generateSQLUpdateStatusItems(List<OUOrderListDepartment> orderList) {
        String text = "Update ou_header_material " +
                "SET hmat_status = 'uploaded' " +
                "WHERE hmat_id in (";
        for (int i = 0; i <= orderList.size() - 1; i++) {
            text += orderList.get(i).getMaterialID();
            if (i != orderList.size() - 1) {
                text += " , ";
            }
        }
        text += ")";
        return text;
    }

    private String generateSQLUpdateStatusDepartment(List<OUOrderListDepartment> orderList) {
        String SQLUpdateDepartment = "Update ou_upload_department " +
                "SET depart_status = 'uploaded' " +
                "WHERE depart_id in ( ";
        for (int i = 0; i <= orderList.size() - 1; i++) {
            SQLUpdateDepartment += orderList.get(i).getDepart_id();
            if (i != orderList.size() - 1) {
                SQLUpdateDepartment += " , ";
            }
        }
        SQLUpdateDepartment += " ) ";
        return SQLUpdateDepartment;
    }

    private String generateSQLText(List<OUOrderListDepartment> orderList, int headerpk) {
        String text = "";
        long milis = System.currentTimeMillis();
        java.sql.Timestamp Timestamp = new java.sql.Timestamp(milis);
        int lastestPK = getLatestPrimaryKey();

        if (headerpk != 0) {
            text += "INSERT ALL ";
            for (int i = 0; i <= orderList.size() - 1; i++) {
                text += " INTO ou_transaction_department values ";
                lastestPK += 1;
                text += "( " + lastestPK + ", " + orderList.get(i).getDepart_id() + ", " + orderList.get(i).getCompanyID() + ", " + orderList.get(i).getMaterialID() + ", '" + orderList.get(i).getSize() + "', '" + orderList.get(i).getQuantity() + "', '" + orderList.get(i).getMaterialfullname() + "', " + "'', " + "'new', " + headerpk + ", " + "to_timestamp('" + Timestamp + "', 'YYYY-MM-DD HH24:MI:SS.FF') )";
            }
            text += " select * from dual ";
        }

        return text;
    }

    private int getLatestPrimaryKey() {
        int primarykey = 0;
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(" SELECT max(tran_depart_id) as lastestpk FROM ou_transaction_department ");
            rs = ps.executeQuery();
            while (rs.next()) {
                primarykey = rs.getInt("lastestpk");
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
        return primarykey;
    }

    private int generateHeaderTransaction(HashMap<String, String> detailFile) {
        String path = detailFile.get("path");
        String[] filename = path.split("/");
        String topic = detailFile.get("topicname");
        int lastestpk = getPrimaryKeyHeaderTransaction();
        long millis = System.currentTimeMillis();
        java.sql.Timestamp Timestamp = new java.sql.Timestamp(millis);
        String sqltext = "INSERT INTO ou_header_transaction_depart " + "(h_id,h_name,h_filename,h_create_date) " + "VALUES (";
        lastestpk += 1;
        sqltext += lastestpk + ", '" + topic + "', '" + filename[3] + "', " + "to_timestamp('" + Timestamp + "', 'YYYY-MM-DD HH24:MI:SS.FF') )";
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(sqltext);
            int resultinsert = ps.executeUpdate();
            if (resultinsert > 0) {
                return lastestpk;
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
        return 0;
    }

    private int getPrimaryKeyHeaderTransaction() {
        int latestpk = 0;
        try {
            conn = ConnectDB.getConnection();
            ps = conn.prepareStatement(" SELECT max(h_id) as lastestpk FROM ou_header_transaction_depart ");
            rs = ps.executeQuery();
            while (rs.next()) {
                latestpk = rs.getInt("lastestpk");
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
        return latestpk;
    }

    private String generateMaterialCodeFullName(String matCode, String matSize) {
        String matCodeFull = "";
        int countCharStandardSize;
        int countCharSpacialSize;

        //เช็คว่ามีไซส์พิเศษหรือไม่ ถ้ามีค่าเท่ากับ -1 คือ ไม่มีไซส์พิเศษ
        if (matSize.indexOf("/") != -1) {
            //แยกไซส์พิเศษกับไซส์ธรรมดา
            String[] arrOfSize = matSize.split("/");
            //นับจำนวนว่าไซส์มีทั้งหมดกี่ตัวอักษร
            countCharStandardSize = arrOfSize[0].length();
            countCharSpacialSize = arrOfSize[1].length();

            //จัดรูปแบบให้กับไซส์พื้นฐาน
            switch (countCharStandardSize) {
                case 1:
                    matCodeFull = matCode + "00" + arrOfSize[0];
                    break;
                case 2:
                    matCodeFull = matCode + "0" + arrOfSize[0];
                    break;
                case 3:
                    matCodeFull = matCode + arrOfSize[0];
                    break;
            }

            //จัดรูปแบบให้กับไซส์พิเศษ
            switch (countCharSpacialSize) {
                case 1:
                    matCodeFull = matCodeFull + "00" + arrOfSize[1];
                    break;
                case 2:
                    matCodeFull = matCodeFull + "0" + arrOfSize[1];
                    break;
                case 3:
                    matCodeFull = matCodeFull + arrOfSize[1];
                    break;
            }
        } else {
            countCharStandardSize = matSize.length();
            switch (countCharStandardSize) {
                case 1:
                    matCodeFull = matCode + "00" + matSize + "000";
                    break;
                case 2:
                    matCodeFull = matCode + "0" + matSize + "000";
                    break;
                case 3:
                    matCodeFull = matCode + matSize + "000";
                    break;
            }
        }

        return matCodeFull;
    }
}
