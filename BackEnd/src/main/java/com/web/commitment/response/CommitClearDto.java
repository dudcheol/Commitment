package com.web.commitment.response;

import lombok.Data;

@Data
public class CommitClearDto {

	private String id;

	private String email;

	private String lat;

	private String lng;

	private String createdAt;

	private int open;

	private String region;

	private String nationalX;

	private String nationalY;

	private String localX;

	private String localY;

	private String address;
}
