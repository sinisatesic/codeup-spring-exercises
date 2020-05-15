package com.codeup.springblogapp.controller;


import com.codeup.springblogapp.model.Ad;
import com.codeup.springblogapp.model.User;
import com.codeup.springblogapp.repositories.AdRepository;
import com.codeup.springblogapp.repositories.PostRepository;
import com.codeup.springblogapp.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdController {

    private UserRepository userRepo;
    private AdRepository adRepo;

    public AdController(UserRepository userRepo, AdRepository adRepo) {
        this.userRepo = userRepo;
        this.adRepo = adRepo;
    }

    //PLACEHOLDER DATA - NOT DYNAMIC
    @GetMapping("/ads")
    public String showAds(Model model){
        List<Ad> adList = new ArrayList<>();
        Ad ad = new Ad("1967 Buick Wildcat", "car");
        adList.add(ad);
        ad = new Ad("2014 Ford Mustang GT/CS", "car");
        adList.add(ad); //adding the ad to array list
        model.addAttribute("ads", adList);
        return "ads/index";
    }

    @GetMapping("ads/create")
    public String gotoCreateAdForm(Model model){
        Ad ad = new Ad();
        model.addAttribute("ad", ad);
        return "ads/create";
    }


//    @PostMapping("/ads/create")
//    public String createAd(@ModelAttribute Ad ad){
//        return "ads/show";
//    }


    @GetMapping("/ad")
    public String showAd(Model model){
        Ad ad = new Ad("Mac Book pro", "computer");
        model.addAttribute("ad", ad);
        return "ads/show";
    }
}
