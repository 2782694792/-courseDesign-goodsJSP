package zsb.servlet;

import zsb.bean.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CustomerRegister_S extends HttpServlet {
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
		CustomerRegister_B zsbBean_CR = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_CR = (CustomerRegister_B)session.getAttribute("zsbBean_CR");
            if(zsbBean_CR == null){
            	zsbBean_CR = new CustomerRegister_B();         //����Javabean����
            	session.setAttribute("zsbBean_CR",zsbBean_CR);
            }
        }
        catch(Exception exp){
        	zsbBean_CR = new CustomerRegister_B();           //����Javabean����
        	session.setAttribute("zsbBean_CR",zsbBean_CR);
        } 
		//�������ݿ����ӷ�ʽ
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		Connection con; 
		PreparedStatement sql; 
		//��ȡJSP�ύ�Ķ�Ӧid�Ľ��ֵ�����¸���
		String Cname = request.getParameter("cname").trim();   //trim()ȥ���ո�
		String Cnum = request.getParameter("cnum").trim();
		String Cid = request.getParameter("cid").trim();
		String Cpassword = request.getParameter("cpassword").trim();
		String Cpassword2 = request.getParameter("cpassword2").trim();
		//�ж����ֵ�����¸��գ������ж���ת��ҳ����ʾ
		if(Cname == null)
			Cname = "";
		if(Cnum == null)
			Cnum = "";
		if(Cid == null)
			Cid = "";
		if(Cpassword == null)
			Cpassword = "";
		if(Cpassword2 == null)
			Cpassword2 = "-1";
		if(!Cpassword.equals(Cpassword2)) { 
			zsbBean_CR.setCbackprompt("�������벻ͬ���˿�ע��ʧ�ܣ�����");
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("CustomerRegister_J.jsp");
			dispatcher.forward(request, response);   
			return;
		}
		//��ȡ��ǰʱ�䣨��ȷ�����룩
		Calendar cal = Calendar.getInstance();
		Date date = (Date) cal.getTime();
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
		//�ж��Ƿ�������
		boolean boo = true;
		if(Cname.length() > 0 && Cpassword.length() > 0 && Cnum.length() > 0 && Cid.length() > 0)
			boo = true;
		else
			boo = false;
		String Cbackprompt0 = null;
		try{   
			con = DriverManager.getConnection(uri,user0,password0);            //�������ݿ�
			System.out.println("���ݿ����ӳɹ�������");
			
			
				String insertCondition = "insert into Customer values(?,?,?,?,'"+now+"',?,?)";
				sql = con.prepareStatement(insertCondition);
				if(boo)
				{ 
					sql.setString(1,Cname);
					sql.setString(2,handleString(Cnum));
					sql.setString(3,handleString(Cid));
					sql.setFloat(4,0);
					sql.setString(5,"��ͨ");
					sql.setString(6,handleString(Cpassword2));
					int m = sql.executeUpdate();
					if(m != 0){
						Cbackprompt0 = "ע��ɹ�";
						zsbBean_CR.setCbackprompt(Cbackprompt0);
						zsbBean_CR.setCname(Cname);
						zsbBean_CR.setCnum(Cnum);
						zsbBean_CR.setCid(Cid);
						zsbBean_CR.setCpassword(Cpassword);
					}
				}
				else {
					Cbackprompt0 = "��Ϣ��д���������������зǷ��ַ�";
					zsbBean_CR.setCbackprompt(Cbackprompt0);  
				}
				con.close();
			
      }
      catch(SQLException e2){
    	  Cbackprompt0 = "�ù˿���Ϣ��������������" + e2;
    	  zsbBean_CR.setCbackprompt(Cbackprompt0); 
      }
      RequestDispatcher dispatcher= 
    		  request.getRequestDispatcher("CustomerRegister_J.jsp");
      dispatcher.forward(request, response);//ת��
   }
	//JSPҳ���ύpost�������д���
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
		doPost(request,response);
		}
}
