package alkemyChallenge.alkemyBlog.service.serviceImpl;

import alkemyChallenge.alkemyBlog.model.Blog;
import alkemyChallenge.alkemyBlog.repository.BlogRepository;
import alkemyChallenge.alkemyBlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
     @Autowired
     private BlogRepository blogRepository;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll(Sort.by(Sort.Direction.ASC, "fecha"));
    }

    @Override
    public Blog getBlogById(Long id) {
        return null;
    }

    @Override
    public void saveBlog(Blog blog) {
        this.blogRepository.save(blog);
    }
}
