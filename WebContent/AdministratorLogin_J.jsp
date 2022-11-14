<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_AL" class="zsb.bean.AdministratorLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>管理员登录页面</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/AL.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<body>
	<div align="center" class="option">
		<table>
        	<tr>管理员登录界面</tr>
        	<form action="ALogin" method="post">
       		<tr><td>编号：</td><td><Input class="input_text" type=text name="ano"></td></tr>
       		<tr><td>账号：</td><td><Input class="input_text" type=text name="aaccount"></td></tr>
        	<tr><td>密码：</td><td><Input class="input_text" type=password name="apassword"></td></tr>
     	</table></br>
		<input class="Administrator" type=submit value="登录">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input class="Administrator" type=reset value="重置">
  	</br><hr></br>
       	登录反馈：<font color=red><jsp:getProperty name="zsbBean_AL" property="abackprompt"/></font></br>
       	编号：<font color=blue><jsp:getProperty name="zsbBean_AL" property="ano"/></font></br>
       	账号：<font color=green><jsp:getProperty name="zsbBean_AL" property="aaccount"/></font>
  	</div>
</body>
</html>