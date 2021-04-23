package alkemyChallenge.alkemyBlog.service.serviceImpl;

import alkemyChallenge.alkemyBlog.model.Post;
import alkemyChallenge.alkemyBlog.repository.PostRepository;
import alkemyChallenge.alkemyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<Post> findPaginated(int pageNro, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable peageable = PageRequest.of(pageNro -1, pageSize, sort);
        return this.postRepository.findAll(peageable);
    }

    @Override
    public void logicDeletePostById(Long id) {
        Post post = this.getPostById(id);
        post.setBorrado(true);
    }

    @Override
    public Boolean postExistById(Long id) {
        Boolean result = true;
        Post post = this.getPostById(id);

        if (post == null){
            result = false;
        }

        return result;
    }


}
