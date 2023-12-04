package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subjects {
    @Id
    @GeneratedValue
    private Long sid;
    private  String title;

    private String icon;
    private String description;
   @OneToMany(cascade = CascadeType.ALL)
    private List<Questions> questions;

    public Subjects(Long sid, String title, String icon, String description, List<Questions> questions) {
        this.sid = sid;
        this.title = title;
        this.icon = icon;
        this.description = description;
        this.questions = questions;
    }

    public Subjects() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return  title;
    }
}
