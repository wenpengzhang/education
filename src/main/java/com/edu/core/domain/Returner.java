package com.edu.core.domain;

public class Returner {

	public Returner() {
		// TODO Auto-generated constructor stub
	}
	
	public Returner(String code,boolean success,String message) {
		// TODO Auto-generated constructor stub
		 this.code=code;
		 this.success = success;
		 this.message = message;
		 
	}
	
	private String code;

    private boolean success;

    private String message;
    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
    
    
    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

}
