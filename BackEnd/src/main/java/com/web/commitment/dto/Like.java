package com.web.commitment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name="save")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Like {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private String id;

	@Column(name="user_email")
	private String email;

	@Column(name="sns_id")
	private int snsId;
	
	@Column(name="writer")
	private String writer;
	
	@ManyToOne
	@JoinColumn(name = "sns_id",insertable=false, updatable=false)
	private Board board;

	@Override
	public String toString() {
		return "Like [id=" + id + ", email=" + email + ", snsId=" + snsId + ", writer=" + writer + ", board=" + board
				+ "]";
	}
	
//	public void setBoardDto(BoardDto boardDto) {
//	this.boardDto=boardDto;
//	if(!boardDto.getLike().contains(this)) {
//		boardDto.setLike(this);
//	}
//}
}
