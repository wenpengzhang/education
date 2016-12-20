package com.edu.core.domain;

import java.util.Date;

public class ViewOrder {
    private String id;

    private String studentid;

    private String courseid;

    private String teacherid;

    private String ordercode;

    private String studentname;

    private String studentmobile;

    private String coursename;

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

    private Long summoney;

    private String paymode;

    private Date ordertime;

    private Integer progress;

    private Integer sumprogress;

    private String stype;

    private String pstate;
    
    private String paystate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname == null ? null : studentname.trim();
    }

    public String getStudentmobile() {
        return studentmobile;
    }

    public void setStudentmobile(String studentmobile) {
        this.studentmobile = studentmobile == null ? null : studentmobile.trim();
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename == null ? null : coursename.trim();
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

    public Long getSummoney() {
        return summoney;
    }

    public void setSummoney(Long summoney) {
        this.summoney = summoney;
    }

    public String getPaymode() {
        return paymode;
    }

    public void setPaymode(String paymode) {
        this.paymode = paymode == null ? null : paymode.trim();
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getSumprogress() {
        return sumprogress;
    }

    public void setSumprogress(Integer sumprogress) {
        this.sumprogress = sumprogress;
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

	public String getPaystate() {
		return paystate;
	}

	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
    
}