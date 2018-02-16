package com.nttdata.testexecutiontracker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.nttdata.testexecutiontracker.util.Util;

public class BaseDao {
	Connection cRead ;
	Connection cWrite ;
    Statement stmt;

	BaseDao() 
	{
		try{
			Class.forName("org.sqlite.JDBC");
			cRead = DriverManager.getConnection(Util.CONNECTION_STRING);
			cWrite = DriverManager.getConnection(Util.CONNECTION_STRING);
			cWrite.setAutoCommit(true);
		}catch(Exception e)
		{
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
		}
	}
	
	protected void closeConnection()
	{
		try{
			stmt.close();
			cRead.close();
			cWrite.close();
			
		}catch(Exception e)
		{
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
		}
	}
}
