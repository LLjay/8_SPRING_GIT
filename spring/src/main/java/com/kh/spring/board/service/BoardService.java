package com.kh.spring.board.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

public interface BoardService {
	// 게시글 총 개수 가져오기
	int selectListCount();
	
	// 게시글 리스트 조회
	ArrayList<Board> selectList(PageInfo pi);

	// 게시글 조회수 증가
	int increaseCount(int bno);
	
	// 게시글 정보 조회
	Board selectBoard(int bno);
	
	// 댓글 리스트 조회
	ArrayList<Reply> selectReplyList(int bno);
	
	// 게시글 추가(insert)
	int insertBoard(Board b);
	
	// 게시글 수정
	int updateBoard(Board b);
	
	// 댓글 추가
	int insertReply(Reply r);
	
	// top5 게시글 조회
	ArrayList<Board> selectTopBoardList();
}
