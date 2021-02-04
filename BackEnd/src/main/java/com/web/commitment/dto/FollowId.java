package com.web.commitment.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Follow 복합키 만들때 생성
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FollowId implements Serializable{
	@Column(name = "follow_from")
	private String fromUser;
	@Column(name = "follow_to")
	private String toUser;
}