package alkemyChallenge.alkemyBlog.service;

import alkemyChallenge.alkemyBlog.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    void savePost(Post post);
    void deletePostById(Long id);
    Page<Post> findPaginated(int pageNro, int pageSize, String sortField, String sortDirection);
    void logicDeletePostById(Long id);
    Boolean postExistById(Long id);
}
