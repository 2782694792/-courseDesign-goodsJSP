<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_SL" class="zsb.bean.SalesmanLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>�ۻ�Ա�˳���¼ҳ��</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/Slogout.txt" %>
	<%@ include file="home_style/Sbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<hr>
	<div align="center">
		<font size=3>
			<%
				String num = zsbBean_SL.getSnum();
				String no = zsbBean_SL.getSno();
				String password = zsbBean_SL.getSpassword();
				if(num != null && password != null && no != null){
					zsbBean_SL.setSnum(null);
					zsbBean_SL.setSno(null);
					zsbBean_SL.setSpassword(null);
					out.print("���˳���¼�ɹ���");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"���ۻ�Ա��¼�Ѿ��˳���\""+");"+"<"+"/script"+">");
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