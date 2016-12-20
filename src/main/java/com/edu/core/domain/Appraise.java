package com.edu.core.domain;

import java.util.Date;

public class Appraise {
    private String id;

    private String teacherid;

    private String studentid;

    private String courseid;

    private String subcourseid;

    private String orderid;

    private String grade;

    private Integer totalscore;

    private Integer factscore;

    private Integer avgscore;

    private Integer attitudescore;

    private Integer qualityscore;

    private String advise;

    private Date firetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid == null ? null : courseid.trim();
    }

    public String getSubcourseid() {
        return subcourseid;
    }

    public void setSubcourseid(String subcourseid) {
        this.subcourseid = subcourseid == null ? null : subcourseid.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Integer getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public Integer getFactscore() {
        return factscore;
    }

    public void setFactscore(Integer factscore) {
        this.factscore = factscore;
    }

    public Integer getAvgscore() {
        return avgscore;
    }

    public void setAvgscore(Integer avgscore) {
        this.avgscore = avgscore;
    }

    public Integer getAttitudescore() {
        return attitudescore;
    }

    public void setAttitudescore(Integer attitudescore) {
        this.attitudescore = attitudescore;
    }

    public Integer getQualityscore() {
        return qualityscore;
    }

    public void setQualityscore(Integer qualityscore) {
        this.qualityscore = qualityscore;
    }

    public String getAdvise() {
        return advise;
    }

    public void setAdvise(String advise) {
        this.advise = advise == null ? null : advise.trim();
    }

    public Date getFiretime() {
        return firetime;
    }

    public void setFiretime(Date firetime) {
        this.firetime = firetime;
    }
}