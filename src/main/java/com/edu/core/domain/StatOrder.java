package com.edu.core.domain;

public class StatOrder {
	
	private String fid;
	
	private Integer ordercount;
	
	private Long summoney;
	
	private Integer progress;

	private Integer sumprogress;

	public StatOrder() {
		// TODO Auto-generated constructor stub
	}
	
	public String getFid() {
        return fid;
    }

    public void setFid(String id) {
        this.fid = fid == null ? null : id.trim();
    }
	
	public Integer getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(Integer ordercount) {
        this.ordercount = ordercount;
    }

	
	public Long getSummoney() {
	        return summoney;
    }

    public void setSummoney(Long summoney) {
        this.summoney = summoney;
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


}
