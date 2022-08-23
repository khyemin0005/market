package net.softsociety.exam.service;

import java.util.ArrayList;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

public interface BoardService {

	// Board
	public int insertBoard(Board board);

	public ArrayList<Board> selectAll();

	public Board selectOne(int boardnum);

	public int deleteBoard(Board board);

	public int buyBoard(Board board);

	public ArrayList<Board> selectSearch(String category, String searchWord);

	
	//Reply
	public int insertReply(Reply reply);

	public ArrayList<Reply> selectReply(int boardnum);


}
