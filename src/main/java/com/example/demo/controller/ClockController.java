package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 26949 on 2018/12/29.
 */
@Controller
public class ClockController {
    @RequestMapping("/clock.htm")
    public ModelAndView clock(){
        ModelAndView mav = new ModelAndView("/clock");
        return mav;
    }

    @RequestMapping("/start.json")
    @ResponseBody
    public void start(){

    }

    @RequestMapping("/end.json")
    @ResponseBody
    public void end(){

    }
}
