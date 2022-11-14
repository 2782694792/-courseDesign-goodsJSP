package zsb.servlet;

import zsb.bean.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SalesmanRegister_S extends HttpServlet {
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
		//javabean�ж���������½�
		SalesmanRegister_B zsbBean_SR = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_SR = (SalesmanRegister_B)session.getAttribute("zsbBean_SR");
            if(zsbBean_SR == null){
            	zsbBean_SR = new SalesmanRegister_B();         //����Javabean����
            	session.setAttribute("zsbBean_SR",zsbBean_SR);
            }
        }
        catch(Exception exp){
        	zsbBean_SR = new SalesmanRegister_B();           //����Javabean����
        	session.setAttribute("zsbBean_SR",zsbBean_SR);
        } 
		//�������ݿ����ӷ�ʽ
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		Connection con; 
		PreparedStatement sql; 
		//��ȡJSP�ύ�Ķ�Ӧid�Ľ��ֵ�����¸���
		String Sname = request.getParameter("sname").trim();   //trim()ȥ���ո�
		String Sno = request.getParameter("sno").trim();
		String Snum = request.getParameter("snum").trim();
		String Sid = request.getParameter("sid").trim();
		String Spassword = request.getParameter("spassword").trim();
		String Spassword2 = request.getParameter("spassword2").trim();
		//�ж����ֵ�����¸��գ������ж���ת��ҳ����ʾ
		if(Sname == null)
			Sname = "";
		if(Sno == null)
			Sno = "";
		if(Snum == null)
			Snum = "";
		if(Sid == null)
			Sid = "";
		if(Spassword == null)
			Spassword = "";
		if(Spassword2 == null)
			Spassword2 = "-1";
		if(!Spassword.equals(Spassword2)) { 
			zsbBean_SR.setSbackprompt("�������벻ͬ���ۻ�Աע��ʧ�ܣ�����");
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("SalesmanRegister_J.jsp");
			dispatcher.forward(request, response);   
			return;
		}
		//�ж��Ƿ�������
		boolean boo = true;
		if(Sname.length() > 0 && Spassword.length() > 0 && Snum.length() > 0 && Sid.length() > 0 && Sno.length() > 0)
			boo = true;
		else
			boo = false;
		String Sbackprompt0 = null;
		try{   
			con = DriverManager.getConnection(uri,user0,password0);            //�������ݿ�
			System.out.println("���ݿ����ӳɹ�������");
			
			
				String insertCondition = "insert into Salesman values(?,?,?,?,?)";
				sql = con.prepareStatement(insertCondition);
				if(boo)
				{ 
					sql.setString(1,handleString(Sno));
					sql.setString(2,handleString(Sname));
					sql.setString(3,handleString(Sid));
					sql.setString(4,handleString(Snum));
					sql.setString(5,handleString(Spassword2));
					int m = sql.executeUpdate();
					if(m != 0){
						Sbackprompt0 = "ע��ɹ�";
						zsbBean_SR.setSbackprompt(Sbackprompt0);
						zsbBean_SR.setSname(Sno);
						zsbBean_SR.setSname(Sname);
						zsbBean_SR.setSnum(Snum);
						zsbBean_SR.setSid(Sid);
						zsbBean_SR.setSpassword(Spassword);
					}
				}
				else {
					Sbackprompt0 = "��Ϣ��д���������������зǷ��ַ�";
					zsbBean_SR.setSbackprompt(Sbackprompt0);  
				}
				con.close();
			
      }
      catch(SQLException e2){
    	  Sbackprompt0 = "���ۻ�Ա��Ϣ��������������" + e2;
    	  zsbBean_SR.setSbackprompt(Sbackprompt0); 
      }
      RequestDispatcher dispatcher= 
    		  request.getRequestDispatcher("SalesmanRegister_J.jsp");
      dispatcher.forward(request, response);//ת��
   }
	//JSPҳ���ύpost�������д���
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
		doPost(request,response);
		}
}
