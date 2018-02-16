package com.nttdata.testexecutiontracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nttdata.testexecutiontracker.dao.ExcelSheetDao;
import com.nttdata.testexecutiontracker.util.Util;

@Controller
public class FilterController {
	ExcelSheetDao excelSheetDao = new ExcelSheetDao();

	@RequestMapping(value="getUnique", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String getUnique(HttpServletRequest request)
	{
		Gson gson=new Gson();

		if(request.getParameter("rowName")==null) return null;
		
		List list = excelSheetDao.getUnique((String)request.getParameter("rowName"));
		String jsonData=gson.toJson(list);
		return jsonData;
	}
	
	@RequestMapping(value="setFilter", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String setFilter(HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		String filterName = (String) request.getParameter("filterName");
		String filterValue = (String) request.getParameter("filterValue");
		
		if(session == null || filterName == null || filterValue == null) 
			return null;
		
		session.setAttribute(Util.FILTER_NAME, filterName);
		session.setAttribute(Util.FILTER_VALUE, filterValue);
		
		return Util.SUCCESS_JSON;
	}

	@RequestMapping(value="clearFilter", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String clearFilter(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		
		if(session == null ) 
			return null;

		session.removeAttribute(Util.FILTER_NAME);
		session.removeAttribute(Util.FILTER_VALUE);
		
		return Util.SUCCESS_JSON;
	}
}
