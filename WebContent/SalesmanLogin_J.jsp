<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_SL" class="zsb.bean.SalesmanLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>�ۻ�Ա��¼ҳ��</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/SL.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<div align="center" class="option">
		<table>
        	<tr>�ۻ�Ա��¼����</tr>
        	<form action="SLogin" method="post">
       		<tr><td>ְ���ţ�</td><td><Input class="input_text" type=text name="sno"></td></tr>
       		<tr><td>�˺ţ�</td><td><Input class="input_text" type=text name="snum"></td></tr>
        	<tr><td>���룺</td><td><Input class="input_text" type=password name="spassword"></td></tr>
     	</table></br>
		<input class="salesman" type=submit value="��¼">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   		<input class="salesman" type=reset value="����">
  	</br>
  	<hr></br>
       	��¼������<font color=red><jsp:getProperty name="zsbBean_SL" property="sbackprompt"/></font></br>
       	ְ���ţ�<font color=blue><jsp:getProperty name="zsbBean_SL" property="sno"/></font></br>
       	�˺ţ�<font color=green><jsp:getProperty name="zsbBean_SL" property="snum"/></font>
  	</div>
</body>
</html>