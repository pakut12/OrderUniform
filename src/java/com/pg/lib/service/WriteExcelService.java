/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pg.lib.service;

import com.pg.lib.model.OUCustomerDetailWithID;
import com.pg.lib.model.OUDepartmentDetailWithID;
import com.pg.lib.model.OUMaterialDetailWithID;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import java.io.FileOutputStream;
import java.util.List;

public class WriteExcelService {
    
        private final String pathFileGenerate_Local = "C:/Users/pakutsing/Desktop/Github/OU/web/attachfile/download/";
        private final String pathFileGenerate_Server = "/web/webapps/OrderUniform/attachfile/download/";
        private final String defaultDownloadPath = "/OrderUniform/attachfile/download/";
    
        public String generateXLSFileCustomer(List<String> company, List<OUMaterialDetailWithID> item, List<OUCustomerDetailWithID> customer){
        String path = "";
        String XLSname = GenerateXLSName(company);
        String concatpath = pathFileGenerate_Local+"customer/"+XLSname;
        HSSFWorkbook workbook = GenerateDetailXLSCustomer(item,customer);
            
            try {
                FileOutputStream outputStream = new FileOutputStream(concatpath);
                workbook.write(outputStream);
                outputStream.close();
                path = defaultDownloadPath+"customer/"+XLSname;
            } catch (Exception e){
                e.printStackTrace();
            }
        
        return path;    
    }
    
    public String generateXLSFileDepartment(List<String> company, List<OUMaterialDetailWithID> item, List<OUDepartmentDetailWithID> department){
        String path = "";
        String XLSname = GenerateXLSName(company);
        String concatpath = pathFileGenerate_Local+"department/"+XLSname;
        HSSFWorkbook workbook = GenerateDetailXLSDepartment(item, department);
            try {
                FileOutputStream outputStream = new FileOutputStream(concatpath);
                workbook.write(outputStream);
                outputStream.close();
                path = defaultDownloadPath+"department/"+XLSname;
            } catch (Exception e){
                e.printStackTrace();
            }
        return path;
    }
    
    private String GenerateXLSName(List<String> company){
            String currentMili =  Long.toString(System.currentTimeMillis());
            //index 1 = company id ;
            //index 3 = type {customer or department}; 
            String name = company.get(1)+"_"+company.get(3)+"_"+currentMili+".xls";
        return name;
    }
    
    private HSSFWorkbook GenerateDetailXLSDepartment(List<OUMaterialDetailWithID> item, List<OUDepartmentDetailWithID> department){
        int coulumnItem = 5 ; 
        int coulumnItemDetail = 5;
        int coulumnItemID = 5;
        int coulumnOfDepartment = 3;
            
            HSSFWorkbook workbook = new HSSFWorkbook();
            
            HSSFSheet sheet = workbook.createSheet("OrderDepartment");
            
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            
                Row row = sheet.createRow(0);
                
                Cell topicDepartment = row.createCell(0);
                
                topicDepartment.setCellValue("ข้อมูลแผนก");
                
                sheet.addMergedRegion(new CellRangeAddress(0,2,0,4));
                
                topicDepartment.setCellStyle(cellStyle);
                
                
                Row rowItemID = sheet.createRow(1);
                for(int i = 0 ; i <= item.size()-1 ; i++){
                    
                    Cell cell = row.createCell(coulumnItem);
                    
                    cell.setCellValue(item.get(i).getHmat_code());
                    
                    sheet.addMergedRegion(new CellRangeAddress(0,0,coulumnItem,coulumnItem+1));
                    
                    cell.setCellStyle(cellStyle);
                    
                    coulumnItem = coulumnItem + 2;
                    
                    Cell cellItemID = rowItemID.createCell(coulumnItemID);
                    
                    cellItemID.setCellValue(item.get(i).getHmat_id());
                    
                    sheet.addMergedRegion(new CellRangeAddress(1,1,coulumnItemID,coulumnItemID+1));
                    
                    cellItemID.setCellStyle(cellStyle);
                    
                    coulumnItemID = coulumnItemID + 2;
                }
                
                Row rowItemdetail = sheet.createRow(2);
                for(int j = 0 ; j <= item.size()-1 ; j++){
                    
                    Cell cellItemSize = rowItemdetail.createCell(coulumnItemDetail);
                    
                    cellItemSize.setCellValue("size");
                    
                    cellItemSize.setCellStyle(cellStyle);
                     
                    Cell cellItemQuantity = rowItemdetail.createCell(coulumnItemDetail+1);
                    
                    cellItemQuantity.setCellValue("จำนวน");
                    
                    cellItemQuantity.setCellStyle(cellStyle);
                    
                    coulumnItemDetail = coulumnItemDetail + 2;
                }
                
                
                for(int k = 0 ; k <= department.size()-1 ; k++){
                    
                    Row rowDepartmentDetail = sheet.createRow(coulumnOfDepartment);
                    
                        for(int l = 0 ; l <= 4 ; l++){
                            
                            Cell Cellofdepartment = rowDepartmentDetail.createCell(l);
                            
                            if(l==0) {
                                Cellofdepartment.setCellValue(department.get(k).getDepart_id());
                            } else if (l==1){
                                Cellofdepartment.setCellValue(department.get(k).getDepart_agency());
                            } else if (l==2){
                                Cellofdepartment.setCellValue(department.get(k).getDepart_division());
                            } else if (l==3){
                                Cellofdepartment.setCellValue(department.get(k).getDepart_name());
                            } else if (l==4){
                                Cellofdepartment.setCellValue(department.get(k).getCompany_id());
                            }
                        }
                    coulumnOfDepartment += 1;
                }
        return workbook;
    }
    
