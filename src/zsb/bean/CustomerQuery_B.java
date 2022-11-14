package zsb.bean;

public class CustomerQuery_B {
	String []columnName ;           //存放列名
	String [][]tableRecord;   //存放查询到的记录
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
	public CustomerQuery_B() {
	    tableRecord = new String[1][1];
	    columnName = new String[1]; 
	}
}