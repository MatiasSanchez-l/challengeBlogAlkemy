package alkemyChallenge.alkemyBlog.controller;


import alkemyChallenge.alkemyBlog.model.Category;
import alkemyChallenge.alkemyBlog.model.Post;
import alkemyChallenge.alkemyBlog.service.CategoryService;
import alkemyChallenge.alkemyBlog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String viewHomePage(Model model){

        return findPaginated(1,"fecha" , "desc",model);
    }

    @GetMapping("/showNewPostForm")
    public String showNewPostForm(Model model){
        Post post = new Post();
        List<Category> categories = categoryService.getAllCategories();

        model.addAttribute("post", post);
        model.addAttribute("categories", categories);
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

    @GetMapping("/showPostInfo/{id}")
    public String showPostInfo(@PathVariable(value = "id") long id,
                                    Model model){
        Post post = postService.getPostById(id);

        model.addAttribute("post", post);
        model.addAttribute("title", post.getTitulo().toUpperCase(Locale.ROOT));
        return "post_info";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") long id){
        this.postService.deletePostById(id);
        return "redirect:/posts";
    }

    @GetMapping("/page/{pageNro}")
    public String findPaginated(@PathVariable (value = "pageNro")int pageNro,
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model){
        int pageSize = 5;
        Page<Post> page =  postService.findPaginated(pageNro, pageSize, sortField, sortDir);
        List<Post> listPosts = page.getContent();

        model.addAttribute("currentPage", pageNro);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("posts", listPosts);
        model.addAttribute("title", "HOME");

        return "index";
    }
}