    private HSSFWorkbook GenerateDetailXLSCustomer(List<OUMaterialDetailWithID> item, List<OUCustomerDetailWithID> customer){
        int coulumnItem = 4 ; 
        int coulumnItemDetail = 4;
        int coulumnItemID = 4;
        int coulumnOfCustomer = 3;
        
            //สร้าง Excel ไฟล์
            HSSFWorkbook workbook = new HSSFWorkbook();
            
            //สร้าง Sheet 
            HSSFSheet sheet = workbook.createSheet("OrderCustomer");
            
            //สร้าง Style โดยการกำหนดให้ตัวอักษรอยู่ตรงกลางของ Cell
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            
            
            //****** รายชื่อพนักงาน *******//
                //สร้าง Row ที่ 0
                Row row = sheet.createRow(0);
                //สร้าง Column ที่ 0
                Cell topicCustomer = row.createCell(0);
                //กำหนดค่าลงช่อง Row 0 , Column 0;
                topicCustomer.setCellValue("ข้อมูลพนักงาน");
                //Merge Column ตำแหน่งที่ (start Row,End Row,start Column,End Column);
                sheet.addMergedRegion(new CellRangeAddress(0,2,0,3));
                //เซ็ตสไตล์ให้ Column และ Row ที่ merge
                topicCustomer.setCellStyle(cellStyle);
            
                Row rowItemID = sheet.createRow(1);
            //***** สร้าง column ตามรายชื่อไอเทมที่เก็บมา *****//
            for(int i = 0 ; i <= item.size()-1 ; i++ ){
                //สร้าง Row ใหม่ตำแหน่งที่ 3 ถัดจาก topic
                Cell cell = row.createCell(coulumnItem);
                //เอาค่าที่ออกมาจาก array หยอดลง Column 
                cell.setCellValue(item.get(i).getHmat_code());
                //merge Column 
                sheet.addMergedRegion(new CellRangeAddress(0,0,coulumnItem,coulumnItem+1));
                //กำหนดสไตล์
                cell.setCellStyle(cellStyle);
                //กำหนดตำแหน่ง cell ถัดไปให้เริ่มห่างจาก cell เก่าไป 3 ตำแหน่ง
                coulumnItem = coulumnItem + 2;
                
                 //สร้าง cell โดยเริ่มจากตำแหน่งที่ 3 หรือ column 4
                Cell cellItemID = rowItemID.createCell(coulumnItemID);
                //ใส่ค่า
                cellItemID.setCellValue(item.get(i).getHmat_id());
                //merge Column 
                sheet.addMergedRegion(new CellRangeAddress(1,1,coulumnItemID,coulumnItemID+1));
                //กำหนดสไตล์
                cellItemID.setCellStyle(cellStyle);
                //กำหนดตำแหน่ง cell ถัดไปให้เริ่มห่างจาก cell เก่าไป 2 ตำแหน่ง
                coulumnItemID = coulumnItemID +2;
            }
            
              
            //***** สร้าง column รายละเอียดย่อย ไว้สำหรับกรอกข้อมูล *****//    
                // กำหนดให้เริ่มที่ row ตำแหน่งที่ 2 หรือก็คือ row 3
                Row rowItemdetail = sheet.createRow(2);
            for(int j = 0 ; j <= item.size()-1; j++){
                //สร้าง cell โดยเริ่มจากตำแหน่งที่ 3 หรือ column 4
                Cell cellItemsize = rowItemdetail.createCell(coulumnItemDetail);
                //ใส่ค่า
                cellItemsize.setCellValue("size");
                //ใส่สไตล์
                cellItemsize.setCellStyle(cellStyle);

                //สร้าง cell โดยเริ่มจากตำแหน่งที่ 5 หรือ column 6
                Cell cellItemQt = rowItemdetail.createCell(coulumnItemDetail+1);
                //ใส่ค่า
                cellItemQt.setCellValue("จำนวน");
                //ใส่สไตล์
                cellItemQt.setCellStyle(cellStyle);

                //กำหนดค่าให้ข้อมูลชุดถนัดเริ่มตำแหน่ง cell ที่ + ไปอีก 2;
                coulumnItemDetail = coulumnItemDetail + 2;
            }
            
            //loop อ่านค่าจาก obj customer
            for(int k = 0; k <= customer.size()-1 ; k++){
                //ชี้ตำแหน่ง Row ที่จะทำการสร้าง coulumnOfCustomer = 2
                Row rowCustomerDetail = sheet.createRow(coulumnOfCustomer);
                    // loop กำหนดให้สร้างเฉพาะสามคอลัมน์แรก 
                    for(int l = 0 ; l <= 3 ; l++){
                        // กำหนดตำแหน่งของ column โดยวนตามลูป
                        Cell Cellofcustomer = rowCustomerDetail.createCell(l);
                        //กระจายค่าแต่ละคอลัมน์
                        if(l == 0){
                            Cellofcustomer.setCellValue(customer.get(k).getCus_id());
                        } else if (l == 1){
                            Cellofcustomer.setCellValue(customer.get(k).getCus_no());
                        } else if (l == 2){
                            Cellofcustomer.setCellValue(customer.get(k).getCus_fname());
                        } else if (l == 3){
                            Cellofcustomer.setCellValue(customer.get(k).getCompany_id());
                        }
                    }
                coulumnOfCustomer = coulumnOfCustomer + 1;
            }
                
        return workbook ;
    }
}
