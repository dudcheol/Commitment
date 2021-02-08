package com.web.commitment.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//Follow 복합키 만들때 생성

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class FollowId implements Serializable{

    @Column(name = "follow_from")
    private String fromUser;
    
    @Column(name = "follow_to")
    private String toUser;
    
    @Column(name="id")
    private String id;
    
    public FollowId(String fromUser, String toUser){
    	this.fromUser = fromUser;
    	this.toUser = toUser;
    }
    
//	@ManyToOne
//	@JoinColumn(name = "fromUser",insertable=false, updatable=false)
//	private User user;
}