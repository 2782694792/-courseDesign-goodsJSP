<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="zsb.bean.*" %>
<%@ page import="java.sql.*" %>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/GTQ.txt" %>
	<%@ include file="home_style/Cbody.txt" %>
</HEAD>
<html>
<body>
	<div align="center" class="option">
		<% 
			String num = zsbBean_CL.getCnum();
			String password = zsbBean_CL.getCpassword();
			if(num == null && password == null){
				out.print("<br>��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡");
				out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡\""+");"+"<"+"/script"+">");
			}
			
		   String Gno0 = request.getParameter("xijie"); 
		   if(Gno0==null) {
		       out.print("û�в�Ʒ�ţ��޷��鿴ϸ��");
		       return;
		   } 
		   String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
  			String user0 = "sa";
  			String password0 = "5436.......";
  			Connection con; 
  			Statement sql; 
  			ResultSet rs;
		   try {  
			   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		   }
		   catch(Exception e){} 
		   try{ 
		     con=DriverManager.getConnection(uri,user0,password0);
		     sql=con.createStatement();
		     String cdn=
		     "SELECT * FROM Goods where Gno = '"+Gno0+"'";
		     rs=sql.executeQuery(cdn);
		     out.print("<table border=2>");
		     out.print("<tr>");
		     out.print("<th>�ľ�ID</th>");
		     out.print("<th>����</th>");
		     out.print("<th>����</th>");
		     out.print("<th>�۸�</th>");
		     out.print("<th>��Ա��</th>");
		     out.print("<th>��������</th>");
		     out.print("<th>���</th>");
		     out.print("<th>������</th>");
		     out.print("<th>������</th>");
		     out.print("<th>ְ����</th>");
		     out.print("<th><font color=blue>���빺�ﳵ</font>");
		     out.print("</TR>");
		     String Gpicture="image/time.gif";
		     String detailMess="";
		     while(rs.next()){
		            String Gno=rs.getString(1);
		            String Gname=rs.getString(2);
		            String Gtype=rs.getString(3);
		            String Gprice=rs.getString(4);
		            String Gvipprice=rs.getString(5);
		            String Gproducttime=rs.getString(6);
		            String Gspecification=rs.getString(7);
		            String Gmanufacturers=rs.getString(8);
		            String Gquantity=rs.getString(9);
		            String GSno=rs.getString(10);
		            Gpicture=rs.getString(11);
		            String goods = "("+Gno+"��"+Gname+"��"+Gtype+"��"+Gprice+"��"+Gvipprice+"��"
		            			+Gproducttime+"��"+Gspecification+"��"+Gmanufacturers+"��"+Gquantity+"��"+GSno+")#"+Gprice;
		        goods = goods.replaceAll("\\p{Blank}","");
		        String button="<form  action='GCinsert' method = 'post'>"+
		                     "<input type ='hidden' name='putcar' value= "+goods+">"+
		                     "<input type ='submit'  value='���빺�ﳵ' ></form>";
		        out.print("<tr>");
	            out.print("<td>"+Gno+"</td>");
	            out.print("<td>"+Gname+"</td>");
	            out.print("<td>"+Gtype+"</td>");
	            out.print("<td>"+Gprice+"</td>");
	            out.print("<td>"+Gvipprice+"</td>");
	            out.print("<td>"+Gproducttime+"</td>");
	            out.print("<td>"+Gspecification+"</td>");
	            out.print("<td>"+Gmanufacturers+"</td>");
	            out.print("<td>"+Gquantity+"</td>");
	            out.print("<td>"+GSno+"</td>");
	            out.print("<td>"+button+"</td>");
	            out.print("</tr>");
		     } 
		     out.print("</table>");
		     out.print("��Ʒ����:<br>");
		     out.println("<div align=center>"+detailMess+"<div>");
		     String pic ="<img src='image/"+Gpicture+"' width=260 height=200 ></img>";
		     out.print(pic); //��ƬͼƬ
		     con.close();
		  }
		  catch(SQLException exp){
			  System.out.println("�ľ�������ʾʧ�ܣ�����");
		  }
		%>
	</div>
</body>
</html>
