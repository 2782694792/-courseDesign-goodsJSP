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
						out.print("！目前顾客尚未登录，请前往顾客登录页面！");
						out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！目前顾客尚未登录，请前往顾客登录页面！\""+");"+"<"+"/script"+">");
					}
				    else {
				    	LinkedList<String> car = zsbBean_CL.getCar();
				    	if(car==null || car.isEmpty())
				     		out.print("<h2>！购物车当前为空！</h2>");
				    	else{
					       Iterator<String> iterator=car.iterator();
					       StringBuffer buyGoods = new StringBuffer();
					       int n=0;
					       double priceSum = 0;
					       out.print("<br><font size='5px'>我的购物车：</font><hr><br><table border=2>");
						   out.print("<tr><td>选择的文具总体信息</td><td>功能</td>");
					       while(iterator.hasNext()) {
					           String goods=iterator.next();
					           String showGoods="";
					           n++; 
					           //购车车物品的后缀是“#价格数字"
					           int index=goods.lastIndexOf("#");
					           if(index!=-1){
					              priceSum += Double.parseDouble(goods.substring(index+1));
					              showGoods = goods.substring(0,index);
					           }
					           System.out.println(priceSum);
					           buyGoods.append(n+":"+showGoods);
					           String del = "<form  action='SCDel' method = 'post'>"+
					                     	"<input type ='hidden' name='delete' value= "+goods+">"+
					                     	"<input type ='submit' class='s0' value='移出购物车' ></form>";
					           out.print("<tr><td>"+showGoods+"</td>");
					           out.print("<td>"+del+"</td></tr>");
					       }
					       out.print("</table><br><hr><br>");
					       String orderForm = "<table><tr><form action='COInsert' method='post'>"+
					             			    "<input type ='hidden' name='buy' value= "+buyGoods+" >"+ 
					              			  	"<input type ='hidden' name='price' value= "+priceSum+" >"+           
					              			  	"<input type ='submit' class='s'  value='生成订单'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
					              			  "</form>"+
						              		  "<form action='SCReset' method='post'>"+
					              				"<input type = submit class='s'  value='清空购物车'>"+
					              			  "</form></tr></table>";
					       out.print(orderForm); 
				    	} 
				    }
			%>
			<br>
		</div>
	</BODY>
</HTML>
