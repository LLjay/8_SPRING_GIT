package com.kh.spring.board.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

// 의존성을 낮추기 위해 인터페이스로 구현
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
//	이미 bean에 SqlSessionTemplate을 등록 해놓은 상태, 그걸 여기 가져와서 써달라고 하는 것
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public int selectListCount() {
		return boardDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		return boardDao.selectList(sqlSession, pi);
	}

	@Override
	public int increaseCount(int bno) {
		return boardDao.increaseCount(sqlSession, bno);
	}

	@Override
	public Board selectBoard(int bno) {
		return boardDao.selectBoard(sqlSession, bno);
	}

	@Override
	public ArrayList<Reply> selectReplyList(int bno) {
		return boardDao.selectReplyList(sqlSession, bno);
	}
	
	
}
