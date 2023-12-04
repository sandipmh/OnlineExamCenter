package com.onlineexamcenter.comitsa.OnlineExamCenter.dto;

import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Questions;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.Subjects;
import com.onlineexamcenter.comitsa.OnlineExamCenter.model.User;
import jakarta.persistence.ManyToMany;

import java.time.LocalDateTime;
import java.util.List;

public class ExamDTO {
    private Long eid;
    private String examTitle;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime lastDate;
    private int time;
    private int numberOfQuestions;
    private int marks;
    private String Status;
    private String trendIcon;
    private String examIcon;
    private String subject;

    public ExamDTO() {
    }

    public ExamDTO(Long eid, String examTitle, String description, LocalDateTime startDate, LocalDateTime lastDate, int time, int numberOfQuestions, int marks, String status, String trendIcon, String examIcon, String subject) {
        this.eid = eid;
        this.examTitle = examTitle;
        this.description = description;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.time = time;
        this.numberOfQuestions = numberOfQuestions;
        this.marks = marks;
        Status = status;
        this.trendIcon = trendIcon;
        this.examIcon = examIcon;
        this.subject = subject;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastDate = lastDate;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTrendIcon() {
        return trendIcon;
    }

    public void setTrendIcon(String trendIcon) {
        this.trendIcon = trendIcon;
    }

    public String getExamIcon() {
        return examIcon;
    }

    public void setExamIcon(String examIcon) {
        this.examIcon = examIcon;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
