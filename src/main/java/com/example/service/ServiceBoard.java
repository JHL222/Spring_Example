package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.DTOBoard;
import com.example.entity.EntityBoard;
import com.example.repository.BoardRepository;

@Service
public class ServiceBoard {
	
	@Autowired
	BoardRepository boardRepo;
	
	
	public Iterable<EntityBoard> getAllBoard() {
		
		return boardRepo.findAll();
	}
	
	
	public void Write(DTOBoard board) {
		System.out.println(board.Title);
		System.out.println(board.Content);
		System.out.println(board.Author);
		System.out.println(board.Date);
		
		boardRepo.save(board.ToEntity());
	}
	
	
	public void Edit(int id, String newTitle, String newContent) {
	    Optional<EntityBoard> optionalBoard = boardRepo.findByBoardId(id);
	    
	    if (optionalBoard.isPresent()) {
	        EntityBoard board = optionalBoard.get();
	        board.setTitle(newTitle);
	        board.setContent(newContent);
	        boardRepo.save(board);
	    }
	}

	public Optional<EntityBoard> getBoard(int id) {
	    return boardRepo.findByBoardId(id);
	}
	
	public void Delete(int id) {
		Optional<EntityBoard> deleteBoard = boardRepo.findByBoardId(id);
		
		if (deleteBoard.isPresent()) {
	        EntityBoard board = deleteBoard.get();
	        boardRepo.delete(board);
	    }
	}
	
}
