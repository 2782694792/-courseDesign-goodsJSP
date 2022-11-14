<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_SL" class="zsb.bean.SalesmanLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>售货员退出登录页面</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/Slogout.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<hr>
	<div align="center">
		<font size=3>
			<%
				String num = zsbBean_SL.getSnum();
				String no = zsbBean_SL.getSno();
				String password = zsbBean_SL.getSpassword();
				if(num != null && password != null && no != null){
					zsbBean_SL.setSnum(null);
					zsbBean_SL.setSno(null);
					zsbBean_SL.setSpassword(null);
					out.print("！退出登录成功！");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！售货员登录已经退出！\""+");"+"<"+"/script"+">");
				}
				else{
					out.print("！退出无效，从未登录！");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！退出无效，从未登录！\""+");"+"<"+"/script"+">");
				}
			%>
		</font>
	</div>
</body>
</html>