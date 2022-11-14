package zsb.servlet;

import zsb.bean.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdministratorRegister_S extends HttpServlet {
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
			byte bb[] = s.getBytes("iso-8859-1");
            s = new String(bb);
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
		AdministratorRegister_B zsbBean_AR = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_AR = (AdministratorRegister_B)session.getAttribute("zsbBean_AR");
            if(zsbBean_AR == null){
            	zsbBean_AR = new AdministratorRegister_B();         //����Javabean����
            	session.setAttribute("zsbBean_AR",zsbBean_AR);
            }
        }
        catch(Exception exp){
        	zsbBean_AR = new AdministratorRegister_B();           //����Javabean����
        	session.setAttribute("zsbBean_AR",zsbBean_AR);
        } 
		//�������ݿ����ӷ�ʽ
		String AR0 = request.getParameter("Ar0").trim();
		String AR1 = request.getParameter("Ar1").trim();
		String AR2 = request.getParameter("Ar2").trim();
		String AR3 = request.getParameter("Ar3").trim();
		String uri = "jdbc:sqlserver://localhost:"+handleString(AR1)+";characterEncoding=gb2312;DatabaseName="+AR0;
		String user0 = handleString(AR2);
		String password0 = handleString(AR3);
		Connection con; 
		PreparedStatement sql; 
		//��ȡJSP�ύ�Ķ�Ӧid�Ľ��ֵ�����¸���
		String Aname = request.getParameter("aname").trim();   //trim()ȥ���ո�
		String Ano = request.getParameter("ano").trim();
		String Aaccount = request.getParameter("aaccount").trim();
		String Apassword = request.getParameter("apassword").trim();
		String Apassword2 = request.getParameter("apassword2").trim();
		//�ж����ֵ�����¸��գ������ж���ת��ҳ����ʾ
		if(Aname == null)
			Aname = "";
		if(Ano == null)
			Ano = "";
		if(Aaccount == null)
			Aaccount = "";
		if(Apassword == null)
			Apassword = "";
		if(Apassword2 == null)
			Apassword2 = "-1";
		if(AR0 == null)
			AR0 = "-1";
		if(AR1 == null)
			AR1 = "-1";
		if(AR2 == null)
			AR2 = "-1";
		if(AR3 == null)
			AR3 = "-1";
		if(!Apassword.equals(Apassword2)) { 
			zsbBean_AR.setAbackprompt("�������벻ͬ������Աע��ʧ�ܣ�����");
			if(AR0 != "֣˸��_JSP_JCSX" || AR1 != "1433" || AR2 != "sa" || AR3 != "5436.......") {
				zsbBean_AR.setAbackprompt("���ݿ����ӳ�������Աע��ʧ�ܣ�����");
			}
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("AdministratorRegister_J.jsp");
			dispatcher.forward(request, response);   
			return;
		}
		//�ж��Ƿ�������
		boolean boo = true;
		if(Aname.length() > 0 && Apassword.length() > 0 && Aaccount.length() > 0 && AR0.length() > 0 && Ano.length() > 0 && AR1.length() > 0 && AR2.length() > 0 && AR3.length() > 0)
			boo = true;
		else
			boo = false;
		String Abackprompt0 = null;
		try{   
			con = DriverManager.getConnection(uri,user0,password0);            //�������ݿ�
			System.out.println("���ݿ����ӳɹ�������");
			
			
				String insertCondition = "insert into Administrator values(?,?,?,?)";
				sql = con.prepareStatement(insertCondition);
				if(boo)
				{ 
					sql.setString(1,handleString(Ano));
					sql.setString(2,handleString(Aname));
					sql.setString(3,handleString(Aaccount));
					sql.setString(4,handleString(Apassword2));
					int m = sql.executeUpdate();
					if(m != 0){
						Abackprompt0 = "ע��ɹ�";
						zsbBean_AR.setAbackprompt(Abackprompt0);
						zsbBean_AR.setAno(Ano);
						zsbBean_AR.setAname(Aname);
						zsbBean_AR.setAaccount(Aaccount);
						zsbBean_AR.setApassword(Apassword);
					}
				}
				else {
					Abackprompt0 = "��Ϣ��д���������������зǷ��ַ�";
					zsbBean_AR.setAbackprompt(Abackprompt0);  
				}
				con.close();
			
      }
      catch(SQLException e2){
    	  Abackprompt0 = "�ù���Ա��Ϣ��������������" + e2;
    	  zsbBean_AR.setAbackprompt(Abackprompt0); 
      }
      RequestDispatcher dispatcher= 
    		  request.getRequestDispatcher("AdministratorRegister_J.jsp");
      dispatcher.forward(request, response);//ת��
   }
	//JSPҳ���ύpost�������д���
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
		doPost(request,response);
		}
}
