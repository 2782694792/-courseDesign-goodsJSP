<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="zsbBean_CQ" class="zsb.bean.CustomerQuery_B" scope="session"/>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/CQ.txt" %>
	<%@ include file="home_style/Cbody.txt" %>
</head>
<title>顾客个人信息页面</title>
<body bgcolor=#43c4c2 link="#3300cc" alink="#a042ff" vlink="#000000">
	<div align="center" class="option">
		<font size=3>
			<table class="table2">
			<%
				String [][]table = zsbBean_CQ.getTableRecord();
				String c0 = zsbBean_CL.getCnum();
				String c1 = zsbBean_CL.getCpassword();
				String []columnName = zsbBean_CQ.getColumnName();
				if(table == null || columnName == null || (c0 == null && c1 == null)){
					out.print("！没有信息,请进行顾客登录！");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！没有信息,请进行顾客登录！\""+");"+"<"+"/script"+">");
					table = null;
					columnName = null;
					return ;
				}
				String columnName1[] = {"姓名：","手机号：","身份证号：","积分：","注册时间：","身份：","密码："};
				if(columnName != null && table != null && c0 != null && c1 != null){
					out.print("<br>");
					out.println(" 您好，这是你的个人信息，请注意保护，避免泄露");
					out.print("<hr><br>");
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