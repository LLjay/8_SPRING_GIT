package com.kh.spring.member.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // 의존성을 주입해야 하는 필드를 생성자로 만들어주는 어노테이션
@Service // Component 보다 더 구체화 해서 Service bean으로 등록하는 것
public class MemberServiceImpl implements MemberService{

//	@Autowired // 이걸 bean에 등록 하겠다는 선언
//	private SqlSessionTemplate sqlSession; // 기존의 myBatis SqlSession 객체 대체
//	
//	@Autowired
//	private MemberDao memberDao;
	
	private final SqlSessionTemplate sqlSession;
	private final MemberDao memberDao;
	
	@Override
	public Member loginMember(Member m) {
//		Map처럼 잠깐 쓰고 버릴 것들은 유저가 써도 된다?
		// SqlSessionTemplate bean 등록 후 @Autowired 했음
		// 스프링이 사용 후 자동으로 반납해주기 때문에 close 메소드로 자원 반납할 필요 없음
		// (서비스 로직 없다면) 한 줄로 기술 가능
		
		return memberDao.loginMember(sqlSession, m);
	}

	@Override
	public int idCheck(String checkId) {
		return memberDao.idCheck(sqlSession, checkId);
	}

	@Override
	public int insertMember(Member m) {
		int result = memberDao.insertMember(sqlSession, m);
		return result;
//		sqlSession 객체는 앞에서 Autowired 어노테이션 붙여서 스프링이 만들게 세팅 해줬으니까
	}

	@Override
	public int updateMember(Member m) {
		int result = memberDao.updateMember(sqlSession, m);
		return result;
	}

	@Override
	public int deleteMember(String userId) {
		int result = memberDao.deleteMember(sqlSession, userId);
		return 0;
	}

}
