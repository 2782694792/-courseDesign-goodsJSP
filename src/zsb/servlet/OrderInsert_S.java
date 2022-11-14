package zsb.servlet;
import zsb.bean.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class OrderInsert_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException { 
	      super.init(config);
	      try{ 
	           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	      }
	      catch(Exception e){
	    	  System.out.println("���ݿ���������ɹ���");
	      } 
	   }
	   public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
	                        throws ServletException,IOException {
	      request.setCharacterEncoding("gb2312");
	      String buyGoodsMess = request.getParameter("buy");
	      if(buyGoodsMess==null || buyGoodsMess.length() == 0) {
	         fail(request,response,"���ﳵû����Ʒ���޷����ɶ���");  
	         return;
	      }
	      String price = request.getParameter("price");
	      if(price==null||price.length()==0) {
	         fail(request,response,"û�м����ܼ۸��޷����ɶ���");  
	         return;
	      }
	      float sum = Float.parseFloat(price);
	      HttpSession session = request.getSession(true); 
			CustomerLogin_B zsbBean_CL = null;
			try{ 
				zsbBean_CL = (CustomerLogin_B)session.getAttribute("zsbBean_CL");
			}
			catch(Exception exp){
			} 
			Connection con; 
			PreparedStatement sql;
			//�������ݿ����ӷ�ʽ
			String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
			String user0 = "sa";
			String password0 = "5436.......";
			//��ȡ��ǰʱ�䣨��ȷ�����룩
			Calendar cal = Calendar.getInstance();
			Date date = (Date) cal.getTime();
			String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
			try{ 
				con = DriverManager.getConnection(uri,user0,password0);
				System.out.println("���ݿ����ӳɹ�");
				String insertCondition="insert into TheOrder values(?,?,?,\'"+now+"\')";
				sql=con.prepareStatement(insertCondition);
				sql.setString(1,zsbBean_CL.getCnum());
				sql.setString(2,buyGoodsMess);
				sql.setFloat(3,sum);
				int k = sql.executeUpdate();
				System.out.println(k);
				LinkedList<String> car = zsbBean_CL.getCar();
				car.clear();  //��չ��ﳵ
				con.close();
				success(request,response);
	      }
	      catch(SQLException exp){
	           fail(request,response,"���ɶ���ʧ��"+exp);
	      }
	   }
	   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
	                        throws ServletException,IOException {
	      doPost(request,response);
	   }
	   public void success(HttpServletRequest request,HttpServletResponse response1) {
		   response1.setContentType("text/html;charset=GB2312");
	       try {
	       PrintWriter out=response1.getWriter();
	       out.println("<!DOCTYPE html><html><body>");
	       out.println("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"�����ɶ����ɹ���\""+");"+"<"+"/script"+">");
	       out.println("����");
	       out.println("<a href =ShopCartQuery.jsp>���ﳵ��ѯ</a>");
	         out.println("<br>");
	         out.println("<br><a href =OrderQuery_J.jsp>������ѯ</a>");
	       out.println("</body></html>");
	       }
	        catch(IOException exp){}
	    }
	   public void fail(HttpServletRequest request,HttpServletResponse response,
	                      String backNews) {
	        response.setContentType("text/html;charset=GB2312");
	        try {
	 	       PrintWriter out=response.getWriter();
	 	       out.println("<!DOCTYPE html><html><body>");
	 	       out.println("<script language=\""+"javascript\""+" type=\""+"text/javascript\">"+"alert(\""+"�����ɶ���ʧ�ܣ�\""+");"+"<"+"/script"+">");
	 	       out.println("����");
	 	       out.println("<a href =ShopCartQuery.jsp>���ﳵ��ѯ</a>");
	 	         out.println("<br>");
	 	       out.println("</body></html>");
	 	       }
	 	        catch(IOException exp){}
	    }
}
