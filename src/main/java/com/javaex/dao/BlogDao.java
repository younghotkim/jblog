package com.javaex.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(Map<String, Object> blogMap) {
		
		System.out.println("[BlogDao.insertBlog()]");
		
		return sqlSession.insert("blog.insertBlog", blogMap);
		
	}
	
	public BlogVo selectBlog(String id) {
		
		System.out.println("[BlogDao.getBlog()]");
		
		System.out.println("DAO: " + id);
		
		return sqlSession.selectOne("blog.selectBlog", id);
		
	}
	
}
