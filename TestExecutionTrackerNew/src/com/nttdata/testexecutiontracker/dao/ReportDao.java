package com.nttdata.testexecutiontracker.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.nttdata.testexecutiontracker.entity.Project;
import com.nttdata.testexecutiontracker.entity.Report;
import com.nttdata.testexecutiontracker.entity.TestStatus;
import com.nttdata.testexecutiontracker.util.Util;

public class ReportDao extends BaseDao{
	
	private static final String GET_ALL_FSD = "select distinct(fsdName) from excelsheet ";
	
	
	private Map getResult(String projectName, String cycleName, String fsdName, String filterName, String filterValue ) throws Exception
	{
		ResultSet rs = null;
		Map map = new LinkedHashMap();
		String sql = getSQl(projectName, cycleName, fsdName, filterName, filterValue);
		stmt.execute(sql);
		rs = stmt.getResultSet(); 
		while(rs.next()){
			String functionality = rs.getString("functionality");
			int failedTests = rs.getInt("tests");
			map.put(functionality, failedTests);
		}
		return map;
	}
	
	private String getSQl( String projectName, String cycleName){
		if(projectName==null || cycleName == null)
			return GET_ALL_FSD;
		else
			return GET_ALL_FSD+" where projectName='"+projectName+"' and cycleName='"+cycleName+"';";
	}
	private String getSQl( String projectName, String cycleName, String fsdName){
		String sql = "select functionality,  count( * ) totalTests from excelsheet where fsdName='"+fsdName+"' group by functionality";

		if(projectName==null || cycleName == null)
			return sql;
		else
			return "select functionality,  count( * ) totalTests from excelsheet where projectName='"+projectName+"' and cycleName='"+cycleName+"' and fsdName='"+fsdName+"' group by functionality";
	}
	private String getSQl( String projectName, String cycleName, String fsdName, String filterName, String filterValue){
		if(projectName==null || cycleName == null)
			return "select functionality,  count( * ) tests from excelsheet where "+filterName+"='"+filterValue+"' and fsdName='"+fsdName+"' group by functionality";
		else
			return "select functionality,  count( * ) tests from excelsheet where projectName='"+projectName+"' and cycleName='"+cycleName+"' and "+filterName+"='"+filterValue+"' and fsdName='"+fsdName+"' group by functionality;";
	}
	
