<%@ page contentType="text/html; charset=gb2312" %>
<jsp:useBean id="zsbBean_AR" class="zsb.bean.AdministratorRegister_B" scope="session"/>
<html>
<head>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/AR.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<title>����Աע��ҳ��</title>
<body bgcolor=#43c4c2>
	<font size=3>
	<div align="center" class="option">
		<form action="ARegister" method="post">
    		<p>������������ĸ�����֡��»��߹��ɣ�*ע�͵���Ϊ������д�</p>
			<table>
   				<tr><td>*��ţ�</td><td><input class="input_text" type=text name="ano" value="201835010349"></td></tr>
   				<tr><td>*������</td><td><input class="input_text" type=text name="aname" value="zsb"></tr>
       			<tr><td>*�˺ţ�</td><td><input class="input_text" type=text name="aaccount" value="201835010349"></td></tr>
       			<tr><td>*���룺</td><td><input class="input_text" type=password name="apassword" value="5436......."></td></tr>
       			<tr><td>*�ٴ��������룺</td><td> <input class="input_text" type=password name="apassword2" value="5436......."></td></tr>
   				<tr><td>*���ݿ�����</td><td><input class="input_text" type=password name="Ar0" value="֣˸��_JSP_JCSX"></tr>
   				<tr><td>*���ݿ����Ӷ˿ںţ�</td><td><input class="input_text" type=password name="Ar1" value="1433"></tr>
   				<tr><td>*���ݿ������û���</td><td><input class="input_text" type=password name="Ar2" value="sa"></tr>
   				<tr><td>*���ݿ��������룺</td><td><input class="input_text" type=password name="Ar3" value="5436......."></tr>
			</table></br>
			<input class="administrator" type=submit value="ע��">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		<input class="administrator" type=reset value="����"></br>
		</form>
	<hr>
		</br>ע�ᷴ����<jsp:getProperty name="zsbBean_AR" property="abackprompt"/></br></br>
		<table border=3>
			<tr><td colspan=2 align="center">����Ա</td></tr>
     		<tr>
     			<td>��ţ�</td><td><jsp:getProperty name="zsbBean_AR" property="ano"/></td>
     		</tr>
     		<tr>
     			<td>������</td><td><jsp:getProperty name="zsbBean_AR" property="aname"/></td>
     		</tr>
     		<tr>
     			<td>�˺ţ�</td><td><jsp:getProperty name="zsbBean_AR" property="aaccount"/></td>
     		</tr>
    		<tr>
    			<td>���룺</td><td><font color=red><jsp:getProperty name="zsbBean_AR" property="apassword"/></font></td>
     		</tr>
		</table>
	</div>
</body>
</html>