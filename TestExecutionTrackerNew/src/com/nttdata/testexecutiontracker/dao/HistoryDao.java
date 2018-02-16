package com.nttdata.testexecutiontracker.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nttdata.testexecutiontracker.entity.History;
import com.nttdata.testexecutiontracker.util.Util;

public class HistoryDao extends BaseDao{
	public void insertRow(int excelSheetId, String updateDate, String updateUser, String historyData)
	{
		int historyId = selectMaxHistoryId() + 1;
	    Statement stmt = null;
	    try {
			stmt = cWrite.createStatement();
			String sql = "INSERT INTO HISTORY (history_id, excelsheet_id, update_date, update_user,history_data) VALUES "
					+ "("+historyId+","+excelSheetId+",'"+updateDate+"','"+updateUser+"','"+historyData+"');";
			stmt.executeUpdate(sql);
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	public int selectMaxHistoryId()
	{
		int historyId = 1;
	    Statement stmt = null;
	    try {
			System.out.println("Opened database successfully");
			stmt = cRead.createStatement();
			
			stmt.execute("SELECT MAX(HISTORY_ID) FROM HISTORY");    
			ResultSet rs = stmt.getResultSet(); // 
			if( rs.next() ){
				historyId = rs.getInt(1);
			}
			
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return historyId;
	}
	
	public List<History> getRowsWithId(int excelSheetId)
	{
		List<History> list = new ArrayList();
		
	    try {
			stmt = cRead.createStatement();
			
			stmt.execute("SELECT * FROM HISTORY WHERE EXCELSHEET_ID="+excelSheetId);    
			ResultSet rs = stmt.getResultSet(); // 
			while( rs.next() ){
				int historyId = rs.getInt("history_id");
				String updateDate = rs.getString("update_date");
				String updateUser = rs.getString("update_user");
				String historyData = rs.getString("history_data");
				History history = new History(historyId, excelSheetId, updateDate, updateUser, historyData);
				list.add(history);
			}
			
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return list;
	}
	
	
	public static void main(String args[])
	{
		HistoryDao historyDao = new HistoryDao();
//		historyDao.insertRow(1, "updateDate", "updateUser", "historyData");
		
		
		List list = historyDao.getRowsWithId(3);
		for(Object history: list)
		{
			History historyObject = ((History)history);
		}
	}
}
