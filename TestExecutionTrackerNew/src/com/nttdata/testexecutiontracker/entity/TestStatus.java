package com.nttdata.testexecutiontracker.entity;

public class TestStatus {
	private int totalTests;
	private int l1PassedTests;
	private int l1FailedTests;
	private int l1NotTestedTests;
	private int l1BlockedTests;
	private int l1InvalidTests;

	private int l2PassedTests;
	private int l2FailedTests;
	private int l2NotTestedTests;
	private int l2BlockedTests;
	private int l2InvalidTests;
	
	public TestStatus()
	{
		
	}

	
	/**
	 * @param totalTests
	 * @param l1PassedTests
	 * @param l1FailedTests
	 * @param l1NotTestedTests
	 * @param l2PassedTests
	 * @param l2FailedTests
	 * @param l2NotTestedTests
	 */
	public TestStatus(int totalTests, int l1PassedTests, int l1FailedTests, int l1NotTestedTests, int l2PassedTests,
			int l2FailedTests, int l2NotTestedTests) {
		super();
		this.totalTests = totalTests;
		this.l1PassedTests = l1PassedTests;
		this.l1FailedTests = l1FailedTests;
		this.l1NotTestedTests = l1NotTestedTests;
		this.l2PassedTests = l2PassedTests;
		this.l2FailedTests = l2FailedTests;
		this.l2NotTestedTests = l2NotTestedTests;
	}

	/**
	 * @param totalTests
	 * @param l1PassedTests
	 * @param l1FailedTests
	 * @param l1NotTestedTests
	 * @param l1BlockedTests
	 * @param l1InvalidTests
	 * @param l2PassedTests
	 * @param l2FailedTests
	 * @param l2NotTestedTests
	 * @param l2BlockedTests
	 * @param l2InvalidTests
	 */
	public TestStatus(int totalTests, int l1PassedTests, int l1FailedTests, int l1NotTestedTests, int l1BlockedTests, int l1InvalidTests, int l2PassedTests,
			int l2FailedTests, int l2NotTestedTests, int l2BlockedTests, int l2InvalidTests) {
		super();
		this.totalTests = totalTests;
		this.l1PassedTests = l1PassedTests;
		this.l1FailedTests = l1FailedTests;
		this.l1NotTestedTests = l1NotTestedTests;
		this.l1BlockedTests = l1BlockedTests;
		this.l1InvalidTests = l1InvalidTests;

		this.l2PassedTests = l2PassedTests;
		this.l2FailedTests = l2FailedTests;
		this.l2NotTestedTests = l2NotTestedTests;
		this.l2BlockedTests = l2BlockedTests;
		this.l2InvalidTests = l2InvalidTests;
	}

	/**
	 * @return the totalTests
	 */
	public int getTotalTests() {
		return totalTests;
	}

	/**
	 * @param totalTests the totalTests to set
	 */
	public void setTotalTests(int totalTests) {
		this.totalTests = totalTests;
	}

	/**
	 * @return the l1PassedTests
	 */
	public int getL1PassedTests() {
		return l1PassedTests;
	}

	/**
	 * @param l1PassedTests the l1PassedTests to set
	 */
	public void setL1PassedTests(int l1PassedTests) {
		this.l1PassedTests = l1PassedTests;
	}

	/**
	 * @return the l1FailedTests
	 */
	public int getL1FailedTests() {
		return l1FailedTests;
	}

	/**
	 * @param l1FailedTests the l1FailedTests to set
	 */
	public void setL1FailedTests(int l1FailedTests) {
		this.l1FailedTests = l1FailedTests;
	}

	/**
	 * @return the l1NotTestedTests
	 */
	public int getL1NotTestedTests() {
		return l1NotTestedTests;
	}

	/**
	 * @param l1NotTestedTests the l1NotTestedTests to set
	 */
	public void setL1NotTestedTests(int l1NotTestedTests) {
		this.l1NotTestedTests = l1NotTestedTests;
	}

	/**
	 * @return the l2PassedTests
	 */
	public int getL2PassedTests() {
		return l2PassedTests;
	}

	/**
	 * @param l2PassedTests the l2PassedTests to set
	 */
	public void setL2PassedTests(int l2PassedTests) {
		this.l2PassedTests = l2PassedTests;
	}

	/**
	 * @return the l2FailedTests
	 */
	public int getL2FailedTests() {
		return l2FailedTests;
	}

	/**
	 * @param l2FailedTests the l2FailedTests to set
	 */
	public void setL2FailedTests(int l2FailedTests) {
		this.l2FailedTests = l2FailedTests;
	}

	/**
	 * @return the l2NotTestedTests
	 */
	public int getL2NotTestedTests() {
		return l2NotTestedTests;
	}

	/**
	 * @param l2NotTestedTests the l2NotTestedTests to set
	 */
	public void setL2NotTestedTests(int l2NotTestedTests) {
		this.l2NotTestedTests = l2NotTestedTests;
	}


	/**
	 * @return the l1BlockedTests
	 */
	public int getL1BlockedTests() {
		return l1BlockedTests;
	}


	/**
	 * @param l1BlockedTests the l1BlockedTests to set
	 */
	public void setL1BlockedTests(int l1BlockedTests) {
		this.l1BlockedTests = l1BlockedTests;
	}


	/**
	 * @return the l1InvalidTests
	 */
	public int getL1InvalidTests() {
		return l1InvalidTests;
	}


	/**
	 * @param l1InvalidTests the l1InvalidTests to set
	 */
	public void setL1InvalidTests(int l1InvalidTests) {
		this.l1InvalidTests = l1InvalidTests;
	}


	/**
	 * @return the l2BlockedTests
	 */
	public int getL2BlockedTests() {
		return l2BlockedTests;
	}


	/**
	 * @param l2BlockedTests the l2BlockedTests to set
	 */
	public void setL2BlockedTests(int l2BlockedTests) {
		this.l2BlockedTests = l2BlockedTests;
	}


	/**
	 * @return the l2InvalidTests
	 */
	public int getL2InvalidTests() {
		return l2InvalidTests;
	}


	/**
	 * @param l2InvalidTests the l2InvalidTests to set
	 */
	public void setL2InvalidTests(int l2InvalidTests) {
		this.l2InvalidTests = l2InvalidTests;
	}
	
}
