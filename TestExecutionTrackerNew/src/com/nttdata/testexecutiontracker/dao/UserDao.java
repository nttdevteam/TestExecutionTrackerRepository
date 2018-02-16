package com.nttdata.testexecutiontracker.dao;

import java.sql.ResultSet;
import java.sql.Statement;

import com.nttdata.testexecutiontracker.entity.User;
import com.nttdata.testexecutiontracker.util.Util;

public class UserDao extends BaseDao{
	public boolean ifExists(String userName, String password)
	{
		int count = 0;
	    Statement stmt = null;
	    try {
			stmt = cRead.createStatement();
			
			stmt.execute("SELECT count(*) FROM AUTHENTICATION WHERE name='"+userName+"' and password='"+password+"';");    
			ResultSet rs = stmt.getResultSet(); // 
			if( rs.next() ){
				count = rs.getInt(1);
			}
			
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return count == 1 ? true:false;
	}
	public User getUser(String userName)
	{
		User user = null;
		int count = 0;
	    Statement stmt = null;
	    try {
			Class.forName("org.sqlite.JDBC");
			stmt = cRead.createStatement();
			
			stmt.execute("SELECT * from PERMISSION p, AUTHENTICATION A where A.NAME='"+userName+"' AND PERMISSION_ID=(SELECT PERMISSION_ID from AUTHORISATION where USER_ID=(SELECT ID from AUTHENTICATION WHERE NAME='"+userName+"'));");    
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				int userId = rs.getInt("ID");
				int permissionId = rs.getInt("PERMISSION_ID");
				String permissionName = rs.getString("PERMISSION_NAME");
				user = new User(userId, permissionId, userName, permissionName);
			}
			
			stmt.close();
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    return user;
	}
	public static void main(String args[])
	{
		UserDao userDao = new UserDao();
//		
//		System.out.println(authentication.ifExists("Sheel", "123"));
//		System.out.println(authentication.ifExists("Sheel", null));
//		System.out.println(authentication.ifExists("Sheel", ""));
//		System.out.println(authentication.ifExists("SHEEL", "AAA"));
//		System.out.println(authentication.ifExists("Sheel1", "0"));
//		System.out.println(authentication.ifExists("Sheel1", "123"));

		User user = userDao.getUser("READACCESS");
		System.out.println(user.getUserId());		
	}
}