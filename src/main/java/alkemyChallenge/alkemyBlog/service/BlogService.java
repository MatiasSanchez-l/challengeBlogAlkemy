package alkemyChallenge.alkemyBlog.service;

import alkemyChallenge.alkemyBlog.model.Blog;

import java.util.List;

public interface BlogService {
    List<Blog> getAllBlogs();
    Blog getBlogById(Long id);
    void saveBlog(Blog blog);
}
