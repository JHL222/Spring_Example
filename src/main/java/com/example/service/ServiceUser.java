package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.DTOUser;
import com.example.entity.EntityUser;
import com.example.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class ServiceUser {
		
	@Autowired
	UserRepository userRepo;
	public void Join(DTOUser user) {
			System.out.println(user.User_Name);
			System.out.println(user.User_Pass);
			
			userRepo.save(user.ToEntity());
	}
	
	public boolean login(DTOUser user) {
		System.out.println(user.User_Name);
		System.out.println(user.User_Pass);
		
		List<EntityUser> list = userRepo.findByUsernameAndUserpass(user.User_Name, user.User_Pass);
		
		if(list.size() == 0) {
			return false;
	    } else {
	    	
	        return true;
	    }
	}
	
	public Iterable<EntityUser> getAllUser() {
		
		return userRepo.findAll();
	}
	
	public void Update(DTOUser user, String newPass) {
	    System.out.println(user.User_Name);
	    System.out.println(user.User_Pass);
	    System.out.println(newPass);
	    
	    userRepo.updateUserPass(user.User_Name, user.User_Pass, newPass);
	}
	
	public void RePass(String id, String pass, String newPass) {
		List<EntityUser> list = userRepo.findByUsernameAndUserpass(id, pass);
		
		if(list.size() > 0) {
			list.get(0).setUser_Pass(newPass);
			userRepo.save(list.get(0));
		}
	}
	
	public void Delete(String id, String pass) {
		List<EntityUser> list = userRepo.findByUsernameAndUserpass(id, pass);
		
		if(list.size() > 0) {
			userRepo.delete(list.get(0));

		}
	}
	

}
