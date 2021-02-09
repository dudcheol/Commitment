package com.web.commitment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Profile {
	
	@Id
	@Column(name = "user_email")
	private String email;

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "file_name")
	private String fileName;
	

}
