package com.nttdata.testexecutiontracker.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import com.nttdata.testexecutiontracker.entity.Project;
import com.nttdata.testexecutiontracker.entity.User;
import com.nttdata.testexecutiontracker.util.Util;

public class ProjectDao extends BaseDao{

	private static String GET_ALL_PROJECT = "select * from project;";
	private static String GET_PROJECT_NAME = "select PROJECT_NAME from project where id=";
	private static String GET_CYCLE_NAME = "select CYCLE_NAME_LIST from project where id=";
	public ProjectDao() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<Project> getAllProjects() 
	{
		List<Project> list = new ArrayList();
		try{
			stmt = cRead.createStatement();
			stmt.execute(GET_ALL_PROJECT);
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				int id = rs.getInt("ID");
				String projectName = rs.getString("PROJECT_NAME");
				String cycleNameList = rs.getString("CYCLE_NAME_LIST");
				Project project = new Project(id, projectName, cycleNameList);
				list.add(project); 
			}
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
		}
		return list;
	}
	
	public boolean addProject(int projectId, String projectName)
	{
		boolean result = false;
		try{
			stmt = cWrite.createStatement();
			stmt.executeUpdate("INSERT INTO PROJECT (ID, PROJECT_NAME, CYCLE_NAME_LIST) VALUES("+projectId+",'"+projectName+"',''"
					+ ");");
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
		}
		return result;
	}
	public boolean addCycle(int projectId, String cycleName)
	{
		if(projectId==0)
			return false;
		if(cycleName==null)
			return false;
		
		boolean result = false;
		String cycleList = null;
		try{
			stmt = cRead.createStatement();
			stmt.execute("SELECT CYCLE_NAME_LIST from project where id="+projectId);    
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				cycleList = rs.getString("CYCLE_NAME_LIST");
			}
			cycleList = cycleList +","+cycleName;
			stmt.executeUpdate("UPDATE PROJECT SET CYCLE_NAME_LIST='"+cycleList+"' where id="+projectId);    
//			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
			return false;
		}
		return true;
	}

	public boolean deleteCycle(int projectId, int cycleId)
	{
		if(projectId==0)
			return false;
		if(cycleId==0)
			return false;
		
		boolean result = false;
		String cycles = null;
		Object cycleArray[] = null;
		List<String> cycleList = null;
		try{
			stmt = cRead.createStatement();
			stmt.execute("SELECT CYCLE_NAME_LIST from project where id="+projectId);    
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				cycles = rs.getString("CYCLE_NAME_LIST");
			}
			cycleArray = cycles.split(",");
			System.out.println("B: --- "+cycles+cycleArray.length +" > "+ cycleId);
			if(cycleArray.length >= cycleId)
			{
				cycleId = cycleId - 1;
	
				cycleList = new ArrayList (Arrays.asList(cycleArray));
				cycleList.remove(cycleId);
			}
			else
			{
				return false;
			}
			
			cycleArray = cycleList.toArray();
			cycles = StringUtils.arrayToCommaDelimitedString(cycleArray);
			System.out.println("A: --- "+cycles);

			stmt = cWrite.createStatement();
			stmt.executeUpdate("UPDATE PROJECT SET CYCLE_NAME_LIST='"+cycles+"' where id="+projectId);    
//			rs.close();
		}catch(Exception e){
			e.printStackTrace();			//TODO: remove printStackTrace and Handle Accordingly
			return false;
		}
		return true;
	}
	public static void main(String args[])
	{
		ProjectDao projectDao = new ProjectDao();
		
		//List list = projectDao.getAllProjects();
		//projectDao.addProject(5, "sheel");
		//projectDao.addCycle(1, "cycleName");
		
		System.out.println(projectDao.getProjectName(1));
		System.out.println(projectDao.getProjectName(2));
		System.out.println(projectDao.getProjectName(3));
		System.out.println(projectDao.getProjectName(4));

		System.out.println(projectDao.getCycleName(4, 1));
		System.out.println(projectDao.getCycleName(4, 2));
		System.out.println(projectDao.getCycleName(4, 3));
		
		int a = 10;
	}


	public String getProjectName(int id)
	{
		String projectName = "";
		List<Project> list = new ArrayList();
		try{
			stmt = cRead.createStatement();
			stmt.execute(GET_PROJECT_NAME+id);
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				projectName = rs.getString("PROJECT_NAME");
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
		}
		return projectName;
	}
	public String getCycleName(int projectId, int cycleId)
	{
		String cycleName = "";
		List<Project> list = new ArrayList();
		try{
			stmt = cRead.createStatement();
			stmt.execute(GET_CYCLE_NAME+projectId);
			ResultSet rs = stmt.getResultSet(); // 
			while ( rs.next() ) {
				cycleName = rs.getString("CYCLE_NAME_LIST");
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
			//TODO: remove printStackTrace and Handle Accordingly
		}
		return cycleName.split(Util.COMMA)[cycleId];
	}
}
