package com.web.commitment.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "sns")
@NoArgsConstructor
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@Column(name="commit_id")
	private String commitId;
	
	@Column(name="user_email")
	private String email;


	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "location")
	private String location;
	@OneToMany
	@JoinColumn(name="sns_id")
	private List<Image> image = new ArrayList<Image>();
//	@ManyToOne
//	@JoinColumn(name = "user_email")
//	private Collection<User> user;
}
