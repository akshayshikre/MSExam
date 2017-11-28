package com.example.akash.mindspaceexam;

import java.util.ArrayList;

/**
 * Created by akash on 13-11-2017.
 */

public class Exam {
    String classs="",branch="",subject="",topic="",typeofexam="",noofquest="",
            marksprquest="",examdate="",examduration="",remark="",gradea="",
            gradeb="",gradec="",graded="",password="",examcode="";
    ArrayList<question> questionlist=new ArrayList<question>();

    public Exam(String classs, String branch, String subject, String topic, String typeofexam, String noofquest, String marksprquest, String examdate, String examduration, String remark, String gradea, String gradeb, String gradec, String graded, String password, String examcode, ArrayList<question> questionlist) {
        this.classs = classs;
        this.branch = branch;
        this.subject = subject;
        this.topic = topic;
        this.typeofexam = typeofexam;
        this.noofquest = noofquest;
        this.marksprquest = marksprquest;
        this.examdate = examdate;
        this.examduration = examduration;
        this.remark = remark;
        this.gradea = gradea;
        this.gradeb = gradeb;
        this.gradec = gradec;
        this.graded = graded;
        this.password = password;
        this.examcode = examcode;
        this.questionlist = questionlist;
    }
}
