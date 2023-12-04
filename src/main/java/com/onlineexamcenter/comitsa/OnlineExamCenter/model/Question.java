package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long qid;
    private String question;
    private String users_answer;
    @OneToMany
    @JoinColumn(insertable = false, updatable = false)
    private List<Options> options;
    private boolean singleSelection;
    private int marks;
    @ManyToOne
    private AnswerSheet answerSheet;

    public Question(Long qid, String question, String users_answer, List<Options> options, boolean singleSelection, int marks, AnswerSheet answerSheet) {
        this.qid = qid;
        this.question = question;
        this.users_answer = users_answer;
        this.options = options;
        this.singleSelection = singleSelection;
        this.marks = marks;
        this.answerSheet = answerSheet;
    }

    public Question() {
    }
    public AnswerSheet getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(AnswerSheet answerSheet) {
        this.answerSheet = answerSheet;
    }

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getUsers_answer() {
        return users_answer;
    }

    public void setUsers_answer(String users_answer) {
        this.users_answer = users_answer;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public boolean isSingleSelection() {
        return singleSelection;
    }

    public void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
