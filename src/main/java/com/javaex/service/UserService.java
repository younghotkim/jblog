package com.javaex.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;

	public int joinUser(UserVo userVo) {

		System.out.println("[UserServicec.joinUser()]");

		int count = userDao.insertUser(userVo);
		
		Map<String, Object> blogMap = new HashMap<String, Object>();
		
		blogMap.put("id", userVo.getId());
		blogMap.put("blogTitle", userVo.getUserName()+"의 블로그입니다.");
		blogMap.put("logoFile", "");
		
		blogDao.insertBlog(blogMap);

		return count;

	}

	public int idCheck(String id) {

		System.out.println("[UserService.idCheck()]");

		int count = userDao.selectUser(id);

		return count;

	}

	public UserVo getUser(UserVo userVo) {

		System.out.println("[UserService.getUser()]");

		UserVo authUser = userDao.selectUser(userVo);

		return authUser;

	}

}
