package com.example.demo.dao;

import com.example.demo.model.Clock;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 71426 on 2019/1/1.
 */
public interface ClockDao {
    public List<Clock> list();
}
