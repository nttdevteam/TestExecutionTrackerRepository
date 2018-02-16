package com.nttdata.testexecutiontracker.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static final String COMMA = ",";
	public static final String NEW_LINE = "\\n";
	public static final String SUCCESS_JSON = "{\"success\": true}";
	public static final String TEST_CASE_FILTER_NAME = "testCaseNumber";
	public static final String FILTER_NAME = "FILTER_NAME";
	public static final String FILTER_VALUE = "FILTER_VALUE";
	public static final String USER = "USER";
	public static final String USER_NAME = "userName";
	public static final String PROJECT_LIST = "PROJECT_LIST";
	public static final String REPORT = "report";
	public static final String CURRENT_LAST = "currentLast";
	public static final String CURRENT_LAST_ROW = "currentLastRow";
	public static int PAGE_SIZE = 50;
	public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\TestExecutionTracker\\TestExecutionTrackerNew\\test.db";
	public static final String GRAND_TOTAL_STRING = "Grand Total";
	public static final String TOTAL_STRING = "Total";

	public static final String DEFAULT_PROJECT = "defaultProject";
	public static final String PROJECT_SELECTED = "selectedProjectName";
	public static final String CYCLE_SELECTED = "selectedCycleName";

	public static String getDateString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		
		return dateFormat.format(date);
	}
}
