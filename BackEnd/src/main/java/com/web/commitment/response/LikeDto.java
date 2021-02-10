package com.web.commitment.response;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.web.commitment.dto.Like;

import lombok.Data;
@Data
public class LikeDto {
	private String email; // 좋아요 누른 사람의 이메일

	public LikeDto(Like like) {
		super();
		this.email = like.getEmail();
	}
	
}
