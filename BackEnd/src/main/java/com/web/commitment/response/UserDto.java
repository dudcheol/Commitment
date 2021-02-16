package com.web.commitment.response;

import com.web.commitment.dto.Profile;
import com.web.commitment.dto.User;

import lombok.Data;

@Data
public class UserDto {
	private String email;
	
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
	
}
