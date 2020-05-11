package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

//    @RequestMapping(path = "/" method = RequestMethod.GET)  --> longer, explicit way of stating path and method
//    @GetMapping("/") // when GET request to
//    //"localhost:8080/", run the method directly beneath this
//    @ResponseBody // also return the method return String as html
//    public String hello(){
//        return "Index page!";
//    }

    @GetMapping("/hello")
    @ResponseBody
    public String helloSpring(){
        return "<em>Hello from Spring!</em>";
    }

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(){
        //do something here for uploading
    }

    //request mapping can be used for get and post requests


    // *** PATH VARIABLES *** //
    @GetMapping("/hello/{name}")
    public String sayHi(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "hello"; //will return anything typed in url portion "/hi/{name}"
    }

    //integer example for path variable:
    @GetMapping("/defined-ad/{id}")
    @ResponseBody
    public String showAd(@PathVariable long id){
        //String declaration above because html needs to be returned
        return "showing details for ad with id: " + id;
    }


    //example with two PathVariables:
    @GetMapping("/defined-ad/{id}/{version}")
    @ResponseBody
    public String showStuff(@PathVariable long id, @PathVariable int version){
        return "stuff with id: " + id + ". and with version: " + version;
    }

    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model) {
        model.addAttribute("cohort", "Welcome to " + cohort + "!");
        return "join";
    }


}
