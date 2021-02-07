package com.web.commitment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Follow")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Follow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String followid;
	
	@Column(name = "follow_from")
	private String fromUser;
	@Column(name = "follow_to")
	private String toUser;

//	@EmbeddedId
//	private FollowId followid;
}


