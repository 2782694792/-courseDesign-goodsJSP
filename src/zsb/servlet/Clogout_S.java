package zsb.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zsb.bean.*;

public class Clogout_S {
	public void success(HttpServletRequest request,HttpServletResponse response,
				String Cnum,String Cpassword) {
		CustomerLogin_B zsbBean_CL = null;
		HttpSession session = request.getSession(true);
		try{  
			zsbBean_CL = (CustomerLogin_B)session.getAttribute("zsbBean");
			if(zsbBean_CL == null){
				zsbBean_CL = new CustomerLogin_B();  //�����µ�����ģ�� 
				session.setAttribute("zsbBean_CL",zsbBean_CL);
				zsbBean_CL = (CustomerLogin_B)session.getAttribute("zsbBean_CL");
			}
			else {  //����ģ�ʹ洢�µĵ�¼�û�
				zsbBean_CL.setCbackprompt(null);
				zsbBean_CL.setCnum(null);
				session.invalidate();
			}
		}
		catch(Exception ee){
			System.out.println("�˳���¼ʧ�ܣ�");
		}
	}
}
