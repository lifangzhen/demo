package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 26949 on 2018/12/29.
 */
@Entity
@Table(name = "clock")
public class Clock {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "start1")
    private String start1;
    @Column(name = "end1")
    private String end1;
    @Column(name = "start2")
    private String start2;
    @Column(name = "end2")
    private String end2;
    @Column(name = "start3")
    private String start3;
    @Column(name = "end3")
    private String end3;
    @Column(name = "start4")
    private String start4;
    @Column(name = "end4")
    private String end4;
    @Column(name = "start5")
    private String start5;
    @Column(name = "end5")
    private String end5;
    @Column(name = "start6")
    private String start6;
    @Column(name = "end6")
    private String end6;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart1() {
        return start1;
    }

    public void setStart1(String start1) {
        this.start1 = start1;
    }

    public String getEnd1() {
        return end1;
    }

    public void setEnd1(String end1) {
        this.end1 = end1;
    }

    public String getStart2() {
        return start2;
    }

    public void setStart2(String start2) {
        this.start2 = start2;
    }

    public String getEnd2() {
        return end2;
    }

    public void setEnd2(String end2) {
        this.end2 = end2;
    }

    public String getStart3() {
        return start3;
    }

    public void setStart3(String start3) {
        this.start3 = start3;
    }

    public String getEnd3() {
        return end3;
    }

    public void setEnd3(String end3) {
        this.end3 = end3;
    }

    public String getStart4() {
        return start4;
    }

    public void setStart4(String start4) {
        this.start4 = start4;
    }

    public String getEnd4() {
        return end4;
    }

    public void setEnd4(String end4) {
        this.end4 = end4;
    }

    public String getStart5() {
        return start5;
    }

    public void setStart5(String start5) {
        this.start5 = start5;
    }

    public String getEnd5() {
        return end5;
    }

    public void setEnd5(String end5) {
        this.end5 = end5;
    }

    public String getStart6() {
        return start6;
    }

    public void setStart6(String start6) {
        this.start6 = start6;
    }

    public String getEnd6() {
        return end6;
    }

    public void setEnd6(String end6) {
        this.end6 = end6;
    }
}
