package zsb.servlet;

import zsb.bean.*;
import javax.sql.rowset.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GoodsTypeQuery_S extends HttpServlet{
	private static final long serialVersionUID = 1L;
	CachedRowSet rowSet = null;
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		try {  
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("加载驱动成功");
		}
		catch(Exception e){
			System.out.println("驱动加载失败！！");
		} 
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
		request.setCharacterEncoding("gb2312");
		
		String GTname = request.getParameter("GTQselect");
		System.out.println(GTname);
		if(GTname == null) 
			GTname = "0";
		Connection con; 
		
		HttpSession session = request.getSession(true); 
		GoodsTypeQuery_B zsbBean_GTQCQ2 = null;
		try{ 
			zsbBean_GTQCQ2 = (GoodsTypeQuery_B)session.getAttribute("zsbBean_GTQCQ2");
			if(zsbBean_GTQCQ2 == null){
				zsbBean_GTQCQ2 = new GoodsTypeQuery_B();  //创建Javabean对象
				session.setAttribute("zsbBean_GTQCQ2",zsbBean_GTQCQ2);
			}
		}
		catch(Exception exp){
			zsbBean_GTQCQ2 = new GoodsTypeQuery_B();  
			session.setAttribute("zsbBean_GTQCQ2",zsbBean_GTQCQ2);
		} 
		
		//定义数据库连接方式
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=郑烁彬_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		
		try{ 
			con = DriverManager.getConnection(uri,user0,password0);
			System.out.println("数据库连接成功");
			Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                                ResultSet.CONCUR_READ_ONLY);
			Statement sql1 = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
			System.out.println("0");
			ResultSet rs = sql.executeQuery("SELECT * FROM Goods where Gtype = '"+GTname+"'");
			ResultSet rs1 = sql1.executeQuery("SELECT * FROM GoodsType where GTname = '"+GTname+"'");
			while(rs1.next()) {
				String gtabout = rs1.getString(2);
				System.out.print(gtabout);
   	        	zsbBean_GTQCQ2.setAbout(gtabout);
			}
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
	          // rowSet=new CachedRowSet();   //创建行集对象S
	           rowSet.populate(rs);
	           zsbBean_GTQCQ2.setRowSet(rowSet);      //行集数据存储在dataBean中  
	           con.close();                     //关闭连接
				System.out.println("0");
		}
		catch(SQLException exp){
			System.out.println("数据库连接失败！！");
		}
		response.sendRedirect("GoodsTypeQuery_J.jsp");//重定向到byPageShow.jsp
	} 
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
		doPost(request,response);
	}
}	