package com.web.commitment.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@ToString
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Commit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@Column(name="user_email")
	private String email;

	@Column(name="lat")
	private String lat;
	
	@Column(name="lng")
	private String lng;
	
	@Column(name="created_at")
	private String createdAt;
	
	@Column(name="open")
	private int open;
	
	@Column(name="region_name")
	private String region;
	
	@Column(name="national_x")
	private String nationalX;
	
	@Column(name="national_y")
	private String nationalY;
	
	@Column(name="local_x")
	private String localX;
	
	@Column(name="local_y")
	private String localY;
	
	@Column(name="address")
	private String address;

//	@OneToMany(mappedBy = "commitId" )
//	private List<Board> board;
	
//	// commit N : 1 User
//	@ManyToOne
//	private User user;
}
