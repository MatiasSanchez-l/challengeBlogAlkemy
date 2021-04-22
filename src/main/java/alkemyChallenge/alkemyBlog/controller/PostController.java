package alkemyChallenge.alkemyBlog.controller;


import alkemyChallenge.alkemyBlog.model.Post;
import alkemyChallenge.alkemyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
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
        String contenido = post.getContenido();
        String titulo = post.getTitulo();

        if(contenido.isEmpty() || titulo.isEmpty()){
            return "redirect:/posts/showNewPostForm";
        }

        LocalDateTime date = LocalDateTime.now();
        post.setFecha(date);

        postService.savePost(post);
        return "redirect:/posts/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id,
                                    Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        model.addAttribute("title", "EDITAR POST");
        return "update_post";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") long id){
        this.postService.deletePostById(id);
        return "redirect:/posts";
    }
}
