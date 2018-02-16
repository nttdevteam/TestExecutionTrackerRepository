package com.nttdata.testexecutiontracker.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Project {

	private int id;
	private String projectName;
	private String cycleNameList;
	private List<String> cycleNames;
	
	
	
	/**
	 * @param id
	 * @param projectName
	 * @param cycleNameList
	 * @param cycleNames
	 */
	public Project(int id, String projectName, String cycleNameList) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.cycleNameList = cycleNameList;
		cycleNames = new ArrayList();
		cycleNames.addAll(Arrays.asList(cycleNameList.split(",")));
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the cycleNameList
	 */
	public String getCycleNameList() {
		return cycleNameList;
	}
	/**
	 * @param cycleNameList the cycleNameList to set
	 */
	public void setCycleNameList(String cycleNameList) {
		this.cycleNameList = cycleNameList;
	}
	/**
	 * @return the cycleNames
	 */
	public List<String> getCycleNames() {
		return cycleNames;
	}
	/**
	 * @param cycleNames the cycleNames to set
	 */
	public void setCycleNames(List<String> cycleNames) {
		this.cycleNames = cycleNames;
	}
	
	
}
