package com.example.demo.controller;

import com.example.demo.dao.ClockDao;
import com.example.demo.dto.ClockDto;
import com.example.demo.jpadao.ClockJpaDao;
import com.example.demo.model.Clock;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.TimeZone;

/**
 * Created by 26949 on 2018/12/29.
 */
@Controller
@Slf4j
public class ClockController {
    @Autowired private ClockJpaDao clockJpaDao;

    @RequestMapping("/list")
    @ResponseBody
    public List<Clock> list(){
        return null;
    }

    @RequestMapping("/clock.htm")
    public ModelAndView clock(){
        List<Clock> list = clockJpaDao.findAll();
        List<ClockDto> relist  = new ArrayList<>();
        if (list!=null && list.size()>0){
            for (Clock clock : list) {
                ClockDto dto = new ClockDto();
                BeanUtils.copyProperties(clock, dto);
                String start1 = clock.getStart1();
                String start2 = clock.getStart2();
                String start3 = clock.getStart3();
                String start4 = clock.getStart4();
                String start5 = clock.getStart5();
                String start6 = clock.getStart6();
                String end1 = clock.getEnd1();
                String end2 = clock.getEnd2();
                String end3 = clock.getEnd3();
                String end4 = clock.getEnd4();
                String end5 = clock.getEnd5();
                String end6 = clock.getEnd6();
                String commonDate = "2019-01-01 ";
                Long times = 0L;
                if (!StringUtils.isEmpty(start1)&&!StringUtils.isEmpty(end1)){
                    start1 = commonDate+ start1;
                    end1 = commonDate+end1;
                    Date date1 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, start1);
                    Date date11 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, end1);
                    Long timestamp1 = date1.getTime();
                    Long timestamp11 = date11.getTime();
                    times = times+(timestamp11-timestamp1);
                }
                if (!StringUtils.isEmpty(start2)&&!StringUtils.isEmpty(end2)){
                    start2 = commonDate+ start2;
                    end2 = commonDate+end2;
                    Date date2 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, start2);
                    Date date22 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, end2);
                    Long timestamp2 = date2.getTime();
                    Long timestamp22 = date22.getTime();
                    times = times+(timestamp22-timestamp2);
                }
                if (!StringUtils.isEmpty(start3)&&!StringUtils.isEmpty(end3)){
                    start3 = commonDate+ start3;
                    end3 = commonDate+end3;
                    Date date3 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, start3);
                    Date date33 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, end3);
                    Long timestamp3 = date3.getTime();
                    Long timestamp33 = date33.getTime();
                    times = times+(timestamp33-timestamp3);
                }
                if (!StringUtils.isEmpty(start4)&&!StringUtils.isEmpty(end4)){
                    start4 = commonDate+ start4;
                    end4 = commonDate+end4;
                    Date date4 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, start4);
                    Date date44 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, end4);
                    Long timestamp4 = date4.getTime();
                    Long timestamp44 = date44.getTime();
                    times = times+(timestamp44-timestamp4);
                }
                if (!StringUtils.isEmpty(start5)&&!StringUtils.isEmpty(end5)){
                    start5 = commonDate+ start5;
                    end5 = commonDate+end5;
                    Date date5 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, start5);
                    Date date55 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, end5);
                    Long timestamp5 = date5.getTime();
                    Long timestamp55 = date55.getTime();
                    times = times+(timestamp55-timestamp5);
                }
                if (!StringUtils.isEmpty(start6)&&!StringUtils.isEmpty(end6)){
                    start6 = commonDate+ start6;
                    end6 = commonDate+end6;
                    Date date6 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, start6);
                    Date date66 = DateUtil.formatDate(DateUtil.DATE_MINUTE_PATTERN, end6);
                    Long timestamp6 = date6.getTime();
                    Long timestamp66 = date66.getTime();
                    times = times+(timestamp66-timestamp6);
                }
                Long hour = times/(60*60*1000);
                Long minute = (times-hour*60*60*1000)/(60*1000);
                dto.setTotal(hour+" 小时 "+minute+" 分");
                relist.add(dto);
            }
        }
        ModelAndView mav = new ModelAndView("/clock");
        mav.addObject("list", relist);
        return mav;
    }

    @RequestMapping("/start.json")
    @ResponseBody
    public String start(){
        //获取今日的日期
        Date dt = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        day.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String today=day.format(dt);

        //获取当前的时间
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        timeformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time=timeformat.format(dt);
        log.info("+++++++++++++++++++++++++++current time is:{}", time);

        //根据今日的日期查出今日是否已经打过卡
        Clock clock = clockJpaDao.findById(today).orElse(null);

        //如果今日的clock是空的，说明还没打过卡，要新增一条数据
        if (clock==null){
            clock = new Clock();
            clock.setId(today);
            clock.setStart1(time);
            clockJpaDao.save(clock);
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
            clockJpaDao.save(clock);
        }
        return "打卡成功";
    }

    @RequestMapping("/end.json")
    @ResponseBody
    public String end(){
        //获取今日的日期
        Date dt = new Date();
        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        day.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String today=day.format(dt);

        //获取当前的时间
        SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm");
        timeformat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time=timeformat.format(dt);

        //根据今日的日期查出今日是否已经打过卡
        Clock clock = clockJpaDao.findById(today).orElse(null);
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
            clockJpaDao.save(clock);
        }
        return "打卡结束";
    }
}
