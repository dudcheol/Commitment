package com.web.commitment.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
public class BoardDto {
	private String id;

	private String commitId;

	private String email;

	private String title;

	private String content;

	private LocalDateTime createdAt;

	private String location;
	
	private Commit commit;
	
	private UserDto user;

	private List<Tag> tag;

	private List<Comment> comment;

	private List<Like> like;
}
