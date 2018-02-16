package com.nttdata.testexecutiontracker.entity;

import java.util.List;

public class ExcelSheet {

	String projectName;
	String cycleName;
	List<ExcelSheetData> list;
	int pageCount;
	
	
	/**
	 * @param projectName
	 * @param cycleName
	 * @param list
	 * @param pageCount
	 */
	public ExcelSheet(String projectName, String cycleName, List<ExcelSheetData> list, int pageCount) {
		super();
		this.projectName = projectName;
		this.cycleName = cycleName;
		this.list = list;
		this.pageCount = pageCount;
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
	 * @return the list
	 */
	public List<ExcelSheetData> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<ExcelSheetData> list) {
		this.list = list;
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
	
}
