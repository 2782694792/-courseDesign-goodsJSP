package zsb.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import zsb.bean.*;

public class AdministratorLogin_S extends HttpServlet{
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
		//定义数据库连接方式
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=郑烁彬_JSP_JCSX";
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
                  //调用登录成功的方法:
                  success(request,response,Ano,Apassword,Aaccount); 
                  RequestDispatcher dispatcher=
                		  request.getRequestDispatcher("AdministratorLogin_J.jsp");//转发
                  dispatcher.forward(request,response);
              }
              else{
                  String Abackprompt = "您输入的账号或密码有误";
                  //调用登录失败的方法:
                  fail(request,response,Ano,Abackprompt,Aaccount); 
              }
           }
           else{
               String Abackprompt = "请输入账号和密码";
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
   //登录成功则更新javabean
   public void success(HttpServletRequest request,HttpServletResponse response,
		   				String ano,String apassword,String aaccount) {
	   AdministratorLogin_B zsbBean_AL = null;
		HttpSession session = request.getSession(true);
	   try{  
		   zsbBean_AL = (AdministratorLogin_B)session.getAttribute("zsbBean_AL");
           if(zsbBean_AL == null){
        	   zsbBean_AL = new AdministratorLogin_B();  //创建新的数据模型 
        	   session.setAttribute("zsbBean_AL",zsbBean_AL);
        	   zsbBean_AL = (AdministratorLogin_B)session.getAttribute("zsbBean_AL");
           }
           String Aaccount = zsbBean_AL.getAaccount();
           if(Aaccount.equals(aaccount)) {
        	   zsbBean_AL.setAbackprompt("已经登录");
        	   zsbBean_AL.setAaccount(aaccount);
        	   zsbBean_AL.setApassword(apassword);
        	   zsbBean_AL.setAno(ano);
           }
           else {  //数据模型存储新的登录用户
        	   zsbBean_AL.setAbackprompt("登录成功");
        	   zsbBean_AL.setAaccount(aaccount);
        	   zsbBean_AL.setApassword(apassword);
        	   zsbBean_AL.setAno(ano);
           }
	   }
	   catch(Exception ee){
		   zsbBean_AL = new AdministratorLogin_B();  
		   session.setAttribute("zsbBean_AL",zsbBean_AL);
		   zsbBean_AL.setAbackprompt("登录成功");
    	   zsbBean_AL.setAaccount(aaccount);
    	   zsbBean_AL.setApassword(apassword);
    	   zsbBean_AL.setAno(ano);
	   }
   	}	
   	//登录失败不更新javabean，直接反馈结果
   	public void fail(HttpServletRequest request,HttpServletResponse response,
                      String aaccount,String abackprompt,String ano) {
        response.setContentType("text/html;charset=gb2312");
        try {
        	PrintWriter out = response.getWriter();
        	out.println("<html><body>");
        	out.println("<h2>输入的编号:"+ano+"<br>账号:"+aaccount+"<br>反馈的结果:"+abackprompt+"</h2>") ;
        	out.println("<center><h3><font color=red>返回登录页面或主页</font></h3></center><br>");
        	out.println("<a href = AdministratorLogin_J.jsp>登录页面</a>");
        	out.println("<br><a href = AdministratorLogin_J.jsp>主页</a>");
        	out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
