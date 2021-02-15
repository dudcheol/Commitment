package com.web.commitment.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	// 댓글 쓴 사람
	@Column(name="user_email")
	private String email;
	
	// 원글 번호, 답변을 단 원래 글(부모글)의 번호
	@Column(name="sns_id")
	private String snsId;
	
	@Column(name="content")
	private String content;
	
	// 댓글의 깊이
	@Column(name="depth")
	private int depth;

	// 원글에 대한 답글인지, 답글에 대한 답글인지 구분
	@Column(name="parent")
	private String parent;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_email", insertable=false, updatable=false)
	private User user;
}
