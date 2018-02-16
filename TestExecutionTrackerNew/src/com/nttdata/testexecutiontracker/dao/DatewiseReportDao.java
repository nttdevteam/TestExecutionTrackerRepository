package com.nttdata.testexecutiontracker.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nttdata.testexecutiontracker.entity.Project;
import com.nttdata.testexecutiontracker.entity.Report;
import com.nttdata.testexecutiontracker.entity.TestStatus;
import com.nttdata.testexecutiontracker.util.Util;

public class DatewiseReportDao extends BaseDao{
	
	private static final String GET_ALL_FSD = "select distinct(fsdName) from excelsheet where ";

	public static void main(String args[]){
		DatewiseReportDao datewiseReport = new DatewiseReportDao();
		String projectName = "";
		String cycleName = "ST_Cycle_3";
		String statusType ="level1TestStatus";
		String level = "level1Date";
		
		List<Map> list = null;
		try{
			list = datewiseReport.getDatewiseReport(projectName, cycleName, statusType, level);
		}catch(Exception e){
			e.printStackTrace();
		}
		for(Map<String, TestStatus> map: list)
		{
			for(Map.Entry<String, TestStatus>entry: map.entrySet())
			{
				System.out.println(entry.getKey()+"\t"+entry.getValue().getL1PassedTests() +"\t"+entry.getValue().getL1FailedTests()+"\t"+entry.getValue().getL1NotTestedTests()+"\t"+entry.getValue().getL1BlockedTests()+"\t"+entry.getValue().getL1InvalidTests());
				
			}
		}
	}
	
	public List<Map> getDatewiseReport(String projectName, String cycleName, String statusType, String level) throws SQLException
	{
		List<String> list = new ArrayList();
		try{
			list = getAllL1DatesLoggedForProjectCycle(projectName, cycleName); 
		}catch(Exception e){
			e.printStackTrace();
		}

		List<Map> datewiseReport = new ArrayList();

		for(String date: list)
		{
			if(date != null) 
				datewiseReport.add(getStatusOnDate(projectName, cycleName, statusType, level, date));
		}
		
		return datewiseReport;
	}
	
	private Map<String, TestStatus> getStatusOnDate(String projectName, String cycleName, String statusType, String level, String date) throws SQLException
	{
		Map<String, TestStatus> map = new LinkedHashMap();
		ResultSet rs = null;
		String sql = null;
		TestStatus testStatus = new TestStatus();
		
		sql = "select "+statusType+", count(*) total from excelsheet where  projectName='"+projectName+"' and cyclename='"+cycleName+"' and substr("+level+", 1,10)='"+date+"' group by "+statusType+";";
		stmt = cRead.createStatement();
		stmt.execute(sql);
		rs = stmt.getResultSet();
		
		int totalTests = 0;
		while(rs.next()){
			String testStatusString = rs.getString(statusType);
			int testStatusCount = rs.getInt("total");
			if(null!=testStatusString)
			{
				if(testStatusString.compareTo("Pass") == 0)
					testStatus.setL1PassedTests(testStatusCount);
				else
					testStatus.setL2PassedTests(testStatusCount);
				if(testStatusString.compareTo("Fail") == 0)
					testStatus.setL1FailedTests(testStatusCount);
				else
					testStatus.setL2FailedTests(testStatusCount);
				if(testStatusString.compareTo("NotTested") == 0)
					testStatus.setL1NotTestedTests(testStatusCount);
				else
					testStatus.setL2NotTestedTests(testStatusCount);

				if(testStatusString.compareTo("Blocked") == 0)
					testStatus.setL1BlockedTests(testStatusCount);
				else
					testStatus.setL2BlockedTests(testStatusCount);

				if(testStatusString.compareTo("Invalid") == 0)
					testStatus.setL1InvalidTests(testStatusCount);
				else
					testStatus.setL2InvalidTests(testStatusCount);
			}
			
			totalTests = totalTests + testStatusCount;
		}
		testStatus.setTotalTests(totalTests);
		map.put(date, testStatus);
		return map;
	}
	
	
	
	public List<String> getAllL1DatesLoggedForProjectCycle(String projectName, String cycleName) throws Exception
	{
		List<String> list = new ArrayList();
		ResultSet rs = null;
		
		stmt = cRead.createStatement();
		
		
		String sql = "select distinct(substr(level1Date, 1,10)) from excelsheet where projectName='"+projectName+"' and cyclename='"+cycleName+"' order by id;";
		stmt.execute(sql);
		rs = stmt.getResultSet(); 
		while(rs.next()){
			String distinctDates = rs.getString(1);
			if(distinctDates!=null)
				list.add(distinctDates);
		}
		return list;
	}
}