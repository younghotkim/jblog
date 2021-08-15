package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PostDao;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostDao postDao;
	
	public void upPost(PostVo postVo) {
		
		postDao.insertPost(postVo);
		
	}
	
	public PostVo crtPost(int cateNo) {
		
		PostVo postVo = postDao.selectCrtPost(cateNo);
		
		return postVo;
		
	}
	
	public PostVo getPost(int postNo) {
		
		PostVo postVo = postDao.selectOnePost(postNo);
		
		return postVo;
		
	}
	
	public List<PostVo> getPostList(int cateNo) {
		
		List<PostVo> postList = postDao.selectPostList(cateNo);
		
		return postList;
		
	}
	
}
