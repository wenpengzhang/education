package com.edu.core.domain;

import java.util.List;

public class Course {
    private String id;

    private String teacherid;

    private String name;

    private String vstage;

    private String vsubject;

    private String vgrade;

    private String cmode;

    private String firsttype;

    private String sectype;

    private String thirdttype;

    private Long price;

    private Integer ccount;

    private Integer cnumber;

    private String cversion;

    private String area;

    private String timezones;

    private String discription;

    private String pichead;
    
    private String[] piclist;

    private String pstate;

    private String sellstate;

    private Integer subnumber;

    private Integer subcount;

    private String published;

    private String showtop;
    
    private List<SubCourse> subcourse;
    
    private List<Teacher> teachers;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getVstage() {
        return vstage;
    }

    public void setVstage(String vstage) {
        this.vstage = vstage == null ? null : vstage.trim();
    }

    public String getVsubject() {
        return vsubject;
    }

    public void setVsubject(String vsubject) {
        this.vsubject = vsubject == null ? null : vsubject.trim();
    }

    public String getVgrade() {
        return vgrade;
    }

    public void setVgrade(String vgrade) {
        this.vgrade = vgrade == null ? null : vgrade.trim();
    }

    public String getCmode() {
        return cmode;
    }

    public void setCmode(String cmode) {
        this.cmode = cmode == null ? null : cmode.trim();
    }

    public String getFirsttype() {
        return firsttype;
    }

    public void setFirsttype(String firsttype) {
        this.firsttype = firsttype == null ? null : firsttype.trim();
    }

    public String getSectype() {
        return sectype;
    }

    public void setSectype(String sectype) {
        this.sectype = sectype == null ? null : sectype.trim();
    }

    public String getThirdttype() {
        return thirdttype;
    }

    public void setThirdttype(String thirdttype) {
        this.thirdttype = thirdttype == null ? null : thirdttype.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getCcount() {
        return ccount;
    }

    public void setCcount(Integer ccount) {
        this.ccount = ccount;
    }

    public Integer getCnumber() {
        return cnumber;
    }

    public void setCnumber(Integer cnumber) {
        this.cnumber = cnumber;
    }

    public String getCversion() {
        return cversion;
    }

    public void setCversion(String cversion) {
        this.cversion = cversion == null ? null : cversion.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getTimezones() {
        return timezones;
    }

    public void setTimezones(String timezones) {
        this.timezones = timezones == null ? null : timezones.trim();
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription == null ? null : discription.trim();
    }

    public String getPichead() {
        return pichead;
    }

    public void setPichead(String pichead) {
        this.pichead = pichead == null ? null : pichead.trim();
    }
    
    public String[] getPiclist() {
        return piclist;
    }

    public void setPiclist(String[] piclist) {
        this.piclist = piclist == null ? null :piclist;
    }

    public String getPstate() {
        return pstate;
    }

    public void setPstate(String pstate) {
        this.pstate = pstate == null ? null : pstate.trim();
    }

    public String getSellstate() {
        return sellstate;
    }

    public void setSellstate(String sellstate) {
        this.sellstate = sellstate == null ? null : sellstate.trim();
    }

    public Integer getSubnumber() {
        return subnumber;
    }

    public void setSubnumber(Integer subnumber) {
        this.subnumber = subnumber;
    }

    public Integer getSubcount() {
        return subcount;
    }

    public void setSubcount(Integer subcount) {
        this.subcount = subcount;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published == null ? null : published.trim();
    }

    public String getShowtop() {
        return showtop;
    }

    public void setShowtop(String showtop) {
        this.showtop = showtop == null ? null : showtop.trim();
    }
    
    public List<SubCourse> getSubCourse() {
        return subcourse;
    }

    public void setSubCourse(List<SubCourse> list) {
        this.subcourse = list == null ? null : list;
    }
    
    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> list) {
        this.teachers = list == null ? null : list;
    }
}