package com.web.commitment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userbadge")
public class UserBadge {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;
	@Column(name = "badge_name")
	private String name;
	@Column(name = "user_email")
	private String email;
	@Column(name = "created_at")
	private String createdAt;
	
	public UserBadge(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	
}
