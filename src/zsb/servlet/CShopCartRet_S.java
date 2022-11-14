package zsb.servlet;
import zsb.bean.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class CShopCartRet_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
public void init(ServletConfig config) throws ServletException { 
      super.init(config);
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
      request.setCharacterEncoding("gb2312");
      CustomerLogin_B zsbBean_CL = null;
      HttpSession session=request.getSession(true);
      try{  
    	  zsbBean_CL=(CustomerLogin_B)session.getAttribute("zsbBean_CL");
            LinkedList<String> car = zsbBean_CL.getCar();
            car.clear();
            success(request,response);
      }
      catch(Exception exp){
      }
      RequestDispatcher dispatcher= 
      request.getRequestDispatcher("ShopCartQuery.jsp");
      dispatcher.forward(request, response);//转发 
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
      doPost(request,response);
   }public void success(HttpServletRequest request,HttpServletResponse response1) {
       response1.setContentType("text/html;charset=GB2312");
       try {
       PrintWriter out=response1.getWriter();
       out.println("<!DOCTYPE html><html><body>");
       out.println("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！购物车清空成功！\""+");"+"<"+"/script"+">");
       out.println("返回");
       out.println("<a href =ShopCartQuery.jsp>购物车查询</a>");
       out.println("</body></html>");
       }
       catch(IOException exp){}
   }
   public void fail(HttpServletRequest request,HttpServletResponse response2) {
	   response2.setContentType("text/html;charset=GB2312");
       try {
       PrintWriter out=response2.getWriter();
       out.println("<!DOCTYPE html><html><body>");
       out.println("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"！购物车清空失败！\""+");"+"<"+"/script"+">");
       out.println("返回");
       out.println("<a href =ShopCartQuery.jsp>购物车查询</a>");
       out.println("</body></html>");
       }
       catch(IOException exp){}
   }
}
