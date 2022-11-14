package zsb.bean;

public class CustomerRegister_B {
	String cname,cnum,cid,cpassword,cbackprompt = "Çë×¢²á";
	String []columnName;
	String [][]tableRecord = null;
	int pageSize = 1;
	int totalPages = 1;

	public String[] getColumnName() {
		return columnName;
	}

	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}

	public String[][] getTableRecord() {
		return tableRecord;
	}

	public void setTableRecord(String[][] tableRecord) {
		this.tableRecord = tableRecord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getCbackprompt() {
		return cbackprompt;
	}

	public void setCbackprompt(String cbackprompt) {
		this.cbackprompt = cbackprompt;
	}
}