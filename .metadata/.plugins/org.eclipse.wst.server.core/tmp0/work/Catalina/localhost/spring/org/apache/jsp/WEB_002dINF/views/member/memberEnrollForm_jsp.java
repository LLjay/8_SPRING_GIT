/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.99
 * Generated at: 2024-04-18 00:35:02 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class memberEnrollForm_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1713235595362L));
    _jspx_dependants.put("jar:file:/C:/workspace/8_spring/spring/src/main/webapp/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("    \r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>회원가입</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("	<!-- 메뉴바 -->\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../common/header.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <div class=\"content\">\r\n");
      out.write("        <br><br>\r\n");
      out.write("        <div class=\"innerOuter\">\r\n");
      out.write("            <h2>회원가입</h2>\r\n");
      out.write("            <br>\r\n");
      out.write("\r\n");
      out.write("            <form action=\"insert.me\" method=\"post\" id=\"enrollForm\">\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    <label for=\"userId\">* ID : </label> \r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"userId\" placeholder=\"Please Enter ID\" name=\"userId\" required>\r\n");
      out.write("                    <div id=\"checkResult\" style=\"font-size:0.7em; display:none;\"></div>\r\n");
      out.write("					<br>\r\n");
      out.write("                    <label for=\"userPwd\">* Password : </label>\r\n");
      out.write("                    <input type=\"password\" class=\"form-control\" id=\"userPwd\" placeholder=\"Please Enter Password\" name=\"userPwd\" required> <br>\r\n");
      out.write("\r\n");
      out.write("                    <label for=\"checkPwd\">* Password Check : </label>\r\n");
      out.write("                    <input type=\"password\" class=\"form-control\" id=\"checkPwd\" placeholder=\"Please Enter Password\" required> <br>\r\n");
      out.write("\r\n");
      out.write("                    <label for=\"userName\">* Name : </label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"userName\" placeholder=\"Please Enter Name\" name=\"userName\" required> <br>\r\n");
      out.write("\r\n");
      out.write("                    <label for=\"email\"> &nbsp; Email : </label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"email\" placeholder=\"Please Enter Email\" name=\"email\"> <br>\r\n");
      out.write("\r\n");
      out.write("                    <label for=\"age\"> &nbsp; Age : </label>\r\n");
      out.write("                    <input type=\"number\" class=\"form-control\" id=\"age\" placeholder=\"Please Enter Age\" name=\"age\"> <br>\r\n");
      out.write("\r\n");
      out.write("                    <label for=\"phone\"> &nbsp; Phone : </label>\r\n");
      out.write("                    <input type=\"tel\" class=\"form-control\" id=\"phone\" placeholder=\"Please Enter Phone (-없이)\" name=\"phone\"> <br>\r\n");
      out.write("                    \r\n");
      out.write("                    <label for=\"address\"> &nbsp; Address : </label>\r\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"address\" placeholder=\"Please Enter Address\" name=\"address\"> <br>\r\n");
      out.write("                    \r\n");
      out.write("                    <label> &nbsp; Gender : </label> &nbsp;&nbsp;\r\n");
      out.write("                    <input type=\"radio\" id=\"Male\" value=\"M\" name=\"gender\" checked>\r\n");
      out.write("                    <label for=\"Male\">남자</label> &nbsp;&nbsp;\r\n");
      out.write("                    <input type=\"radio\" id=\"Female\" value=\"F\" name=\"gender\">\r\n");
      out.write("                    <label for=\"Female\">여자</label> &nbsp;&nbsp;\r\n");
      out.write("                </div> \r\n");
      out.write("                <br>\r\n");
      out.write("                <div class=\"btns\" align=\"center\">\r\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-primary\" disabled>회원가입</button>\r\n");
      out.write("                    <button type=\"reset\" class=\"btn btn-danger\">초기화</button>\r\n");
      out.write("                </div>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("        <br><br>  \r\n");
      out.write("        <script>\r\n");
      out.write("            // window.onload해도됨? 로드가 되어야 이걸 가져오니까\r\n");
      out.write("            $(function(){\r\n");
      out.write("                const idInput = document.querySelector(\"#enrollForm input[name=userId]\");\r\n");
      out.write("                let eventFlag; // window 스코프에 들어감, 따라서 setTimeOut 변수가 계속해서 덮어씌워짐\r\n");
      out.write("                // #userId로 가져오지 않는 이유 -> include로 많이 가져오기 때문에 실수로 userId를 여러 개 사용할 수도 있음\r\n");
      out.write("                // 오류 방지를 위함\r\n");
      out.write("                // script import 헤드에서 해줄 때 아직 돔이 생성되지 않으면 식별자를 찾을 수 없음\r\n");
      out.write("                // -> 돔이 로드 되었을 때 실행해달라는 이벤트를 걸어줘야 함\r\n");
      out.write("                idInput.onkeyup = function(ev){ // 발생 이벤트를 첫 번째 매개변수로 가져올 수 있음\r\n");
      out.write("                    // onkeyup -> 눌러서 뗐을 때 이벤트가 발생한다.?\r\n");
      out.write("                    \r\n");
      out.write("                    clearTimeout(eventFlag);\r\n");
      out.write("                    // 눌렀을 때 실행해줘야 함, 값을 입력해줄 때마다 값을 지워줄 것\r\n");
      out.write("\r\n");
      out.write("                    // 키가 눌릴 때마다 해당 아이디가 중복 되는지 검사\r\n");
      out.write("                    // 서버에 데이터를 보내 응답 받아야 함 -> ajax\r\n");
      out.write("\r\n");
      out.write("                    // 한 글자 넣을 때마다 계속 요청이 가니까 5글자 이상이면 요청해달라\r\n");
      out.write("                    // 정규식으로 해도 됨\r\n");
      out.write("                    const str = ev.target.value;\r\n");
      out.write("                    if (str.trim().length >= 5) {\r\n");
      out.write("                        // 그래도 5글자 이상이면 계속 감\r\n");
      out.write("                        \r\n");
      out.write("                        eventFlag = setTimeout(function(){ // 1.5초 후에 서버로 check 요청 전송\r\n");
      out.write("                                    $.ajax({\r\n");
      out.write("                                        url: \"idCheck.me\",\r\n");
      out.write("                                        // data: {checkId : idInput.value} == \r\n");
      out.write("                                        data: {checkId : ev.target.value}, // 체크하고 싶은 사용자가 입력한 아이디\r\n");
      out.write("                                        // 이 이벤트가 발생한 타겟의 밸류\r\n");
      out.write("                                        success: function(result){\r\n");
      out.write("                                            const checkResult = document.getElementById(\"checkResult\");\r\n");
      out.write("                                            checkResult.style.display = \"block\";\r\n");
      out.write("                                            if(result === \"NNNNN\"){ // 사용이 불가한 경우\r\n");
      out.write("                                                // 회원가입 버튼 비활성화\r\n");
      out.write("                                                document.querySelector(\"#enrollForm button[type='submit']\").disabled = true;\r\n");
      out.write("                                                \r\n");
      out.write("                                                checkResult.style.color = \"red\";\r\n");
      out.write("                                                checkResult.innerText = \"이미 사용 중인 아이디입니다.\"\r\n");
      out.write("                                            } else { // 사용이 가능한 경우\r\n");
      out.write("                                                // 회원가입 버튼 활성화\r\n");
      out.write("                                                document.querySelector(\"#enrollForm button[type='submit']\").disabled = false;\r\n");
      out.write("                                            \r\n");
      out.write("                                                checkResult.style.color = \"green\";\r\n");
      out.write("                                                checkResult.innerText = \"사용 가능한 아이디입니다.\"\r\n");
      out.write("                                            }\r\n");
      out.write("                                        },\r\n");
      out.write("                                        error: function(){\r\n");
      out.write("                                            console.log(\"아이디 중복 체크 ajax 실패\")\r\n");
      out.write("                                        }\r\n");
      out.write("                                    })\r\n");
      out.write("                                }, 500)\r\n");
      out.write("                    } else { // 5글자 이하\r\n");
      out.write("                        // disabled 상태 유지\r\n");
      out.write("                        // checkResult 안 보이는 상태\r\n");
      out.write("                        document.querySelector(\"#enrollForm button[type='submit']\").disabled = true;\r\n");
      out.write("                        document.getElementById(\"checkResult\").style.display = \"none\";\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("            })\r\n");
      out.write("        </script> \r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <!-- 푸터바 -->\r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../common/footer.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
