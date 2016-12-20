package com.edu.core.domain;

import java.util.Date;
import java.util.List;

public class Teacher {
    private String id;

    private String accounts;

    private String password;

    private String nickname;

    private String realname;

    private String pichead;

    private String sex;

    private String nation;

    private Date birthday;

    private String idnumber;

    private String address;

    private String degrees;

    private String grade;

    private String school;

    private Integer seniority;

    private String cv;

    private String feature;

    private String subject;

    private String mobileother;

    private String mobile;

    private String email;

    private String picidcard;

    private String picidcard2;

    private String piceducation;

    private String picteacher;

    private String picother;

    private String stype;

    private String pstate;

    private Date registertime;

    private String showtop;

    private String published;

    private Integer recstate;
    
    private StatOrder statorder;
    
    private List<TeachExperience> list;

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

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees == null ? null : degrees.trim();
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

    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv == null ? null : cv.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
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

    public String getPicidcard() {
        return picidcard;
    }

    public void setPicidcard(String picidcard) {
        this.picidcard = picidcard == null ? null : picidcard.trim();
    }

    public String getPicidcard2() {
        return picidcard2;
    }

    public void setPicidcard2(String picidcard2) {
        this.picidcard2 = picidcard2 == null ? null : picidcard2.trim();
    }

    public String getPiceducation() {
        return piceducation;
    }

    public void setPiceducation(String piceducation) {
        this.piceducation = piceducation == null ? null : piceducation.trim();
    }

    public String getPicteacher() {
        return picteacher;
    }

    public void setPicteacher(String picteacher) {
        this.picteacher = picteacher == null ? null : picteacher.trim();
    }

    public String getPicother() {
        return picother;
    }

    public void setPicother(String picother) {
        this.picother = picother == null ? null : picother.trim();
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

    public String getShowtop() {
        return showtop;
    }

    public void setShowtop(String showtop) {
        this.showtop = showtop == null ? null : showtop.trim();
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published == null ? null : published.trim();
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
    
    public List<TeachExperience> getExperience() {
        return list;
    }

    public void setExperience(List<TeachExperience> list) {
        this.list = list == null ? null : list;
    }
}