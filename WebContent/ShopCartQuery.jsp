<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="zsb.bean.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="zsbBean_CL" class="zsb.bean.CustomerLogin_B" scope="session"/>
<HTML>
<HEAD>
	<%@ include file="home_style/home.txt" %>
	<%@ include file="home_style/SCQ.txt" %>
	<%@ include file="home_style/Cbody.txt" %>
</HEAD>
	<BODY>
		<div align="center" class="option">
			<%
				HttpSession session0 = request.getSession(true);
					zsbBean_CL = (CustomerLogin_B)session0.getAttribute("zsbBean_CL");
					String num = zsbBean_CL.getCnum();
					String password = zsbBean_CL.getCpassword();
					if(num == null && password == null){
						out.print("��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡");
						out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"��Ŀǰ�˿���δ��¼����ǰ���˿͵�¼ҳ�棡\""+");"+"<"+"/script"+">");
					}
				    else {
				    	LinkedList<String> car = zsbBean_CL.getCar();
				    	if(car==null || car.isEmpty())
				     		out.print("<h2>�����ﳵ��ǰΪ�գ�</h2>");
				    	else{
					       Iterator<String> iterator=car.iterator();
					       StringBuffer buyGoods = new StringBuffer();
					       int n=0;
					       double priceSum = 0;
					       out.print("<br><font size='5px'>�ҵĹ��ﳵ��</font><hr><br><table border=2>");
						   out.print("<tr><td>ѡ����ľ�������Ϣ</td><td>����</td>");
					       while(iterator.hasNext()) {
					           String goods=iterator.next();
					           String showGoods="";
					           n++; 
					           //��������Ʒ�ĺ�׺�ǡ�#�۸�����"
					           int index=goods.lastIndexOf("#");
					           if(index!=-1){
					              priceSum += Double.parseDouble(goods.substring(index+1));
					              showGoods = goods.substring(0,index);
					           }
					           System.out.println(priceSum);
					           buyGoods.append(n+":"+showGoods);
					           String del = "<form  action='SCDel' method = 'post'>"+
					                     	"<input type ='hidden' name='delete' value= "+goods+">"+
					                     	"<input type ='submit' class='s0' value='�Ƴ����ﳵ' ></form>";
					           out.print("<tr><td>"+showGoods+"</td>");
					           out.print("<td>"+del+"</td></tr>");
					       }
					       out.print("</table><br><hr><br>");
					       String orderForm = "<table><tr><form action='COInsert' method='post'>"+
					             			    "<input type ='hidden' name='buy' value= "+buyGoods+" >"+ 
					              			  	"<input type ='hidden' name='price' value= "+priceSum+" >"+           
					              			  	"<input type ='submit' class='s'  value='���ɶ���'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
					              			  "</form>"+
						              		  "<form action='SCReset' method='post'>"+
					              				"<input type = submit class='s'  value='��չ��ﳵ'>"+
					              			  "</form></tr></table>";
					       out.print(orderForm); 
				    	} 
				    }
			%>
			<br>
		</div>
	</BODY>
</HTML>
