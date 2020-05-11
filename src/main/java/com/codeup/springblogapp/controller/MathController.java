package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{firstNum}/and/{secondNum}")
    @ResponseBody
    public String addition(@PathVariable int firstNum, @PathVariable int secondNum){
        return "3 plus 4 is: " + (firstNum + secondNum);
    }

    @GetMapping("/subtract/{firstNum}/from/{secondNum}")
    //can also be: /{operation}/{firstNum}/{keyword}/{secondNum}
    @ResponseBody
    public String subtract(@PathVariable int firstNum, @PathVariable int secondNum){
        return "3 subtracted from 10: " + (secondNum - firstNum);
    }

    @GetMapping("/multiply/{firstNum}/and/{secondNum}")
    @ResponseBody
    public String multiply(@PathVariable int firstNum, @PathVariable int secondNum){
        return "4 multiplied by 5 is: " + (firstNum * secondNum);
    }

    @GetMapping("/divide/{firstNum}/by/{secondNum}")
    @ResponseBody
    public String divide(@PathVariable int firstNum, @PathVariable int secondNum){
        return "6 divided by 3 is: " + (firstNum / secondNum);
    }

}
