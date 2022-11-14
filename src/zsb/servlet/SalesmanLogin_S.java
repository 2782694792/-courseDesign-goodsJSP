package zsb.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import zsb.bean.*;

public class SalesmanLogin_S extends HttpServlet{
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
        ResultSet rs,rs1;
        
		String Snum = request.getParameter("snum").trim(),
				Spassword = request.getParameter("spassword").trim(),
				 Sno = request.getParameter("sno").trim();
		Snum = handleString(Snum);
		Spassword = handleString(Spassword);
		Sno = handleString(Sno);
		
		//javabean判定存在与否并新建
		CustomerQuery_B zsbBean_SQ = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_SQ = (CustomerQuery_B)session.getAttribute("zsbBean_SQ");
            if(zsbBean_SQ == null){
            	zsbBean_SQ = new CustomerQuery_B();         //创建Javabean对象
            	session.setAttribute("zsbBean_SQ",zsbBean_SQ);
            }
        }
        catch(Exception exp){
        	zsbBean_SQ = new CustomerQuery_B();           //创建Javabean对象
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
           System.out.println("查询语句结果集元数据成功获取！！！");

     		 //用于个人信息的查询
           ResultSetMetaData metaData = rs.getMetaData();
           int columnCount = metaData.getColumnCount();     //得到结果集的列数
           String []columnName = new String[columnCount];
           for(int i = 0;i < columnName.length;i ++) {
               columnName[i] = metaData.getColumnName(i + 1);        //得到列名
           }
           zsbBean_SQ.setColumnName(columnName);            //更新Javabean数据模型
           System.out.println("数据模型的列名成功更新！！！");
           rs.last();
           int rowNumber = rs.getRow();                     //得到记录数
           String [][] tableRecord = zsbBean_SQ.getTableRecord();
           tableRecord = new String[rowNumber][columnCount];
           rs.beforeFirst();
           int i = 0;
           while(rs.next()){
               for(int k = 0;k < columnCount;k ++) 
                   tableRecord[i][k] = rs.getString(k + 1);
               i ++; 
           }
           zsbBean_SQ.setTableRecord(tableRecord);            //更新Javabean数据模型
           System.out.println("数据模型的表记录成功更新！！！");
           
           if(boo){
              rs1 = sql.executeQuery(condition);
              boolean m = rs1.next();
              if(m == true){ 
                  //调用登录成功的方法:
                  success(request,response,Snum,Spassword,Sno); 
                  RequestDispatcher dispatcher=
                		  request.getRequestDispatcher("SalesmanLogin_J.jsp");//转发
                  dispatcher.forward(request,response);
              }
              else{
                  String Sbackprompt = "您输入的账号或密码有误";
                  //调用登录失败的方法:
                  fail(request,response,Snum,Sbackprompt,Sno); 
              }
           }
           else{
               String Sbackprompt = "请输入账号和密码";
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
   //登录成功则更新javabean
   public void success(HttpServletRequest request,HttpServletResponse response,
		   				String snum,String spassword,String sno) {
	   SalesmanLogin_B zsbBean_SL = null;
		HttpSession session = request.getSession(true);
	   try{  
		   zsbBean_SL = (SalesmanLogin_B)session.getAttribute("zsbBean_SL");
           if(zsbBean_SL == null){
        	   zsbBean_SL = new SalesmanLogin_B();  //创建新的数据模型 
        	   session.setAttribute("zsbBean_SL",zsbBean_SL);
        	   zsbBean_SL = (SalesmanLogin_B)session.getAttribute("zsbBean_SL");
           }
           String Sno = zsbBean_SL.getSno();
           if(Sno.equals(sno)) {
        	   zsbBean_SL.setSbackprompt("已经登录");
        	   zsbBean_SL.setSnum(snum);
        	   zsbBean_SL.setSpassword(spassword);
        	   zsbBean_SL.setSno(Sno);
           }
           else {  //数据模型存储新的登录用户
        	   zsbBean_SL.setSbackprompt("登录成功");
        	   zsbBean_SL.setSnum(snum);
        	   zsbBean_SL.setSpassword(spassword);
        	   zsbBean_SL.setSno(Sno);
           }
	   }
	   catch(Exception ee){
		   zsbBean_SL = new SalesmanLogin_B();  
		   session.setAttribute("zsbBean_SL",zsbBean_SL);
		   zsbBean_SL.setSbackprompt("登录成功");
		   zsbBean_SL.setSnum(snum);
    	   zsbBean_SL.setSpassword(spassword);
    	   zsbBean_SL.setSno(sno);
	   }
   	}	
   	//登录失败不更新javabean，直接反馈结果
   	public void fail(HttpServletRequest request,HttpServletResponse response,
                      String snum,String sbackprompt,String sno) {
        response.setContentType("text/html;charset=gb2312");
        try {
        	PrintWriter out = response.getWriter();
        	out.println("<html><body>");
        	out.println("<h2>输入的账号:"+snum+"<br>"+"输入的职工号:"+sno+"<br>反馈的结果:"+sbackprompt+"</h2>") ;
        	out.println("<center><h3><font color=red>返回登录页面或主页</font></h3></center><br>");
        	out.println("<a href = SalesmanLogin_J.jsp>登录页面</a>");
        	out.println("<br><a href = SalesmanLogin_J.jsp>主页</a>");
        	out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
