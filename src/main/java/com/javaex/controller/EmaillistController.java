package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.EmaillistDao;
import com.javaex.vo.EmaillistVo;

@Controller
public class EmaillistController {
	
	@Autowired//자동으로 연결해달라 
	private EmaillistDao emaillistDao;
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form() {
		System.out.println("form 진입");
		return "form";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute EmaillistVo emaillistVo) {
		System.out.println(emaillistVo.toString());
		
		emaillistDao.insert(emaillistVo);
		
		return "redirect:/list";//list라고 하면 포워드하는거 
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println("list 진입");
		
		List<EmaillistVo> eList = emaillistDao.getList();
		model.addAttribute("eList", eList);//데이터랑 뷰를 dispatcherservlet에 보낸거 
		
		return "list";
	}
	
}
