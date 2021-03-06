package com.codeup.springblogapp.controller;
import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import com.codeup.springblogapp.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class PostController {


    //********* DEPENDENCY INJECTION ***********
    private PostRepository postRepo; //can also be called postDao as logical naming convention
    private UserRepository userRepo;
    private EmailService emailService;

    public PostController(PostRepository postRepo, UserRepository userRepo, EmailService emailService) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }
    //***********************************



    //********* SHOW ALL POSTS ***********
    @GetMapping("/posts")
    public String showPosts(Model model){
        //da wae of jpa:
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }
    //***********************************



    //********* CREATE NEW POST ***********
    @GetMapping("posts/create")
    public String gotoCreatePostForm(Model model){
        model.addAttribute("post", new Post());
        //creates post from model
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        emailService.prepareAndSend(post,"post created", "You done created a post");
        postRepo.save(post);
        return "posts/show";
    }
    //***********************************



    //********* SHOW SINGLE POST ***********

    //view individual post with id
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.getPostById(id));
        //can also do .getOne(id), which is JPA, instead of .getPostById(id);
        return "/posts/show";
    }
    //***********************************


    //********* EDIT A POST ***********
    @GetMapping("/posts/edit/{id}")
    public String postEditForm(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    //below is original way - before @ModelAttribute
//    @PostMapping("/posts/edit/{id}")
//    public String postEdit(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
//        Post post = postRepo.getPostById(id);
//        post.setTitle(title);
//        post.setBody(body);
//        postRepo.save(post);
//        return "redirect:/posts";
//    }

    @PostMapping("/posts/edit/{id}")
    public String postEdit(@ModelAttribute Post post) {
        postRepo.save(post);
        return "redirect:/posts";
    }
    //***********************************



    //********* DELETE A POST ***********
    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable long id, Model model){

        postRepo.deleteById(id);
        return "redirect:/posts";
    }
    //***********************************
}
