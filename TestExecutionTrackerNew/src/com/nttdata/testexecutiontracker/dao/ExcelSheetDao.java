package com.nttdata.testexecutiontracker.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nttdata.testexecutiontracker.entity.ExcelSheetData;
import com.nttdata.testexecutiontracker.util.Util;

public class ExcelSheetDao extends BaseDao{
	public static final String CONNECTION_STRING = "jdbc:sqlite:C:/workspaces/Deepak_WorkSpace/TestExecutionTracker/test.db";
	ProjectDao projectDao = new ProjectDao();
	public void updateRow(int rowId, String columnName, String columnValue, String userNameColumn, String dateNameColumn, String userNameValue, String dateValue)
	{
		Statement stmt = null;
		try {
			stmt = cWrite.createStatement();
			
			String sql = "UPDATE EXCELSHEET set "+ columnName +" = '"+ columnValue +"' ,"+ userNameColumn +" = '"+ userNameValue +"', "+ dateNameColumn +" = '"+ dateValue +"' where ID="+rowId+";";
			stmt.executeUpdate(sql);
			
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
	    }
	}
	
	public List<ExcelSheetData> getRow(String projectSelected, String cycleSelected, int rowId)
	{
		List<ExcelSheetData> list = new ArrayList();
		Statement stmt = null;
		try {
			stmt = cRead.createStatement();
			String sql = "SELECT * FROM EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"' and ID="+rowId+";";
			ResultSet rs = stmt.executeQuery(sql);
			list = populateDataSet(rs);

			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
	    }
		return list;
	}
	
