package com.web.commitment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="image")
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	
	@Column(name="sns_id")
	private String snsId;
	
	@Column(name="file_name")
	private String fileName;

	@Column(name="file_path")
	private String filePath;
	
	
//	@ManyToOne
//	@JoinColumn(name = "user_email")
//	private Collection<User> user;
}
