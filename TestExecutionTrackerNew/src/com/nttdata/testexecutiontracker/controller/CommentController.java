package com.nttdata.testexecutiontracker.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nttdata.testexecutiontracker.dao.CommentDao;
import com.nttdata.testexecutiontracker.dao.HistoryDao;
import com.nttdata.testexecutiontracker.entity.Comment;
import com.nttdata.testexecutiontracker.entity.History;
import com.nttdata.testexecutiontracker.form.LoginForm;
import com.nttdata.testexecutiontracker.util.Util;

@Controller
public class CommentController {
	CommentDao commentDao = new CommentDao();
	
	@RequestMapping(value="comment/{excelSheetId}", method = RequestMethod.GET)
	public String showCommentsPage(@PathVariable int excelSheetId, ModelMap model, HttpServletRequest request) {
		
		List<Comment> list = commentDao.getRowsWithId(excelSheetId);
		model.addAttribute("commentList", list);
		return "comment";
    }

	@RequestMapping(value="comment/{excelSheetId}", method = RequestMethod.POST)
	public String enterComment(@PathVariable int excelSheetId, ModelMap model, HttpServletRequest request) {
		
		String newComment = request.getParameter("newComment");
		
		commentDao.insertRow(excelSheetId, Util.getDateString(), (String)request.getSession().getAttribute(Util.USER_NAME), newComment);
		List<Comment> list = commentDao.getRowsWithId(excelSheetId);
		model.addAttribute("commentList", list);
		return "comment";
    }

}
