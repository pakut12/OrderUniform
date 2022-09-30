/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.service;

import com.pg.lib.model.OUUCustomerOrder;
import com.pg.lib.model.OUUDepartmentOrder;
import com.pg.lib.model.OUUItemOrder;
import com.pg.lib.model.OUUploadCustomer;
import com.pg.lib.model.OUUploadDepartment;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author 111525
 */
public class ReadExcelService {
    //C:/Users/pakutsing/Desktop/Github/OU/web/attachfile/

    private final String pathAttachfileLocalhost = "C:/Users/pakutsing/Desktop/Github/OU/web/attachfile/";
    private final String pathAttachfileOnServer = "/web/webapps/OrderUniform/attachfile/";

    public List<OUUploadCustomer> readExcelFileCustomer(HashMap<String, String> pathfile)
            throws FileNotFoundException, IOException {

        int indexOfHash = 0;
        List<OUUploadCustomer> dataList = new ArrayList<OUUploadCustomer>();
        HashMap<Integer, OUUploadCustomer> mp = new HashMap<Integer, OUUploadCustomer>();

        try {

            FileInputStream fileInputStream = new FileInputStream(pathAttachfileLocalhost + pathfile.get("path"));
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("UploadMasterCustomer");

            Iterator<Row> rowIterator = worksheet.iterator();

            //Loop Row
            while (rowIterator.hasNext()) {
                OUUploadCustomer ouploadcus = new OUUploadCustomer();
                Row row = rowIterator.next();
                //skip column name at first row
                if (row.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                //Loop Column
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println(cell.getNumericCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            switch (cell.getColumnIndex()) {
                                case 0:
                                    ouploadcus.setCustomerID(cell.getStringCellValue());
                                    mp.put(indexOfHash, ouploadcus);
                                    break;
                                case 1:
                                    ouploadcus.setPrename(cell.getStringCellValue());
                                    mp.put(indexOfHash, ouploadcus);
                                    break;
                                case 2:
                                    ouploadcus.setFullname(cell.getStringCellValue());
                                    mp.put(indexOfHash, ouploadcus);
                                    break;
                                case 3:
                                    ouploadcus.setDepartment(cell.getStringCellValue());
                                    mp.put(indexOfHash, ouploadcus);
                                    break;
                            }
                            break;
                    }
                    ouploadcus.setCompany(pathfile.get("comp"));
                    mp.put(indexOfHash, ouploadcus);
                }
                indexOfHash++;
            }
            for (OUUploadCustomer d : mp.values()) {
                dataList.add(d);
            }
            fileInputStream.close();

        //Debug check data
                    /*for(int i = 0 ; i < dataList.size() ; i++ ){
        System.out.println("index : "+i);
        System.out.println("CustomerID : "+dataList.get(i).getCustomerID());
        System.out.println("Prename : "+dataList.get(i).getPrename());
        System.out.println("Fullname : "+dataList.get(i).getFullname());
        System.out.println("Department : "+dataList.get(i).getDepartment());
        System.out.println("Company : "+dataList.get(i).getCompany());
        System.out.println("===================");
        } */

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    public List<OUUploadDepartment> readExcelFileDepartment(HashMap<String, String> pathfile)
            throws FileNotFoundException, IOException {

        List<OUUploadDepartment> arrResult = new ArrayList<OUUploadDepartment>();

        try {

            FileInputStream fileInputStream = new FileInputStream(pathAttachfileLocalhost + pathfile.get("path"));
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("UploadMasterDepartment");
            Iterator<Row> rowIterator = worksheet.iterator();


            //Loop Row
            while (rowIterator.hasNext()) {
                OUUploadDepartment ouploadDepart = new OUUploadDepartment();
                Row row = rowIterator.next();

                //skip column name at first row
                if (row.getRowNum() == 0) {
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();

                //Loop Column
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println(cell.getNumericCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            switch (cell.getColumnIndex()) {
                                case 0:
                                    ouploadDepart.setAgency(cell.getStringCellValue()); //หน่วยงาน 1 ฝ่าย

                                    break;
                                case 1:
                                    ouploadDepart.setDivision(cell.getStringCellValue()); //หน่วยงาน 2 กอง

                                    break;
                                case 2:
                                    ouploadDepart.setDepartment(cell.getStringCellValue()); // สาขา

                                    break;
                            }
                            ouploadDepart.setCompany(pathfile.get("comp"));
                            break;
                    }
                }
                arrResult.add(ouploadDepart);
            }

            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrResult;
    }

    public List<OUUCustomerOrder> readExcelFileTransactionCustomer(HashMap<String, String> pathFile)
            throws FileNotFoundException, IOException {

        int countDetailItem = 0, item = 4, materialID = 0;
        String size = "", materialCode = "", quatity = "";
        List<OUUCustomerOrder> arrResult = new ArrayList<OUUCustomerOrder>();

        try {

            FileInputStream fileInputStream = new FileInputStream(pathAttachfileLocalhost + pathFile.get("path"));
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("OrderCustomer");
            Iterator<Row> rowIterator = worksheet.iterator();
            while (rowIterator.hasNext()) {
                OUUCustomerOrder cusOrder = new OUUCustomerOrder();
                List<OUUItemOrder> arr = new ArrayList<OUUItemOrder>();
                Row row = rowIterator.next();

                if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2) {
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            cusOrder.setId((int) cell.getNumericCellValue());
                            //System.out.println( (int) cell.getNumericCellValue());
                            break;
                        case 1:
                            cusOrder.setCode(cell.getStringCellValue());
                            //System.out.println(cell.getStringCellValue());
                            break;
                        case 2:
                            cusOrder.setName(cell.getStringCellValue());
                            //System.out.println(cell.getStringCellValue());
                            break;
                        case 3:
                            cusOrder.setCompanyid((int) cell.getNumericCellValue());
                            //System.out.println( (int) cell.getNumericCellValue());
                            break;
                        default:
                            Row contentRow = worksheet.getRow(1);
                            Row materialCodeRow = worksheet.getRow(0);
                            //if ดักว่าถึง cell สุดท้ายของ Row แรกหรือยัง
                            if (item >= contentRow.getLastCellNum()) {
                                item = 4;
                            }

                            /* เข้าสู่ช่วงเก็บรายละเอียดของไอเทม
                             * 0 = size ;
                             * 1 = จำนวน ;
                             */
                            if (countDetailItem == 0) {
                                /*
                                 * contentRow = 0 เริ่มจาก Row ที่ 0
                                 * item = 4 เริ่มจาก cell ที่ 4
                                 * เก็บ Code ของ material 
                                 */
                                Cell materialCodeCell = materialCodeRow.getCell(item);
                                materialCode = materialCodeCell.getStringCellValue();

                                /*
                                 * contentRow = 1 เริ่มจาก Row ที่ 1
                                 * item = 4 เริ่มจาก cell ที่ 4
                                 * เก็บ id ของ material 
                                 */
                                Cell contentCell = contentRow.getCell(item);
                                materialID = (int) contentCell.getNumericCellValue();

                                //เอาค่า size จาก row ตามรายชื่อพนักงาน 
                                size = cell.getStringCellValue();

                                //บวกค่าของ cell เอาไว้ใช้เก็บค่า จำนวน ในเงื่อนไขถัดไป
                                countDetailItem++;
                            } else if (countDetailItem == 1) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                quatity = cell.getStringCellValue();

                                //บวกค่าของ cell ให้เริ่มเก็บข้อมูลจาก cell ที่เป็น size ;
                                countDetailItem = 0;

                                //เปลี่ยน cell ของไอเทมจากไอเทมแรกไปเป็นไอเทมถัดไป
                                item += 2;

                                OUUItemOrder objItem = new OUUItemOrder();
                                objItem.setMatid(materialID);
                                objItem.setMatcode(materialCode);
                                objItem.setSize(size);
                                objItem.setQty(quatity);
                                arr.add(objItem);
                            //System.out.println("code : "+code+" size : "+size+" quatity :"+quatity);
                            //System.out.println("============================");
                            }
                    }

                }
                cusOrder.setItem(arr);
                arrResult.add(cusOrder);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrResult;
    }

    public List<OUUDepartmentOrder> readExcelFileTransactionDepartment(HashMap<String, String> pathFile) {

        int itemStartAt = 5, countDetailItem = 0, materialID = 0;
        String size = "", quantity = "", materialCode = "";
        List<OUUDepartmentOrder> arrResult = new ArrayList<OUUDepartmentOrder>();

        try {

            FileInputStream fileInputStream = new FileInputStream(pathAttachfileLocalhost + pathFile.get("path"));
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
            HSSFSheet worksheet = workbook.getSheet("OrderDepartment");
            Iterator<Row> rowIterator = worksheet.iterator();
            while (rowIterator.hasNext()) {
                OUUDepartmentOrder departOrder = new OUUDepartmentOrder();
                List<OUUItemOrder> arr = new ArrayList<OUUItemOrder>();
                Row row = rowIterator.next();

                if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 2) {
                    continue;
                }

                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getColumnIndex()) {
                        case 0:
                            departOrder.setId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            departOrder.setAgency(cell.getStringCellValue());
                            break;
                        case 2:
                            departOrder.setDivision(cell.getStringCellValue());
                            break;
                        case 3:
                            departOrder.setDepartment(cell.getStringCellValue());
                            break;
                        case 4:
                            departOrder.setCompany_id((int) cell.getNumericCellValue());
                            break;
                        default:
                            Row contentRow = worksheet.getRow(1);
                            Row matCodeRow = worksheet.getRow(0);

                            if (itemStartAt >= contentRow.getLastCellNum()) {
                                itemStartAt = 5;
                            }

                            if (countDetailItem == 0) {

                                Cell matCodeCell = matCodeRow.getCell(itemStartAt);
                                materialCode = matCodeCell.getStringCellValue();

                                Cell contentCell = contentRow.getCell(itemStartAt);
                                materialID = (int) contentCell.getNumericCellValue();

                                size = cell.getStringCellValue();

                                countDetailItem++;
                            } else if (countDetailItem == 1) {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                quantity = cell.getStringCellValue();

                                countDetailItem = 0;

                                itemStartAt += 2;

                                OUUItemOrder objItem = new OUUItemOrder();
                                objItem.setMatid(materialID);
                                objItem.setMatcode(materialCode);
                                objItem.setSize(size);
                                objItem.setQty(quantity);
                                arr.add(objItem);
                            }
                    }
                }
                departOrder.setItem(arr);
                arrResult.add(departOrder);
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrResult;
    }
}
