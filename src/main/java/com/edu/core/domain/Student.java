package com.edu.core.domain;

import java.util.Date;

public class Student {
    private String id;

    private String accounts;

    private String password;

    private String nickname;

    private String realname;

    private String pichead;

    private String sex;

    private String nation;

    private Date birthday;

    private String address;

    private String grade;

    private String school;

    private String mobileother;

    private String mobile;

    private String email;

    private String stype;

    private String pstate;

    private Date registertime;

    private Integer recstate;
    
    private StatOrder statorder;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts == null ? null : accounts.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPichead() {
        return pichead;
    }

    public void setPichead(String pichead) {
        this.pichead = pichead == null ? null : pichead.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getMobileother() {
        return mobileother;
    }

    public void setMobileother(String mobileother) {
        this.mobileother = mobileother == null ? null : mobileother.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype == null ? null : stype.trim();
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate == null ? null : pstate.trim();
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public Integer getRecstate() {
        return recstate;
    }

    public void setRecstate(Integer recstate) {
        this.recstate = recstate;
    }
    
    public StatOrder getStatOrder() {
        return statorder;
    }

    public void setStatOrder(StatOrder statorder) {
        this.statorder = statorder;
    }
}