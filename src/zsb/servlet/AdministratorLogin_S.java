package zsb.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import zsb.bean.*;

public class AdministratorLogin_S extends HttpServlet{
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
		String Aaccount = request.getParameter("aaccount").trim(),
				Apassword = request.getParameter("apassword").trim(),
				 Ano = request.getParameter("ano").trim();
		Ano = handleString(Ano);
		Aaccount = handleString(Aaccount);
		Apassword = handleString(Apassword);
		
		boolean boo=(Ano.length() > 0) && (Apassword.length() > 0) && (Aaccount.length() > 0);  
		try{ 
           con = DriverManager.getConnection(uri,user0,password0);
           String condition = "select * from Administrator"
        		   			+" where Aaccount = '"+handleString(Aaccount)+"'"
        		   			+" and Apassword ='"+handleString(Apassword)+"'"
                		   	+" and Ano ='"+handleString(Ano)+"'";
           sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);  
           if(boo){
              ResultSet rs = sql.executeQuery(condition);
              boolean m = rs.next();
              if(m == true){ 
                  //���õ�¼�ɹ��ķ���:
                  success(request,response,Ano,Apassword,Aaccount); 
                  RequestDispatcher dispatcher=
                		  request.getRequestDispatcher("AdministratorLogin_J.jsp");//ת��
                  dispatcher.forward(request,response);
              }
              else{
                  String Abackprompt = "��������˺Ż���������";
                  //���õ�¼ʧ�ܵķ���:
                  fail(request,response,Ano,Abackprompt,Aaccount); 
              }
           }
           else{
               String Abackprompt = "�������˺ź�����";
               fail(request,response,Ano,Abackprompt,Aaccount);
           }
           con.close();
      }
      catch(SQLException exp){
          String Abackprompt = ""+exp;
          fail(request,response,Ano,Abackprompt,Aaccount);
      }
   }
   public void doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
	   doPost(request,response);
   }
   //��¼�ɹ������javabean
   public void success(HttpServletRequest request,HttpServletResponse response,
		   				String ano,String apassword,String aaccount) {
	   AdministratorLogin_B zsbBean_AL = null;
		HttpSession session = request.getSession(true);
	   try{  
		   zsbBean_AL = (AdministratorLogin_B)session.getAttribute("zsbBean_AL");
           if(zsbBean_AL == null){
        	   zsbBean_AL = new AdministratorLogin_B();  //�����µ�����ģ�� 
        	   session.setAttribute("zsbBean_AL",zsbBean_AL);
        	   zsbBean_AL = (AdministratorLogin_B)session.getAttribute("zsbBean_AL");
           }
           String Aaccount = zsbBean_AL.getAaccount();
           if(Aaccount.equals(aaccount)) {
        	   zsbBean_AL.setAbackprompt("�Ѿ���¼");
        	   zsbBean_AL.setAaccount(aaccount);
        	   zsbBean_AL.setApassword(apassword);
        	   zsbBean_AL.setAno(ano);
           }
           else {  //����ģ�ʹ洢�µĵ�¼�û�
        	   zsbBean_AL.setAbackprompt("��¼�ɹ�");
        	   zsbBean_AL.setAaccount(aaccount);
        	   zsbBean_AL.setApassword(apassword);
        	   zsbBean_AL.setAno(ano);
           }
	   }
	   catch(Exception ee){
		   zsbBean_AL = new AdministratorLogin_B();  
		   session.setAttribute("zsbBean_AL",zsbBean_AL);
		   zsbBean_AL.setAbackprompt("��¼�ɹ�");
    	   zsbBean_AL.setAaccount(aaccount);
    	   zsbBean_AL.setApassword(apassword);
    	   zsbBean_AL.setAno(ano);
	   }
   	}	
   	//��¼ʧ�ܲ�����javabean��ֱ�ӷ������
   	public void fail(HttpServletRequest request,HttpServletResponse response,
                      String aaccount,String abackprompt,String ano) {
        response.setContentType("text/html;charset=gb2312");
        try {
        	PrintWriter out = response.getWriter();
        	out.println("<html><body>");
        	out.println("<h2>����ı��:"+ano+"<br>�˺�:"+aaccount+"<br>�����Ľ��:"+abackprompt+"</h2>") ;
        	out.println("<center><h3><font color=red>���ص�¼ҳ�����ҳ</font></h3></center><br>");
        	out.println("<a href = AdministratorLogin_J.jsp>��¼ҳ��</a>");
        	out.println("<br><a href = AdministratorLogin_J.jsp>��ҳ</a>");
        	out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
