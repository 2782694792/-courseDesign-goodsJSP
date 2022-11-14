<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_AL" class="zsb.bean.AdministratorLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>顾客退出登录页面</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/Alogout.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<hr>
	<div align="center">
		<font size=3>
			<%
				String account = zsbBean_AL.getAaccount();
				String no = zsbBean_AL.getAno();
				String password = zsbBean_AL.getApassword();
				if(no != null && account != null && password != null){
					zsbBean_AL.setAaccount(null);
					zsbBean_AL.setAno(null);
					zsbBean_AL.setApassword(null);
					out.print("！退出登录成功！");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！管理员登录已经退出！\""+");"+"<"+"/script"+">");
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