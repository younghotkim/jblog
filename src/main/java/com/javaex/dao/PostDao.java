package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertPost(PostVo postVo) {
		
		return sqlSession.insert("post.insertPost", postVo);
		
	}
	
	public PostVo selectCrtPost(int cateNo) {
		
		return sqlSession.selectOne("post.selectOnePost", cateNo);

	}
	
	public PostVo selectOnePost(int postNo) {
		
		return sqlSession.selectOne("post.selectPostByNo", postNo);
		
	}
	
	public List<PostVo> selectPostList(int cateNo) {
		
		return sqlSession.selectList("post.selectPostList", cateNo);
		
	}
	
}
