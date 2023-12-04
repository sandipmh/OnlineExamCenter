package com.onlineexamcenter.comitsa.OnlineExamCenter.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Language {
    @Id
    @GeneratedValue
    private Integer lid;
    private String languageName;

    public Language() {
    }

    public Language(Integer lid, String languageName) {
        this.lid = lid;
        this.languageName = languageName;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
