<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="zsbBean_CR" class="zsb.bean.CustomerRegister_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/CR.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<title>�˿�ע��ҳ��</title>
<body bgcolor=#43c4c2 link="#3300cc" alink="#a042ff" vlink="#000000">
	<font size=3>
	<div align="center" class="option">
		<form action="CRegister" method="post">
    		<p>������������ĸ�����֡��»��߹��ɣ�*ע�͵���Ϊ������д�</p>
			<table>
   				<tr><td>*������</td><td><input class="input_text" type=text name="cname" value="֣˸��"></td></tr>
   				<tr><td>*�ֻ��ţ�</td><td><input class="input_text" type=text name="cnum" value="18566668888"></tr>
       			<tr><td>*���֤�ţ�</td><td><input class="input_text" type=text name="cid" value="440513201835010349"></td></tr>
       			<tr><td>*���룺</td><td><input class="input_text" type=password name="cpassword" value="5436......."></td></tr>
       			<tr><td>*�ٴ��������룺</td><td> <input class="input_text" type=password name="cpassword2" value="5436......."></td></tr>
			</table></br>
			<input class="customer" type=submit value="ע��">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input class="customer" type=reset value="����"></br>
		</form>
	</div>
	<hr>
	<div align="center">
		</br>ע�ᷴ����<jsp:getProperty name="zsbBean_CR" property="cbackprompt"/></br></br>
		<table border=3>
			<tr><td colspan=2 align="center">�˿�</td></tr>
     		<tr>
     			<td>������</td><td><jsp:getProperty name="zsbBean_CR" property="cname"/></td>
     		</tr>
     		<tr>
     			<td>�ֻ��ţ�</td><td><jsp:getProperty name="zsbBean_CR" property="cnum"/></td>
     		</tr>
     		<tr>
     			<td>���֤�ţ�</td><td><jsp:getProperty name="zsbBean_CR" property="cid"/></td>
     		</tr>
    		<tr>
    			<td>�˺ţ�</td><td><font color=red><jsp:getProperty name="zsbBean_CR" property="cnum"/></font></td>
     		</tr>
    		<tr>
    			<td>���룺</td><td><jsp:getProperty name="zsbBean_CR" property="cpassword"/></td>
     		</tr>
		</table>
	</div>
</body>
</html>