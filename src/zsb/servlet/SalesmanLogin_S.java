package zsb.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import zsb.bean.*;

public class SalesmanLogin_S extends HttpServlet{
	private static final long serialVersionUID = 1L;
	//��ʼ��sevlet������������
	public void init(ServletConfig config) throws ServletException { 
		super.init(config);
		try {  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch(Exception e0){
			System.out.print("SQL���ݿ���������ʧ�ܣ�");
		} 
	}
	//���Ĵ����ַ���
	public String handleString(String s)
	{   
		try{ 
			byte bb[]=s.getBytes("iso-8859-1");
            s=new String(bb);
		}
		catch(Exception e1){
			System.out.print("ISO�����ַ�ת��ʧ�ܣ���");
		} 
		return s;  
	}
	//JSPҳ���ύpost�������д���
	public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                         throws ServletException,IOException {
		request.setCharacterEncoding("gb2312");
		//�������ݿ����ӷ�ʽ
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		Connection con; 
		Statement sql; 
        ResultSet rs,rs1;
        
		String Snum = request.getParameter("snum").trim(),
				Spassword = request.getParameter("spassword").trim(),
				 Sno = request.getParameter("sno").trim();
		Snum = handleString(Snum);
		Spassword = handleString(Spassword);
		Sno = handleString(Sno);
		
		//javabean�ж���������½�
		CustomerQuery_B zsbBean_SQ = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_SQ = (CustomerQuery_B)session.getAttribute("zsbBean_SQ");
            if(zsbBean_SQ == null){
            	zsbBean_SQ = new CustomerQuery_B();         //����Javabean����
            	session.setAttribute("zsbBean_SQ",zsbBean_SQ);
            }
        }
        catch(Exception exp){
        	zsbBean_SQ = new CustomerQuery_B();           //����Javabean����
        	session.setAttribute("zsbBean_SQ",zsbBean_SQ);
        } 
		
		boolean boo=(Snum.length() > 0) && (Spassword.length() > 0) && (Sno.length() > 0);  
		try{ 
           con = DriverManager.getConnection(uri,user0,password0);
           String condition = "select * from Salesman "
           					+" where Snum = "+"\'"+handleString(Snum)+"\'"
        		   			+" and Spassword = "+"\'"+handleString(Spassword)+"\'"
                		    +" and Sno = "+"\'"+handleString(Sno)+"\'";
           sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);  
           rs = sql.executeQuery(condition);
           System.out.println("��ѯ�������Ԫ���ݳɹ���ȡ������");

     		 //���ڸ�����Ϣ�Ĳ�ѯ
           ResultSetMetaData metaData = rs.getMetaData();
           int columnCount = metaData.getColumnCount();     //�õ������������
           String []columnName = new String[columnCount];
           for(int i = 0;i < columnName.length;i ++) {
               columnName[i] = metaData.getColumnName(i + 1);        //�õ�����
           }
           zsbBean_SQ.setColumnName(columnName);            //����Javabean����ģ��
           System.out.println("����ģ�͵������ɹ����£�����");
           rs.last();
           int rowNumber = rs.getRow();                     //�õ���¼��
           String [][] tableRecord = zsbBean_SQ.getTableRecord();
           tableRecord = new String[rowNumber][columnCount];
           rs.beforeFirst();
           int i = 0;
           while(rs.next()){
               for(int k = 0;k < columnCount;k ++) 
                   tableRecord[i][k] = rs.getString(k + 1);
               i ++; 
           }
           zsbBean_SQ.setTableRecord(tableRecord);            //����Javabean����ģ��
           System.out.println("����ģ�͵ı��¼�ɹ����£�����");
           
           if(boo){
              rs1 = sql.executeQuery(condition);
              boolean m = rs1.next();
              if(m == true){ 
                  //���õ�¼�ɹ��ķ���:
                  success(request,response,Snum,Spassword,Sno); 
                  RequestDispatcher dispatcher=
                		  request.getRequestDispatcher("SalesmanLogin_J.jsp");//ת��
                  dispatcher.forward(request,response);
              }
              else{
                  String Sbackprompt = "��������˺Ż���������";
                  //���õ�¼ʧ�ܵķ���:
                  fail(request,response,Snum,Sbackprompt,Sno); 
              }
           }
           else{
               String Sbackprompt = "�������˺ź�����";
               fail(request,response,Snum,Sbackprompt,Sno);
           }
           con.close();
      }
      catch(SQLException exp){
          String Sbackprompt = ""+exp;
          fail(request,response,Snum,Sbackprompt,Sno);
      }
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
	   doPost(request,response);
   }
   //��¼�ɹ������javabean
   public void success(HttpServletRequest request,HttpServletResponse response,
		   				String snum,String spassword,String sno) {
	   SalesmanLogin_B zsbBean_SL = null;
		HttpSession session = request.getSession(true);
	   try{  
		   zsbBean_SL = (SalesmanLogin_B)session.getAttribute("zsbBean_SL");
           if(zsbBean_SL == null){
        	   zsbBean_SL = new SalesmanLogin_B();  //�����µ�����ģ�� 
        	   session.setAttribute("zsbBean_SL",zsbBean_SL);
        	   zsbBean_SL = (SalesmanLogin_B)session.getAttribute("zsbBean_SL");
           }
           String Sno = zsbBean_SL.getSno();
           if(Sno.equals(sno)) {
        	   zsbBean_SL.setSbackprompt("�Ѿ���¼");
        	   zsbBean_SL.setSnum(snum);
        	   zsbBean_SL.setSpassword(spassword);
        	   zsbBean_SL.setSno(Sno);
           }
           else {  //����ģ�ʹ洢�µĵ�¼�û�
        	   zsbBean_SL.setSbackprompt("��¼�ɹ�");
        	   zsbBean_SL.setSnum(snum);
        	   zsbBean_SL.setSpassword(spassword);
        	   zsbBean_SL.setSno(Sno);
           }
	   }
	   catch(Exception ee){
		   zsbBean_SL = new SalesmanLogin_B();  
		   session.setAttribute("zsbBean_SL",zsbBean_SL);
		   zsbBean_SL.setSbackprompt("��¼�ɹ�");
		   zsbBean_SL.setSnum(snum);
    	   zsbBean_SL.setSpassword(spassword);
    	   zsbBean_SL.setSno(sno);
	   }
   	}	
   	//��¼ʧ�ܲ�����javabean��ֱ�ӷ������
   	public void fail(HttpServletRequest request,HttpServletResponse response,
                      String snum,String sbackprompt,String sno) {
        response.setContentType("text/html;charset=gb2312");
        try {
        	PrintWriter out = response.getWriter();
        	out.println("<html><body>");
        	out.println("<h2>������˺�:"+snum+"<br>"+"�����ְ����:"+sno+"<br>�����Ľ��:"+sbackprompt+"</h2>") ;
        	out.println("<center><h3><font color=red>���ص�¼ҳ�����ҳ</font></h3></center><br>");
        	out.println("<a href = SalesmanLogin_J.jsp>��¼ҳ��</a>");
        	out.println("<br><a href = SalesmanLogin_J.jsp>��ҳ</a>");
        	out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
