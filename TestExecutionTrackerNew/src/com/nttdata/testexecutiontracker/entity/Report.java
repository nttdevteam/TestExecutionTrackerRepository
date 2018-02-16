package com.nttdata.testexecutiontracker.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
	private String projectName;
	private String cycleName;
	private int pageCount;
	private List<String> fsds;
	private Map<String, Map<String, TestStatus>> fsdFunctionality;
	
	/*
	 * Contains a map of fsds.
	 * The corresponding values contain map.
	 * 	The Map contains name as functionality and values as testsStatus
	 * Eg:
	 * (
	 * 		"fsd1" = [
	 * 						"FSD1_functionality1"={5,3,2},
	 * 						"FSD1_functionality2"={5,3,2}, 
	 * 						"FSD1_functionality3"={5,3,2}
	 * 				 ]
	 * 		"fsd2" = [
	 * 						"FSD2_functionality1"={10,6,4},
	 * 						"FSD2_functionality2"={100,56,44},
	 * 						"FSD2_functionality3"={5,3,2}
	 * 				 ]
	 * ) 
	 */
	
	/**
	 * 
	 */
	public Report() {
		fsds = new ArrayList();
		fsdFunctionality = new HashMap();
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
	 * @return the pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * @param pageCount the pageCount to set
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * @return the fsds
	 */
	public List<String> getFsds() {
		return fsds;
	}

	/**
	 * @param fsds the fsds to set
	 */
	public void setFsds(List<String> fsds) {
		this.fsds = fsds;
	}


	/**
	 * @return the fsdFunctionality
	 */
	public Map<String, Map<String, TestStatus>> getFsdFunctionality() {
		return fsdFunctionality;
	}

	/**
	 * @param fsdFunctionality the fsdFunctionality to set
	 */
	public void setFsdFunctionality(Map<String, Map<String, TestStatus>> fsdFunctionality) {
		this.fsdFunctionality = fsdFunctionality;
	}
}
