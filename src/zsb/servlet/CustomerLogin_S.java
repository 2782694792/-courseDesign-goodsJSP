package zsb.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import zsb.bean.*;

public class CustomerLogin_S extends HttpServlet{
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
        
		String Cnum = request.getParameter("cnum").trim(),
				Cpassword = request.getParameter("cpassword").trim();
		Cnum = handleString(Cnum);
		Cpassword = handleString(Cpassword);

		//javabean判定存在与否并新建
		CustomerQuery_B zsbBean_CQ = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_CQ = (CustomerQuery_B)session.getAttribute("zsbBean_CQ");
            if(zsbBean_CQ == null){
            	zsbBean_CQ = new CustomerQuery_B();         //创建Javabean对象
            	session.setAttribute("zsbBean_CQ",zsbBean_CQ);
            }
        }
        catch(Exception exp){
        	zsbBean_CQ = new CustomerQuery_B();           //创建Javabean对象
        	session.setAttribute("zsbBean_CQ",zsbBean_CQ);
        } 
		
		boolean boo=(Cnum.length() > 0) && (Cpassword.length() > 0);  
		try{ 
           con = DriverManager.getConnection(uri,user0,password0);
           String condition = "select * from Customer where Cnum = '"+handleString(Cnum)+
        		   				"' and Cpassword ='"+handleString(Cpassword)+"'";
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
             zsbBean_CQ.setColumnName(columnName);            //更新Javabean数据模型
             System.out.println("数据模型的列名成功更新！！！");
             rs.last();
             int rowNumber = rs.getRow();                     //得到记录数
             String [][] tableRecord = zsbBean_CQ.getTableRecord();
             tableRecord = new String[rowNumber][columnCount];
             rs.beforeFirst();
             int i = 0;
             while(rs.next()){
                 for(int k = 0;k < columnCount;k ++) 
                     tableRecord[i][k] = rs.getString(k + 1);
                 i ++; 
             }
             zsbBean_CQ.setTableRecord(tableRecord);            //更新Javabean数据模型
             System.out.println("数据模型的表记录成功更新！！！");
             
           if(boo){
              rs1 = sql.executeQuery(condition);
              boolean m = rs1.next();
              if(m == true){ 
                  //调用登录成功的方法:
                  success(request,response,Cnum,Cpassword); 
                 
                  RequestDispatcher dispatcher=
                		  request.getRequestDispatcher("CustomerLogin_J.jsp");//转发
                  dispatcher.forward(request,response);
              }
              else{
                  String Cbackprompt = "您输入的账号或密码有误";
                  //调用登录失败的方法:
                  fail(request,response,Cnum,Cbackprompt); 
              }
           }
           else{
               String Cbackprompt = "请输入账号和密码";
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
   //登录成功则更新javabean
   public void success(HttpServletRequest request,HttpServletResponse response,
		   				String Cnum,String Cpassword) {
	   CustomerLogin_B zsbBean_CL = null;
	   HttpSession session = request.getSession(true);
	   try{  
		   zsbBean_CL = (CustomerLogin_B)session.getAttribute("zsbBean_CL");
           if(zsbBean_CL == null){
        	   zsbBean_CL = new CustomerLogin_B();  //创建新的数据模型 
        	   session.setAttribute("zsbBean_CL",zsbBean_CL);
        	   zsbBean_CL = (CustomerLogin_B)session.getAttribute("zsbBean_CL");
           }
           String cnum = zsbBean_CL.getCnum();
           if(cnum.equals(Cnum)) {
        	   zsbBean_CL.setCbackprompt("已经登录");
        	   zsbBean_CL.setCnum(Cnum);
        	   zsbBean_CL.setCpassword(Cpassword);
           }
           else {  //数据模型存储新的登录用户
        	   zsbBean_CL.setCbackprompt("登录成功");
        	   zsbBean_CL.setCnum(Cnum);
        	   zsbBean_CL.setCpassword(Cpassword);
           }
	   }
	   catch(Exception ee){
		   zsbBean_CL = new CustomerLogin_B();  
		   session.setAttribute("zsbBean_CL",zsbBean_CL);
		   zsbBean_CL.setCbackprompt("登录成功");
		   zsbBean_CL.setCnum(Cnum);
    	   zsbBean_CL.setCpassword(Cpassword);
	   }
   	}	
   	//登录失败不更新javabean，直接反馈结果
   	public void fail(HttpServletRequest request,HttpServletResponse response,
                      String cnum,String cbackprompt) {
        response.setContentType("text/html;charset=gb2312");
        try {
        	PrintWriter out = response.getWriter();
        	out.println("<html><body>");
        	out.println("<h2>输入的账号:"+cnum+"<br>反馈的结果:"+cbackprompt+"</h2>") ;
        	out.println("<center><h3><font color=red>返回登录页面或主页</font></h3></center><br>");
        	out.println("<a href = CustomerLogin_J.jsp>登录页面</a>");
        	out.println("<br><a href = CustomerLogin_J.jsp>主页</a>");
        	out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
