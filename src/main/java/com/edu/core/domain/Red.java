package com.edu.core.domain;

public class Red {
    private Integer id;

    private String userid;

    private Float price;

    private Integer rstatus;

    private String redname;

    private String contion;

    private String edate;

    private String redtype;

    private String sdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRstatus() {
        return rstatus;
    }

    public void setRstatus(Integer rstatus) {
        this.rstatus = rstatus;
    }

    public String getRedname() {
        return redname;
    }

    public void setRedname(String redname) {
        this.redname = redname == null ? null : redname.trim();
    }

    public String getContion() {
        return contion;
    }

    public void setContion(String contion) {
        this.contion = contion == null ? null : contion.trim();
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate == null ? null : edate.trim();
    }

    public String getRedtype() {
        return redtype;
    }

    public void setRedtype(String redtype) {
        this.redtype = redtype == null ? null : redtype.trim();
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate == null ? null : sdate.trim();
    }
}