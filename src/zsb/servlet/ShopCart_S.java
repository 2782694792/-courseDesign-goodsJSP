package zsb.servlet;
import zsb.bean.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class ShopCart_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
public void init(ServletConfig config) throws ServletException { 
      super.init(config);
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
      request.setCharacterEncoding("gb2312");
      String goods = request.getParameter("putcar");

      CustomerLogin_B zsbBean_CL = null;
      HttpSession session = request.getSession(true);
      zsbBean_CL=(CustomerLogin_B)session.getAttribute("zsbBean_CL");
      	String c0 = zsbBean_CL.getCnum();
		String c1 = zsbBean_CL.getCpassword();
		if(c0 == null && c1 == null){
			System.out.print("！没有信息,请进行顾客登录！");
			System.out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！没有信息,请进行顾客登录！\""+");"+"<"+"/script"+">");
			return ;
		}
      try{  
            boolean b = zsbBean_CL.getCnum()==null || zsbBean_CL.getCpassword()==null;
            if(b) {
    			System.out.print("！没有信息,请进行顾客登录！");
    			System.out.print("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！没有信息,请进行顾客登录！\""+");"+"<"+"/script"+">");
    			return ;
            }
            System.out.println(goods);
            LinkedList<String> car = zsbBean_CL.getCar();System.out.println("0");
            car.add(goods);System.out.println("001");
            speakSomeMess(request,response,goods);  System.out.println("1");
            
      }
      catch(Exception exp){
    	  System.out.println(exp);
      }
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }
   public void speakSomeMess(HttpServletRequest request,
                     HttpServletResponse response,String goods) {
       response.setContentType("text/html;charset=gb2312");
        try {
         PrintWriter out=response.getWriter();
         out.println("<!DOCTYPE html><html><body>");
         out.println("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"
        		 	+"alert(\""
        		 	+"！加入购物车成功！\\n"
        		 	+goods+"\""+");"+"<"+"/script"+">");
         out.println("<h2>"+goods+" -----> 已加入购物车</h2><br>返回") ;
	       out.println("<a href =GoodsQuery.jsp>文具查询</a>");
	         out.println("<br>");
         out.println("<a href = GoodsTypeQuery_J.jsp>查看文具类型</a>");
         out.println("<br>");
         out.println("<a href = ShopCartQuery.jsp>查看购物车</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){
        	System.out.println(exp);
        }
   }
}
