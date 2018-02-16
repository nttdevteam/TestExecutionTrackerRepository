package com.nttdata.testexecutiontracker.entity;

public class History {
	private int historyId;
	private int excelSheetId;
	private String updateDate;
	private String updateUser;
	private String historyData;
	
	
	/**
	 * @param historyId
	 * @param excelSheetId
	 * @param updateDate
	 * @param updateUser
	 * @param historyData
	 */
	public History(int historyId, int excelSheetId, String updateDate, String updateUser, String historyData) {
		super();
		this.historyId = historyId;
		this.excelSheetId = excelSheetId;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.historyData = historyData;
	}


	/**
	 * @return the historyId
	 */
	public int getHistoryId() {
		return historyId;
	}


	/**
	 * @param historyId the historyId to set
	 */
	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}


	/**
	 * @return the excelSheetId
	 */
	public int getExcelSheetId() {
		return excelSheetId;
	}


	/**
	 * @param excelSheetId the excelSheetId to set
	 */
	public void setExcelSheetId(int excelSheetId) {
		this.excelSheetId = excelSheetId;
	}


	/**
	 * @return the updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}


	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}


	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}


	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	/**
	 * @return the historyData
	 */
	public String getHistoryData() {
		return historyData;
	}


	/**
	 * @param historyData the historyData to set
	 */
	public void setHistoryData(String historyData) {
		this.historyData = historyData;
	}
	

}

