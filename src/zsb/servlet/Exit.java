package zsb.servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Exit extends HttpServlet {   
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
       HttpSession session=request.getSession(true); 
       session.invalidate();              //�����û���session����
       response.sendRedirect("CustomerLogin_J.jsp"); //������ҳ 
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException {
      doPost(request,response);
   }
}
