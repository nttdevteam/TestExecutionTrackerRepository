package com.nttdata.testexecutiontracker.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nttdata.testexecutiontracker.entity.ExcelSheetData;

public class SQLiteJDBC {
	public void connect(){
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:D:\\TestExecutionTracker\\TestExecutionTrackerNew\\test.db");
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
	
	public void createTables()
	{
	    Connection c = null;
	    Statement stmt = null;
	    
		try {
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:D:\\TestExecutionTracker\\TestExecutionTrackerNew\\test.db");
		    System.out.println("Opened database successfully");
		    stmt = c.createStatement();
		    
		    
		    //1. Create AUTHENTICATION Table
		    String sql = "CREATE TABLE AUTHENTICATION " +
		                   "(ID INT PRIMARY KEY     NOT NULL," +
		                   " NAME           TEXT    NOT NULL, " + 
		                   " PASSWORD            TEXT     NOT NULL)"; 
		    stmt.executeUpdate(sql);


		    //2. Create PERMISSION Table
		    sql = "CREATE TABLE PERMISSION " +
	                   "(PERMISSION_ID INT NOT NULL," +
	                   " PERMISSION_NAME TEXT)"; 
		    stmt.executeUpdate(sql);

		    //3. Create AUTHORISATION Table
		    sql = "CREATE TABLE AUTHORISATION " +
	                   "(USER_ID INT NOT NULL," +
	                   " PERMISSION_ID INT)"; 
		    stmt.executeUpdate(sql);

		    
		    //4. Create EXCELSHEET Table
		    sql = "CREATE TABLE EXCELSHEET "
		    		+ "(id INT NOT NULL,"
		    		+ "projectName TEXT NOT NULL,"
		    		+ "cycleName TEXT NOT NULL,"
		    		+ "fsdName TEXT NOT NULL,"
		    		+ "functionality TEXT NOT NULL,"
		    		+ "testCaseNumber	TEXT,"
		    		+ "browser	TEXT,"
		    		+ "level1TestStatus	TEXT,"
		    		+ "level1User	TEXT,"
		    		+ "level1Date	TEXT,"
		    		+ "almDefect	TEXT,"
		    		+ "testEvidenceUploaded	TEXT,"
		    		+ "level2BVStatus	TEXT,"
		    		+ "level2User	TEXT,"
		    		+ "level2Date	TEXT,"
		    		+ "remarksId	INT);"; 
		    stmt.executeUpdate(sql);

		    //5. Create COMMENTS Table
		    sql = "CREATE TABLE COMMENT"
		    		+ "(comment_Id INT,"
		    		+ "excelsheet_Id INT,"
		    		+ "comment_User TEXT,"
		    		+ "comment_Date TEXT,"
		    		+ "comment TEXT);"; 
		    stmt.executeUpdate(sql);
		    
		    //6. Create HISTORY Table
		    sql = "CREATE TABLE HISTORY"
		    		+ "(HISTORY_Id INT,"
		    		+ "EXCELSHEET_ID TEXT,"
		    		+ "UPDATE_DATE TEXT,"
		    		+ "UPDATE_USER TEXT,"
		    		+ "HISTORY_DATA TEXT);"; 
		    stmt.executeUpdate(sql);
		    
		    sql = "CREATE TABLE PROJECT"
		    		+ "(ID INT NOT NULL,"
		    		+ "PROJECT_NAME TEXT,"
		    		+ "CYCLE_NAME_LIST TEXT)";
		    stmt.executeUpdate(sql);

		    
		    stmt.close();
		    c.close();
		} catch ( Exception e ) {
			e.printStackTrace();
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}
	
	public void insertData()
	{
		Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c=DriverManager.getConnection("jdbc:sqlite:test.db");
	    	c.setAutoCommit(false);
	    	System.out.println("Open database successfully");
	    	stmt =c.createStatement();
	    	
	    	//1. Insert into Authentication Table
	    	
	    	String sql ="INSERT INTO AUTHENTICATION (ID,NAME,PASSWORD) VALUES (1, 'SHEEL', 'AAA');";
	    	stmt.executeQuery(sql);
	    	
	    	//2. Insert into Permission Table
	    	
	    	sql ="INSERT INTO PERMISSION (PERMISSION_ID,PERMISSION_NAME) VALUES (1, 'FULL_ACCESS');";
	    	stmt.executeQuery(sql);
	    	
	    	//1. Insert into Authorisation Table
	    	
	    	sql ="INSERT INTO AUTHORISATION (USER_ID,PERMISSION_ID) VALUES (1,1);";
	    	stmt.executeQuery(sql);
	    	
	    	sql="INSERT INTO PROJECT (ID, PROJECT_NAME, CYCLE_NAME_LIST) VALUES (1,'Project1' , 'Cycle1,Cycle2,Cycle3');";
	    	stmt.executeUpdate(sql);
	    	
	    	String projectName = "'project1'";
	    	String cycleName = "'cycle1'";
	    	String fsdName = "'FSD1'";
	    	
	    	sql="INSERT INTO EXCELSHEET (ID, PROJECTNAME, CYCLENAME, FSDNAME,FUNCTIONALITY, TESTCASENUMBER, BROWSER) VALUES (1,"+projectName+" ,"+cycleName+","+fsdName+",'5.2','5.2','Win 7 / Chrome');";
	    	stmt.executeUpdate(sql);
	    	
	    	stmt.close();
	    	c.commit();
	    	c.close();
	    	
	    } catch ( Exception e ) {
	    	e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	}
	
	public static void main(String args[])
	{
		SQLiteJDBC sQLiteJDBC = new SQLiteJDBC();
		sQLiteJDBC.createTables();
		sQLiteJDBC.insertData();
	}
	
	
}