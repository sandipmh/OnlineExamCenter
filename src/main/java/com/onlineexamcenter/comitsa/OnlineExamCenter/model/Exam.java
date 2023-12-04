package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Exam {
    @Id
    @GeneratedValue
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
    private boolean isPublic;

    @ManyToMany(mappedBy = "exam")
    private List<User> users;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "exam_question",
            joinColumns = @JoinColumn(name = "eid"),
            inverseJoinColumns = @JoinColumn(name = "qid")
    )
    private List<Questions> questions;
    public Exam() {
    }

    public Exam(Long eid, String examTitle, String description, LocalDateTime startDate, LocalDateTime lastDate, int time, int numberOfQuestions, int marks, String status, String trendIcon, String examIcon, String subject, boolean isPublic, List<User> users, List<Questions> questions) {
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
        this.isPublic = isPublic;
        this.users = users;
        this.questions = questions;
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

    public String getTrendIcon() {
        return trendIcon;
    }

    public void setTrendIcon(String trendIcon) {
        this.trendIcon = trendIcon;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
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

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String toString(){
        return examTitle;
    }
}
