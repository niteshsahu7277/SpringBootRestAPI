package com.niit.myblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.myblog.model.Blog;
import com.niit.myblog.repository.BlogRepository;

@RestController
public class BlogDataController {
	@Autowired
	BlogRepository blogRepository;
	@PostMapping("/blogdata")
	public Blog create(@RequestBody Map<String, String> body){
	String title = body.get("title");
	String content = body.get("content");
	return blogRepository.save(new Blog(title,content));
	}

}
