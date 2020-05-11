package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex(){
        return "posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewIndividualPost(@PathVariable long id){
        return "view an individual post at id: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String creatingPost(){
        return "view the form for creating a post";
    }

//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
//    public void postingPost(){
//        //posting stuff
//    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String postingPost(){
        return "create a new post";
    }

}
