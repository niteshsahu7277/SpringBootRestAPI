package com.niit.myblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/blogdata")
	public List<Blog> index(){
		return blogRepository.findAll();
	}
	@GetMapping("/blogdata/{id}")
	public Optional<Blog> show(@PathVariable String id){
	int blogId = Integer.parseInt(id);
	return blogRepository.findById(blogId);
	}
	@PutMapping("/blogdata/{id}")
	public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
	int blogId = Integer.parseInt(id);
	// getting blog
	Blog blog = blogRepository.findById(blogId).get();
	blog.setTitle(body.get("title"));
	blog.setContent(body.get("content"));
	return blogRepository.save(blog);
	}
	@PostMapping("/blogdata/search")
	public List<Blog> search(@RequestBody Map<String, String> body){
	String searchTerm = body.get("text");
	return blogRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
	}
	@DeleteMapping("blogdata/{id}")
	public boolean delete(@PathVariable String id){
	int blogId = Integer.parseInt(id);
	blogRepository.deleteById(blogId);
	return true;
	}

}
