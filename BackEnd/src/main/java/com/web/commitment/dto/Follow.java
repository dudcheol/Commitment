package com.web.commitment.dto;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
	@EmbeddedId
	private FollowId followid;
}


