package zsb.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import zsb.bean.*;

public class CustomerLogin_S extends HttpServlet{
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
        
		String Cnum = request.getParameter("cnum").trim(),
				Cpassword = request.getParameter("cpassword").trim();
		Cnum = handleString(Cnum);
		Cpassword = handleString(Cpassword);

		//javabean�ж���������½�
		CustomerQuery_B zsbBean_CQ = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_CQ = (CustomerQuery_B)session.getAttribute("zsbBean_CQ");
            if(zsbBean_CQ == null){
            	zsbBean_CQ = new CustomerQuery_B();         //����Javabean����
            	session.setAttribute("zsbBean_CQ",zsbBean_CQ);
            }
        }
        catch(Exception exp){
        	zsbBean_CQ = new CustomerQuery_B();           //����Javabean����
        	session.setAttribute("zsbBean_CQ",zsbBean_CQ);
        } 
		
		boolean boo=(Cnum.length() > 0) && (Cpassword.length() > 0);  
		try{ 
           con = DriverManager.getConnection(uri,user0,password0);
           String condition = "select * from Customer where Cnum = '"+handleString(Cnum)+
        		   				"' and Cpassword ='"+handleString(Cpassword)+"'";
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
             zsbBean_CQ.setColumnName(columnName);            //����Javabean����ģ��
             System.out.println("����ģ�͵������ɹ����£�����");
             rs.last();
             int rowNumber = rs.getRow();                     //�õ���¼��
             String [][] tableRecord = zsbBean_CQ.getTableRecord();
             tableRecord = new String[rowNumber][columnCount];
             rs.beforeFirst();
             int i = 0;
             while(rs.next()){
                 for(int k = 0;k < columnCount;k ++) 
                     tableRecord[i][k] = rs.getString(k + 1);
                 i ++; 
             }
             zsbBean_CQ.setTableRecord(tableRecord);            //����Javabean����ģ��
             System.out.println("����ģ�͵ı��¼�ɹ����£�����");
             
           if(boo){
              rs1 = sql.executeQuery(condition);
              boolean m = rs1.next();
              if(m == true){ 
                  //���õ�¼�ɹ��ķ���:
                  success(request,response,Cnum,Cpassword); 
                 
                  RequestDispatcher dispatcher=
                		  request.getRequestDispatcher("CustomerLogin_J.jsp");//ת��
                  dispatcher.forward(request,response);
              }
              else{
                  String Cbackprompt = "��������˺Ż���������";
                  //���õ�¼ʧ�ܵķ���:
                  fail(request,response,Cnum,Cbackprompt); 
              }
           }
           else{
               String Cbackprompt = "�������˺ź�����";
               fail(request,response,Cnum,Cbackprompt);
           }
           con.close();
      }
      catch(SQLException exp){
          String Cbackprompt = ""+exp;
          fail(request,response,Cnum,Cbackprompt);
      }
   }
   public  void  doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
	   doPost(request,response);
   }
   //��¼�ɹ������javabean
   public void success(HttpServletRequest request,HttpServletResponse response,
		   				String Cnum,String Cpassword) {
	   CustomerLogin_B zsbBean_CL = null;
	   HttpSession session = request.getSession(true);
	   try{  
		   zsbBean_CL = (CustomerLogin_B)session.getAttribute("zsbBean_CL");
           if(zsbBean_CL == null){
        	   zsbBean_CL = new CustomerLogin_B();  //�����µ�����ģ�� 
        	   session.setAttribute("zsbBean_CL",zsbBean_CL);
        	   zsbBean_CL = (CustomerLogin_B)session.getAttribute("zsbBean_CL");
           }
           String cnum = zsbBean_CL.getCnum();
           if(cnum.equals(Cnum)) {
        	   zsbBean_CL.setCbackprompt("�Ѿ���¼");
        	   zsbBean_CL.setCnum(Cnum);
        	   zsbBean_CL.setCpassword(Cpassword);
           }
           else {  //����ģ�ʹ洢�µĵ�¼�û�
        	   zsbBean_CL.setCbackprompt("��¼�ɹ�");
        	   zsbBean_CL.setCnum(Cnum);
        	   zsbBean_CL.setCpassword(Cpassword);
           }
	   }
	   catch(Exception ee){
		   zsbBean_CL = new CustomerLogin_B();  
		   session.setAttribute("zsbBean_CL",zsbBean_CL);
		   zsbBean_CL.setCbackprompt("��¼�ɹ�");
		   zsbBean_CL.setCnum(Cnum);
    	   zsbBean_CL.setCpassword(Cpassword);
	   }
   	}	
   	//��¼ʧ�ܲ�����javabean��ֱ�ӷ������
   	public void fail(HttpServletRequest request,HttpServletResponse response,
                      String cnum,String cbackprompt) {
        response.setContentType("text/html;charset=gb2312");
        try {
        	PrintWriter out = response.getWriter();
        	out.println("<html><body>");
        	out.println("<h2>������˺�:"+cnum+"<br>�����Ľ��:"+cbackprompt+"</h2>") ;
        	out.println("<center><h3><font color=red>���ص�¼ҳ�����ҳ</font></h3></center><br>");
        	out.println("<a href = CustomerLogin_J.jsp>��¼ҳ��</a>");
        	out.println("<br><a href = CustomerLogin_J.jsp>��ҳ</a>");
        	out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
