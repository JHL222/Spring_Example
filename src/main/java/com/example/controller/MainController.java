package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.DTOBoard;
import com.example.dto.DTOUser;
import com.example.entity.EntityBoard;
import com.example.repository.BoardRepository;
import com.example.repository.UserRepository;
import com.example.service.ServiceBoard;
import com.example.service.ServiceUser;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BoardRepository boardRepo;

	@Autowired
	ServiceUser _serviceUser;
	
	@Autowired
	ServiceBoard _serviceBoard;
	
	@GetMapping("/Home")
	public String loginHome(Model m, HttpSession session) {
		if (session.getAttribute("LoginOK") == "LoginOK") {
			m.addAttribute("tableList", _serviceBoard.getAllBoard());
			return "Board";
		}
		return "Login";
	}
	
	@GetMapping("/MakeDummy")
	public String makeDummy() {

		for(int i = 0; i < 10; i++) {
			DTOUser user = new DTOUser();
			user.User_Pass = String.valueOf(i);
			user.User_Name = String.valueOf(i);
			_serviceUser.Join(user);
		}
		return "Login";
	}
	
//	Query문으로 처리
//	@PostMapping("/update")
//	public String update(DTOUser user, HttpSession session,Model m, String newPass) {
//	    _serviceUser.Update(user, newPass);
//	    m.addAttribute("tableList", _serviceUser.getAllUser());
//	    return "Mypage";
//	}
	
	//userRepo.save로 처리
	@PostMapping("/update")
	public String update(DTOUser user, Model m, String newPass, HttpSession session) {
	    _serviceUser.RePass(user.User_Name, user.User_Pass, newPass);
	    m.addAttribute("tableList", _serviceUser.getAllUser());
	    session.removeAttribute("LoginOK");
	    return "Mypage";
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("LoginOK");
		return "Login";
	}
	
	@GetMapping("/delete/{id}/{pass}")
	public String delete(@PathVariable String id, @PathVariable String pass, Model m, HttpSession session) {
		_serviceUser.Delete(id, pass);
		 session.removeAttribute("LoginOK");
		return "Login";
	}
	
//	@PostMapping("/delete")
//	public String delete(DTOUser user, Model m, HttpSession session) {
//		_serviceUser.Delete(user.User_Name, user.User_Pass);
//		 session.removeAttribute("LoginOK");
//		return "Login";
//	}
	
	@PostMapping("/register")
	public String register(DTOUser user) {

		_serviceUser.Join(user);
		return "Login";
	}

	@PostMapping("/login")
	public String userLogin(DTOUser user, Model m, HttpSession session) {

		if (session.getAttribute("LoginOK") == "LoginOK") {
			m.addAttribute("tableList", _serviceBoard.getAllBoard());
			return "redirect:/Home";
		}else {
			boolean b = _serviceUser.login(user);
		
			if (b) {
				session.setAttribute("LoginOK", "LoginOK");
				session.setAttribute("userDTO", user);
				m.addAttribute("tableList", _serviceBoard.getAllBoard());
				return "redirect:/Home";
			} else
				return "Login";
		}
	}
	
	@GetMapping("/board")
	public String boardHome(DTOBoard board, Model m, HttpSession session) {
		session.setAttribute("boardDTO", board);
		m.addAttribute("tableList", _serviceBoard.getAllBoard());
		return "Home";
	}
	

	
	@PostMapping("/write")
	public String write(DTOBoard board, Model m, HttpSession session) {
	    DTOUser user = (DTOUser) session.getAttribute("userDTO");
	    if (user != null) {
	        board.setAuthor(user.getUser_Name());
	        _serviceBoard.Write(board);
	        
	        session.setAttribute("boardDTO", board);
	        m.addAttribute("tableList", _serviceBoard.getAllBoard());
	        return "redirect:/Home";
	    } else {
	        return "Login";
	    }
	}
	
	@GetMapping("/detail/{id}")
	public String boardDetail(@PathVariable int id, Model m, HttpSession session) {
	    Optional<EntityBoard> optionalBoard = boardRepo.findByBoardId(id);
	    if (optionalBoard.isPresent()) {
	        EntityBoard board = optionalBoard.get();
	        m.addAttribute("board", board);
	        if (session.getAttribute("LoginOK") == "LoginOK") {
	        	return "Detail";
	        }
	        return "NotAuthor";
	    } else {
	        return "Board";
	    }
	}

	
	@PostMapping("/save/{id}")
	public String handleEditForm(@PathVariable int id, Model m, String newTitle, String newContent) {
		_serviceBoard.Edit(id, newTitle, newContent);
	    return "redirect:/Home";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable int id, Model model) {
	    Optional<EntityBoard> boardOptional = _serviceBoard.getBoard(id);
	    if (boardOptional.isPresent()) {
	        EntityBoard board = boardOptional.get();
	        model.addAttribute("board", board);
	        return "edit";
	    } else {
	        return "error"; 
	    }
	}
	
	@GetMapping("deleteBoard/{id}")
	public String deleteBoardForm(@PathVariable int id) {
		_serviceBoard.Delete(id);
		
		return "redirect:/Home";
	}



//	@PostMapping("/dtoTest")
//	@ResponseBody
//	public String DTOTest(DTOUser user) {
//		System.out.println(user.User_Name);
//		System.out.println(user.User_Pass);
//		userRepo.save(user.ToEntity());
//		return user.toString();
//	}
//	
//	@GetMapping("/dtoDeleteTest/{id}")
//	@ResponseBody
//	public String DTODeleteTest(@PathVariable int id) {
//		userRepo.deleteById(id);
//		return "";
//	}
//	
//	@GetMapping("/hello")
//	public String Hello(Model m) {
//		m.addAttribute("value", "Hello");
//		return "template";
//	}
//	
////	
//	@GetMapping("/getTest/{id}")
//	@ResponseBody
//	public String GetTest(@PathVariable int id) {
//		return String.valueOf(id);
//	}
////	
//	@PostMapping("/postTest")
//	@ResponseBody
////	@RequestBody : 서버에 요청
//	public String PostTest(@RequestBody String param) {
//		return param;
//	}
//	
//	@GetMapping("/dtoGetTest/{name}")
//	@ResponseBody
//	public String DTOGetTest(@PathVariable String name, String pass) {
//		List<EntityUser> list = userRepo.findDataQuery(name, pass);
//		if(list.size() == 0)
//			return "no user";
//		else
//			return list.get(0).getUser_Name();
//		
//	}

//	    
//		
//	}
//	
//	    
//	@GetMapping("/example/{id}")
//	public ResponseEntity<String> getExampleById(@PathVariable Long id){
//		if(id == 1) {
//			return ResponseEntity.ok("Example with ID 1 found");
//		}else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Example not found");
//		}
//	}

}
