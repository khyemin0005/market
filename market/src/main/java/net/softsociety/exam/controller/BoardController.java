package net.softsociety.exam.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;
import net.softsociety.exam.service.BoardService;

/**
 * 상품게시판 관련 콘트롤러
 */
@Slf4j
@RequestMapping("board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping("list")
	public String list(Model model) {
		ArrayList<Board> boardList = service.selectAll();

		log.debug("BoardList: {}", boardList);
		
		model.addAttribute("boardList", boardList);
		
		return "/boardView/list";
	}
	
	@GetMapping("write")
	public String write() {
		return "/boardView/writeForm";
	}
	
	@PostMapping("write")
	public String write(
			Board board
			, @AuthenticationPrincipal UserDetails user) {
		
		String id = user.getUsername();
		board.setMemberid(id);
		
		log.debug("Board: {}", board);
		
		service.insertBoard(board);
		
		return "redirect:list";
	}
	
	@GetMapping("read")
	public String read(Model model
			, @RequestParam(name="boardnum", defaultValue="0") int boardnum) {
		log.debug("Boardnum: {}", boardnum);
		
		Board board = service.selectOne(boardnum);
		log.debug("Board: {}", board);
		
		if(boardnum == 0) {
			return "redirect:/";
		}
		
		ArrayList<Reply> replyList = service.selectReply(boardnum);
		
		model.addAttribute("board", board);
		model.addAttribute("replyList", replyList);
		
		return "/boardView/read";
	}
	
	@GetMapping("delete")
	public String delete(
			int boardnum
			, @AuthenticationPrincipal UserDetails user) {
		
		Board board = service.selectOne(boardnum);
		log.debug("Delete board: {}", board);
		
		if(board == null) {
			return "redirect:/";
		}
		
		service.deleteBoard(board);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("buyBoard")
	public String buyBoard(
			Board board
			, @AuthenticationPrincipal UserDetails user) {
		
		log.debug("Boardnum : {}", board.getBoardnum());
		log.debug("구매자(로그인한 아이디) : {}", user.getUsername());
		
		board.setSoldout("Y");
		board.setBuyerid(user.getUsername());
		
		log.debug("Board : {}", board);
		
		service.buyBoard(board);
		
		return "redirect:/board/list";
	}

	@PostMapping("writeReply")
	public String writeReply(
			Reply reply
			, @AuthenticationPrincipal UserDetails user) {
		
		String id = user.getUsername();
		reply.setMemberid(id);
		
		log.debug("Reply: {}", reply);
		service.insertReply(reply);
		
		return "redirect:/board/read?boardnum=" + reply.getBoardnum();
	}
	
	@GetMapping("search")
	public String search(Model model) {
		
		ArrayList<Board> boardList = service.selectAll();
		
		/* log.debug("BoardList: {}", boardList); */
		
		model.addAttribute("boardList", boardList);
		
		return "/boardView/search";
	}
	
	@ResponseBody
	@GetMapping("boardList")
	public ArrayList<Board> boardList(Board board, String category, String searchWord) {
		
		log.debug("Category: {}, SearchWord: {}", category, searchWord);
		
		ArrayList<Board> boardList = service.selectSearch(category, searchWord);

		log.debug("list: {}", boardList);
		
		return boardList;
	}
	
}
