<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="zsb.bean.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.sql.rowset.*" %>
<HTML>
<jsp:useBean id="zsbBean_GQ0" class="zsb.bean.GoodsTypeQuery_B" scope="session"/>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<HEAD>
	<%@ include file="home_style/home.txt" %>
<%@ include file="home_style/GQ.txt" %>
<%@ include file="home_style/Cbody.txt" %>
</HEAD>
<BODY>
	<div align="center" class="option">
		<%
			HttpSession session0 = request.getSession(true);
			zsbBean_CL = (CustomerLogin_B)session0.getAttribute("zsbBean_CL");
			String num = zsbBean_CL.getCnum();
			String password = zsbBean_CL.getCpassword();
			
			CachedRowSet rowSet=zsbBean_GQ0.getRowSet();
			if(num == null && password == null){
				out.print("��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡");
				out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡\""+");"+"<"+"/script"+">");
			}
			else{
		%>
				֧��ģ����ѯ<br>
				�۸���ѡ�����䣬����1-2<br>
				
				<FORM action="GQuery" Method="post" >
				   <br>�����ѯ��Ϣ��<Input type=text name="searchMess"><br>
				   <Input type =radio name="radio" value="number">�ľ�ID
				   <Input type =radio name="radio" value="name" checked>�ľ�����
				   <Input type =radio name="radio" value="price">�ľ߼۸�
				   <br><br><Input type=submit name="g" class='s' value="��ѯ">
				</Form>
				<jsp:setProperty name="zsbBean_GQ0" property="pageSize" param="pageSize"/>
				<jsp:setProperty name="zsbBean_GQ0" property="currentPage" param="currentPage"/>
		      <% if(rowSet==null) {
		         out.print("���޸��ľߣ�������ѡ�������ľ���Ϣ��");
		         return;  
		      }
		      else{
		      %>
			  <table border="2px">
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
		      out.println("<hr><br><br>"); //ȫ����¼��
		      int pageSize = zsbBean_GQ0.getPageSize();  //ÿҳ��ʾ�ļ�¼��
		      int totalPages = zsbBean_GQ0.getTotalPages();
		      if(totalRecord%pageSize == 0)
		           totalPages = totalRecord/pageSize;//��ҳ��
		      else
		           totalPages = totalRecord/pageSize+1;
		      zsbBean_GQ0.setPageSize(pageSize);
		      zsbBean_GQ0.setTotalPages(totalPages);
		      if(totalPages>=1) {
		         if(zsbBean_GQ0.getCurrentPage()<1)
		        	 zsbBean_GQ0.setCurrentPage(zsbBean_GQ0.getTotalPages());
		         if(zsbBean_GQ0.getCurrentPage() > zsbBean_GQ0.getTotalPages())
		        	 zsbBean_GQ0.setCurrentPage(1);
		         int index=(zsbBean_GQ0.getCurrentPage() - 1) * pageSize + 1;
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
			                     "<input type ='submit'  value='���빺�ﳵ' ></form>";
			            String detail="<form  action='GoodsDetail.jsp' method = 'post'>"+
			                             "<input type ='hidden' name='xijie' value= "+Gno+">"+
			                             "<input type ='submit'  value='�鿴����' ></form>";
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
		      }
		%>
		 </table>
		 <BR>ÿҳ�����ʾ<font color=red><jsp:getProperty name="zsbBean_GQ0" property="pageSize"/></font>����Ϣ
		 <BR>��ǰ��ʾ��<Font color=blue>
		     <jsp:getProperty name="zsbBean_GQ0" property="currentPage"/>
		   </Font>ҳ,����
		   <Font color=blue><jsp:getProperty name="zsbBean_GQ0" property="totalPages"/>
		   </Font>ҳ��
		<Table>
		  <tr><td><FORM action="" method=post>
		          <Input type=hidden name="currentPage" value="<%=zsbBean_GQ0.getCurrentPage()-1 %>">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		           <Input type=submit name="g" value="��һҳ"></FORM></td>
		      <td><FORM action="" method=post>
		          <Input type=hidden name="currentPage" value="<%=zsbBean_GQ0.getCurrentPage()+1 %>">
		          <Input type=submit name="g" value="��һҳ"></FORM></td></tr>
		 <tr><td> <FORM action="" method=post>
		          ÿҳ��ʾ<Input type=text name="pageSize" value =1 size=3>
		          ����¼<Input type=submit name="g" value="ȷ��"></FORM></td>
		      <td> <FORM action="" method=post>
		           ����ҳ�룺<Input type=text name="currentPage" size=2 >
		           <Input type=submit name="g" value="�ύ"></FORM></td></tr>
		</Table>
		<% }%>
	</div>
</BODY>
</HTML>
