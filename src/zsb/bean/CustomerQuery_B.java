package zsb.bean;

public class CustomerQuery_B {
	String []columnName ;           //�������
	String [][]tableRecord;   //��Ų�ѯ���ļ�¼
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