package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok
 * - 자동 코드 생성 라이브러리
 * - 반복되는 getter setter toString 등의 메소드를 자동으로 생성해줄 수 있는 라이브러리
 * 
 * Lombok 설치 방법
 * 1. 라이브러리 다운로드 후 적용 (pom.xml)
 * 2. 다운로드 된 jar 파일을 찾아 설치 (사용하는 ide에 설치)
 * 3. ide 재실행
 * 
 */

// lombok 설치해야 나오는 클래스
// 어노테이션으로 쓰기만 하면 lombok이 다 자동으로 생성해줌
// 어노테이션의 적용 범위는 이 파일 내
// 멤버 객체 안을 눌러보면 뜸
// 접두 필드명이 한 글자면 가끔 오류 남 ex) uPassword => usPassword로 만들기

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 전체생성자
@Getter
@Setter
@ToString

public class Member {
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
}
