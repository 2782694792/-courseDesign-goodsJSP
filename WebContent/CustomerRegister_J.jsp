<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="zsbBean_CR" class="zsb.bean.CustomerRegister_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/CR.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<title>顾客注册页面</title>
<body bgcolor=#43c4c2 link="#3300cc" alink="#a042ff" vlink="#000000">
	<font size=3>
	<div align="center" class="option">
		<form action="CRegister" method="post">
    		<p>姓名必须由字母、数字、下划线构成，*注释的项为必须填写项。</p>
			<table>
   				<tr><td>*姓名：</td><td><input class="input_text" type=text name="cname" value="郑烁彬"></td></tr>
   				<tr><td>*手机号：</td><td><input class="input_text" type=text name="cnum" value="18566668888"></tr>
       			<tr><td>*身份证号：</td><td><input class="input_text" type=text name="cid" value="440513201835010349"></td></tr>
       			<tr><td>*密码：</td><td><input class="input_text" type=password name="cpassword" value="5436......."></td></tr>
       			<tr><td>*再次输入密码：</td><td> <input class="input_text" type=password name="cpassword2" value="5436......."></td></tr>
			</table></br>
			<input class="customer" type=submit value="注册">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input class="customer" type=reset value="重置"></br>
		</form>
	</div>
	<hr>
	<div align="center">
		</br>注册反馈：<jsp:getProperty name="zsbBean_CR" property="cbackprompt"/></br></br>
		<table border=3>
			<tr><td colspan=2 align="center">顾客</td></tr>
     		<tr>
     			<td>姓名：</td><td><jsp:getProperty name="zsbBean_CR" property="cname"/></td>
     		</tr>
     		<tr>
     			<td>手机号：</td><td><jsp:getProperty name="zsbBean_CR" property="cnum"/></td>
     		</tr>
     		<tr>
     			<td>身份证号：</td><td><jsp:getProperty name="zsbBean_CR" property="cid"/></td>
     		</tr>
    		<tr>
    			<td>账号：</td><td><font color=red><jsp:getProperty name="zsbBean_CR" property="cnum"/></font></td>
     		</tr>
    		<tr>
    			<td>密码：</td><td><jsp:getProperty name="zsbBean_CR" property="cpassword"/></td>
     		</tr>
		</table>
	</div>
</body>
</html>