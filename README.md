Greetings!

This is a brief explanation of this repo and projects within - it is titled codeup-spring-exercises
as Codeup is the bootcamp (full-stack web development) I underwent to learn Spring (along with other
languages/technologies). This particular project is the Fortuna Spring Blog. Fortuna is the name of
my Codeup cohort, and this application is intended to serve as a site for CRUD-enabled posts built with
Spring and Thymeleaf via MVC design. 
 
The consistent navbar you see with available options is a partial - below is its code:
 
 ```
<nav th:fragment="navbar" class="navbar navbar-default">
    <!-- awesome navbar goes here -->
    <ul class="nav navbar-nav">
        <li class="nav-item"><a href="/home">Home</a> </li>
        <li class="nav-item"><a href="/hello/Fortuna">Hello</a></li>
        <li class="nav-item"><a href="/roll-dice">Dice Game</a></li>
        <li class="nav-item"><a href="/posts">Posts</a></li>
        <li class="nav-item"><a href="/posts/create">Create Post</a></li>
        <li class="nav-item">
        <li class="nav-item">
            <form th:action="@{/login}" th:method="post">
                <button class="nav-link" style="margin-top: 2px">Login</button>
            </form>
        </li>
        <li class="nav-item">
            <form th:action="@{/logout}" th:method="post">
                <button class="nav-link" style="margin-top: 2px">Logout</button>
            </form>
        </li>
        <li class="nav-item">
            <a href="/register">
                <button class="nav-link register-button">Register</button>
            </a>
        </li>
    </ul>
</nav>

```

This was my first Spring project, and was thrilling to create. This project absolutely made me fall
in love with Jpa; it is a breathe of fresh air regarding MVC design, without a doubt.

Since this project was creating using IntelliJ, below is a visual depiction of the file structure
of the controllers, models and repos:

![image](https://user-images.githubusercontent.com/56378424/85210894-82dfbd00-b309-11ea-8ce1-72194ad09836.png)

Frameworks Bootstrap and jQuery were also utilized in the creation of this project, as well as 
plenty of custom CSS:

![image](https://user-images.githubusercontent.com/56378424/85210931-db16bf00-b309-11ea-82c2-331e92a3c123.png)

The vast majority of the functionality on site is affiliated with the posts, and controller for
it is as below:

```
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
```
Furthermore, another interesting components to the Spring Blog is the dice-game within it.
The controller details, along with additional commentary, are as below:

```
package com.codeup.springblogapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDiceTemplate(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String initiateRollDice(@PathVariable int n, Model model){
        ArrayList<Integer>diceRolls = new ArrayList<>();
        // creating new ArrayList of int type called diceRolls
        for (int i = 0; i < 6; i++){
            //looping through each possible number user can pick from
            diceRolls.add((int) Math.floor((Math.random() * 6)) + 1);
            //adding random int of 1 through 6 to array list diceRolls (+ 1 for floor)
        }
        model.addAttribute("diceRolls", diceRolls);
        // adding attribute of diceRolls to model object (random int of 1 - 6)
        model.addAttribute("userRoll", n);
        // adding attribute of userRoll to model object (whatever num user selects)
        return "roll-dice";
        // returning roll-dice template
    }
}
```

I loved making this project, and it was the first of what will be many. If you have any questions,
or wish to ever collaborate on any project, please feel free to get in contact: sinisa.tesic210@gmail.com




   
