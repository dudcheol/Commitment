package com.web.commitment.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Rank {
	String email;
	int rank;
	public Rank(String email, int rank) {
		super();
		this.email = email;
		this.rank = rank;
	}
	
	
}
