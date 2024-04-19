package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.board.service.BoardService;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cpage", defaultValue="1") int currentPage, Model model) {
		int boardCount = boardService.selectListCount();
		
		// 페이지에 해당하는 게시글을 실제로 가져오기 위해 페이지 인포를 넘겨주는 것
		PageInfo pi = Pagination.getPageInfo(boardCount, currentPage, 10, 5);
		ArrayList<Board> list = boardService.selectList(pi);
		
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		return "board/boardListView";
	}
	
	// get, post, foot? detail? 등의 요청을 한 번에 다 받아줌, 근데 나눠서 받는 설정이 가능함
	// get은 보통 가져올 때, post는 보통 데이터를 보낼 때
//	@RequestMapping(value = "detail.bo", method = RequestMethod.POST)
	@RequestMapping("detail.bo")
	public String selectBoard(int bno, Model model) {
		int result = boardService.increaseCount(bno); // 증가 시키는 것만 왜 따로 뺴지?
		// 제어문이 두 개라 트랜잭션이 둘 다 필요한 때 묶어주기, 아니면 서로 분리해서 메소드 만들기
		if(result > 0) {
			Board b = boardService.selectBoard(bno);
			model.addAttribute("b", b);
			
			return "board/boardDetailView";
		} else {
			model.addAttribute("errorMsg", "게시글 조회 실패");
			return "common/errorPage";
		}
	}
	
	@ResponseBody // ?
	@RequestMapping(value = "rlist.bo", produces="application/json; charset-UTF-8")
	// json 형식에 관한 설정은 produces 에 넣어줌
	public String ajaxSelectReplyList(int bno) {
		ArrayList<Reply> list = boardService.selectReplyList(bno);
		
		return new Gson().toJson(list);
	}
	
}