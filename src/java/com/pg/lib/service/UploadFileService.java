/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pg.lib.service;

import java.io.File;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author 111525
 */
public class UploadFileService {

    private final String pathAttachfileLocalhost = "C:/Users/pakutsing/Desktop/Github/OU/web/attachfile/";
    private final String pathAttachfileOnServer = "/web/webapps/OrderUniform/attachfile/";

    public HashMap<String, String> checkMultiContent(HttpServletRequest request, String content) throws UnsupportedEncodingException {
        HashMap<String, String> result = new HashMap<String, String>();
        String path = "";
        if (ServletFileUpload.isMultipartContent(request)) {

            try {

                //Create a factory for disk-based file and Create a new file upload handler 
                List multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                Iterator iterator = multipart.iterator();

                //Loop check content FormData;
                while (iterator.hasNext()) {
                    FileItem item = (FileItem) iterator.next();

                    //Item for Upload
                    if (!item.isFormField()) {

                        if (content.equalsIgnoreCase("uploadcustomer")) {
                            path = uploadexcelCustomer(item);
                        } else if (content.equalsIgnoreCase("uploaddepartment")) {
                            path = uploadexcelDepartment(item);
                        } else if (content.equalsIgnoreCase("uploadtransactioncustomer")) {
                            path = uploadexcelTransactionCustomer(item);
                        } else if (content.equalsIgnoreCase("uploadtransactiondepartment")) {
                            path = uploadexcelTransactionDepartment(item);
                        }

                        //value return;
                        result.put("path", path);
                    }

                    //other 
                    if (item.isFormField()) {

                        //Get value from input companyname;
                        if (item.getFieldName().equals("companyname")) {
                            String compCode = item.getString();
                            //GetIdCompany
                            String compID = CompanyService.getIDCompany(compCode);
                            //value return;
                            result.put("comp", compID);
                        } else if (item.getFieldName().equals("topiccustomer")) {
                            result.put("topicname", item.getString("UTF-8"));
                        } else if (item.getFieldName().equals("topicdepartment")) {
                            result.put("topicname", item.getString("UTF-8"));
                        }
                    }
                }

            } catch (FileUploadException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

    public String uploadexcelCustomer(FileItem item) throws Exception {

        // Get Filename
        String filename = item.getName();

        // Generate Directory
        String folderName = makeDirectorySaveFile(pathAttachfileLocalhost + "upload_master/customer");

        // Generate path Upload
        File uploadFile = new File(pathAttachfileLocalhost + "upload_master/customer/" + folderName + "/" + filename);

        // Upload...
        item.write(uploadFile);

        String Strpath = "upload_master/customer/" + folderName + "/" + filename;

        return Strpath;
    }

    public String uploadexcelDepartment(FileItem item) throws Exception {

        //Get Filename
        String filename = item.getName();

        //Generate Directory
        String folderName = makeDirectorySaveFile(pathAttachfileLocalhost + "upload_master/department");

        //Generate path Upload
        File uploadFile = new File(pathAttachfileLocalhost + "upload_master/department/" + folderName + "/" + filename);

        item.write(uploadFile);

        String strpath = "upload_master/department/" + folderName + "/" + filename;

        return strpath;
    }

    public String uploadexcelTransactionCustomer(FileItem item) throws Exception {

        String filename = item.getName();

        String folderName = makeDirectorySaveFile(pathAttachfileLocalhost + "upload_transaction/customer");

        File uploadFile = new File(pathAttachfileLocalhost + "upload_transaction/customer/" + folderName + "/" + filename);

        item.write(uploadFile);

        String strpath = "upload_transaction/customer/" + folderName + "/" + filename;

        return strpath;
    }

    public String uploadexcelTransactionDepartment(FileItem item) throws Exception {

        String filename = item.getName();

        String folderName = makeDirectorySaveFile(pathAttachfileLocalhost + "upload_transaction/department");

        File uploadFile = new File(pathAttachfileLocalhost + "upload_transaction/department/" + folderName + "/" + filename);

        item.write(uploadFile);

        String strpath = "upload_transaction/department/" + folderName + "/" + filename;

        return strpath;
    }

    private String makeDirectorySaveFile(String defualtpath) {
        String currentMili = Long.toString(System.currentTimeMillis());
        String pathForUpload = defualtpath + "/" + currentMili;
        File theDir = new File(pathForUpload);

        if (!theDir.exists()) {
            theDir.mkdirs();
        } else {
            System.out.println("Could not Create The Directory... ");
        }

        return currentMili;
    }
}