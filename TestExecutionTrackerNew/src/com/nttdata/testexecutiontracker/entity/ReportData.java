package com.nttdata.testexecutiontracker.entity;

public class ReportData {
	
	private String functionality;
	
	private String totalTests;
	
	private String testsPassed;
	
	private String testsFailed;

	private String testsNotTested;
	
	/**
	 * @param functionality
	 * @param totalTests
	 * @param testsPassed
	 * @param testsFailed
	 * @param testsNotTested
	 */
	public ReportData(String functionality, String totalTests, String testsPassed, String testsFailed,
			String testsNotTested) {
		super();
		this.functionality = functionality;
		this.totalTests = totalTests;
		this.testsPassed = testsPassed;
		this.testsFailed = testsFailed;
		this.testsNotTested = testsNotTested;
	}
	
	/**
	 * @return the functionality
	 */
	public String getFunctionality() {
		return functionality;
	}
	/**
	 * @param functionality the functionality to set
	 */
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	/**
	 * @return the totalTests
	 */
	public String getTotalTests() {
		return totalTests;
	}
	/**
	 * @param totalTests the totalTests to set
	 */
	public void setTotalTests(String totalTests) {
		this.totalTests = totalTests;
	}
	/**
	 * @return the testsPassed
	 */
	public String getTestsPassed() {
		return testsPassed;
	}
	/**
	 * @param testsPassed the testsPassed to set
	 */
	public void setTestsPassed(String testsPassed) {
		this.testsPassed = testsPassed;
	}
	/**
	 * @return the testsFailed
	 */
	public String getTestsFailed() {
		return testsFailed;
	}
	/**
	 * @param testsFailed the testsFailed to set
	 */
	public void setTestsFailed(String testsFailed) {
		this.testsFailed = testsFailed;
	}
	/**
	 * @return the testsNotTested
	 */
	public String getTestsNotTested() {
		return testsNotTested;
	}
	/**
	 * @param testsNotTested the testsNotTested to set
	 */
	public void setTestsNotTested(String testsNotTested) {
		this.testsNotTested = testsNotTested;
	}
	
	
}