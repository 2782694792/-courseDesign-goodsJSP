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
	<hr><font size="5px">�ҵĶ�����Ϣ��</font><br><hr><br>
	<%
		String num = zsbBean_CL.getCnum();
			String password = zsbBean_CL.getCpassword();
			if(num == null && password == null){
		out.print("��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡");
		out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡\""+");"+"<"+"/script"+">");
			}
			else {
		try{ 
			HttpSession session0 = request.getSession(true);
			zsbBean_CL = (CustomerLogin_B)session0.getAttribute("zsbBean_CL");
			if(zsbBean_CL == null){
				zsbBean_CL = new CustomerLogin_B();  //����Javabean����
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
			    	System.out.println("���ݿ�����ʧ��");
			    }
		//�������ݿ����ӷ�ʽ
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		
		try{ 
			con = DriverManager.getConnection(uri,user0,password0);
			System.out.println("���ݿ����ӳɹ�");
			Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		                                                ResultSet.CONCUR_READ_ONLY);
			        String cdn = "SELECT TheOrder.Ocno,TheOrder.Ocontent,TheOrder.Osum,TheOrder.Otime FROM TheOrder"
			        			+" where Ocno = '"+zsbBean_CL.getCnum()+"'";
			          rs=sql.executeQuery(cdn);
			          out.print("<table border=2>");
			          out.print("<tr>");
			            out.print("<th width=100>"+"�ֻ���");
			            out.print("<th width=150>"+"����");
			            out.print("<th width=35>"+"�۸�");
			            out.print("<th width=100>"+"ʱ��");
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
