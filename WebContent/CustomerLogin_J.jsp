<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<jsp:useBean id="zsbBean_CLO" class="zsb.bean.Clogout_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>�˿͵�¼ҳ��</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/CL.txt" %>
	<%@ include file="home_style/Cbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<div align="center" class="option">
		<table>
        	<tr>�˿͵�¼����</tr>
        	<form action="CLogin" method="post">
       		<tr><td>�˺ţ�</td><td><Input class="input_text" type=text name="cnum"></td></tr>
        	<tr><td>���룺</td><td><Input class="input_text" type=password name="cpassword"></td></tr>
     	</table></br>
		<input class="customer" type=submit value="��¼">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input class="customer" type=reset value="����">
  	</br>
  	<hr></br>
       	��¼������<font color=red><jsp:getProperty name="zsbBean_CL" property="cbackprompt"/></font></br>
       	�˺ţ�<font color=green><jsp:getProperty name="zsbBean_CL" property="cnum"/></font>
  	</div>
</body>
</html>