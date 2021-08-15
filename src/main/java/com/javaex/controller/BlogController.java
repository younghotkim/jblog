package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	//////////////////////// BLOG////////////////////////
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blog(@PathVariable("id") String id, Model model) {

		System.out.println("[BlogController.blog()]");

		BlogVo blogVo = blogService.getBlog(id);

		System.out.println(blogVo);

		model.addAttribute("blogVo", blogVo);

		return "blog/blog-main";

	}

	//////////////////////// ADMIN////////////////////////
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogAdmin(@PathVariable("id") String id, Model model) {

		System.out.println("[BlogController.blogAdmin()]");

		BlogVo blogVo = blogService.getBlog(id);

		System.out.println(blogVo);

		model.addAttribute("blogVo", blogVo);

		return "blog/admin/blog-admin-basic";

	}

	//////////////////////// MODIFY////////////////////////
	@RequestMapping(value = "/{id}/admin/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogModify(@PathVariable("id") String id, @RequestParam("blogTitle") String blogTitle,
			@RequestParam("logoFile") MultipartFile logoFile) {

		System.out.println("[BlogController.blogModify()]");

		int count = blogService.modifyBlog(id, blogTitle, logoFile);

		System.out.println(count + "건 수정완료");

		return "redirect:/{id}/admin/basic";

	}

	//////////////////////// WRITE////////////////////////
	@RequestMapping(value = "/{id}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogWrite(@PathVariable("id") String id, Model model) {

		System.out.println("[BlogController.blogWrite()]");

		BlogVo blogVo = blogService.getBlog(id);

		List<CategoryVo> categoryList = categoryService.getList(id);

		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryList", categoryList);

		return "blog/admin/blog-admin-write";

	}
	
	//////////////////////// CATEGORY////////////////////////
	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public String blogCategory(@PathVariable("id") String id, Model model) {

		System.out.println("[BlogController.blogCategory()]");

		BlogVo blogVo = blogService.getBlog(id);
	
		System.out.println(blogVo);

		model.addAttribute("blogVo", blogVo);

		return "blog/admin/blog-admin-cate";

	}
	
}