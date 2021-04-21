package alkemyChallenge.alkemyBlog.controller;


import alkemyChallenge.alkemyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listBlog", postService.getAllPosts());
        return "index";
    }
}