	public Report getReportCard(String projectName, String cycleName)
	{
		List<List<Map<String, int[]>>> reportList = new ArrayList();
		List<String>fsdList = new ArrayList();
		List<String>functionalityList = new ArrayList();

		Map<String, Map<String, TestStatus>>fsdFunctionalityMap = new LinkedHashMap();
		Map<String, TestStatus>consolidatedMap = new LinkedHashMap();
		consolidatedMap.put(Util.GRAND_TOTAL_STRING, new TestStatus(0,0,0,0,0,0,0));
		
		Report report = new Report();
		ResultSet rs = null;
		try{
			stmt = cRead.createStatement();
			
			stmt.execute(getSQl(projectName, cycleName));
			rs = stmt.getResultSet();
			while ( rs.next() ) {
				String fsdName = rs.getString(1);
				fsdList.add(fsdName); 
			}
			

			for(String fsdName: fsdList){
				Map<String, TestStatus>functionalityTestsMap = new LinkedHashMap();
				Map<String, Integer>totalTestsMap = new LinkedHashMap();
				Map<String, Integer>l1FunctionalityPassedTestsMap = new LinkedHashMap();
				Map<String, Integer>l1FunctionalityFailedTestsMap = new LinkedHashMap();
				Map<String, Integer>l1FunctionalityNotTestedTestsMap = new LinkedHashMap();
				
				Map<String, Integer>l2FunctionalityPassedTestsMap = new LinkedHashMap();
				Map<String, Integer>l2FunctionalityFailedTestsMap = new LinkedHashMap();
				Map<String, Integer>l2FunctionalityNotTestedTestsMap = new LinkedHashMap();
				
				//Get All Functionality and counts of their corresponding tests.
				stmt.execute(getSQl( projectName, cycleName, fsdName));
				rs = stmt.getResultSet(); 
				while(rs.next()){
					String functionality = rs.getString("functionality");
					int totalTests = rs.getInt("totalTests");
					functionalityList.add(functionality);
					totalTestsMap.put(functionality, totalTests);
				}
				//Get All Functionality and counts of their corresponding PASSED tests for L1 users
				l1FunctionalityPassedTestsMap = getResult(projectName, cycleName, fsdName, "level1teststatus", "Pass");
				
				//Get All Functionality and counts of their corresponding FAILED tests for L1 users
				l1FunctionalityFailedTestsMap = getResult(projectName, cycleName, fsdName, "level1teststatus", "Fail");
				
				//Get All Functionality and counts of their corresponding NOT TESTED tests for L1 users
				l1FunctionalityNotTestedTestsMap = getResult(projectName, cycleName, fsdName, "level1teststatus", "NotTested");

				//Get All Functionality and counts of their corresponding PASSED tests for L2 users
				l2FunctionalityPassedTestsMap = getResult(projectName, cycleName, fsdName, "level2BVStatus", "Pass");
				
				//Get All Functionality and counts of their corresponding FAILED tests for L2 users
				l2FunctionalityFailedTestsMap = getResult(projectName, cycleName, fsdName, "level2BVStatus", "Fail");
				
				//Get All Functionality and counts of their corresponding NOT TESTED tests for L2 users
				l2FunctionalityNotTestedTestsMap = getResult(projectName, cycleName, fsdName, "level2BVStatus", "NotTested");
				
				//Consolidate all test results
				for (Map.Entry<String, Integer> entry : totalTestsMap.entrySet())
				{
					TestStatus testStatus = new TestStatus();
					testStatus.setTotalTests(entry.getValue());
					if(l1FunctionalityPassedTestsMap.containsKey(entry.getKey()))
						testStatus.setL1PassedTests(l1FunctionalityPassedTestsMap.get(entry.getKey()));	
					if(l1FunctionalityFailedTestsMap.containsKey(entry.getKey()))
						testStatus.setL1FailedTests(l1FunctionalityFailedTestsMap.get(entry.getKey()));
					if(l1FunctionalityNotTestedTestsMap.containsKey(entry.getKey()))
						testStatus.setL1NotTestedTests(l1FunctionalityNotTestedTestsMap.get(entry.getKey()));

					if(l2FunctionalityPassedTestsMap.containsKey(entry.getKey()))
						testStatus.setL2PassedTests(l1FunctionalityPassedTestsMap.get(entry.getKey()));	
					if(l2FunctionalityFailedTestsMap.containsKey(entry.getKey()))
						testStatus.setL2FailedTests(l2FunctionalityFailedTestsMap.get(entry.getKey()));
					if(l2FunctionalityNotTestedTestsMap.containsKey(entry.getKey()))
						testStatus.setL2NotTestedTests(l2FunctionalityNotTestedTestsMap.get(entry.getKey()));

					functionalityTestsMap.put(entry.getKey(), testStatus);
				}
				Map<String, TestStatus> map = addAll(totalTestsMap, l1FunctionalityPassedTestsMap, l1FunctionalityFailedTestsMap, l1FunctionalityNotTestedTestsMap,
						l2FunctionalityPassedTestsMap, l2FunctionalityFailedTestsMap, l2FunctionalityNotTestedTestsMap);
				consolidatedMap = consolidateAll(consolidatedMap, map);
				functionalityTestsMap.putAll(map);
				fsdFunctionalityMap.put(fsdName, functionalityTestsMap);
			}
			fsdFunctionalityMap.put(Util.GRAND_TOTAL_STRING, consolidatedMap);
			report.setFsdFunctionality(fsdFunctionalityMap);
			
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
		}
		return report;
	}

