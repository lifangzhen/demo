package com.example.demo.controller;

import com.example.demo.dao.ClockDao;
import com.example.demo.model.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dao.ClockDao;
import com.example.demo.model.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        ModelAndView mav = new ModelAndView("/clock");
        mav.addObject("list", list);
        return mav;
    }

    @RequestMapping("/start.json")
    @ResponseBody
    public String start(){
        //获取今日的日期
        Date dt = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        String today=day.format(dt);

        //获取当前的时间
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        String time=timeformat.format(dt);

        //根据今日的日期查出今日是否已经打过卡
        Clock clock = clockDao.findById(today).orElse(null);

        //如果今日的clock是空的，说明还没打过卡，要新增一条数据
        if (clock==null){
            clock = new Clock();
            clock.setId(today);
            clock.setStart1(time);
            clockDao.save(clock);
        }else { //若今日的clock不为空，说明已经打过卡了，是更新数据
            //从start2开始判断，哪个为空，说明就到哪次了，
            // 比如getStart2不等于null的话，说明已经有数据了，肯定已经最少是第三次打卡了
            if (clock.getStart2()==null){
                if (clock.getEnd1()==null){
                    return "第一次打卡还没结束呢";
                }
                clock.setStart2(time);
            }else if (clock.getStart3()==null){
                if (clock.getEnd2()==null){
                    return "第二次打卡还没结束呢";
                }
                clock.setStart3(time);
            }else if (clock.getStart4()==null){
                if (clock.getEnd3()==null){
                    return "第三次打卡还没结束呢";
                }
                clock.setStart4(time);
            }else if (clock.getStart5()==null){
                if (clock.getEnd4()==null){
                    return "第四次打卡还没结束呢";
                }
                clock.setStart5(time);
            }else{
                if (clock.getEnd5()==null){
                    return "第五次打卡还没结束呢";
                }
                clock.setStart6(time);
            }
            clockDao.save(clock);
        }
        return "打卡成功";
    }

    @RequestMapping("/end.json")
    @ResponseBody
    public String end(){
        //获取今日的日期
        Date dt = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        String today=day.format(dt);

        //获取当前的时间
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        String time=timeformat.format(dt);

        //根据今日的日期查出今日是否已经打过卡
        Clock clock = clockDao.findById(today).orElse(null);
        if (clock==null){
            //理论上结束的时候不可能存在空数据
            //若存在的话，说明还没点开始呢
            //这里应该报错，告诉点击的人还没点开始呢
            return "还没开始呢";
        }else {
            if (clock.getEnd1()==null){
                if (clock.getStart1()==null){
                    return "第一次打卡还没开始";
                }
                clock.setEnd1(time);
            }else if (clock.getEnd2()==null){
                if (clock.getStart2()==null){
                    return "第二次打卡还没开始";
                }
                clock.setEnd2(time);
            }else if (clock.getEnd3()==null){
                if (clock.getStart3()==null){
                    return "第三次打卡还没开始";
                }
                clock.setEnd3(time);
            }else if (clock.getEnd4()==null){
                if (clock.getStart4()==null){
                    return "第四次打卡还没开始";
                }
                clock.setEnd4(time);
            }else if (clock.getEnd5()==null){
                if (clock.getStart5()==null){
                    return "第五次打卡还没开始";
                }
                clock.setEnd5(time);
            }else {
                if (clock.getStart6()==null){
                    return "第六次打卡还没开始";
                }
                clock.setEnd6(time);
            }
            clockDao.save(clock);
        }
        return "打卡结束";
    }
}
