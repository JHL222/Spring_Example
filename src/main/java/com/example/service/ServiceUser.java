package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.dto.DTOUser;
import com.example.entity.EntityUser;
import com.example.repository.UserRepository;

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
}