	private Map<String, TestStatus> addAll(Map<String, Integer> totalTests, Map<String, Integer> l1PassedTests, Map<String, Integer> l1FailedTests, Map<String, Integer> l1NotTestedTests,
			Map<String, Integer> l2PassedTests, Map<String, Integer> l2FailedTests, Map<String, Integer> l2NotTestedTests)
	{
		Map<String, TestStatus> map = new LinkedHashMap();
		int total = 0;
		int l1Passed = 0;
		int l1Failed = 0;
		int l1NotTested = 0;
		int l2Passed = 0;
		int l2Failed = 0;
		int l2NotTested = 0;
		
		
		
		for (Map.Entry<String, Integer> entry : totalTests.entrySet())
		{
			total = total + entry.getValue();
			if(l1PassedTests.containsKey(entry.getKey()))
				l1Passed = l1Passed + l1PassedTests.get(entry.getKey());
			if(l1FailedTests.containsKey(entry.getKey()))
				l1Failed = l1Failed + l1FailedTests.get(entry.getKey());
			if(l1NotTestedTests.containsKey(entry.getKey()))
				l1NotTested = l1NotTested + l1NotTestedTests.get(entry.getKey());
			if(l2PassedTests.containsKey(entry.getKey()))
				l2Passed = l2Passed + l2PassedTests.get(entry.getKey());
			if(l2FailedTests.containsKey(entry.getKey()))
				l2Failed = l2Failed + l2FailedTests.get(entry.getKey());
			if(l2NotTestedTests.containsKey(entry.getKey()))
				l2NotTested = l2NotTested + l2NotTestedTests.get(entry.getKey());
			
			
		}
		map.put("Total", new TestStatus(total, l1Passed, l1Failed, l1NotTested, l2Passed, l2Failed, l2NotTested));
		return map;
	}
	private Map<String, TestStatus> consolidateAll(Map<String, TestStatus> consolidatedMap, Map<String, TestStatus> currentMap)
	{
		int total = 0;
		int passed = 0;
		int failed = 0;
		int notTested = 0;
		
		TestStatus consolidatedTestStatus = consolidatedMap.get(Util.GRAND_TOTAL_STRING);
		TestStatus currentTestStatus = currentMap.get(Util.TOTAL_STRING);
		
		consolidatedTestStatus.setTotalTests(consolidatedTestStatus.getTotalTests() + currentTestStatus.getTotalTests());
		consolidatedTestStatus.setL1PassedTests(consolidatedTestStatus.getL1PassedTests() + currentTestStatus.getL1PassedTests());
		consolidatedTestStatus.setL1FailedTests(consolidatedTestStatus.getL1FailedTests() + currentTestStatus.getL1FailedTests());
		consolidatedTestStatus.setL1NotTestedTests(consolidatedTestStatus.getL1NotTestedTests() + currentTestStatus.getL1NotTestedTests());
		
		consolidatedTestStatus.setL2PassedTests(consolidatedTestStatus.getL2PassedTests() + currentTestStatus.getL2PassedTests());
		consolidatedTestStatus.setL2FailedTests(consolidatedTestStatus.getL2FailedTests() + currentTestStatus.getL2FailedTests());
		consolidatedTestStatus.setL2NotTestedTests(consolidatedTestStatus.getL2NotTestedTests() + currentTestStatus.getL2NotTestedTests());

		consolidatedMap.put(Util.GRAND_TOTAL_STRING, consolidatedTestStatus);
		return consolidatedMap;
	}
	
	public static void main(String args[]){
		ReportDao reportDao = new ReportDao();
		Report report = reportDao.getReportCard(null, null);
		
		Map<String, Map<String, TestStatus>> fsdFunctionality = report.getFsdFunctionality();

		for(Map.Entry<String, Map<String, TestStatus>> entry : fsdFunctionality.entrySet() ){
			System.out.println("---------------"+entry.getKey());
			
			Map<String, TestStatus> functionalities = entry.getValue();
			for(Map.Entry<String, TestStatus> functionality : functionalities.entrySet()){
				System.out.println(functionality.getKey()+" = "+ functionality.getValue().getTotalTests() +" = "+ functionality.getValue().getL1PassedTests() +" + "+ functionality.getValue().getL1FailedTests());
			}
		}
	}
}