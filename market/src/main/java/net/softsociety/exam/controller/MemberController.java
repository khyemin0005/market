package net.softsociety.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.domain.Member;
import net.softsociety.exam.service.MemberService;

/**
 * 회원 정보 관련 콘트롤러
 */

@Slf4j
@RequestMapping("member")
@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@GetMapping("join")
	public String joinForm() {
		return "/memberView/joinForm";
	}
	
	@PostMapping("join")
	public String join(Member member) {
		log.debug("Member: {}", member);
		
		service.insertMember(member);
		
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String loginForm() {
		return "/memberView/loginForm";
	}
	
}
