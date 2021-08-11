package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;

	public BlogVo getBlog(String id) {

		System.out.println("[BlogService.getBlog()]");

		return blogDao.selectBlog(id);

	}
	
	public int modifyBlog(String id, String blogTitle, MultipartFile file) {
		
		System.out.println("[BlogService.modifyBlog()]");
		
		Map<String, Object> blogMap = new HashMap<String, Object>();
		
		blogMap.put("id", id);
		blogMap.put("blogTitle", blogTitle);
		blogMap.put("logoFile", file);
		
		return blogDao.updateBlog(blogMap);
		
	}


}
