package zsb.servlet;

import zsb.bean.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SalesmanRegister_S extends HttpServlet {
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
			byte bb[]=s.getBytes("iso-8859-1");
            s=new String(bb);
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
		SalesmanRegister_B zsbBean_SR = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_SR = (SalesmanRegister_B)session.getAttribute("zsbBean_SR");
            if(zsbBean_SR == null){
            	zsbBean_SR = new SalesmanRegister_B();         //创建Javabean对象
            	session.setAttribute("zsbBean_SR",zsbBean_SR);
            }
        }
        catch(Exception exp){
        	zsbBean_SR = new SalesmanRegister_B();           //创建Javabean对象
        	session.setAttribute("zsbBean_SR",zsbBean_SR);
        } 
		//定义数据库连接方式
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=郑烁彬_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		Connection con; 
		PreparedStatement sql; 
		//获取JSP提交的对应id的结果值并重新复制
		String Sname = request.getParameter("sname").trim();   //trim()去除空格
		String Sno = request.getParameter("sno").trim();
		String Snum = request.getParameter("snum").trim();
		String Sid = request.getParameter("sid").trim();
		String Spassword = request.getParameter("spassword").trim();
		String Spassword2 = request.getParameter("spassword2").trim();
		//判定结果值并重新赋空，密码判定并转换页面显示
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
			zsbBean_SR.setSbackprompt("两次密码不同，售货员注册失败！！！");
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("SalesmanRegister_J.jsp");
			dispatcher.forward(request, response);   
			return;
		}
		//判定是否有输入
		boolean boo = true;
		if(Sname.length() > 0 && Spassword.length() > 0 && Snum.length() > 0 && Sid.length() > 0 && Sno.length() > 0)
			boo = true;
		else
			boo = false;
		String Sbackprompt0 = null;
		try{   
			con = DriverManager.getConnection(uri,user0,password0);            //连接数据库
			System.out.println("数据库连接成功！！！");
			
			
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
						Sbackprompt0 = "注册成功";
						zsbBean_SR.setSbackprompt(Sbackprompt0);
						zsbBean_SR.setSname(Sno);
						zsbBean_SR.setSname(Sname);
						zsbBean_SR.setSnum(Snum);
						zsbBean_SR.setSid(Sid);
						zsbBean_SR.setSpassword(Spassword);
					}
				}
				else {
					Sbackprompt0 = "信息填写不完整或名字中有非法字符";
					zsbBean_SR.setSbackprompt(Sbackprompt0);  
				}
				con.close();
			
      }
      catch(SQLException e2){
    	  Sbackprompt0 = "该售货员信息有误，请您更换！" + e2;
    	  zsbBean_SR.setSbackprompt(Sbackprompt0); 
      }
      RequestDispatcher dispatcher= 
    		  request.getRequestDispatcher("SalesmanRegister_J.jsp");
      dispatcher.forward(request, response);//转发
   }
	//JSP页面提交post方法进行处理
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
		doPost(request,response);
		}
}
