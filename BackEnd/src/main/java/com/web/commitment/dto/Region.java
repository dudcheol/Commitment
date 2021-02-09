package com.web.commitment.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name="region")
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Region {
	@Id
	@Column(name="name")
	String name;

}
