<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_AL" class="zsb.bean.AdministratorLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>�˿��˳���¼ҳ��</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/Alogout.txt" %>
	<%@ include file="home_style/Abody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<hr>
	<div align="center">
		<font size=3>
			<%
				String account = zsbBean_AL.getAaccount();
				String no = zsbBean_AL.getAno();
				String password = zsbBean_AL.getApassword();
				if(no != null && account != null && password != null){
					zsbBean_AL.setAaccount(null);
					zsbBean_AL.setAno(null);
					zsbBean_AL.setApassword(null);
					out.print("���˳���¼�ɹ���");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"������Ա��¼�Ѿ��˳���\""+");"+"<"+"/script"+">");
				}
				else{
					out.print("���˳���Ч����δ��¼��");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"���˳���Ч����δ��¼��\""+");"+"<"+"/script"+">");
				}
			%>
		</font>
	</div>
</body>
</html>