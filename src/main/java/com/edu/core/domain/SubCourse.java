package com.edu.core.domain;

import java.util.Date;

public class SubCourse {
    private String id;

    private String courseid;

    private String teacherid;

    private Date datestart;

    private Date dateend;

    private String stratdate;

    private String enddate;

    private String subname;

    private String lecturer;

    private String subtype;

    private Long subprice;

    private String substate;

    private String liveurl;

    private String chatname;
    
    private String recordurl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    public String getStratdate() {
        return stratdate;
    }

    public void setStratdate(String stratdate) {
        this.stratdate = stratdate == null ? null : stratdate.trim();
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate == null ? null : enddate.trim();
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname == null ? null : subname.trim();
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer == null ? null : lecturer.trim();
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype == null ? null : subtype.trim();
    }

    public Long getSubprice() {
        return subprice;
    }

    public void setSubprice(Long subprice) {
        this.subprice = subprice;
    }

    public String getSubstate() {
        return substate;
    }

    public void setSubstate(String substate) {
        this.substate = substate == null ? null : substate.trim();
    }

    public String getLiveurl() {
        return liveurl;
    }

    public void setLiveurl(String liveurl) {
        this.liveurl = liveurl == null ? null : liveurl.trim();
    }

    public String getChatname() {
        return chatname;
    }

    public void setChatname(String chatname) {
        this.chatname = chatname == null ? null : chatname.trim();
    }

	public String getRecordurl() {
		return recordurl;
	}

	public void setRecordurl(String recordurl) {
		this.recordurl = recordurl;
	}
    
}