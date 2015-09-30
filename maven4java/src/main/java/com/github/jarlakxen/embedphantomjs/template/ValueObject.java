package com.github.jarlakxen.embedphantomjs.template;

public class ValueObject {
	private String fname;  
    private String gname;  
    private int age;  
    public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private String email;  
    private String manager;  
    public String getFname() {  
        return fname;  
    }  
    public void setFname(String fname) {  
        this.fname = fname;  
    }  
    public String getGname() {  
        return gname;  
    }  
    public void setGname(String gname) {  
        this.gname = gname;  
    }  
    public String getEmail() {  
        return email;  
    }  
    public void setEmail(String email) {  
        this.email = email;  
    }  
    public String getManager() {  
        return manager;  
    }  
    public void setManager(String manager) {  
        this.manager = manager;  
    }      
}
