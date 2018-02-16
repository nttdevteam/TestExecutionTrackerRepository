package com.nttdata.testexecutiontracker.entity;

public class User {
	private int userId;
	private int permissionId;
	private String userName;
	private String permissionName;
	
	
	/**
	 * @param userId
	 * @param permissionId
	 * @param userName
	 * @param permissionName
	 */
	public User(int userId, int permissionId, String userName, String permissionName) {
		super();
		this.userId = userId;
		this.permissionId = permissionId;
		this.userName = userName;
		this.permissionName = permissionName;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the permissionId
	 */
	public int getPermissionId() {
		return permissionId;
	}
	/**
	 * @param permissionId the permissionId to set
	 */
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the permissionName
	 */
	public String getPermissionName() {
		return permissionName;
	}
	/**
	 * @param permissionName the permissionName to set
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	
}
