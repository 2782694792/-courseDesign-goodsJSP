package zsb.bean;

import java.util.LinkedList;

public class CustomerLogin_B {
	String cnum = null,cpassword = null,cbackprompt = "���¼";

	LinkedList<String> car = null; //�û��Ĺ��ﳵ
	public LinkedList<String> getCar() {
		return car;
	}
	public CustomerLogin_B() {
		this.car = new LinkedList<String>();
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public String getCbackprompt() {
		return cbackprompt;
	}

	public void setCbackprompt(String cbackprompt) {
		this.cbackprompt = cbackprompt;
	}
}
