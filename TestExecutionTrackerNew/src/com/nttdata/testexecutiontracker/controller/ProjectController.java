package com.nttdata.testexecutiontracker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nttdata.testexecutiontracker.dao.ProjectDao;
import com.nttdata.testexecutiontracker.form.UpdateForm;

@Controller
public class ProjectController {
	
	ProjectDao projectDao = new ProjectDao();
	
	@RequestMapping(value="addProject", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String addProject(HttpServletRequest request, HttpServletResponse response)
	{
		String projectName = request.getParameter("projectName");
		String projectId = request.getParameter("projectId");
		
		projectDao.addProject(Integer.parseInt(projectId), projectName);
		return "{success:true}";
	}
	
	
	@RequestMapping(value="addCycle", method=RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String addCycle(HttpServletRequest request, HttpServletResponse response)
	{
		String projectId = request.getParameter("projectId");
		String cycleName = request.getParameter("cycleName");
		
		projectDao.addCycle(Integer.parseInt(projectId), cycleName);
		return "{success:true}";
	}

	@RequestMapping(value="deleteCycle", method=RequestMethod.POST, produces = "application/text")
	@ResponseBody
	public String deleteCycle(HttpServletRequest request, HttpServletResponse response)
	{
		String projectId = request.getParameter("projectId");
		String cycleId = request.getParameter("cycleId");
		
		boolean deleteCycleResult = projectDao.deleteCycle(Integer.parseInt(projectId), Integer.parseInt(cycleId));
		
		if(deleteCycleResult)
			return "{\"success\":true}";
		else
			return "{\"success\":false}";
	}
}
