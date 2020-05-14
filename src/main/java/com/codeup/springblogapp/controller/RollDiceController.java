package com.codeup.springblogapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

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
