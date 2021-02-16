package com.web.commitment.response;

import java.util.List;

import com.web.commitment.dto.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class FollowCommitMapDto {
	
    public UserDto user;

    public List<int[]> commit;
    
}
