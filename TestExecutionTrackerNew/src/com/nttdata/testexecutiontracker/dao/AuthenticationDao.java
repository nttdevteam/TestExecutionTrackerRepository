package com.nttdata.testexecutiontracker.dao;

import java.sql.ResultSet;
import java.sql.Statement;


public class AuthenticationDao extends BaseDao{
	public boolean ifExists(String userName, String password)
	{
		int count = 0;
	    Statement stmt = null;
	    try {
			Class.forName("org.sqlite.JDBC");
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
	public static void main(String args[])
	{
		AuthenticationDao authentication = new AuthenticationDao();
		
		System.out.println(authentication.ifExists("Sheel", "123"));
		System.out.println(authentication.ifExists("Sheel", null));
		System.out.println(authentication.ifExists("Sheel", ""));
		System.out.println(authentication.ifExists("SHEEL", "AAA"));
		System.out.println(authentication.ifExists("Sheel1", "0"));
		System.out.println(authentication.ifExists("Sheel1", "123"));
		
	}
}
