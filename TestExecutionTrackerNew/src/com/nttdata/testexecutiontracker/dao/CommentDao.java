package com.nttdata.testexecutiontracker.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nttdata.testexecutiontracker.entity.Comment;
import com.nttdata.testexecutiontracker.util.Util;

public class CommentDao extends BaseDao{
	public void insertRow(int excelSheetId, String commentDate, String commentUser, String comment)
	{
		int commentId = selectMaxCommentId() + 1;
	    Statement stmt = null;
	    try {
			stmt = cWrite.createStatement();
			String sql = "INSERT INTO comment (comment_id, excelsheet_id, comment_user, comment_date, comment) VALUES "
					+ "("+commentId+","+excelSheetId+",'"+commentDate+"','"+commentUser+"','"+comment+"');";
			stmt.executeUpdate(sql);
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	public int selectMaxCommentId()
	{
		int commentId = 1;
	    Statement stmt = null;
	    try {
			stmt = cRead.createStatement();
			
			stmt.execute("SELECT MAX(comment_id) FROM comment");    
			ResultSet rs = stmt.getResultSet(); // 
			if( rs.next() ){
				commentId = rs.getInt(1);
			}
			
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return commentId;
	}
	
	public List<Comment> getRowsWithId(int excelSheetId)
	{
		List<Comment> list = new ArrayList();
		
	    Statement stmt = null;
	    try {
			stmt = cRead.createStatement();
			
			stmt.execute("SELECT * FROM COMMENT WHERE EXCELSHEET_ID="+excelSheetId);    
			ResultSet rs = stmt.getResultSet(); // 
			while( rs.next() ){
				int commentId = rs.getInt("comment_id");
				String updateDate = rs.getString("comment_date");
				String updateUser = rs.getString("comment_user");
				String commentData = rs.getString("comment");
				Comment comment = new Comment(commentId, excelSheetId, updateDate, updateUser, commentData);
				list.add(comment);
			}
			
//			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return list;
	}
	
	
	public static void main(String args[])
	{
		CommentDao commentDao = new CommentDao();
		commentDao.insertRow(1, "commentDate", "commmentUser", "commentData");
		
		
		List list = commentDao.getRowsWithId(1);
		for(Object comment: list)
		{
			Comment commentObject = ((Comment)comment);
		}
	}
}
