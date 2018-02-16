package com.nttdata.testexecutiontracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.nttdata.testexecutiontracker.dao.AuthenticationDao;
import com.nttdata.testexecutiontracker.dao.ExcelSheetDao;
import com.nttdata.testexecutiontracker.dao.ProjectDao;
import com.nttdata.testexecutiontracker.dao.UserDao;
import com.nttdata.testexecutiontracker.entity.ExcelSheetData;
import com.nttdata.testexecutiontracker.entity.JGrid;
import com.nttdata.testexecutiontracker.entity.User;
import com.nttdata.testexecutiontracker.entity.JGrid.Data;
import com.nttdata.testexecutiontracker.entity.JGrid.UserData;
import com.nttdata.testexecutiontracker.form.LoginForm;
import com.nttdata.testexecutiontracker.util.Permission;
import com.nttdata.testexecutiontracker.util.Util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class LoginController {
	private LoginForm loginForm;
	
	AuthenticationDao authenticationDao = new AuthenticationDao();
	UserDao userDao = new UserDao();
	ProjectDao projectDao = new ProjectDao();
	
	//@RequestBody LoginForm loginForm
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String authenticate(@ModelAttribute LoginForm loginForm, ModelMap model, HttpServletRequest request) {
		
		if(!(authenticationDao.ifExists(loginForm.getUserName(), loginForm.getPassword()) ))
		{
			model.addAttribute("errorMessage", "Invalid Password");
			return "index";
		}
		
		User user = userDao.getUser(loginForm.getUserName());
		request.getSession().putValue(Util.USER_NAME, loginForm.getUserName());
		request.getSession().putValue(Util.USER, user);
		model.addAttribute(Util.USER, user);
		model.addAttribute(Util.USER_NAME, user.getUserName());

		if(user.getPermissionName().compareTo(Permission.ADMIN.name()) == 0)
		{
			List list = projectDao.getAllProjects();
			model.addAttribute(Util.PROJECT_LIST, list);
			return "admin";
		}
		else
		{
			model.addAttribute(Util.USER, loginForm.getUserName());
			System.out.println("Logged in User: " + loginForm.getUserName());
			
			if(request.getSession().getAttribute(Util.PROJECT_SELECTED) == null)
			{
				List list = projectDao.getAllProjects();
				model.addAttribute(Util.PROJECT_LIST, list);
				return "projectCycleSelection";	
			}
			else{
				return "home";
			}
		}
    }
	
	
	@RequestMapping(value="login", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		
		if(request.getSession().isNew() == false && request.getSession().getValue(Util.USER_NAME) !=null)
		{
			User user = (User)request.getSession().getValue(Util.USER);
			if(user==null) return "index";
			
			request.getSession().putValue(Util.USER_NAME, user.getUserName());
			model.addAttribute(Util.USER, user);
			model.addAttribute(Util.USER_NAME, user.getUserName());

			if(user==null)
			{
				return "index";
			}
			if(user.getPermissionName().compareTo(Permission.ADMIN.name()) == 0)
			{
				List list = projectDao.getAllProjects();
				model.addAttribute(Util.PROJECT_LIST, list);
				return "admin";
			}
			else
			{
				if(request.getSession().getAttribute(Util.PROJECT_SELECTED) == null)
				{
					List list = projectDao.getAllProjects();
					model.addAttribute(Util.PROJECT_LIST, list);
					return "projectCycleSelection";	
				}
				else{
					System.out.println("Logged in User: " + user.getUserName());
					return "home";	
				}
			}
		}
		else
		{
			return "index";
		}
    }

	@RequestMapping(value="logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request, ModelMap model) {
		if(!request.getSession().isNew())
		{
			request.getSession().invalidate();
		}
		return "index";
    }

	
	@RequestMapping(value="projectCycleSelection", method = RequestMethod.POST)
	public String projectCycleSelection(HttpServletRequest request, ModelMap model) {
		if(!request.getSession().isNew())
		{
			String projectSelected = request.getParameter(Util.PROJECT_SELECTED);
			String cycleSelected = request.getParameter(Util.CYCLE_SELECTED);
			

			request.getSession().setAttribute(Util.PROJECT_SELECTED, projectSelected);
			request.getSession().setAttribute(Util.CYCLE_SELECTED, cycleSelected);
			
		}
		return "home";
    }
	@RequestMapping(value="projectCycleSelection", method = RequestMethod.GET)
	public String getProjectCycleSelection(HttpServletRequest request, ModelMap model) {
		request.getSession().removeAttribute(Util.PROJECT_SELECTED);
		request.getSession().removeAttribute(Util.CYCLE_SELECTED);
		List list = projectDao.getAllProjects();
		model.addAttribute(Util.PROJECT_LIST, list);

		return "projectCycleSelection";
    }
}
