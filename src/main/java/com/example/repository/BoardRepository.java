package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.EntityBoard;
import com.example.entity.EntityUser;

public interface BoardRepository extends CrudRepository<EntityBoard, Integer> {
	@Query(value = "select * from Entity_Board",
			nativeQuery = true)
	List<EntityBoard> findBoardData();
	
	
	@Query(value = "select * from Entity_Board where id = :id ",
			nativeQuery = true)
	Optional<EntityBoard> findByBoardId(int id);
	List<EntityBoard> findById(int id);
}
