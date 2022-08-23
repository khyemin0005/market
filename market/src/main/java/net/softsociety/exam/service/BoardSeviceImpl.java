package net.softsociety.exam.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.softsociety.exam.dao.BoardDAO;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;


@Transactional
@Service
public class BoardSeviceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	//Board
	@Override
	public int insertBoard(Board board) {
		int result = boardDAO.insertBoard(board);
		return result;
	}

	@Override
	public ArrayList<Board> selectAll() {
		ArrayList<Board> boardList = boardDAO.selectAll();
		return boardList;
	}

	@Override
	public Board selectOne(int boardnum) {
		Board board = boardDAO.selectOne(boardnum);
		return board;
	}

	@Override
	public int deleteBoard(Board board) {
		int result = boardDAO.deleteBoard(board);
		return result;
	}

	@Override
	public int buyBoard(Board board) {
		int result = boardDAO.updateBoard(board);
		return result;
	}

	@Override
	public ArrayList<Board> selectSearch(String category, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("searchWord", searchWord);
		
		ArrayList<Board> boardList = boardDAO.selectSearch(map);
		return boardList;
	}

	
	// Reply
	@Override
	public int insertReply(Reply reply) {
		int result = boardDAO.insertReply(reply);
		return result;
	}

	@Override
	public ArrayList<Reply> selectReply(int boardnum) {
		ArrayList<Reply> replyList = boardDAO.selectReply(boardnum);
		return replyList;
	}


}
