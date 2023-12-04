package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.domain.PageRequest;

@Entity
public class Theme {
    @Id
    private long tid;
    private String name;

    private String bgColor;
    public Theme() {
    }

    public Theme(long tid, String name, String bgColor) {
        this.tid = tid;
        this.name = name;
        this.bgColor = bgColor;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
