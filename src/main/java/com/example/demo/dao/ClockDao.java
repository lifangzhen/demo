package com.example.demo.dao;

import com.example.demo.model.Clock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by 26949 on 2018/12/29.
 */
public interface ClockDao extends JpaRepository<Clock, String> {
}
