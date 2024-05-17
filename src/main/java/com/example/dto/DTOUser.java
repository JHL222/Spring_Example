package com.example.dto;

import com.example.entity.EntityUser;

public class DTOUser {
	public String User_Name;
	public String User_Pass;
	
	public DTOUser(String user_Name, String user_Pass) {
		super();
		User_Name = user_Name;
		User_Pass = user_Pass;
	}
	
	public DTOUser() {
		
	}

	public String getUser_Name() {
		return User_Name;
	}
	public void setUser_Name(String user_Name) {
		User_Name = user_Name;
	}
	public String getUser_Pass() {
		return User_Pass;
	}
	public void setUser_Pass(String user_Pass) {
		User_Pass = user_Pass;
	}
	
	public EntityUser ToEntity() {
		return new EntityUser(-1, this.User_Name, this.User_Pass);
	}
    @Override
    public String toString() {
        return "DTOUser [User_Name=" + User_Name + ", User_Age=" + User_Pass + "]"; 
    }
}
