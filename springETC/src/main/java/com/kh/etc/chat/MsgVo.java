package com.kh.etc.chat;

import lombok.Data;

// 생성자, 게터 세터 등 기본적인 것들 만들어주는 어노테이션
@Data
public class MsgVo {
	private String msg;
	private String nick;
	private String time;
}
