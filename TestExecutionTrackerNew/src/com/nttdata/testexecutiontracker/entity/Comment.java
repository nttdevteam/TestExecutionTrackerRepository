package com.nttdata.testexecutiontracker.entity;

public class Comment {
	private int commentId;
	private int excelSheetId;
	private String commentDate;
	private String commentUser;
	private String comment;
	
	
	/**
	 * @param historyId
	 * @param excelSheetId
	 * @param updateDate
	 * @param updateUser
	 * @param historyData
	 */
	public Comment(int commentId, int excelSheetId, String commentDate, String commentUser, String comment) {
		super();
		this.commentId = commentId;
		this.excelSheetId = excelSheetId;
		this.commentDate = commentDate;
		this.commentUser = commentUser;
		this.comment = comment;
	}


	/**
	 * @return the commentId
	 */
	public int getCommentId() {
		return commentId;
	}


	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	 * @return the commentDate
	 */
	public String getCommentDate() {
		return commentDate;
	}


	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}


	/**
	 * @return the commentUser
	 */
	public String getCommentUser() {
		return commentUser;
	}


	/**
	 * @param commentUser the commentUser to set
	 */
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}


	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}

