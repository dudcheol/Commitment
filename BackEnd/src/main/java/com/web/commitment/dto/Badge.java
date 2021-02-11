package com.web.commitment.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Badge")
public class Badge {
	@Id
	@Column(name = "user_email")
	String userEmail;

	@Column(name = "total_commit")
	int totalCommit;
	
	@Column(name = "first_commit")
	int firstCommit;
	
	@Column(name = "num_following")
	int numFollowing;
	
	@Column(name = "num_follwer")
	int numFollower;
	
	@Column(name = "first_ranking")
	int firstRanking;
	
	@Column(name = "second_ranking")
	int secondRanking;
	
	@Column(name = "third_ranking")
	int thirdRanking;
	
	
	@Column(name = "dokdo")
	int dokdo;
	
	@Column(name = "seoul")
	int seoul;
	
	@Column(name = "gangwondo")
	int gangwondo;
	
	@Column(name = "gwanju")
	int gwanju;
	
	@Column(name = "gyeonggido")
	int gyeonggido;
	
	@Column(name = "ulsan")
	int ulsan;

}
