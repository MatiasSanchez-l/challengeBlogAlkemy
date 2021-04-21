package alkemyChallenge.alkemyBlog.service.serviceImpl;

import alkemyChallenge.alkemyBlog.model.Post;
import alkemyChallenge.alkemyBlog.repository.PostRepository;
import alkemyChallenge.alkemyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {
     @Autowired
     private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.ASC, "fecha"));
    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);
    }
}
