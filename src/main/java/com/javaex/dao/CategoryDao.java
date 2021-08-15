package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private CategoryVo categoryVo;
	
	public List<CategoryVo> selectList(String id) {
		
		System.out.println("[CategoryDao.selectList()]");
		
		return sqlSession.selectList("category.selectList", id);
		
	}
	
	public CategoryVo selectOne(int cateNo) {
		
		System.out.println("[CategoryDao.selectOne()]");
		
		return sqlSession.selectOne("category.selectCategoryByNo", cateNo);
		
	}
	
	public int insertCategory(CategoryVo categoryVo) {
		
		System.out.println("[CategoryDao.insertCategory()]");
		
		return sqlSession.insert("category.insertCategory", categoryVo);
		
	}
	
	public int deleteCategory(int cateNo) {
		
		System.out.println("[CategoryDao.deleteCategory()]");
		
		return sqlSession.delete("category.deleteCategory", cateNo);
		
	}
	
	public void insertCategoryDefault(UserVo userVo) {
		
		categoryVo.setId(userVo.getId());
		
		sqlSession.insert("category.insertDefault", categoryVo);
		
	}
	
}
