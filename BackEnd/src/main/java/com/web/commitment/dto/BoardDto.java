//package com.web.commitment.dto;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonInclude;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonIgnoreProperties
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class BoardDto {
//	
//	private String id;
//	
//	private String commitId;
//	
//	private String email;
//
//	private String title;
//
//	private String content;
//
//	private LocalDateTime createdAt;
//
//	private String location;
//	
//	private List<Image> image = new ArrayList<Image>();
//	
//	private Commit commit;
//	
//	private User user;
//	
//	private List<Tag> tag;
//	
//	private List<Comment> comment;
//	
//	private List<Like> like;
//	
//	public BoardDto(Board b) {
//		this.id = b.getId();
//		this.commitId =  b.getCommitId();
//		this.email =  b.getEmail();
//		this.title =  b.getTitle();
//		this.content =  b.getContent();
//		this.createdAt =  b.getCreatedAt();
//		this.location =  b.getLocation();
//		this.image =  b.getImage();
//		this.commit =  b.getCommit();
//		this.user =  b.getUser();
//		this.tag = b.getTag();
//		this.comment = b.getComment();
//		this.like = b.getLike();
//	}
//	public void setLike(Like like) {
//		this.like.add(like);
//		if(like.getBoardDto()!=this) {
//			like.setBoardDto(this);
//		}
//	}
//}
