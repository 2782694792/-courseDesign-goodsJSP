<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_AL" class="zsb.bean.AdministratorLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>����Ա��¼ҳ��</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/AL.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<body>
	<div align="center" class="option">
		<table>
        	<tr>����Ա��¼����</tr>
        	<form action="ALogin" method="post">
       		<tr><td>��ţ�</td><td><Input class="input_text" type=text name="ano"></td></tr>
       		<tr><td>�˺ţ�</td><td><Input class="input_text" type=text name="aaccount"></td></tr>
        	<tr><td>���룺</td><td><Input class="input_text" type=password name="apassword"></td></tr>
     	</table></br>
		<input class="Administrator" type=submit value="��¼">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input class="Administrator" type=reset value="����">
  	</br><hr></br>
       	��¼������<font color=red><jsp:getProperty name="zsbBean_AL" property="abackprompt"/></font></br>
       	��ţ�<font color=blue><jsp:getProperty name="zsbBean_AL" property="ano"/></font></br>
       	�˺ţ�<font color=green><jsp:getProperty name="zsbBean_AL" property="aaccount"/></font>
  	</div>
</body>
</html>