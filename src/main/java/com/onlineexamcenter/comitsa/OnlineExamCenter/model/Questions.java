package com.onlineexamcenter.comitsa.OnlineExamCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Questions {
    @Id
    @GeneratedValue
    private Long qid;
    private String question;
    @JsonIgnore
    private String answer;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Options> options;
    private boolean singleSelection;
    private int marks;
    @ManyToOne
    private Subjects subject;

    @JsonBackReference
    @ManyToMany(mappedBy = "questions",cascade = CascadeType.ALL)
    private List<Exam> exam;

    public Questions() {
    }

    public Questions(Long qid, String question, String answer, List<Options> options, boolean singleSelection, int marks, Subjects subject, List<Exam> exam) {
        this.qid = qid;
        this.question = question;
        this.answer = answer;
        this.options = options;
        this.singleSelection = singleSelection;
        this.marks = marks;
        this.subject = subject;
        this.exam = exam;
    }

    public boolean isSingleSelection() {
        return singleSelection;
    }

    public void setSingleSelection(boolean singleSelection) {
        this.singleSelection = singleSelection;
    }

    public List<Exam> getExam() {
        return exam;
    }

    public void setExam(List<Exam> exam) {
        this.exam = exam;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<Options> getOptions() {
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subject) {
        this.subject = subject;
    }
}
