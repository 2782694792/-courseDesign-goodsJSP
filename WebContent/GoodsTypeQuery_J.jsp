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
		out.print("��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡");
		out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡\""+");"+"<"+"/script"+">");
			}else{
	   			try {  
	   				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	   			}
	   			catch(Exception e0){
	   				System.out.print("SQL���ݿ���������ʧ�ܣ�");
	   			} 
	   			//�������ݿ����ӷ�ʽ
	   			String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
	   			String user0 = "sa";
	   			String password0 = "5436.......";
	   			Connection con0; 
	   			Statement sql; 
	   			ResultSet rs;
	   		 	
	   			request.setCharacterEncoding("gb2312");
		 		//javabean�ж���������½�
		 		HttpSession session2 = request.getSession(true);
			zsbBean_GTQCQ = (GoodsTypeQuery_B)session2.getAttribute("zsbBean_GTQCQ");
		            	zsbBean_GTQCQ = new GoodsTypeQuery_B();         //����Javabean����
		            	session.setAttribute("zsbBean_GTQCQ",zsbBean_GTQCQ);
	   			
		   		try {
		   	        con0 = DriverManager.getConnection(uri,user0,password0);
		   	        sql = con0.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		   	        //��ȡclassify����÷��ࣺ  
		   	        rs = sql.executeQuery("SELECT * FROM GoodsType");
		   	        out.print("<form action="+"\"GTQuery\""+" method ="+"\"post\""+">") ;
		   	        out.print("<select name='GTQselect'>") ;
		   	        while(rs.next()){
		   	           String gtname = rs.getString(1);
		   	           String gtabout = rs.getString(2);
		   	           out.print("<option value ="+gtname+">"+gtname+"</option>");
		   	        }  
		   	        out.print("</select>&nbsp;&nbsp;&nbsp;&nbsp;");
		   	        out.print("<input class=\""+"s"+"\" type =\""+"submit"+"\" value =\""+"�鿴�ľ�"+"\">");  
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
		         out.print("<font color=red><b>����ѡ�е��ľ����͵��ľߣ�������ѡ��</b></font><br><br>");
		         return;  
		      }%><hr>
		  <table border=2>
		  <tr>
		  	<% out.print(zsbBean_GTQCQ2.getAbout()); %>
		  </tr>
		  <tr>
		    <th>�ľ�ID</th>
		    <th>����</th>
		    <th>����</th>
		    <th>�۸�</th>
		    <th>��Ա��</th>
		    <!-- 
		    <th>��������</th>
		    <th>���</th>
		    <th>������</th>
		    <th>������</th>
		     -->
		    <th>ְ����</th>
		    <th><font color=yellow>�ľ�����</font></th>
		    <th><font color=blue>���빺�ﳵ</font></th>
		  </tr>
		  <%      
		      rowSet.last(); 
		      int totalRecord = rowSet.getRow();
		      out.println("<hr><br>"+"ȫ����¼��"+totalRecord+"<br><br>"); //ȫ����¼��
		      int pageSize = zsbBean_GTQCQ.getPageSize();  //ÿҳ��ʾ�ļ�¼��
		      int totalPages = zsbBean_GTQCQ.getTotalPages();
		      if(totalRecord%pageSize == 0)
		           totalPages = totalRecord/pageSize;//��ҳ��
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
		         rowSet.absolute(index);  //��ѯλ���ƶ���currentPageҳ��ʼλ��
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
		            String goods = "("+Gno+"��"+Gname+"��"+Gtype+"��"+Gprice+"��"+Gvipprice+"��"
	            			 //+Gproducttime+","+Gspecification+","+Gmanufacturers+","
	            			   +Gquantity
	            			 //+","+GSno
	            			   +")#"+Gprice;
		            //���ڹ��ﳵ����۸�,β׺��"#�۸�ֵ"
		            goods = goods.replaceAll("\\p{Blank}","");
		            System.out.println(goods);
		            String button="<form  action='GCinsert' method = 'post'>"+
		                     "<input type ='hidden' name='putcar' value= \""+goods+"\">"+
		                     "<input type ='submit' class='s0' value='���빺�ﳵ' ></form>";
		            String detail="<form  action='GoodsDetail.jsp' method = 'post'>"+
		                             "<input type ='hidden' name='xijie' value= "+Gno+">"+
		                             "<input type ='submit' class='s' value='�鿴����' ></form>";
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
		 <BR>ÿҳ�����ʾ<font color=red><jsp:getProperty name="zsbBean_GTQCQ2" property="pageSize"/></font>����Ϣ
		 <BR>��ǰ��ʾ��<Font color=blue>
		     <jsp:getProperty name="zsbBean_GTQCQ2" property="currentPage"/>
		   </Font>ҳ,����
		   <Font color=blue><jsp:getProperty name="zsbBean_GTQCQ2" property="totalPages"/>
		   </Font>ҳ��
		<Table>
		  <tr><td><FORM action="" method=post>
		          <Input type=hidden name="currentPage" value="<%=zsbBean_GTQCQ2.getCurrentPage()-1 %>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		           <Input type=submit name="g" value="��һҳ"></FORM></td>
		      <td><FORM action="" method=post>
		          <Input type=hidden name="currentPage" value="<%=zsbBean_GTQCQ2.getCurrentPage()+1 %>">
		          <Input type=submit name="g" value="��һҳ"></FORM></td></tr>
		 <tr><td> <FORM action="" method=post>
		          ÿҳ��ʾ<Input type=text name="pageSize" value =1 size=3>
		          ����¼<Input type=submit name="g" value="ȷ��"></FORM></td>
		      <td> <FORM action="" method=post>
		           ����ҳ�룺<Input type=text name="currentPage" size=2 >
		           <Input type=submit name="g" value="�ύ"></FORM></td></tr>
		</Table>
		<% } %>
		</div>
</body>
</html>
