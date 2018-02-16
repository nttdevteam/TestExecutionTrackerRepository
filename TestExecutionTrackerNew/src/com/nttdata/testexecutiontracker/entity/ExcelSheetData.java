package com.nttdata.testexecutiontracker.entity;

import java.util.Date;

public class ExcelSheetData {
	private int id;
	private String projectName;
	private String cycleName;
	private String fsdName;
	private String functionality;
	private String testCaseNumber;
	private String browser;
	private String level1TestStatus;
	private String level1User;
	private String level1Date;
	private String almDefect;
	private String testEvidenceUploaded;
	private String level2BVStatus;
	private String level2User;
	private String level2Date;
	private int commentsId;
	
	
	public String getFunctionality() {
		return functionality;
	}
	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}
	public String getTestCaseNumber() {
		return testCaseNumber;
	}
	public void setTestCaseNumber(String testCaseNumber) {
		this.testCaseNumber = testCaseNumber;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getLevel1User() {
		return level1User;
	}
	public void setLevel1User(String level1User) {
		this.level1User = level1User;
	}
	public String getLevel1Date() {
		return level1Date;
	}
	public void setLevel1Date(String level1Date) {
		this.level1Date = level1Date;
	}
	public String getAlmDefect() {
		return almDefect;
	}
	public void setAlmDefect(String almDefect) {
		this.almDefect = almDefect;
	}
	public String getTestEvidenceUploaded() {
		return testEvidenceUploaded;
	}
	public void setTestEvidenceUploaded(String testEvidenceUploaded) {
		this.testEvidenceUploaded = testEvidenceUploaded;
	}
	public String getLevel2BVStatus() {
		return level2BVStatus;
	}
	public void setLevel2BVStatus(String level2bvStatus) {
		level2BVStatus = level2bvStatus;
	}
	public String getLevel2User() {
		return level2User;
	}
	public void setLevel2User(String level2User) {
		this.level2User = level2User;
	}
	public String getLevel2Date() {
		return level2Date;
	}
	public void setLevel2Date(String level2Date) {
		this.level2Date = level2Date;
	}
	public ExcelSheetData(int id, String projectName, String cycleName, String fsdName, String functionality, String testCaseNumber, String browser, 
			String level1TestStatus, String level1User,	String level1Date, String almDefect, 
			String testEvidenceUploaded, String level2bvStatus, String level2User,String level2Date, 
			int commentsId) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.cycleName = cycleName;
		this.fsdName = fsdName;
		this.functionality = functionality;
		this.testCaseNumber = testCaseNumber;
		this.level1TestStatus = level1TestStatus;
		this.browser = browser;
		this.level1User = level1User;
		this.level1Date = level1Date;
		this.almDefect = almDefect;
		this.testEvidenceUploaded = testEvidenceUploaded;
		level2BVStatus = level2bvStatus;
		this.level2User = level2User;
		this.level2Date = level2Date;
	}
	public String getLevel1TestStatus() {
		return level1TestStatus;
	}
	public void setLevel1TestStatus(String level1TestStatus) {
		this.level1TestStatus = level1TestStatus;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCommentsId() {
		return commentsId;
	}
	public void setCommentsId(int commentsId) {
		this.commentsId = commentsId;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * @return the cycleName
	 */
	public String getCycleName() {
		return cycleName;
	}
	/**
	 * @param cycleName the cycleName to set
	 */
	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}
	/**
	 * @return the fsdName
	 */
	public String getFsdName() {
		return fsdName;
	}
	/**
	 * @param fsdName the fsdName to set
	 */
	public void setFsdName(String fsdName) {
		this.fsdName = fsdName;
	}
	
	
}
