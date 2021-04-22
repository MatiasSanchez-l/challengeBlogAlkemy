package alkemyChallenge.alkemyBlog.service.serviceImpl;

import alkemyChallenge.alkemyBlog.model.Post;
import alkemyChallenge.alkemyBlog.repository.PostRepository;
import alkemyChallenge.alkemyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {
     @Autowired
     private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "fecha"));
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> optional = postRepository.findById(id);
        Post post = null;
        if(optional.isPresent()){
            post = optional.get();
        }else{
            throw new RuntimeException("Error 404 Post not found for id :: " + id);
        }
        return post;
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        this.postRepository.deleteById(id);
    }
}
