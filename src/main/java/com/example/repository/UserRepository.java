package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.EntityUser;

public interface UserRepository extends CrudRepository<EntityUser, Integer> {
	@Query("SELECT u FROM EntityUser u WHERE u.username = :userName")
	List<EntityUser> findUserData(String userName);
	
	@Query(value = "select * from EntityUser where username = :userName and userpass = :userPass",
			nativeQuery = true)
	List<EntityUser> findDataQuery(String userName, String userPass);
	
	@Query(value = "select * from EntityUser where username = :userName and userpass = :userPass",
			nativeQuery = true)
	List<EntityUser> loginQuery(String userName, String userPass);
	
	List<EntityUser> findByUsernameAndUserpass(String userName, String userPass);
	

	@Modifying
	@Transactional
	@Query(value = "UPDATE EntityUser SET userpass = :newPass WHERE username  = :userName AND userpass = :userPass")
	int updateUserPass(String userName, String userPass, String newPass);


//	@Modifying
//	@Transactional
//	@Query(value = "DELETE FROM EntityUser WHERE username = :userName AND userpass = :userPass")
//	int deleteUser(String userName, String userPass);
}
