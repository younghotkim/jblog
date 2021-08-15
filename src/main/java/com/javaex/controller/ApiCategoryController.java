package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class ApiCategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
////////////////////////AJAXCATEGORYLIST////////////////////////
	
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/list", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo> getCategoryList(@RequestBody String id) {
		
		System.out.println("[ApiCategoryController.getCategorylist()]");
		
		List<CategoryVo> categoryList = categoryService.getList(id);
		
		return categoryList;
		
	}
	
////////////////////////AJAXADDCATEGORY////////////////////////
	
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/add", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo addCategoryList(@RequestBody CategoryVo categoryVo) {
	
		System.out.println("[ApiCategoryController.addCategoryList()]");
		
		CategoryVo addcategoryVo = categoryService.addCategory(categoryVo);
				
		return addcategoryVo;
		
	}
	
////////////////////////AJAXDELETECATEGORY////////////////////////
	
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public int deleteCategoryList(@RequestBody int cateNo) {
	
		System.out.println("[ApiCategoryController.deleteCategoryList()]");
		
		int count = categoryService.removeCategory(cateNo);
		
		return count;
		
	}

}
