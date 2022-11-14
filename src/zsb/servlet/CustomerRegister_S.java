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
		CustomerRegister_B zsbBean_CR = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_CR = (CustomerRegister_B)session.getAttribute("zsbBean_CR");
            if(zsbBean_CR == null){
            	zsbBean_CR = new CustomerRegister_B();         //创建Javabean对象
            	session.setAttribute("zsbBean_CR",zsbBean_CR);
            }
        }
        catch(Exception exp){
        	zsbBean_CR = new CustomerRegister_B();           //创建Javabean对象
        	session.setAttribute("zsbBean_CR",zsbBean_CR);
        } 
		//定义数据库连接方式
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=郑烁彬_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		Connection con; 
		PreparedStatement sql; 
		//获取JSP提交的对应id的结果值并重新复制
		String Cname = request.getParameter("cname").trim();   //trim()去除空格
		String Cnum = request.getParameter("cnum").trim();
		String Cid = request.getParameter("cid").trim();
		String Cpassword = request.getParameter("cpassword").trim();
		String Cpassword2 = request.getParameter("cpassword2").trim();
		//判定结果值并重新赋空，密码判定并转换页面显示
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
			zsbBean_CR.setCbackprompt("两次密码不同，顾客注册失败！！！");
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("CustomerRegister_J.jsp");
			dispatcher.forward(request, response);   
			return;
		}
		//获取当前时间（精确到毫秒）
		Calendar cal = Calendar.getInstance();
		Date date = (Date) cal.getTime();
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
		//判定是否有输入
		boolean boo = true;
		if(Cname.length() > 0 && Cpassword.length() > 0 && Cnum.length() > 0 && Cid.length() > 0)
			boo = true;
		else
			boo = false;
		String Cbackprompt0 = null;
		try{   
			con = DriverManager.getConnection(uri,user0,password0);            //连接数据库
			System.out.println("数据库连接成功！！！");
			
			
				String insertCondition = "insert into Customer values(?,?,?,?,'"+now+"',?,?)";
				sql = con.prepareStatement(insertCondition);
				if(boo)
				{ 
					sql.setString(1,Cname);
					sql.setString(2,handleString(Cnum));
					sql.setString(3,handleString(Cid));
					sql.setFloat(4,0);
					sql.setString(5,"普通");
					sql.setString(6,handleString(Cpassword2));
					int m = sql.executeUpdate();
					if(m != 0){
						Cbackprompt0 = "注册成功";
						zsbBean_CR.setCbackprompt(Cbackprompt0);
						zsbBean_CR.setCname(Cname);
						zsbBean_CR.setCnum(Cnum);
						zsbBean_CR.setCid(Cid);
						zsbBean_CR.setCpassword(Cpassword);
					}
				}
				else {
					Cbackprompt0 = "信息填写不完整或名字中有非法字符";
					zsbBean_CR.setCbackprompt(Cbackprompt0);  
				}
				con.close();
			
      }
      catch(SQLException e2){
    	  Cbackprompt0 = "该顾客信息有误，请您更换！" + e2;
    	  zsbBean_CR.setCbackprompt(Cbackprompt0); 
      }
      RequestDispatcher dispatcher= 
    		  request.getRequestDispatcher("CustomerRegister_J.jsp");
      dispatcher.forward(request, response);//转发
   }
	//JSP页面提交post方法进行处理
	public  void  doGet(HttpServletRequest request,HttpServletResponse response)
                        throws ServletException,IOException {
		doPost(request,response);
		}
}
