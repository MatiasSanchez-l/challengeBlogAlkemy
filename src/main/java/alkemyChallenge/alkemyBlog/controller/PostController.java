package alkemyChallenge.alkemyBlog.controller;


import alkemyChallenge.alkemyBlog.model.Post;
import alkemyChallenge.alkemyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listBlog", postService.getAllPosts());
        model.addAttribute("title", "HOME");
        return "index";
    }

    @GetMapping("/showNewPostForm")
    public String showNewPostForm(Model model){
        Post post = new Post();

        model.addAttribute("post", post);
        model.addAttribute("title", "CREAR POST");
        return "new_post";
    }

    @PostMapping("/savePost")
    public String savePost(@ModelAttribute("post") Post post){
        postService.savePost(post);
        return "redirect:/posts/";
    }
}
