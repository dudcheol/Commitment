package com.web.commitment.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="commit")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Commit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	// commit N : 1 User
	@ManyToOne
//	@Column(name="user_email")
	private User user;
	
//	@Column(name="user_email")
//	private String email;
	
	@Column(name="lat")
	private String lat;
	
	@Column(name="lng")
	private String lng;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="open")
	private int open;
}
