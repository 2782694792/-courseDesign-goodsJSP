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
				out.print("<br>！目前顾客尚未登录，请前往顾客登录页面！");
				out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！目前顾客尚未登录，请前往顾客登录页面！\""+");"+"<"+"/script"+">");
			}
			
		   String Gno0 = request.getParameter("xijie"); 
		   if(Gno0==null) {
		       out.print("没有产品号，无法查看细节");
		       return;
		   } 
		   String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=郑烁彬_JSP_JCSX";
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
		     out.print("<th>文具ID</th>");
		     out.print("<th>名称</th>");
		     out.print("<th>种类</th>");
		     out.print("<th>价格</th>");
		     out.print("<th>会员价</th>");
		     out.print("<th>出厂日期</th>");
		     out.print("<th>规格</th>");
		     out.print("<th>制造商</th>");
		     out.print("<th>现有量</th>");
		     out.print("<th>职工号</th>");
		     out.print("<th><font color=blue>加入购物车</font>");
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
		            String goods = "("+Gno+"，"+Gname+"，"+Gtype+"，"+Gprice+"，"+Gvipprice+"，"
		            			+Gproducttime+"，"+Gspecification+"，"+Gmanufacturers+"，"+Gquantity+"，"+GSno+")#"+Gprice;
		        goods = goods.replaceAll("\\p{Blank}","");
		        String button="<form  action='GCinsert' method = 'post'>"+
		                     "<input type ='hidden' name='putcar' value= "+goods+">"+
		                     "<input type ='submit'  value='加入购物车' ></form>";
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
		     out.print("产品详情:<br>");
		     out.println("<div align=center>"+detailMess+"<div>");
		     String pic ="<img src='image/"+Gpicture+"' width=260 height=200 ></img>";
		     out.print(pic); //产片图片
		     con.close();
		  }
		  catch(SQLException exp){
			  System.out.println("文具详情显示失败！！！");
		  }
		%>
	</div>
</body>
</html>
