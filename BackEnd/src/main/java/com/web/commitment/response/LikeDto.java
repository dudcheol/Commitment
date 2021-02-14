package com.web.commitment.response;

import com.web.commitment.dto.Like;

import lombok.Data;

@Data
public class LikeDto {
	private String id;

	private String email; // 좋아요 누른 사람의 이메일

	private int snsId;
	
	private String createdAt;
	
	private BoardDto board;

}
