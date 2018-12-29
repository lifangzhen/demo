package com.example.demo.controller;

import com.example.demo.dao.ClockDao;
import com.example.demo.model.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 26949 on 2018/12/29.
 */
@Controller
public class ClockController {
    @Autowired private ClockDao clockDao;
    @RequestMapping("/clock.htm")
    public ModelAndView clock(){
        List<Clock> list = clockDao.findAll();
        Clock clock = new Clock();
        clock.setId("2018-12-29");
        clock.setStart1("8:00");
        clock.setEnd1("9:00");
        clock.setStart2("10:00");
        clock.setEnd2("11:00");
        clock.setStart3("13:00");
        clock.setEnd3("18:00");
        list.add(clock);
        ModelAndView mav = new ModelAndView("/clock");
        mav.addObject("list", list);
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
