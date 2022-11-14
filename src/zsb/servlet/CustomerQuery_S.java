package zsb.servlet;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import zsb.bean.*;

public class CustomerQuery_S extends HttpServlet {
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
			byte bb[]=s.getBytes("iso-8859-1");
            s=new String(bb);
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
		CustomerQuery_B zsbBean_CQ = null;
		CustomerLogin_B zsbBean_CL;
		HttpSession session0 = request.getSession(true);
		zsbBean_CL = (CustomerLogin_B)session0.getAttribute("zsbBean_CL");
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_CQ = (CustomerQuery_B)session.getAttribute("zsbBean_CQ");
            if(zsbBean_CQ == null){
            	zsbBean_CQ = new CustomerQuery_B();         //����Javabean����
            	session.setAttribute("zsbBean_CQ",zsbBean_CQ);
            }
        }
        catch(Exception exp){
        	zsbBean_CQ = new CustomerQuery_B();           //����Javabean����
        	session.setAttribute("zsbBean_CQ",zsbBean_CQ);
        } 
		//�������ݿ����ӷ�ʽ
		String uri = "jdbc:sqlserver://localhost:1433;characterEncoding=gb2312;DatabaseName=֣˸��_JSP_JCSX";
		String user0 = "sa";
		String password0 = "5436.......";
		Connection con; 
		Statement sql;
        ResultSet rs;
		try{ 
	        con = DriverManager.getConnection(uri,user0,password0);
	        sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        String condition = " select * from Customer"
	        				  +" where Cnum = '"+zsbBean_CL.getCnum()+"'"
		   					  +" and Cpassword ='"+zsbBean_CL.getCpassword()+"'";
	        rs = sql.executeQuery(condition);
	        System.out.println("��ѯ�������Ԫ���ݳɹ���ȡ������");
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();     //�õ������������
            String []columnName = new String[columnCount];
            for(int i = 0;i < columnName.length;i ++) {
                columnName[i] = metaData.getColumnName(i + 1);        //�õ�����
            }
            zsbBean_CQ.setColumnName(columnName);            //����Javabean����ģ��
            System.out.println("����ģ�͵������ɹ����£�����");
            rs.last();
            int rowNumber = rs.getRow();                     //�õ���¼��
            String [][] tableRecord = zsbBean_CQ.getTableRecord();
            tableRecord = new String[rowNumber][columnCount];
            rs.beforeFirst();
            int i = 0;
            while(rs.next()){
                for(int k = 0;k < columnCount;k ++) 
                    tableRecord[i][k] = rs.getString(k + 1);
                i ++; 
            }
            zsbBean_CQ.setTableRecord(tableRecord);            //����Javabean����ģ��
            System.out.println("����ģ�͵ı��¼�ɹ����£�����");
            con.close();
            RequestDispatcher dispatcher=
                    request.getRequestDispatcher("CustomerQuery_J.jsp");
            dispatcher.forward(request,response);
	        }
	    	catch(SQLException exp){
	    		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response) 
	                        throws ServletException,IOException{
		doPost(request,response);
		}
}