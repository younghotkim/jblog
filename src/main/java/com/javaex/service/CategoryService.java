package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<CategoryVo> getList(String id) {
		
		System.out.println("[CategoryService.getList()]");
		
		return categoryDao.selectList(id);
		
	}
	
	public CategoryVo addCategory(CategoryVo categoryVo) {
		
		System.out.println("[CategoryService.addCategory()]");
		
		categoryDao.insertCategory(categoryVo);
		
		int cateNo = categoryVo.getCateNo();
		
		return categoryDao.selectOne(cateNo);	
		
	}
	
	public int removeCategory(int cateNo) {
		
		System.out.println("[CategoryService.removeCategory()]");
		
		return categoryDao.deleteCategory(cateNo);
		
	}
	
	
}
