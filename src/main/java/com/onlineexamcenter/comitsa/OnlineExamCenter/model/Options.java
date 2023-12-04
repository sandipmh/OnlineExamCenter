package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Options {
    @Id
    @GeneratedValue
    private Long pid;
    private String OptionText;

    private boolean isCorrect;

    public Options() {
    }

    public Options(String optionText) {
        OptionText = optionText;
    }

    public Options(Long pid, String optionText, boolean isCorrect) {
        this.pid = pid;
        OptionText = optionText;
        this.isCorrect = isCorrect;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getOptionText() {
        return OptionText;
    }

    public void setOptionText(String optionText) {
        OptionText = optionText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return  OptionText;
    }
}
