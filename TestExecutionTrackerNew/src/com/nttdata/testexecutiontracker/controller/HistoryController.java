package com.nttdata.testexecutiontracker.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.testexecutiontracker.dao.HistoryDao;
import com.nttdata.testexecutiontracker.entity.History;
import com.nttdata.testexecutiontracker.form.LoginForm;
import com.nttdata.testexecutiontracker.util.Util;

@Controller
public class HistoryController {
	HistoryDao historyDao = new HistoryDao();
	
	@RequestMapping(value="history/{excelSheetId}", method = RequestMethod.GET)
	public String index(@PathVariable int excelSheetId, ModelMap model, HttpServletRequest request) {
		
		model.addAttribute("userName", request.getSession().getValue(Util.USER_NAME));
		List<History> list = historyDao.getRowsWithId(excelSheetId);
				
		model.addAttribute("historyList", list);
		return "history";
    }
	
}
