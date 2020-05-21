package com.codeup.springblogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

//    @GetMapping("/")
//    @ResponseBody
//    public String landing(){
//        return "the landing page";
//    }

    @GetMapping("")
    public String homePage(){
        return "home";
    }
}
