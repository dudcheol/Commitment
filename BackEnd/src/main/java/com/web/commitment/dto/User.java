package com.web.commitment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@DynamicInsert
@DynamicUpdate
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)--> 자동생성 할떄만 사용
	@Column(name = "email")
	private String email;

//	@JsonIgnore // json 보낼떄 무시(굳이 보낼 필요 없음)
	@Column(name = "pass")
	private String pass;

	@Column(name = "nickname", nullable = false, unique = true)
	private String nickname;

	@Column(name = "tel")
	private String tel;

	@Column(name = "age")
	private String age;

	@Column(name = "gender")
	private String gender;

	@Column(name = "mystory")
	private String mystory;

	@Column(name = "lat")
	private String lat;

	@Column(name = "lng")
	private String lng;

	@Column(name = "badge_name")
	private String badge;

//	@JsonIgnore // json 보낼떄 무시(굳이 보낼 필요 없음)
	@Column(name = "authkey")
	private String authkey;

	@Column(name = "auth")
	private String auth;
	
	@Column(name = "region_name")
	private String region_name;

	@Builder
	public User(String email, String pass, String nickname, String tel, String age, String gender, String mystory) {
		super();
		this.email = email;
		this.pass = pass;
		this.nickname = nickname;
		this.tel = tel;
		this.age = age;
		this.gender = gender;
		this.mystory = mystory;
	}

	@OneToOne
	@JoinColumn(name = "email", referencedColumnName = "user_email")
	private Profile profile;

	// User 1: N Commit
	@OneToMany(mappedBy = "email")
	private List<Commit> commit;
	
}
