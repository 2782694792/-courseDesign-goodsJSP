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
            System.out.println("���������ɹ�");
		}
		catch(Exception e){
			System.out.println("��������ʧ�ܣ���");
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
				zsbBean_GTQCQ2 = new GoodsTypeQuery_B();  //����Javabean����
				session.setAttribute("zsbBean_GTQCQ2",zsbBean_GTQCQ2);
			}
		}
		catch(Exception exp){
			zsbBean_GTQCQ2 = new GoodsTypeQuery_B();  
			session.setAttribute("zsbBean_GTQCQ2",zsbBean_GTQCQ2);
		} 
		
		//�������ݿ����ӷ�ʽ
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		
		try{ 
			con = DriverManager.getConnection(uri,user0,password0);
			System.out.println("���ݿ����ӳɹ�");
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
	          // rowSet=new CachedRowSet();   //�����м�����S
	           rowSet.populate(rs);
	           zsbBean_GTQCQ2.setRowSet(rowSet);      //�м����ݴ洢��dataBean��  
	           con.close();                     //�ر�����
				System.out.println("0");
		}
		catch(SQLException exp){
			System.out.println("���ݿ�����ʧ�ܣ���");
		}
		response.sendRedirect("GoodsTypeQuery_J.jsp");//�ض���byPageShow.jsp
	} 
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
                        throws ServletException,IOException{
		doPost(request,response);
	}
}	