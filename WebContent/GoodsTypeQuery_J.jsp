<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="zsb.bean.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.sql.rowset.*" %>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<jsp:useBean id="zsbBean_GTQCQ" class="zsb.bean.GoodsTypeQuery_B" scope="session"/>
<jsp:useBean id="zsbBean_GTQCQ2" class="zsb.bean.GoodsTypeQuery_B" scope="session"/>
	<%@ include file="home_style/home.txt" %>
<%@ include file="home_style/GTQ.txt" %>
<%@ include file="home_style/Cbody.txt" %>
<html>
<body bgcolor=#43c4c2>
	<div align="center" class="option">
	<%
		HttpSession session0 = request.getSession(true);
		zsbBean_CL = (CustomerLogin_B)session0.getAttribute("zsbBean_CL");
			String num = zsbBean_CL.getCnum();
			String password = zsbBean_CL.getCpassword();
			if(num == null && password == null){
		out.print("！目前顾客尚未登录，请前往顾客登录页面！");
		out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！目前顾客尚未登录，请前往顾客登录页面！\""+");"+"<"+"/script"+">");
			}else{
	   			try {  
	   				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	   			}
	   			catch(Exception e0){
	   				System.out.print("SQL数据库驱动连接失败！");
	   			} 
	   			//定义数据库连接方式
	   			String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=郑烁彬_JSP_JCSX";
	   			String user0 = "sa";
	   			String password0 = "5436.......";
	   			Connection con0; 
	   			Statement sql; 
	   			ResultSet rs;
	   		 	
	   			request.setCharacterEncoding("gb2312");
		 		//javabean判定存在与否并新建
		 		HttpSession session2 = request.getSession(true);
			zsbBean_GTQCQ = (GoodsTypeQuery_B)session2.getAttribute("zsbBean_GTQCQ");
		            	zsbBean_GTQCQ = new GoodsTypeQuery_B();         //创建Javabean对象
		            	session.setAttribute("zsbBean_GTQCQ",zsbBean_GTQCQ);
	   			
		   		try {
		   	        con0 = DriverManager.getConnection(uri,user0,password0);
		   	        sql = con0.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		   	        //读取classify表，获得分类：  
		   	        rs = sql.executeQuery("SELECT * FROM GoodsType");
		   	        out.print("<form action="+"\"GTQuery\""+" method ="+"\"post\""+">") ;
		   	        out.print("<select name='GTQselect'>") ;
		   	        while(rs.next()){
		   	           String gtname = rs.getString(1);
		   	           String gtabout = rs.getString(2);
		   	           out.print("<option value ="+gtname+">"+gtname+"</option>");
		   	        }  
		   	        out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
		   	        out.print("<input class=\""+"s"+"\" type =\""+"submit"+"\" value =\""+"查看文具"+"\">");  
		   	        out.print("</form>");
		   	     }
		   	     catch(SQLException e){ 
		   	     	out.print(e);
		   	     }
	%>
		<jsp:setProperty name="zsbBean_GTQCQ2" property="pageSize" param="pageSize"/>
		<jsp:setProperty name="zsbBean_GTQCQ2" property="currentPage" param="currentPage"/>
		<%    
		      CachedRowSet rowSet = zsbBean_GTQCQ2.getRowSet();
		      if(rowSet==null) {
		         out.print("<font color=red><b>无所选中的文具类型的文具，请重新选择</b></font><br><br>");
		         return;  
		      }%><hr>
		  <table border=2>
		  <tr>
		  	<% out.print(zsbBean_GTQCQ2.getAbout()); %>
		  </tr>
		  <tr>
		    <th>文具ID</th>
		    <th>名称</th>
		    <th>种类</th>
		    <th>价格</th>
		    <th>会员价</th>
		    <!-- 
		    <th>出厂日期</th>
		    <th>规格</th>
		    <th>制造商</th>
		    <th>现有量</th>
		     -->
		    <th>职工号</th>
		    <th><font color=yellow>文具详情</font></th>
		    <th><font color=blue>加入购物车</font></th>
		  </tr>
		  <%      
		      rowSet.last(); 
		      int totalRecord = rowSet.getRow();
		      out.println("<hr><br>"+"全部记录数"+totalRecord+"<br><br>"); //全部记录数
		      int pageSize = zsbBean_GTQCQ.getPageSize();  //每页显示的记录数
		      int totalPages = zsbBean_GTQCQ.getTotalPages();
		      if(totalRecord%pageSize == 0)
		           totalPages = totalRecord/pageSize;//总页数
		      else
		           totalPages = totalRecord/pageSize+1;
		      zsbBean_GTQCQ2.setPageSize(pageSize);
		      zsbBean_GTQCQ2.setTotalPages(totalPages);
		      if(totalPages>=1) {
		         if(zsbBean_GTQCQ2.getCurrentPage()<1)
		        	 zsbBean_GTQCQ2.setCurrentPage(zsbBean_GTQCQ2.getTotalPages());
		         if(zsbBean_GTQCQ2.getCurrentPage() > zsbBean_GTQCQ2.getTotalPages())
		        	 zsbBean_GTQCQ2.setCurrentPage(1);
		         int index=(zsbBean_GTQCQ2.getCurrentPage() - 1) * pageSize + 1;
		         rowSet.absolute(index);  //查询位置移动到currentPage页起始位置
		         boolean boo=true;
		      for(int i = 1;i <= pageSize && boo;i ++) { 
		            String Gno=rowSet.getString(1);
		            String Gname=rowSet.getString(2);
		            String Gtype=rowSet.getString(3);
		            String Gprice=rowSet.getString(4);
		            String Gvipprice=rowSet.getString(5);
		            String Gproducttime=rowSet.getString(6);
		            String Gspecification=rowSet.getString(7);
		            String Gmanufacturers=rowSet.getString(8);
		            String Gquantity=rowSet.getString(9);
		            String GSno=rowSet.getString(10);
		            String goods = "("+Gno+"，"+Gname+"，"+Gtype+"，"+Gprice+"，"+Gvipprice+"，"
	            			 //+Gproducttime+","+Gspecification+","+Gmanufacturers+","
	            			   +Gquantity
	            			 //+","+GSno
	            			   +")#"+Gprice;
		            //便于购物车计算价格,尾缀上"#价格值"
		            goods = goods.replaceAll("\\p{Blank}","");
		            System.out.println(goods);
		            String button="<form  action='GCinsert' method = 'post'>"+
		                     "<input type ='hidden' name='putcar' value= \""+goods+"\">"+
		                     "<input type ='submit' class='s0' value='加入购物车' ></form>";
		            String detail="<form  action='GoodsDetail.jsp' method = 'post'>"+
		                             "<input type ='hidden' name='xijie' value= "+Gno+">"+
		                             "<input type ='submit' class='s' value='查看详情' ></form>";
		            out.print("<tr>");
		            out.print("<td>"+Gno+"</td>");
		            out.print("<td>"+Gname+"</td>");
		            out.print("<td>"+Gtype+"</td>");
		            out.print("<td>"+Gprice+"</td>");
		            out.print("<td>"+Gvipprice+"</td>");
		            //out.print("<td>"+Gproducttime+"</td>");
		            //out.print("<td>"+Gspecification+"</td>");
		            //out.print("<td>"+Gmanufacturers+"</td>");
		            out.print("<td>"+Gquantity+"</td>");
		            //out.print("<td>"+GSno+"</td>");
		            out.print("<td>"+detail+"</td>");
		            out.print("<td>"+button+"</td>");
		            out.print("</tr>");
		            boo=rowSet.next();
		         }
		     }
		%>
		 </table>
		 <BR>每页最多显示<font color=red><jsp:getProperty name="zsbBean_GTQCQ2" property="pageSize"/></font>条信息
		 <BR>当前显示第<Font color=blue>
		     <jsp:getProperty name="zsbBean_GTQCQ2" property="currentPage"/>
		   </Font>页,共有
		   <Font color=blue><jsp:getProperty name="zsbBean_GTQCQ2" property="totalPages"/>
		   </Font>页。
		<Table>
		  <tr><td><FORM action="" method=post>
		          <Input type=hidden name="currentPage" value="<%=zsbBean_GTQCQ2.getCurrentPage()-1 %>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		           <Input type=submit name="g" value="上一页"></FORM></td>
		      <td><FORM action="" method=post>
		          <Input type=hidden name="currentPage" value="<%=zsbBean_GTQCQ2.getCurrentPage()+1 %>">
		          <Input type=submit name="g" value="下一页"></FORM></td></tr>
		 <tr><td> <FORM action="" method=post>
		          每页显示<Input type=text name="pageSize" value =1 size=3>
		          条记录<Input type=submit name="g" value="确定"></FORM></td>
		      <td> <FORM action="" method=post>
		           输入页码：<Input type=text name="currentPage" size=2 >
		           <Input type=submit name="g" value="提交"></FORM></td></tr>
		</Table>
		<% } %>
		</div>
</body>
</html>
