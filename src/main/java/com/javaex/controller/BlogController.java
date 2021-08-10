package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	//////////////////////// BLOG////////////////////////
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blog(@PathVariable("id") String id, Model model) {
		
		System.out.println(id);
		
		BlogVo blogVo = blogService.getBlog(id);
		
		System.out.println(blogVo);

		model.addAttribute("blogVo", blogVo);

		return "blog/blog-main";

	}

}