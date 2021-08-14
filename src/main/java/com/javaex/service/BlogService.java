package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private CategoryDao categoryDao;

	public BlogVo getBlog(String id) {

		System.out.println("[BlogService.getBlog()]");

		return blogDao.selectBlog(id);

	}

	public int modifyBlog(String id, String blogTitle, MultipartFile logoFile) {

		System.out.println("[BlogService.modifyBlog()]");

		Map<String, Object> blogMap = new HashMap<String, Object>();

		blogMap.put("id", id);
		blogMap.put("blogTitle", blogTitle);

		if (logoFile.getSize() == 0) {

			blogMap.put("logoFile", "");

			return blogDao.updateBlog(blogMap);

		} else {

			String saveDir = "C:\\javaStudy\\upload";

			String exName = logoFile.getOriginalFilename().substring(logoFile.getOriginalFilename().lastIndexOf("."));
			String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

			String filePath = saveDir + "\\" + saveName;

			try {
				byte[] fileData = logoFile.getBytes();

				OutputStream out = new FileOutputStream(filePath);
				BufferedOutputStream bout = new BufferedOutputStream(out);

				bout.write(fileData);
				bout.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			blogMap.put("logoFile", saveName);

			return blogDao.updateBlog(blogMap);

		}

	}
	
	public List<CategoryVo> getList(String id) {
		
		System.out.println("[BlogService.getList()]");
		
		return categoryDao.selectList(id);
		
	}

}
