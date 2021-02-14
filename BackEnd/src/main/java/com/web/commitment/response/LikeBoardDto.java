package com.web.commitment.response;

import javax.persistence.Column;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.web.commitment.dto.Like;

import lombok.Data;
//board에서 쓰이는 like용
@Data
public class LikeBoardDto {
	private String email; // 좋아요 누른 사람의 이메일
}
