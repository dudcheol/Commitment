package com.web.commitment.response;

import java.time.LocalDateTime;


import com.web.commitment.dto.Profile;
import com.web.commitment.dto.User;

import lombok.Data;
@Data
public class CommentCleanDto {
	private String id;

	private String email;

	private String snsId;

	private String content;

	private int depth;

	private String parent;

	private LocalDateTime createdAt;
}
