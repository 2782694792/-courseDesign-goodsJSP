<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<jsp:useBean id="zsbBean_CLO" class="zsb.bean.Clogout_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>顾客管理页面</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/SMC.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<div align="center" class="option">
		<table>
        	<tr>顾客管理界面</tr>
   			<form  action="CQuery" method="post">
       		<tr><td>账号：</td><td><Input class="input_text" type=text name="cnum"></td></tr>
        	<tr><td>密码：</td><td><Input class="input_text" type=password name="cpassword"></td></tr>
     	</table></br><table><tr>
			</form>
		<form>
   			<input class="customer" type=submit value="查看">
   		</form>
   		<form  action="CChange" method="get">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   			<input class="customer" type=submit value="修改">
   		</form>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input class="customer" type=reset value="重置">
   		</tr></table>
  	</br>
  	<hr></br>
       	登录反馈：<font color=red><jsp:getProperty name="zsbBean_CL" property="cbackprompt"/></font></br>
       	账号：<font color=green><jsp:getProperty name="zsbBean_CL" property="cnum"/></font>
  	</div>
</body>
</html>