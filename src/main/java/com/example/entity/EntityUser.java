package com.example.entity;

import jakarta.persistence.*;

@Entity
public class EntityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    String username;

    @Column
    String userpass;

    public EntityUser(){
        this.id = -1;
        this.username = "";
        this.userpass = "";
    }
    public EntityUser(int id , String UserName , String UserPass) {
        super();
        this.id = id;
        this.username = UserName;
        this.userpass = UserPass;
    }

    @Override
    public String toString() {
        return "EntityUser{" +
                "UserName='" + username + '\'' +
                ", id=" + id +
                ", UserPass='" + userpass + '\'' +
                '}';
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_Name() {
		return username;
	}
	public void setUser_Name(String UserName) {
		username = UserName;
	}
	public String getUser_Pass() {
		return userpass;
	}
	public void setUser_Pass(String UserPass) {
		userpass = UserPass;
	}
}
