package com.web.commitment.request;

import lombok.Data;

@Data
public class LoginRequest {
	private String email;
	private String pass;
	private String name;
	private String image;
	private String token;
}
