<%-- 
    Document   : testdynamicinput
    Created on : 9 มิ.ย. 2564, 15:54:00
    Author     : 111525
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@ include file = "share/header.jsp" %>
    </head>
    <body>
        <div class="input_fields_wrap">
          <button class="add_field_button">Add More Fields</button>
          <div><input type="text" name="mytext[]"></div>
        </div>
        <button class="submit">Submit</button>
        <div>
            <a 
            href="/OrderUniform/attachfile/download/customer/C100_customer_1624243320040.xls" 
            download="C100_customer_1624243785597.xls">download
            </a>
        </div>
        <table class="table">
            <thead>
                <tr>
                  <th></th>  
                  <th>ssss</th>  
                  <th>ssss</th>  
                  <th>ssss</th>  
                  <th>ssss</th>  
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>12345</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>12345</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>12345</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>12345</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>12345</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>12345</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </body>
    
    <script language="javascript">
          $(document).ready(function() {
              var max_fields = 10; //maximum input boxes allowed
              var wrapper = $(".input_fields_wrap"); //Fields wrapper
              var add_button = $(".add_field_button"); //Add button ID

              var x = 1; //initlal text box count
              $(add_button).click(function(e) { //on add input button click
                e.preventDefault();
                if (x < max_fields) { //max input box allowed
                  x++; //text box increment
                  $(wrapper).append('<div><input type="text" name="mytext[]"/><a href="#" class="remove_field">Remove</a></div>'); //add input box
                }
              });

              $(wrapper).on("click", ".remove_field", function(e) { //user click on remove text
                e.preventDefault();
                $(this).parent('div').remove();
                x--;
              });

              /* ADDED THIS */
              $('.submit').click(function() {
                var list = wrapper.find('input').map(function() {
                  return $(this).val();
                }).get();
                // send to server here
                console.log(list);
              });
            });
    </script>
</html>
