<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_SR" class="zsb.bean.SalesmanRegister_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/SR.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<title>�˿�ע��ҳ��</title>
<body bgcolor=#43c4c2>
	<font size=3>
	<div align="center" class="option">
		<form action="SRegister" method="post">
    		<p>������������ĸ�����֡��»��߹��ɣ�*ע�͵���Ϊ������д�</p>
			<table>
   				<tr><td>*ְ���ţ�</td><td><input class="input_text" type=text name="sno" value="201835010349"></tr>
   				<tr><td>*������</td><td><input class="input_text" type=text name="sname" value="֣˸��"></td></tr>
   				<tr><td>*�ֻ��ţ�</td><td><input class="input_text" type=text name="snum" value="18566668888"></tr>
       			<tr><td>*���֤�ţ�</td><td><input class="input_text" type=text name="sid" value="440513201835010349"></td></tr>
       			<tr><td>*���룺</td><td><input class="input_text" type=password name="spassword" value="5436......."></td></tr>
       			<tr><td>*�ٴ��������룺</td><td> <input class="input_text" type=password name="spassword2" value="5436......."></td></tr>
			</table></br>
			<input class="salesman" type=submit value="ע��">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input class="salesman" type=reset value="����"></br>
		</form>
	</div>
	<hr>
	<div align="center">
		</br>ע�ᷴ����<jsp:getProperty name="zsbBean_SR" property="sbackprompt"/></br></br>
		<table border=2>
			<tr><td colspan=2 align="center">�ۻ�Ա</td></tr>
     		<tr>
     			<td>ְ���ţ�</td><td><jsp:getProperty name="zsbBean_SR" property="sno"/></td>
     		</tr>
     		<tr>
     			<td>������</td><td><jsp:getProperty name="zsbBean_SR" property="sname"/></td>
     		</tr>
     		<tr>
     			<td>���֤�ţ�</td><td><jsp:getProperty name="zsbBean_SR" property="sid"/></td>
     		</tr>
     		<tr>
     			<td>�ֻ��ţ�</td><td><jsp:getProperty name="zsbBean_SR" property="snum"/></td>
     		</tr>
    		<tr>
    			<td>�˺ţ�</td><td><font color=red><jsp:getProperty name="zsbBean_SR" property="snum"/></font></td>
     		</tr>
    		<tr>
    			<td>���룺</td><td><jsp:getProperty name="zsbBean_SR" property="spassword"/></td>
     		</tr>
		</table>
	</div>
</body>
</html>