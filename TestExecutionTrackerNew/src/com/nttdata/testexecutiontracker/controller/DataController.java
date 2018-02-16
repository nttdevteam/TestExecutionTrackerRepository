package com.nttdata.testexecutiontracker.controller;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.google.gson.Gson;
import com.nttdata.testexecutiontracker.dao.ExcelSheetDao;
import com.nttdata.testexecutiontracker.dao.HistoryDao;
import com.nttdata.testexecutiontracker.dao.ProjectDao;
import com.nttdata.testexecutiontracker.entity.ExcelSheet;
import com.nttdata.testexecutiontracker.entity.ExcelSheetData;
import com.nttdata.testexecutiontracker.entity.User;
import com.nttdata.testexecutiontracker.exception.DataException;
import com.nttdata.testexecutiontracker.form.LoginForm;
import com.nttdata.testexecutiontracker.form.UpdateForm;
import com.nttdata.testexecutiontracker.util.CallType;
import com.nttdata.testexecutiontracker.util.Permission;
import com.nttdata.testexecutiontracker.util.Util;

@Controller
public class DataController {
	ExcelSheetDao excelSheetDao = new ExcelSheetDao();
	HistoryDao historyDao = new HistoryDao();
	ProjectDao projectDao = new ProjectDao();

	private String getFilter(HttpServletRequest request){
		String filter = null;
		String filterName = (String)request.getSession().getAttribute(Util.FILTER_NAME);
		String filterValue = (String)request.getSession().getAttribute(Util.FILTER_VALUE); 
		if(filterName !=null && filterValue !=null)
		{
			if(Util.TEST_CASE_FILTER_NAME.compareTo(filterName) == 0){
				filter = filterName+" like '%"+filterValue+"%'";
			}
			else{
				filter = filterName+"='"+filterValue+"'";
			}
		}
		else
		{
			return "";
		}
		return filter;
	}
	
