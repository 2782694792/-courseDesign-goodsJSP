package zsb.bean;

import javax.sql.rowset.*; 

public class GoodsTypeQuery_B{
	String about=null;
    public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	CachedRowSet rowSet=null;         //�洢����ȫ����¼���м�����
    int pageSize=3;                      //ÿҳ��ʾ�ļ�¼��
    int totalPages=1;                     //��ҳ�����ҳ��
    int currentPage =1   ;                 //��ǰ��ʾҳ 
    public void setRowSet(CachedRowSet set){
       rowSet=set;
    }
    public CachedRowSet getRowSet(){
       return rowSet;
    }
    public void setPageSize(int size){
       pageSize=size;
    }
    public int getPageSize(){
       return pageSize;
    } 
    public int getTotalPages(){
       return totalPages;
    } 
    public void setTotalPages(int n){
       totalPages=n; 
    }
    public void setCurrentPage(int n){
       currentPage =n;
    }
    public int getCurrentPage(){
       return currentPage ;
    }
}
