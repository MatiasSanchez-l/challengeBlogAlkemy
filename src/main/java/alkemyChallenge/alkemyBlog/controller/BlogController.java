package alkemyChallenge.alkemyBlog.controller;


import alkemyChallenge.alkemyBlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listBlog", blogService.getAllBlogs());
        return "index";
    }
}
