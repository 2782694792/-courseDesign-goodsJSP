package zsb.servlet;
import zsb.bean.*;
import javax.sql.rowset.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class GoodsQuery_S extends HttpServlet{
  
	private static final long serialVersionUID = 1L;
	CachedRowSet rowSet=null;
   public void init(ServletConfig config) throws ServletException{
      super.init(config);
      try {  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      }
      catch(Exception e){
    	  System.out.println("���ݿ������ɹ�������");
      } 
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
      request.setCharacterEncoding("gb2312");
      String searchMess= request.getParameter("searchMess");
      String radioMess= request.getParameter("radio");
      if(searchMess==null || searchMess.length()==0) {
         fail(request,response,"û�в�ѯ��Ϣ���޷���ѯ");
         return;
      }
      String condition="";
      if(radioMess.equals("number")) {
          condition = 
         "SELECT * FROM Goods where Gno LIKE '%"+searchMess+"%'"; 
      }
      else if(radioMess.equals("name")) {
          condition = 
         "SELECT * FROM Goods where Gname LIKE '%"+searchMess+"%'"; 
      }
      else if(radioMess.equals("price")) {
          double max=0,min=0;
          String regex = "[^0123456789.]";
          String [] priceMess =searchMess.split(regex);
          if(priceMess.length==1) {
             max =min = Double.parseDouble(priceMess[0]);
          }
          else if(priceMess.length==2) {
             min = Double.parseDouble(priceMess[0]);
             max = Double.parseDouble(priceMess[1]);
             if(max<min) {
                double t = max;
                max = min;
                min = t;
             }
          }
          else {
             fail(request,response,"����ļ۸��ʽ�д���");
             return; 
          }
          condition = "SELECT * FROM Goods "
          			+ " where (Gprice <= "+max+" AND Gprice >= "+min+")"
          			+ " or (Gvipprice <= "+max+" AND Gvipprice>="+min+")";
      }
      HttpSession session=request.getSession(true); 
      Connection con=null; 
      GoodsTypeQuery_B zsbBean_GQ0=null;
      try{ 
    	  zsbBean_GQ0=(GoodsTypeQuery_B)session.getAttribute("zsbBean_GQ0");
           if(zsbBean_GQ0==null){
              zsbBean_GQ0=new GoodsTypeQuery_B();  //����Javabean����
              session.setAttribute("zsbBean_GQ0",zsbBean_GQ0);
           }
      }
      catch(Exception exp){
           zsbBean_GQ0=new GoodsTypeQuery_B();  
           session.setAttribute("dataBean",zsbBean_GQ0);
      } 
    //�������ݿ����ӷ�ʽ
    		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
    		String user0 = "sa";
    		String password0 = "5436.......";
    		
    	try{ 
    			con = DriverManager.getConnection(uri,user0,password0);
           Statement sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
           ResultSet rs=sql.executeQuery(condition);
           rowSet=RowSetProvider.newFactory().createCachedRowSet();   //�����м�����
           rowSet.populate(rs);
           zsbBean_GQ0.setRowSet(rowSet);      //�м����ݴ洢��dataBean��  
           con.close();                     //�ر�����
      }
      catch(SQLException exp){
    	  System.out.println("�������ݴ��󣡣�");
      }
      response.sendRedirect("GoodsQuery.jsp");
   } 
   public void doGet(HttpServletRequest request,
              HttpServletResponse response) 
                        throws ServletException,IOException{
       doPost(request,response);
   }
   public void fail(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=GB2312");
        try {
         PrintWriter out=response.getWriter();
         out.println("<html><body>");
         out.println("<h2>"+backNews+"</h2>") ;
         out.println("���أ�");
         out.println("<a href = GoodsQuery.jsp>��ѯ�ľ�</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
