<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="zsbBean_AR" class="zsb.bean.AdministratorRegister_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/AR.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<title>管理员注册页面</title>
<body bgcolor=#43c4c2>
	<font size=3>
	<div align="center" class="option">
		<form action="ARegister" method="post">
    		<p>姓名必须由字母、数字、下划线构成，*注释的项为必须填写项。</p>
			<table>
   				<tr><td>*编号：</td><td><input class="input_text" type=text name="ano" value="201835010349"></td></tr>
   				<tr><td>*姓名：</td><td><input class="input_text" type=text name="aname" value="zsb"></tr>
       			<tr><td>*账号：</td><td><input class="input_text" type=text name="aaccount" value="201835010349"></td></tr>
       			<tr><td>*密码：</td><td><input class="input_text" type=password name="apassword" value="5436......."></td></tr>
       			<tr><td>*再次输入密码：</td><td> <input class="input_text" type=password name="apassword2" value="5436......."></td></tr>
   				<tr><td>*数据库名：</td><td><input class="input_text" type=password name="Ar0" value="郑烁彬_JSP_JCSX"></tr>
   				<tr><td>*数据库连接端口号：</td><td><input class="input_text" type=password name="Ar1" value="1433"></tr>
   				<tr><td>*数据库连接用户：</td><td><input class="input_text" type=password name="Ar2" value="sa"></tr>
   				<tr><td>*数据库连接密码：</td><td><input class="input_text" type=password name="Ar3" value="5436......."></tr>
			</table></br>
			<input class="administrator" type=submit value="注册">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input class="administrator" type=reset value="重置"></br>
		</form>
	<hr>
		</br>注册反馈：<jsp:getProperty name="zsbBean_AR" property="abackprompt"/></br></br>
		<table border=3>
			<tr><td colspan=2 align="center">管理员</td></tr>
     		<tr>
     			<td>编号：</td><td><jsp:getProperty name="zsbBean_AR" property="ano"/></td>
     		</tr>
     		<tr>
     			<td>姓名：</td><td><jsp:getProperty name="zsbBean_AR" property="aname"/></td>
     		</tr>
     		<tr>
     			<td>账号：</td><td><jsp:getProperty name="zsbBean_AR" property="aaccount"/></td>
     		</tr>
    		<tr>
    			<td>密码：</td><td><font color=red><jsp:getProperty name="zsbBean_AR" property="apassword"/></font></td>
     		</tr>
		</table>
	</div>
</body>
</html>