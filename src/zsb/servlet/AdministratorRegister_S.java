package zsb.servlet;

import zsb.bean.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdministratorRegister_S extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//初始化sevlet，连接驱动器
	public void init(ServletConfig config) throws ServletException { 
		super.init(config);
		try {  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch(Exception e0){
			System.out.print("SQL数据库驱动连接失败！");
		} 
	}
	//中文处理字符串
	public String handleString(String s)
	{   
		try{ 
			byte bb[] = s.getBytes("iso-8859-1");
            s = new String(bb);
		}
		catch(Exception e1){
			System.out.print("ISO中文字符转换失败！！");
		} 
		return s;  
	}
	//JSP页面提交post方法进行处理
	public  void  doPost(HttpServletRequest request,HttpServletResponse response) 
                         throws ServletException,IOException {
		request.setCharacterEncoding("gb2312");
		//javabean判定存在与否并新建
		AdministratorRegister_B zsbBean_AR = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_AR = (AdministratorRegister_B)session.getAttribute("zsbBean_AR");
            if(zsbBean_AR == null){
            	zsbBean_AR = new AdministratorRegister_B();         //创建Javabean对象
            	session.setAttribute("zsbBean_AR",zsbBean_AR);
            }
        }
        catch(Exception exp){
        	zsbBean_AR = new AdministratorRegister_B();           //创建Javabean对象
        	session.setAttribute("zsbBean_AR",zsbBean_AR);
        } 
		//定义数据库连接方式
		String AR0 = request.getParameter("Ar0").trim();
		String AR1 = request.getParameter("Ar1").trim();
		String AR2 = request.getParameter("Ar2").trim();
		String AR3 = request.getParameter("Ar3").trim();
		String uri = "jdbc:sqlserver://localhost:"+handleString(AR1)+";characterEncoding=gb2312;DatabaseName="+AR0;
		String user0 = handleString(AR2);
		String password0 = handleString(AR3);
		Connection con; 
		PreparedStatement sql; 
		//获取JSP提交的对应id的结果值并重新复制
		String Aname = request.getParameter("aname").trim();   //trim()去除空格
		String Ano = request.getParameter("ano").trim();
		String Aaccount = request.getParameter("aaccount").trim();
		String Apassword = request.getParameter("apassword").trim();
		String Apassword2 = request.getParameter("apassword2").trim();
		//判定结果值并重新赋空，密码判定并转换页面显示
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
			zsbBean_AR.setAbackprompt("两次密码不同，管理员注册失败！！！");
			if(AR0 != "郑烁彬_JSP_JCSX" || AR1 != "1433" || AR2 != "sa" || AR3 != "5436.......") {
				zsbBean_AR.setAbackprompt("数据库连接出错，管理员注册失败！！！");
			}
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("AdministratorRegister_J.jsp");
			dispatcher.forward(request, response);   
			return;
		}
		//判定是否有输入
		boolean boo = true;
		if(Aname.length() > 0 && Apassword.length() > 0 && Aaccount.length() > 0 && AR0.length() > 0 && Ano.length() > 0 && AR1.length() > 0 && AR2.length() > 0 && AR3.length() > 0)
			boo = true;
		else
			boo = false;
		String Abackprompt0 = null;
		try{   
			con = DriverManager.getConnection(uri,user0,password0);            //连接数据库
			System.out.println("数据库连接成功！！！");
			
			
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
						Abackprompt0 = "注册成功";
						zsbBean_AR.setAbackprompt(Abackprompt0);
						zsbBean_AR.setAno(Ano);
						zsbBean_AR.setAname(Aname);
						zsbBean_AR.setAaccount(Aaccount);
						zsbBean_AR.setApassword(Apassword);
					}
				}
				else {
					Abackprompt0 = "信息填写不完整或名字中有非法字符";
					zsbBean_AR.setAbackprompt(Abackprompt0);  
				}
				con.close();
			
      }
      catch(SQLException e2){
    	  Abackprompt0 = "该管理员信息有误，请您更换！" + e2;
    	  zsbBean_AR.setAbackprompt(Abackprompt0); 
      }
      RequestDispatcher dispatcher= 
    		  request.getRequestDispatcher("AdministratorRegister_J.jsp");
      dispatcher.forward(request, response);//转发
   }
	//JSP页面提交post方法进行处理
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
		doPost(request,response);
		}
}
