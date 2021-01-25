package com.web.commitment.dto;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
	private Date createdAt;
	
	@Column(name="open")
	private int open;
	
	@Column(name="region_name")
	private String name;
	
	@Column(name="national_x")
	private int nationalX;
	
	@Column(name="national_y")
	private int nationalY;
	
	@Column(name="local_x")
	private int localX;
	
	@Column(name="local_y")
	private int localY;
	
	@OneToOne
	@JoinColumn(name = "id", referencedColumnName = "commit_id")
	private Board board;
	
//	// commit N : 1 User
//	@ManyToOne
//	private User user;
}
