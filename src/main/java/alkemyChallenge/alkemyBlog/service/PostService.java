package alkemyChallenge.alkemyBlog.service;

import alkemyChallenge.alkemyBlog.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(Long id);
    void savePost(Post post);
}
