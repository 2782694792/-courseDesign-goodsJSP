package zsb.servlet;
import zsb.bean.*; //��������2�е�Javabeanģ��
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AShopCartDel_S extends HttpServlet{
	private static final long serialVersionUID = 1L;
public void init(ServletConfig config) throws ServletException{
      super.init(config);
   }
   public void doPost(HttpServletRequest request,HttpServletResponse response)
               throws ServletException,IOException{
	   CustomerRegister_B zsbBean_SCD=null;
      try{  zsbBean_SCD=(CustomerRegister_B)request.getAttribute("zsbBean_SCD");
            if(zsbBean_SCD==null){
                zsbBean_SCD=new CustomerRegister_B(); //����Javabean����
                request.setAttribute("resultBean",zsbBean_SCD);
            }
      }
      catch(Exception exp){
            zsbBean_SCD=new CustomerRegister_B();  //����Javabean����
            request.setAttribute("resultBean",zsbBean_SCD);
      } 
     try{  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
     }
     catch(Exception e){
    	 System.out.println("���ݿ�����ʧ�ܣ���");
     } 
     request.setCharacterEncoding("gb2312");
     
     String searchMess= request.getParameter("searchMess");
     String radioMess= request.getParameter("radio");
     if(searchMess==null||searchMess.length()==0) {
         fail(request,response,"û�в�ѯ��Ϣ���޷���ѯ");
         return;
      }
      String condition="";
      if(radioMess.equals("nongchanpin_number")) {
          condition = 
        		  "delete from nongchanpinForm where nongchanpin_number='"+searchMess+"'";
      }
      else if(radioMess.equals("nongchanpin_name")) {
          condition = 
        		  "delete from nongchanpinForm where nongchanpin_name='"+searchMess+"'"; 
      }
      else if(radioMess.equals("nongchanpin_price")) {
    	  float p=Float.parseFloat(searchMess);
    	  condition=
    			  "delete from nongchanpinForm where nongchanpin_price='"+p+"'"; 
      }
     Connection con;
     Statement sql; 
     ResultSet rs;
     try{ 
          String uri="jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=JSP_NongChanPin";
          con=DriverManager.getConnection(uri,"sa","990726");
          sql=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                 ResultSet.CONCUR_READ_ONLY);
          sql.executeUpdate(condition);
          rs=sql.executeQuery("SELECT * FROM nongchanpinForm");
          ResultSetMetaData metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount(); //�õ������������
          String []columnName = new String[columnCount];
          for(int i=0;i<columnName.length;i++) {
             columnName[i] = metaData.getColumnName(i+1); //�õ�����
          }
          zsbBean_SCD.setColumnName(columnName);   //����Javabean����ģ��
          rs.last();
          int rowNumber=rs.getRow();  //�õ���¼��
          String [][] tableRecord=zsbBean_SCD.getTableRecord();
          tableRecord = new String[rowNumber][columnCount];
          rs.beforeFirst();
          int i=0;
          while(rs.next()){
            for(int k=0;k<columnCount;k++) 
              tableRecord[i][k] = rs.getString(k+1);
            i++; 
          }
          zsbBean_SCD.setTableRecord(tableRecord); //����Javabean����ģ��
          con.close();
          RequestDispatcher dispatcher=
          request.getRequestDispatcher("showRecord.jsp");
          dispatcher.forward(request,response);
     }
     catch(SQLException e){
          System.out.println(e);
          fail(request,response,"��Ӽ�¼ʧ��:"+e.toString());
     }  
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response)
           throws ServletException,IOException{
       doPost(request,response);
   }
   public void fail(HttpServletRequest request,HttpServletResponse response,
                      String backNews) {
        response.setContentType("text/html;charset=gb2312");
        try {
         PrintWriter out=response.getWriter();
         out.println("<html><body bgcolor=yellow>");
         out.println("<h2>"+backNews+"</h2>") ;
         out.println("����");
         out.println("<a href =deleteNongchanp.jsp>���������¼</a>");
         out.println("</body></html>");
        }
        catch(IOException exp){} 
    }
}
