<%@ page contentType="text/html; charset=gb2312"%>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<!DOCTYPE html>
<html>
<head>
	<title>�˿��˳���¼ҳ��</title>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/Clogout.txt" %>
	<%@ include file="home_style/Cbody.txt" %>
</head>
<body bgcolor=#43c4c2>
	<hr>
	<div align="center">
		<font size=3>
			<%
				String num = zsbBean_CL.getCnum();
				String password = zsbBean_CL.getCpassword();
				if(num != null && password != null){
					zsbBean_CL.setCnum(null);
					zsbBean_CL.setCpassword(null);
					out.print("���˳���¼�ɹ���");
					out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"���˿͵�¼�Ѿ��˳���\""+");"+"<"+"/script"+">");
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