package com.pg.lib.service;

import com.pg.lib.model.OUOrderList;
import com.pg.lib.model.OUSummaryOrderByCustomer;
import com.pg.lib.model.OUSummaryOrderCustomerByMaterialAndSize;
import com.pg.lib.model.OUTransactionCustomerDetail;
import com.pg.lib.model.OUUCustomerOrder;
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

public class TransactionCustomerService {
    
    private Connection conn ;
    private PreparedStatement ps ;
    private ResultSet rs;
    
    public int createNewTransaction(List<OUOrderList> orderlist,HashMap<String,String> detailFile){
          int headerpk = generateHeaderTransaction(detailFile);
          String sqlplaintext = generateSQLText(orderlist,headerpk);
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqlplaintext);
                int i = ps.executeUpdate();
                    if( i > 0){
                        return headerpk;
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
        return 0;
    }
       
    public boolean updateStatusCustomers(List<OUOrderList> orderlist){
        String sqlUpdate = generateSQLUpdateStatusCustomer(orderlist);
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqlUpdate);
                int result = ps.executeUpdate();
                    if(result > 0){
                        return true;
                    }
            } catch (Exception e){
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
        return false;
    }
    
    public boolean updateStatusItems(List<OUOrderList> orderlist){
         String sqlUpdate = generateSQLUpdateStatusItems(orderlist);
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqlUpdate);
                int result = ps.executeUpdate();
                    if(result > 0){
                        return true;
                    }
            } catch (Exception e){
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
        return false;
    }
   
    
    public List<OUTransactionCustomerDetail> getDetailTransactionByDocumentId(String doc_id){
        List<OUTransactionCustomerDetail> listdetailtransaction = new ArrayList<OUTransactionCustomerDetail>();
        String sqlquery = "SELECT " +
                "a.h_id as docID, " +
                "b.tran_cus_id as transactionID, " +
                "c.cus_id as customerID, " +
                "e.comp_id as companyID, " +
                "d.hmat_id as materialID, " +
                "a.h_name as docName, " +
                "c.cus_prename as prename, " +
                "c.cus_fname as fullname, " +
                "c.cus_no as customerCode, " +
                "c.cus_department as departmentname, " +
                "d.hmat_code as materialname, " +
                "d.hmat_color as materialcolor, " + 
                "d.hmat_desc as materialdesc, " +
                "b.tran_cus_size as materialsize, " +
                "b.tran_cus_quatity as materialquantity, " +
                "e.comp_name as companyname, " +
                "b.tran_cus_matcode as matfullname, " +
                "b.tran_cus_barcode as barcode " +
                "FROM ou_header_transaction_customer a " +
                "LEFT JOIN ou_transaction_customer b ON a.h_id = b.header_id " +
                "LEFT JOIN ou_upload_customer c ON b.cus_id = c.cus_id " +
                "LEFT JOIN ou_header_material d ON b.hmat_id = d.hmat_id " +
                "LEFT JOIN ou_company e ON b.compa_id = e.comp_id " +
                "WHERE " +
                "a.h_id = ? " +
                "ORDER BY b.tran_cus_id asc ";
                try {
                    conn = ConnectDB.getConnection();
                    ps = conn.prepareStatement(sqlquery);
                    ps.setString(1, doc_id);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            OUTransactionCustomerDetail obj = new OUTransactionCustomerDetail();
                                obj.setDocID(rs.getInt("docID"));
                                obj.setTransactionID(rs.getInt("transactionID"));
                                obj.setCustomerID(rs.getInt("customerID"));
                                obj.setCompanyID(rs.getInt("companyID"));
                                obj.setMaterialID(rs.getInt("materialID"));
                                obj.setDocName(rs.getString("docName"));
                                obj.setPrename(rs.getString("prename"));
                                obj.setCustomerCode(rs.getString("customerCode"));
                                obj.setFname(rs.getString("fullname"));
                                obj.setDepartmentname(rs.getString("departmentname"));
                                obj.setMaterialname(rs.getString("materialname"));
                                obj.setColor(rs.getString("materialcolor"));
                                obj.setDesc(rs.getString("materialdesc"));
                                obj.setSize(rs.getString("materialsize"));
                                obj.setQuantity(rs.getString("materialquantity"));
                                obj.setCompanyname(rs.getString("companyname"));
                                obj.setMatfullname(rs.getString("matfullname"));
                                obj.setBarcode(rs.getString("barcode"));
                            listdetailtransaction.add(obj);
                        }
                } catch (Exception e){
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
        return listdetailtransaction;
    }
    public List<OUTransactionCustomerDetail> getDetailFromBarcodeDepart(String doc_id,String cus_department){
        List<OUTransactionCustomerDetail> listdetailtransaction = new ArrayList<OUTransactionCustomerDetail>();
        String sqlquery = "SELECT " +
                "a.h_id as docID, " +
                "b.tran_cus_id as transactionID, " +
                "c.cus_id as customerID, " +
                "e.comp_id as companyID, " +
                "d.hmat_id as materialID, " +
                "a.h_name as docName, " +
                "c.cus_prename as prename, " +
                "c.cus_fname as fullname, " +
                "c.cus_no as customerCode, " +
                "c.cus_department as departmentname, " +
                "d.hmat_code as materialname, " +
                "d.hmat_color as materialcolor, " + 
                "d.hmat_desc as materialdesc, " +
                "b.tran_cus_size as materialsize, " +
                "b.tran_cus_quatity as materialquantity, " +
                "e.comp_name as companyname, " +
                "b.tran_cus_matcode as matfullname, " +
                "b.tran_cus_barcode as barcode " +
                "FROM ou_header_transaction_customer a " +
                "LEFT JOIN ou_transaction_customer b ON a.h_id = b.header_id " +
                "LEFT JOIN ou_upload_customer c ON b.cus_id = c.cus_id " +
                "LEFT JOIN ou_header_material d ON b.hmat_id = d.hmat_id " +
                "LEFT JOIN ou_company e ON b.compa_id = e.comp_id " +
                "WHERE " +
                "a.h_id = ? and c.cus_department = ?" +
                "ORDER BY c.cus_department asc ";
                try {
                    conn = ConnectDB.getConnection();
                    ps = conn.prepareStatement(sqlquery);
                    ps.setString(1, doc_id);
                    ps.setString(2, cus_department); 
                    rs = ps.executeQuery();
                        while(rs.next()){
                            OUTransactionCustomerDetail obj = new OUTransactionCustomerDetail();
                                obj.setDocID(rs.getInt("docID"));
                                obj.setTransactionID(rs.getInt("transactionID"));
                                obj.setCustomerID(rs.getInt("customerID"));
                                obj.setCompanyID(rs.getInt("companyID"));
                                obj.setMaterialID(rs.getInt("materialID"));
                                obj.setDocName(rs.getString("docName"));
                                obj.setPrename(rs.getString("prename"));
                                obj.setCustomerCode(rs.getString("customerCode"));
                                obj.setFname(rs.getString("fullname"));
                                obj.setDepartmentname(rs.getString("departmentname"));
                                obj.setMaterialname(rs.getString("materialname"));
                                obj.setColor(rs.getString("materialcolor"));
                                obj.setDesc(rs.getString("materialdesc"));
                                obj.setSize(rs.getString("materialsize"));
                                obj.setQuantity(rs.getString("materialquantity"));
                                obj.setCompanyname(rs.getString("companyname"));
                                obj.setMatfullname(rs.getString("matfullname"));
                                obj.setBarcode(rs.getString("barcode"));
                            listdetailtransaction.add(obj);
                        }
                } catch (Exception e){
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
        return listdetailtransaction;
    }
     public List<OUTransactionCustomerDetail> getDetailFromBarcodeDepartForPrint (String doc_id,String cus_department,String cus_no){
        List<OUTransactionCustomerDetail> listdetailtransaction = new ArrayList<OUTransactionCustomerDetail>();
        String sqlquery = "SELECT " +
                "a.h_id as docID, " +
                "b.tran_cus_id as transactionID, " +
                "c.cus_id as customerID, " +
                "e.comp_id as companyID, " +
                "d.hmat_id as materialID, " +
                "a.h_name as docName, " +
                "c.cus_prename as prename, " +
                "c.cus_fname as fullname, " +
                "c.cus_no as customerCode, " +
                "c.cus_department as departmentname, " +
                "d.hmat_code as materialname, " +
                "d.hmat_color as materialcolor, " + 
                "d.hmat_desc as materialdesc, " +
                "b.tran_cus_size as materialsize, " +
                "b.tran_cus_quatity as materialquantity, " +
                "e.comp_name as companyname, " +
                "b.tran_cus_matcode as matfullname, " +
                "b.tran_cus_barcode as barcode " +
                "FROM ou_header_transaction_customer a " +
                "LEFT JOIN ou_transaction_customer b ON a.h_id = b.header_id " +
                "LEFT JOIN ou_upload_customer c ON b.cus_id = c.cus_id " +
                "LEFT JOIN ou_header_material d ON b.hmat_id = d.hmat_id " +
                "LEFT JOIN ou_company e ON b.compa_id = e.comp_id " +
                "WHERE " +
                "a.h_id = ? and c.cus_department = ? and c.cus_no= ?" +
                "ORDER BY c.cus_department asc ";
                try {
                    conn = ConnectDB.getConnection();
                    ps = conn.prepareStatement(sqlquery);
                    ps.setString(1, doc_id);
                    ps.setString(2, cus_department);
                    ps.setString(3, cus_no);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            OUTransactionCustomerDetail obj = new OUTransactionCustomerDetail();
                                obj.setDocID(rs.getInt("docID"));
                                obj.setTransactionID(rs.getInt("transactionID"));
                                obj.setCustomerID(rs.getInt("customerID"));
                                obj.setCompanyID(rs.getInt("companyID"));
                                obj.setMaterialID(rs.getInt("materialID"));
                                obj.setDocName(rs.getString("docName"));
                                obj.setPrename(rs.getString("prename"));
                                obj.setCustomerCode(rs.getString("customerCode"));
                                obj.setFname(rs.getString("fullname"));
                                obj.setDepartmentname(rs.getString("departmentname"));
                                obj.setMaterialname(rs.getString("materialname"));
                                obj.setColor(rs.getString("materialcolor"));
                                obj.setDesc(rs.getString("materialdesc"));
                                obj.setSize(rs.getString("materialsize"));
                                obj.setQuantity(rs.getString("materialquantity"));
                                obj.setCompanyname(rs.getString("companyname"));
                                obj.setMatfullname(rs.getString("matfullname"));
                                obj.setBarcode(rs.getString("barcode"));
                            listdetailtransaction.add(obj);
                        }
                } catch (Exception e){
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
        return listdetailtransaction;
    }
    public List<OUTransactionCustomerDetail> getDetailFromBarcode(String doc_id,String cus_no){
        List<OUTransactionCustomerDetail> listdetailtransaction = new ArrayList<OUTransactionCustomerDetail>();
        String sqlquery = "SELECT " +
                "a.h_id as docID, " +
                "b.tran_cus_id as transactionID, " +
                "c.cus_id as customerID, " +
                "e.comp_id as companyID, " +
                "d.hmat_id as materialID, " +
                "a.h_name as docName, " +
                "c.cus_prename as prename, " +
                "c.cus_fname as fullname, " +
                "c.cus_no as customerCode, " +
                "c.cus_department as departmentname, " +
                "d.hmat_code as materialname, " +
                "d.hmat_color as materialcolor, " + 
                "d.hmat_desc as materialdesc, " +
                "b.tran_cus_size as materialsize, " +
                "b.tran_cus_quatity as materialquantity, " +
                "e.comp_name as companyname, " +
                "b.tran_cus_matcode as matfullname, " +
                "b.tran_cus_barcode as barcode " +
                "FROM ou_header_transaction_customer a " +
                "LEFT JOIN ou_transaction_customer b ON a.h_id = b.header_id " +
                "LEFT JOIN ou_upload_customer c ON b.cus_id = c.cus_id " +
                "LEFT JOIN ou_header_material d ON b.hmat_id = d.hmat_id " +
                "LEFT JOIN ou_company e ON b.compa_id = e.comp_id " +
                "WHERE " +
                "a.h_id = ? and c.cus_no = ?" +
                "ORDER BY c.cus_department asc ";
                try {
                    conn = ConnectDB.getConnection();
                    ps = conn.prepareStatement(sqlquery);
                    ps.setString(1, doc_id);
                    ps.setString(2, cus_no);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            OUTransactionCustomerDetail obj = new OUTransactionCustomerDetail();
                                obj.setDocID(rs.getInt("docID"));
                                obj.setTransactionID(rs.getInt("transactionID"));
                                obj.setCustomerID(rs.getInt("customerID"));
                                obj.setCompanyID(rs.getInt("companyID"));
                                obj.setMaterialID(rs.getInt("materialID"));
                                obj.setDocName(rs.getString("docName"));
                                obj.setPrename(rs.getString("prename"));
                                obj.setCustomerCode(rs.getString("customerCode"));
                                obj.setFname(rs.getString("fullname"));
                                obj.setDepartmentname(rs.getString("departmentname"));
                                obj.setMaterialname(rs.getString("materialname"));
                                obj.setColor(rs.getString("materialcolor"));
                                obj.setDesc(rs.getString("materialdesc"));
                                obj.setSize(rs.getString("materialsize"));
                                obj.setQuantity(rs.getString("materialquantity"));
                                obj.setCompanyname(rs.getString("companyname"));
                                obj.setMatfullname(rs.getString("matfullname"));
                                obj.setBarcode(rs.getString("barcode"));
                            listdetailtransaction.add(obj);
                        }
                } catch (Exception e){
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
        return listdetailtransaction;
    }
    
    public List<OUSummaryOrderByCustomer> summaryOrderByCustomer(String doc_id){
        List<OUSummaryOrderByCustomer> listResult = new ArrayList<OUSummaryOrderByCustomer>();
        String plainSQL = "SELECT " +
                          "b.cus_id as customerid, " +
                          "c.cus_no as customercode, " +
                          "c.cus_department as departmentname, " +
                          "c.cus_prename as prename, " +
                          "c.cus_fname as customername, " +
                          "sum(b.tran_cus_quatity) as quantity " +
                          "FROM ou_transaction_customer b " +
                          "LEFT JOIN ou_upload_customer c ON b.cus_id = c.cus_id " +
                          "LEFT JOIN ou_header_material d ON b.hmat_id = d.hmat_id " +
                          "WHERE b.header_id = ? " +
                          "GROUP BY b.cus_id, c.cus_no, c.cus_prename, c.cus_fname ,c.cus_department " +
                          "ORDER BY b.cus_id ";
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(plainSQL);
                ps.setString(1, doc_id);
                rs = ps.executeQuery();
                    while(rs.next()){
                        OUSummaryOrderByCustomer obj = new OUSummaryOrderByCustomer();
                            obj.setCustomerid(rs.getString("customerid"));
                            obj.setCustomerno(rs.getString("customercode"));
                            obj.setCustomerprename(rs.getString("prename"));
                            obj.setCustomername(rs.getString("customername"));
                            obj.setDepartmentname(rs.getString("departmentname"));
                            obj.setQuantity(rs.getString("quantity"));
                            
                            
                        listResult.add(obj);
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    ConnectDB.closeConnection(conn);
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        return listResult;
    }
    
    public List<OUSummaryOrderCustomerByMaterialAndSize> summaryOrderByMaterialAndSize(String doc_id){
        List<OUSummaryOrderCustomerByMaterialAndSize> result = new ArrayList<OUSummaryOrderCustomerByMaterialAndSize>(); 
        String sqlString = "SELECT " +
                           "a.hmat_id as materialid, " +
                           "b.hmat_code as materialname, " +
                           "b.hmat_desc as materialdesc, " +
                           "a.tran_cus_size as materialsize, " +
                           "sum(a.tran_cus_quatity) as summary " +
                           "FROM ou_transaction_customer a " +
                           "LEFT JOIN ou_header_material b ON a.hmat_id = b.hmat_id " +
                           "WHERE a.header_id = ? " +
                           "GROUP BY a.hmat_id, b.hmat_code, b.hmat_desc ,a.tran_cus_size " +
                           "ORDER BY a.hmat_id ";
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqlString);
                ps.setString(1,doc_id);
                rs = ps.executeQuery();
                    while(rs.next()){
                        OUSummaryOrderCustomerByMaterialAndSize obj = new OUSummaryOrderCustomerByMaterialAndSize();
                            obj.setMaterialid(rs.getString("materialid"));
                            obj.setMaterialname(rs.getString("materialname"));
                            obj.setMaterialdesc(rs.getString("materialdesc"));
                            obj.setMaterialsize(rs.getString("materialsize"));
                            obj.setQuantitySummary(rs.getString("summary"));
                        result.add(obj);
                    }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try {
                    rs.close();
                    ps.close();
                    ConnectDB.closeConnection(conn);
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        return result;
    }
    
    public HashMap<String,Integer> calculateTotalByMaterial(List<OUSummaryOrderCustomerByMaterialAndSize> data){
        String tempmaterialcode = "";
        int tempquantity = 0;
        HashMap<String,Integer> result = new HashMap<String,Integer>();
        
            for(int i = 0 ; i <= data.size()-1 ; i++){
                if(!data.get(i).getMaterialid().equals(tempmaterialcode)){
                    tempmaterialcode = data.get(i).getMaterialid();
                    tempquantity = Integer.parseInt(data.get(i).getQuantitySummary());
                }else if(data.get(i).getMaterialid().equals(tempmaterialcode)){
                    tempquantity += Integer.parseInt(data.get(i).getQuantitySummary());
                }
                result.put(tempmaterialcode, tempquantity);
            }
        
        return result;
    }
    
    public List<TreeMap> getHeaderTransaction(){
        List<TreeMap> arrlist = new ArrayList<TreeMap>();
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT h_id, h_name, h_filename, h_filename, h_create_date FROM ou_header_transaction_customer WHERE h_id != 99 ");
                rs = ps.executeQuery();
                    while(rs.next()){
                        String datetime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(rs.getTimestamp("h_create_date"));
                        TreeMap<String,String> objtree = new TreeMap<String,String>();
                            objtree.put("h_id", String.valueOf(rs.getInt("h_id")));
                            objtree.put("h_name", rs.getString("h_name"));
                            objtree.put("h_filename", rs.getString("h_filename"));
                            objtree.put("h_create_date", datetime);
                        arrlist.add(objtree);
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
        return arrlist;
    }
    
    private String generateSQLText(List<OUOrderList> orderlist,int headerpk){
        String text = "";
        long millis=System.currentTimeMillis();  
        java.sql.Timestamp  Timestamp  = new java.sql.Timestamp (millis);  
        int primarykey = getLatestPrimaryKey();    
        
            if(headerpk != 0){
                text += "INSERT ALL ";
                    for(int i = 0; i <= orderlist.size()-1 ; i++){
                        text += " INTO ou_transaction_customer values ";
                                primarykey = primarykey + 1;
                        text += "( "+primarykey+", " 
                                +orderlist.get(i).getCustomerId()+", "
                                +orderlist.get(i).getCompanyId()+", "
                                +orderlist.get(i).getMaterialId()+", '"
                                +orderlist.get(i).getSize()+"', '"
                                +orderlist.get(i).getQuantity()+"', '"
                                +orderlist.get(i).getMaterialfullname()+"', "
                                +"'', "
                                +"'new', "
                                +headerpk+", "
                                +"to_timestamp('"+Timestamp+"', 'YYYY-MM-DD HH24:MI:SS.FF') )";          
                    }
                text += " select * from dual";
            }

        return text;
    }
    
    private int getLatestPrimaryKey(){
        
        int primarykey = 0;
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(" SELECT max(tran_cus_id) as pk FROM ou_transaction_customer ");
                rs = ps.executeQuery();
                    while(rs.next()){
                       primarykey = rs.getInt("pk");
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
        return primarykey;
        
    }
    
    public List<OUOrderList> processData(List<OUUCustomerOrder> transactiondetail){
        List<OUOrderList> result = new ArrayList<OUOrderList>();
        String matCode , matSize, matQty , matFullname ;
        int matID, comID, cusID; 
        
            for(int i = 0 ; i<= transactiondetail.size()-1 ; i++){
                for(int j = 0 ; j <= transactiondetail.get(i).getItem().size()-1 ; j++){
                    if(transactiondetail.get(i).getItem().get(j).getSize().equals("-") &&
                       transactiondetail.get(i).getItem().get(j).getQty().equals("-")){
                       continue;
                    } else {
                        OUOrderList obj = new OUOrderList();
                        
                            // กำหนดตัวแปร
                            cusID = transactiondetail.get(i).getId();
                            comID = transactiondetail.get(i).getCompanyid();
                            matID = transactiondetail.get(i).getItem().get(j).getMatid();
                            matCode = transactiondetail.get(i).getItem().get(j).getMatcode();
                            matSize = transactiondetail.get(i).getItem().get(j).getSize();
                            matQty = transactiondetail.get(i).getItem().get(j).getQty();
                            
                            //concat รหัสสินค้ารวมกับ Size และ Spacial Size
                            matFullname = generateMaterialCodeFullName(matCode,matSize);
                            
                            //เซ็ต Obj
                            obj.setCustomerId(cusID);
                            obj.setCompanyId(comID);
                            obj.setMaterialId(matID);
                            obj.setMaterialfullname(matFullname);
                            obj.setSize(matSize);
                            obj.setQuantity(matQty);
                            
                        result.add(obj);
                    }
                }
            }
        
        return result;
    }
    
    private String generateSQLUpdateStatusItems(List<OUOrderList> transactiondetail){
        String text =   "Update ou_header_material " +
                        "SET hmat_status = 'uploaded' " +
                        "WHERE hmat_id in (";
            for(int i = 0 ; i <= transactiondetail.size()-1 ; i++){
                text += transactiondetail.get(i).getMaterialId();
                if(i!=transactiondetail.size()-1){
                    text += " , ";
                }
            }
        text += ")";
        return text;
    }
    
    private String generateSQLUpdateStatusCustomer(List<OUOrderList> transactiondetail){
        String SQLUpdateCustomer =  "UPDATE ou_upload_customer " +
                                    "SET cus_status = 'uploaded' " +
                                    "WHERE cus_id in ( ";
            for(int i = 0 ; i <= transactiondetail.size()-1 ; i++){
                SQLUpdateCustomer += transactiondetail.get(i).getCustomerId();
                if(i!=transactiondetail.size()-1){
                    SQLUpdateCustomer += " , ";
                }
            }
        SQLUpdateCustomer += " ) ";
        return SQLUpdateCustomer;
    }
    
    private int generateHeaderTransaction(HashMap<String,String> detailFile){
        
        String path = detailFile.get("path");
        String[] filename = path.split("/");
        String topic = detailFile.get("topicname");
        int lastestpk = getPrimarykeyHeaderTransaction();
        long millis=System.currentTimeMillis();  
        java.sql.Timestamp  Timestamp  = new java.sql.Timestamp (millis);  
        String sqltext = "INSERT INTO ou_header_transaction_customer " 
                       + "(h_id,h_name,h_filename,h_create_date) " 
                       + "VALUES (";
                       lastestpk = lastestpk+1;
               sqltext += lastestpk+", '" 
                        + topic +"', '"
                        + filename[3] +"', "
                        + "to_timestamp('"+Timestamp+"', 'YYYY-MM-DD HH24:MI:SS.FF') )";        
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement(sqltext);
                int resultinsert = ps.executeUpdate();
                    if(resultinsert > 0){
                        return lastestpk;
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
        return 0;
        
    }
    
    private int getPrimarykeyHeaderTransaction(){
        int latestpk = 0;
            try {
                conn = ConnectDB.getConnection();
                ps = conn.prepareStatement("SELECT max(h_id) as latestpk FROM ou_header_transaction_customer");
                rs = ps.executeQuery();
                    while(rs.next()){
                      latestpk = rs.getInt("latestpk");
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
        return latestpk;
    }
    
    private String generateMaterialCodeFullName(String matCode, String matSize){
        String matCodeFull = "";
        int countCharStandardSize ;
        int countCharSpacialSize ;
        
            //เช็คว่ามีไซส์พิเศษหรือไม่ ถ้ามีค่าเท่ากับ -1 คือ ไม่มีไซส์พิเศษ
            if(matSize.indexOf("/") != -1){
                 //แยกไซส์พิเศษกับไซส์ธรรมดา
                 String[] arrOfSize = matSize.split("/");
                 //นับจำนวนว่าไซส์มีทั้งหมดกี่ตัวอักษร
                 countCharStandardSize = arrOfSize[0].length();
                 countCharSpacialSize = arrOfSize[1].length();
                 
                        //จัดรูปแบบให้กับไซส์พื้นฐาน
                        switch(countCharStandardSize){
                            case 1 :
                                matCodeFull = matCode+"00"+arrOfSize[0];
                                break;
                            case 2 :
                                matCodeFull = matCode+"0"+arrOfSize[0];
                                break;
                            case 3 :
                                matCodeFull = matCode+arrOfSize[0];
                                break;
                        }       
                        
                        //จัดรูปแบบให้กับไซส์พิเศษ
                        switch(countCharSpacialSize){
                            case 1 :
                                matCodeFull = matCodeFull+"00"+arrOfSize[1];
                                break;
                            case 2 :
                                matCodeFull = matCodeFull+"0"+arrOfSize[1];
                                break;
                            case 3 :
                                matCodeFull = matCodeFull+arrOfSize[1];
                                break;    
                        }
            } else {
                countCharStandardSize = matSize.length();
                    switch(countCharStandardSize){
                        case 1 :
                            matCodeFull = matCode+"00"+matSize+"000";
                            break;
                        case 2 :
                            matCodeFull = matCode+"0"+matSize+"000";
                            break;
                        case 3 :
                            matCodeFull = matCode+matSize+"000";
                            break;
                    }      
            }

        return matCodeFull;
    }
}
