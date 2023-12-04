package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;

    private boolean notification;
    @ManyToOne
    private Theme theme;
    @ManyToOne
    private Language language;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public Settings() {
    }

    public Settings(Long sid, boolean notification, Theme theme, Language language, User user) {
        this.sid = sid;
        this.notification = notification;
        this.theme = theme;
        this.language = language;
//        this.user = user;
    }

    public Settings(Long sid, boolean notification, Theme theme, Language language) {
        this.sid = sid;
        this.notification = notification;
        this.theme = theme;
        this.language = language;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
