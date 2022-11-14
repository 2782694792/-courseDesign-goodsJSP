<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<%@ page import="java.sql.*" %>
<%@ page import="zsb.bean.*" %>
<%@ page import="java.util.*" %>
<HTML>
<HEAD>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/OQ.txt" %>
	<%@ include file="home_style/Cbody.txt" %>
</HEAD>
<BODY>
	<div align="center" class="option">
	<hr><font size="5px">我的订单信息：</font><br><hr><br>
	<%
		String num = zsbBean_CL.getCnum();
			String password = zsbBean_CL.getCpassword();
			if(num == null && password == null){
		out.print("！目前顾客尚未登录，请前往顾客登录页面！");
		out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！目前顾客尚未登录，请前往顾客登录页面！\""+");"+"<"+"/script"+">");
			}
			else {
		try{ 
			HttpSession session0 = request.getSession(true);
			zsbBean_CL = (CustomerLogin_B)session0.getAttribute("zsbBean_CL");
			if(zsbBean_CL == null){
				zsbBean_CL = new CustomerLogin_B();  //创建Javabean对象
				session.setAttribute("zsbBean_CL",zsbBean_CL);
			}
		}
		catch(Exception exp){
			zsbBean_CL = new CustomerLogin_B();  
			session.setAttribute("zsbBean_CL",zsbBean_CL);
		} 
			    Connection con;
			    ResultSet rs;
			    try{  
			    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			    }
			    catch(Exception e){
			    	System.out.println("数据库驱动失败");
			    }
		//定义数据库连接方式
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=郑烁彬_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		
		try{ 
			con = DriverManager.getConnection(uri,user0,password0);
			System.out.println("数据库连接成功");
			Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		                                                ResultSet.CONCUR_READ_ONLY);
			        String cdn = "SELECT TheOrder.Ocno,TheOrder.Ocontent,TheOrder.Osum,TheOrder.Otime FROM TheOrder"
			        			+" where Ocno = '"+zsbBean_CL.getCnum()+"'";
			          rs=sql.executeQuery(cdn);
			          out.print("<table border=2>");
			          out.print("<tr>");
			            out.print("<th width=100>"+"手机号");
			            out.print("<th width=150>"+"内容");
			            out.print("<th width=35>"+"价格");
			            out.print("<th width=100>"+"时间");
			          out.print("</TR>");
			          while(rs.next()){
			            out.print("<tr>");
			              out.print("<td >"+rs.getString(1)+"</td>"); 
			              out.print("<td >"+rs.getString(2)+"</td>");
			              out.print("<td >"+rs.getString(3)+"</td>");
			              out.print("<td >"+rs.getString(4)+"</td>");
			              out.print("</tr>") ; 
			          }
			          out.print("</table>");
			          con.close();
			    }
			    catch(SQLException e){ 
			          out.print(e);
			    }
			}
	%>
</div>
</BODY></HTML>
