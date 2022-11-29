package org.apache.jsp.report;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Test_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\n");
      out.write("\"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Pagdasdasde</title>\n");
      out.write("        <script src=\"../js/pdfmake.min.js\"></script>\n");
      out.write("        <script src=\"../js/vfs_fonts.js\"></script>\n");
      out.write("        \n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/jsbarcode/3.6.0/JsBarcode.all.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            function textToBase64Barcode(text){\n");
      out.write("                var canvas = document.createElement(\"canvas\");\n");
      out.write("                JsBarcode(canvas, text, {format: \"CODE39\"});\n");
      out.write("                return canvas.toDataURL(\"image/png\");\n");
      out.write("            }\n");
      out.write("            pdfMake.fonts = {\n");
      out.write("                THSarabunNew: {\n");
      out.write("                    normal: 'THSarabunNew.ttf',\n");
      out.write("                    bold: 'THSarabunNew-Bold.ttf',\n");
      out.write("                    italics: 'THSarabunNew-Italic.ttf',\n");
      out.write("                    bolditalics: 'THSarabunNew-BoldItalic.ttf'\n");
      out.write("                },\n");
      out.write("                Roboto: {\n");
      out.write("                    normal: 'Roboto-Regular.ttf',\n");
      out.write("                    bold: 'Roboto-Medium.ttf',\n");
      out.write("                    italics: 'Roboto-Italic.ttf',\n");
      out.write("                    bolditalics: 'Roboto-MediumItalic.ttf'\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            var dd = {\n");
      out.write("                pageSize: \"A4\",\n");
      out.write("                pageMargins: [ 30, 150, 30, 130 ],\n");
      out.write("                footer: function(currentPage, pageCount) { \n");
      out.write("                    return [\n");
      out.write("                        {\n");
      out.write("                            columns: [\n");
      out.write("                                {\n");
      out.write("                                    // auto-sized columns have their widths based on their content\n");
      out.write("                                    width: 'auto',\n");
      out.write("                                    text: 'First column'\n");
      out.write("                                },\n");
      out.write("                                {\n");
      out.write("                                    // star-sized columns fill the remaining space\n");
      out.write("                                    // if there's more than one star-column, available width is divided equally\n");
      out.write("                                    width: '*',\n");
      out.write("                                    text: 'Second column'\n");
      out.write("                                }\n");
      out.write("                            ]\n");
      out.write("                        }\n");
      out.write("                    ]\n");
      out.write("                },\n");
      out.write("                header: function(currentPage, pageCount, pageSize) {\n");
      out.write("                    return [\n");
      out.write("                        {\n");
      out.write("                            columns: [\n");
      out.write("                                {\n");
      out.write("                                    // auto-sized columns have their widths based on their content\n");
      out.write("                                    width: 'auto',\n");
      out.write("                                    text: 'First column'\n");
      out.write("                                },\n");
      out.write("                                {\n");
      out.write("                                    // star-sized columns fill the remaining space\n");
      out.write("                                    // if there's more than one star-column, available width is divided equally\n");
      out.write("                                    width: '*',\n");
      out.write("                                    text: 'Second column'\n");
      out.write("                                }\n");
      out.write("                            ]\n");
      out.write("                        }\n");
      out.write("                    ]\n");
      out.write("                },\n");
      out.write("                content: [\n");
      out.write("                    {\n");
      out.write("                        image : this.textToBase64Barcode(\"123456789\"),\n");
      out.write("                        width: 150\n");
      out.write("                    },\n");
      out.write("                    \n");
      out.write("                ]\n");
      out.write("                ,\n");
      out.write("                styles: {\n");
      out.write("                    footer:{\n");
      out.write("                        margin: [0, 0, 0, 15]\n");
      out.write("                    },\n");
      out.write("                    tableExample: {\n");
      out.write("                        margin: [0, 0, 0, 0],\n");
      out.write("                                \n");
      out.write("                        alignment: 'center',\n");
      out.write("                        bold:true\n");
      out.write("                    }\n");
      out.write("                    \n");
      out.write("                },\n");
      out.write("                defaultStyle: {\n");
      out.write("                    font: 'THSarabunNew'\n");
      out.write("                }\n");
      out.write("               \n");
      out.write("            }\n");
      out.write("            pdfMake.createPdf(dd).open({}, window); \n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
