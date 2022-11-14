<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_SL" class="zsb.bean.SalesmanLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>售货员登录页面</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/SL.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<div align="center" class="option">
		<table>
        	<tr>售货员登录界面</tr>
        	<form action="SLogin" method="post">
       		<tr><td>职工号：</td><td><Input class="input_text" type=text name="sno"></td></tr>
       		<tr><td>账号：</td><td><Input class="input_text" type=text name="snum"></td></tr>
        	<tr><td>密码：</td><td><Input class="input_text" type=password name="spassword"></td></tr>
     	</table></br>
		<input class="salesman" type=submit value="登录">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input class="salesman" type=reset value="重置">
  	</br>
  	<hr></br>
       	登录反馈：<font color=red><jsp:getProperty name="zsbBean_SL" property="sbackprompt"/></font></br>
       	职工号：<font color=blue><jsp:getProperty name="zsbBean_SL" property="sno"/></font></br>
       	账号：<font color=green><jsp:getProperty name="zsbBean_SL" property="snum"/></font>
  	</div>
</body>
</html>