package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> selectList(String id) {
		
		System.out.println("[CategoryDao.getBlog()]");
		
		return sqlSession.selectList("category.selectList", id);
		
	}
	
}
