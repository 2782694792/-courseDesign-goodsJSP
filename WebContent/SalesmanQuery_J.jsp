<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="zsbBean_SQ" class="zsb.bean.CustomerQuery_B" scope="session"/>
<jsp:useBean id="zsbBean_SL" class="zsb.bean.SalesmanLogin_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/SQ.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<title>�ۻ�Ա������Ϣҳ��</title>
<body bgcolor=#43c4c2>
	<div align="center" class="option">
		<font size=3>
			<table class="table2">
			<%
				String [][]table = zsbBean_SQ.getTableRecord();
				String c0 = zsbBean_SL.getSnum();
				String c1 = zsbBean_SL.getSpassword();
				String c2 = zsbBean_SL.getSno();
				String []columnName = zsbBean_SQ.getColumnName();
				if(table == null || columnName == null || (c0 == null && c1 == null && c2 == null)){
					out.print("��û����Ϣ,������ۻ�Ա��¼��");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"��û����Ϣ,������ۻ�Ա��¼��\""+");"+"<"+"/script"+">");
				}
				String columnName1[] = {"ְ���ţ�","������","���֤�ţ�","�ֻ��ţ�","���룺"};
				if(columnName != null && table != null && c0 != null && c1 != null){
					out.print("<br>");
					out.println(" ���ã�������ĸ�����Ϣ����ע�Ᵽ��������й¶");
					out.print(" <hr><br>");
					out.print("<br>");
					for(int i = 0;i < table.length;i ++){
						for(int j = 0;j < columnName.length;j ++){
								out.print("<tr>");
								out.print("<td class=\"" + "td2\""+" align=\""+"center\">" + columnName1[j] + "</td>");
								out.print("<td class=\"" + "td2\">" + table[i][j] + "</td>");
								out.print("</tr>");
							}
						}
					}
			%>
			</table>
		</font>
	</div>
</body>
</html>