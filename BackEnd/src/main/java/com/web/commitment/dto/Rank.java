package com.web.commitment.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.amazonaws.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Rank {
	@Transient
	String email;
	@Transient
	int rank;
	
	public Rank() {}
	
	public Rank(String email, int rank) {
		this.email = email;
		this.rank = rank;
	}

}