	public int getAllRowsCount(String projectSelected, String cycleSelected, String filter)
	{
		int totalRowsCount = 0 ;
		if(filter!=null && filter!="")
			filter = "and "+ filter;
			
		try{
			stmt = cRead.createStatement();
			stmt.execute("SELECT count(*) from EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"' "+filter);    
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				totalRowsCount = rs.getInt(1);
			}
//			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
			return 0;
		}
		return totalRowsCount;
	}
	public List<String> getUnique(String rowName)
	{
		List<String> list = new ArrayList();
		try{
			stmt = cRead.createStatement();
			stmt.execute("SELECT distinct("+rowName+") from EXCELSHEET");    
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				list.add(rs.getString(1));
			}
//			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
			return null;
		}
		return list;
	}
	
	public List<ExcelSheetData> getAllRows(String projectSelected, String cycleSelected, String filter)
	{
		List<ExcelSheetData> list = new ArrayList();
		if(filter == null || filter.compareTo("") == 0 ) {
			filter ="";
		}
		else
		{
			filter =" and " + filter;
		}
	    Statement stmt = null;
	    try {
	      stmt = cRead.createStatement();
	      String sql = "SELECT * FROM EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"' "+filter+" order by id LIMIT 50;";
	      ResultSet rs = stmt.executeQuery( sql );
	      list = populateDataSet(rs);
	      
//	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	    }
	    return list;
	}
	public List<ExcelSheetData> getNext(String projectSelected, String cycleSelected, int currentLast, String filter)
	{
		List<ExcelSheetData> list = new ArrayList();

		
	    Statement stmt = null;
	    try {
	      stmt = cRead.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"'  and id>"+currentLast+filter+" order by id limit 50;" );
	      list = populateDataSet(rs);

//	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	    }
	    return list;
	}

	public List<ExcelSheetData> getPrevious(String projectSelected, String cycleSelected, int currentLast, String filter)
	{
		String GET_PREVIOUS_SQL = "SELECT * FROM EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"'  and id>"+currentLast+filter+" order by id limit 50;";
		
		List<ExcelSheetData> list = new ArrayList();
		
		if(filter == null || filter == "")
		{
			if(currentLast%Util.PAGE_SIZE !=0)currentLast = (currentLast - (currentLast%(Util.PAGE_SIZE))) - 100;
			else			currentLast = currentLast - 100;	
			GET_PREVIOUS_SQL = "SELECT * FROM EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"'  and id>"+currentLast+filter+" order by id limit 50;";
		}
		if(filter!=null && filter!="")
		{
			GET_PREVIOUS_SQL = "SELECT * FROM EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"'  and "+filter+" order by id ;";
			filter = " and "+ filter;
		}

	    try {
	      stmt = cRead.createStatement();
	      ResultSet rs = stmt.executeQuery( GET_PREVIOUS_SQL );
	      list = populateDataSet(rs);

//	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	    }
		if(filter!=null && filter!="")
		{
			List<ExcelSheetData> temporaryList = new ArrayList();
			List<ExcelSheetData> filteredList = new ArrayList();

			int i = 0 ;
			for(ExcelSheetData excelSheetData:list)
			{
				temporaryList.add(excelSheetData);
				if(excelSheetData.getId() == currentLast)
				{
					filteredList = getFilteredList(temporaryList, i);
					return filteredList;
				}
				i++;
			}
		}
		else{
		    return list;
		}
		return list;
	}

	private List<ExcelSheetData> getFilteredList(List<ExcelSheetData> list, int count)
	{
		List<ExcelSheetData> filteredList = new ArrayList();
		
		for(int i= count - (2*(Util.PAGE_SIZE)) + 1; i<= (count - (Util.PAGE_SIZE)); i++)
		{
			filteredList.add(list.get(i));
		}
		return filteredList;
	}
	public List<ExcelSheetData> getPage(String projectSelected, String cycleSelected, int pageNumber, String filter)
	{
		List<ExcelSheetData> list = new ArrayList();
		int currentLast = (pageNumber-1)*Util.PAGE_SIZE;
		
	    try {
	      stmt = cRead.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM EXCELSHEET where projectName='"+projectSelected+"' and cycleName='"+cycleSelected+"'  and id>"+currentLast+filter+" order by id limit 50;" );
	      list = populateDataSet(rs);

//	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	    }
	    return list;
	}
	public static void main(String args[])
	{
		ExcelSheetDao excelSheetDao =new ExcelSheetDao();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		excelSheetDao.updateRow(3, "browser", "test", "level1User", "level1Date", "sheelaa", dateFormat.format(date));
	}

	private List<ExcelSheetData> populateDataSet(ResultSet rs) throws SQLException 
	{
		List<ExcelSheetData> list = new ArrayList();
		
		while ( rs.next() ) {
	         int id = rs.getInt("id");
	         String  functionality = rs.getString("functionality");
	         String  projectName = rs.getString("projectName");
	         String  cycleName = rs.getString("cycleName");
	         String  fsdName = rs.getString("fsdName");
	         String  testCaseNumber = rs.getString("testCaseNumber");
	         String  browser = rs.getString("browser") != null ? rs.getString("browser") : "";
	         String  level1TestStatus = rs.getString("level1TestStatus") != null ? rs.getString("level1TestStatus") : "";
	         String  level1User = rs.getString("level1User") != null ? rs.getString("level1User") : "";
	         String  level1Date = rs.getString("level1Date") != null ? rs.getString("level1Date") : null;
	         String  almDefect = rs.getString("almDefect") != null ? rs.getString("almDefect") : "";
	         String  testEvidenceUploaded = rs.getString("testEvidenceUploaded") != null ? rs.getString("testEvidenceUploaded") : "";
	         String  level2BVStatus = rs.getString("level2BVStatus") != null ? rs.getString("level2BVStatus") : "";
	         String  level2User = rs.getString("level2User") != null ? rs.getString("level2User") : "";
	         String level2Date = rs.getString("level2Date") != null ? rs.getString("level2Date") : null;
	         int  commentsId = rs.getInt("remarksId") != 0 ? rs.getInt("remarksId") : 0;

	         ExcelSheetData excelSheetRow = new ExcelSheetData(id, projectName, cycleName, fsdName, functionality, testCaseNumber, browser, 
	        		 level1TestStatus, level1User,level1Date, almDefect, 
	        		 testEvidenceUploaded, level2BVStatus, level2User,level2Date, 
	        		 commentsId);
	         list.add(excelSheetRow);
		}
		return list;
	}
	
	public boolean insertRow(int projectId, int cycleId, String fsd, String functionality, String testCaseName)
	{
		boolean status = false;
		
		String projectName = projectDao.getProjectName(projectId);
		String cycleName = projectDao.getCycleName(projectId, cycleId);

		int id = selectMaxExcelSheetId() + 1;
		
	    try {
			stmt = cWrite.createStatement();
			String sql = "insert into EXCELSHEET (ID, PROJECTNAME, CYCLENAME, FSDNAME, FUNCTIONALITY, TESTCASENUMBER) VALUES('"+id+"','"+projectName+"','"+cycleName+"','"+fsd+"','"+functionality+"','"+testCaseName+"');";
			stmt.executeUpdate(sql);
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    }
		return true;
		
	}
	
	public int selectMaxExcelSheetId()
	{
		int historyId = 1;
	    Statement stmt = null;
	    try {
			stmt = cRead.createStatement();
			stmt.execute("SELECT MAX(ID) FROM EXCELSHEET");    
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

	
}
