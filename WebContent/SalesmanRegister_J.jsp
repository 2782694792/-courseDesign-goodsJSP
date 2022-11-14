<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_SR" class="zsb.bean.SalesmanRegister_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/SR.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<title>顾客注册页面</title>
<body bgcolor=#43c4c2>
	<font size=3>
	<div align="center" class="option">
		<form action="SRegister" method="post">
    		<p>姓名必须由字母、数字、下划线构成，*注释的项为必须填写项。</p>
			<table>
   				<tr><td>*职工号：</td><td><input class="input_text" type=text name="sno" value="201835010349"></tr>
   				<tr><td>*姓名：</td><td><input class="input_text" type=text name="sname" value="郑烁彬"></td></tr>
   				<tr><td>*手机号：</td><td><input class="input_text" type=text name="snum" value="18566668888"></tr>
       			<tr><td>*身份证号：</td><td><input class="input_text" type=text name="sid" value="440513201835010349"></td></tr>
       			<tr><td>*密码：</td><td><input class="input_text" type=password name="spassword" value="5436......."></td></tr>
       			<tr><td>*再次输入密码：</td><td> <input class="input_text" type=password name="spassword2" value="5436......."></td></tr>
			</table></br>
			<input class="salesman" type=submit value="注册">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input class="salesman" type=reset value="重置"></br>
		</form>
	</div>
	<hr>
	<div align="center">
		</br>注册反馈：<jsp:getProperty name="zsbBean_SR" property="sbackprompt"/></br></br>
		<table border=2>
			<tr><td colspan=2 align="center">售货员</td></tr>
     		<tr>
     			<td>职工号：</td><td><jsp:getProperty name="zsbBean_SR" property="sno"/></td>
     		</tr>
     		<tr>
     			<td>姓名：</td><td><jsp:getProperty name="zsbBean_SR" property="sname"/></td>
     		</tr>
     		<tr>
     			<td>身份证号：</td><td><jsp:getProperty name="zsbBean_SR" property="sid"/></td>
     		</tr>
     		<tr>
     			<td>手机号：</td><td><jsp:getProperty name="zsbBean_SR" property="snum"/></td>
     		</tr>
    		<tr>
    			<td>账号：</td><td><font color=red><jsp:getProperty name="zsbBean_SR" property="snum"/></font></td>
     		</tr>
    		<tr>
    			<td>密码：</td><td><jsp:getProperty name="zsbBean_SR" property="spassword"/></td>
     		</tr>
		</table>
	</div>
</body>
</html>