package com.web.commitment.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@AllArgsConstructor
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
	
	@OneToMany(mappedBy = "snsId")
	private List<Image> image = new ArrayList<Image>();
	
	@ManyToOne
	@JoinColumn(name = "commit_id",insertable=false, updatable=false)
	private Commit commit;
	
	@ManyToOne
	@JoinColumn(name = "user_email",insertable=false, updatable=false)
	private User user;
	
	@OneToMany(mappedBy = "snsId")
	private List<Tag> tag;
	
	@OneToMany(mappedBy = "snsId")
	private List<Comment> comment;
	
	@OneToMany(mappedBy = "snsId")
	private List<Like> like;

	@Override
	public String toString() {
		return "Board [id=" + id + ", commitId=" + commitId + ", email=" + email + ", title=" + title + ", content="
				+ content + ", createdAt=" + createdAt + ", location=" + location + ", image=" + image + ", commit="
				+ commit + ", user=" + user + ", tag=" + tag + ", comment=" + comment + ", like=" + like + "]";
	}
}
