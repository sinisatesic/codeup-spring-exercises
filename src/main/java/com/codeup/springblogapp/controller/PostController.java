package com.codeup.springblogapp.controller;


import com.codeup.springblogapp.model.Post;
import com.codeup.springblogapp.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PostController {
    //    generic string returns - not templates - BEGIN
//    @GetMapping("/posts")
//    @ResponseBody
//    public String postsIndex(){
//        return "posts index page";
//    }
//
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String viewIndividualPost(@PathVariable long id){
//        return "view an individual post at id: " + id;
//    }
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String creatingPost(){
//        return "view the form for creating a post";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String postingPost(){
//        return "create a new post";
//    }
    //    generic string returns - not templates - END



    //********* DEPENDENCY INJECTION ***********
    private PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }
    //***********************************


    //********* SHOW ALL POSTS ***********
    @GetMapping("/posts")
    public String showPosts(Model model){
    //jpa da wae:
    model.addAttribute("posts", postRepo.findAll());

     //hardcoded - before jpa
//    List<Post> postList = new ArrayList<>();
//    Post post = new Post("Blog post 1", "cheese");
//    postList.add(post);
//    post = new Post("Blog post 2", "grapes");
//    postList.add(post); //adding the ad to array list
//    model.addAttribute("posts", postList);
        return "posts/index";
}
    //***********************************



    //********* CREATE NEW POST ***********
    @GetMapping("posts/create")
    public String gotoCreatePostForm(Model model){
        return "posts/create";
    }


    @PostMapping("/posts/create")
    public String createPost(@RequestParam(name = "title") String title,
                           @RequestParam(name = "body") String body,
                           Model model) {
        Post post = new Post(title, body);
        postRepo.save(post);
                //save persists the post object
        model.addAttribute("post", post);
        //shuffles this to view page/template
        //pushes to db to view
        return "posts/show";
    }
    //***********************************


    //********* SHOW SINGLE POST ***********
    // commented out below is before jpa
//    @GetMapping("/post")
//    public String showPost(Model model){
//        Post post = new Post("single blog post", "stuff");
//        model.addAttribute("Post", post);
//        return "posts/show";
//    }


    //view individual post with id
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.getPostById(id));
        return "/posts/show";
    }
    //***********************************



    //********* EDIT A POST ***********
    @GetMapping("/posts/edit/{id}")
    public String postEditForm(@PathVariable long id, Model model) {
        Post post = postRepo.getPostById(id);
        model.addAttribute("post", post);
        return "/posts/edit";
    }
    @PostMapping("/posts/edit/{id}")
    public String postEdit(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body) {
        Post post = postRepo.getPostById(id);
        post.setTitle(title);
        post.setBody(body);
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
