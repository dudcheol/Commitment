package com.web.commitment.request;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.web.commitment.dto.Comment;
import com.web.commitment.dto.Commit;
import com.web.commitment.dto.Image;
import com.web.commitment.dto.Like;
import com.web.commitment.dto.Tag;

import lombok.Data;

@Data
public class BoardTagReqDto {
	private String id;

	private String commitId;

	private String email;

	private String title;

	private String content;

	private LocalDateTime createdAt;

	private String location;
	
	private Tag[] tag;
}
