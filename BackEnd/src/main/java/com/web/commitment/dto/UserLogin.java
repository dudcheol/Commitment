package com.web.commitment.dto;

import lombok.Data;

@Data
public class UserLogin {
	private String email;
	private String pass;
	private String nickname;
	private String tel;
	private String age;
	private String gender;
	private String mystory;
	private String lat;
	private String lng;
	private String badge;
	private String authkey;
	private String auth;
	private String region_name;
	private Profile profile;
	
	public UserLogin(User user) {
		super();
		this.email = user.getEmail();
		this.pass = user.getPass();
		this.nickname = user.getNickname();
		this.tel = user.getTel();
		this.age = user.getAge();
		this.gender = user.getGender();
		this.mystory = user.getMystory();
		this.lat = user.getLat();
		this.lng = user.getLng();
		this.badge = user.getBadge();
		this.authkey = user.getAuthkey();
		this.auth = user.getAuth();
		this.region_name = user.getRegion_name();
		this.profile=user.getProfile();
	}
	
	
}