	@RequestMapping(value="data", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String data(HttpServletRequest request)throws Exception
	{
		Gson gson=new Gson();
		
		String callType = request.getParameter("callType");
		List<ExcelSheetData> list = new ArrayList();
		int currentLast = 0;
		if(request.getSession().getAttribute(Util.CURRENT_LAST) != null)
			currentLast = (int) request.getSession().getAttribute(Util.CURRENT_LAST);

		String projectSelected= (String) request.getSession().getAttribute(Util.PROJECT_SELECTED);
		String cycleSelected = (String) request.getSession().getAttribute(Util.CYCLE_SELECTED);
		
		//if(currentLast%Util.PAGE_SIZE != 0 )throw new Exception();
		if(currentLast == Util.PAGE_SIZE && callType.compareTo(CallType.PREVIOUS.name()) ==0 ) return "{\"firstPage\":true}";

		String filter = getFilter(request);
		
		if(callType.compareTo(CallType.HOME.name()) ==0)
			list = excelSheetDao.getAllRows(projectSelected, cycleSelected, filter);
		else if(callType.compareTo(CallType.NEXT.name()) ==0)
			list = excelSheetDao.getNext(projectSelected, cycleSelected, currentLast, filter);
		else if(callType.compareTo(CallType.PREVIOUS.name()) ==0)
			list = excelSheetDao.getPrevious(projectSelected, cycleSelected, currentLast, filter);
		else if(callType.compareTo(CallType.PAGE.name()) ==0)
		{
			if(request.getParameter("pageNumber") != null)
			list = excelSheetDao.getPage(projectSelected, cycleSelected, Integer.parseInt(request.getParameter("pageNumber")), filter);
		}
			
		
		int lastIndex = 49; 
		if(list.size()!=50)
			lastIndex = list.size() - 1;
		
		if(list.size()==0 && currentLast%Util.PAGE_SIZE != 0 && callType.compareTo(CallType.NEXT.name()) ==0 ) return "{\"noDataFound\":true}";
		
		if(list.size() == 0)return "{\"error\":true}";
		
		ExcelSheetData excelSheetData = list.get(lastIndex);
		request.getSession().setAttribute(Util.CURRENT_LAST_ROW, excelSheetData);
		request.getSession().setAttribute(Util.CURRENT_LAST, excelSheetData.getId());
		ExcelSheet excelSheet = new ExcelSheet(list.get(0).getProjectName(), list.get(0).getCycleName(), list, getPageCount(projectSelected, cycleSelected, filter));
		
		String jsonData=gson.toJson(excelSheet);
		return jsonData;
	}


	@RequestMapping(value="updateExcelSheet/{rowId}/{columnName}/{columnValue}", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void updateExcelSheet(@PathVariable int rowId, @PathVariable String columnName, @PathVariable String columnValue, HttpServletRequest request)
	{
		List<ExcelSheetData> list = new ArrayList();
		try{
			String userNameColumn ="";
			String dateNameColumn ="";
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date();
			
			excelSheetDao.updateRow(rowId, columnName, columnValue, userNameColumn , dateNameColumn,(String)request.getSession().getAttribute(Util.USER_NAME), dateFormat.format(date));	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value="updateExcelSheetPost", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String updateExcelSheet(@ModelAttribute UpdateForm updateForm, HttpServletRequest request, HttpServletResponse response)
	{
		int rowId = updateForm.getRowId(); 
		String columnName = updateForm.getColumnName();
		String columnValue = updateForm.getColumnValue();
		
		String userNameColumn ="level1User";
		String dateNameColumn ="level1Date";
		User user = (User) request.getSession().getAttribute(Util.USER);
		if(user.getPermissionName().compareTo(Permission.L2USER.name()) == 0)
		{
			userNameColumn ="level2User";
			dateNameColumn ="level2Date";
		}

		List<ExcelSheetData> list = new ArrayList();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			String currentUser = (String)request.getSession().getAttribute(Util.USER_NAME);
			String historyData = "Column Changed: "+columnName +" TO: "+columnValue;
			
			if(currentUser == null){
				response.sendRedirect("index");
			}
			excelSheetDao.updateRow(rowId, columnName, columnValue, userNameColumn , dateNameColumn, currentUser, currentDate);
			historyDao.insertRow(rowId, currentDate,currentUser, historyData);			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "{success=true}";
	}
	
	@RequestMapping(value="getExcelSheetRow", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getExcelSheetRow(@ModelAttribute UpdateForm updateForm, HttpServletRequest request, HttpServletResponse response)
	{
		Gson gson=new Gson();
		ExcelSheet excelSheet = null;
		int rowId = updateForm.getRowId(); 


		String projectSelected= (String) request.getSession().getAttribute(Util.PROJECT_SELECTED);
		String cycleSelected = (String) request.getSession().getAttribute(Util.CYCLE_SELECTED);
		
		
		List<ExcelSheetData> list = new ArrayList();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date();
			String currentDate = dateFormat.format(date);
			String currentUser = (String)request.getSession().getAttribute(Util.USER_NAME);
			
			if(currentUser == null){
				response.sendRedirect("index");
			}
			list = excelSheetDao.getRow(projectSelected, cycleSelected, rowId);
			excelSheet = new ExcelSheet(list.get(0).getProjectName(), list.get(0).getCycleName(), list, 0);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String jsonData=gson.toJson(excelSheet);
		return jsonData;
	}
	
	
	@RequestMapping(value="uploadFile/{projectId}/{cycleId}", method=RequestMethod.POST, produces = "application/txt")
	@ResponseBody
	public String uploadFile(@PathVariable int projectId, @PathVariable int cycleId, @ModelAttribute UpdateForm updateForm, HttpServletRequest request, HttpServletResponse response)
	{
		String stringFileData = null;
        DefaultMultipartHttpServletRequest dmhsRequest = (DefaultMultipartHttpServletRequest) request;
        MultipartFile multipartFile = (MultipartFile) dmhsRequest.getFile("fileupload");
        try{
        	ByteArrayInputStream stream = new ByteArrayInputStream(multipartFile.getBytes());
            stringFileData = IOUtils.toString(stream, "UTF-8");
            if(canUpdateFile(projectId, cycleId))
            {
            	insertTestsToDB(projectId, cycleId, stringFileData);
            }
            else
            {
            	return "{success=false}";
            }
        }catch(Exception e)
        {
        	e.printStackTrace();//TODO: Log correct message. 
        }
        
		return "{success=true}";
	}
	
	
	private int getPageCount(String projectSelected, String cycleSelected, String filter)
	{
		int rowsCount = excelSheetDao.getAllRowsCount(projectSelected, cycleSelected, filter);
		
		if(rowsCount/Util.PAGE_SIZE == 0) 
			return rowsCount/Util.PAGE_SIZE;
		else 
			return (rowsCount/Util.PAGE_SIZE) + 1;
	}

	private void insertTestsToDB(int projectId, int cycleId, String stringFileData) throws DataException
	{
        String lines[] = stringFileData.split(Util.NEW_LINE);
        
        for(int i = 0; i< lines.length; i++)
        {
        	String line = lines[i];
        	String lineData[] = line.split(Util.COMMA);
        	if(lineData.length != 3)
        	{
        		throw new DataException();
        	}
        	else
        	{
        		excelSheetDao.insertRow(projectId, cycleId, lineData[0].trim(), lineData[1].trim(), lineData[2].trim());
        	}
        }
	}
	private boolean canUpdateFile(int projectId, int cycleId)
	{
		String projectName = projectDao.getProjectName(projectId);
		String cycleName = projectDao.getCycleName(projectId, cycleId);

		List<ExcelSheetData> list = excelSheetDao.getAllRows(projectName, cycleName, null);
		for(ExcelSheetData row: list)
			if((row.getLevel1User() !=null && row.getLevel1User().compareTo("") != 0 )|| (row.getLevel2User() != null && row.getLevel2User().compareTo("") != 0) )
				return false;
		return true;
	}
}
