<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>顾客退出登录页面</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/Clogout.txt" %>
	<%@ include file="home_style/Cbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<hr>
	<div align="center">
		<font size=3>
			<%
				String num = zsbBean_CL.getCnum();
				String password = zsbBean_CL.getCpassword();
				if(num != null && password != null){
					zsbBean_CL.setCnum(null);
					zsbBean_CL.setCpassword(null);
					out.print("！退出登录成功！");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！顾客登录已经退出！\""+");"+"<"+"/script"+">");
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