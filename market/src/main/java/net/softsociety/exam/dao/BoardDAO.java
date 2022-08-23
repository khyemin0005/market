package net.softsociety.exam.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface BoardDAO {

	// Board
	public int insertBoard(Board board);

	public ArrayList<Board> selectAll();

	public Board selectOne(int boardnum);

	public int deleteBoard(Board board);

	public int updateBoard(Board board);

	public ArrayList<Board> selectSearch(HashMap<String, String> map);

	
	// Reply
	public int insertReply(Reply reply);

	public ArrayList<Reply> selectReply(int boardnum);

}
