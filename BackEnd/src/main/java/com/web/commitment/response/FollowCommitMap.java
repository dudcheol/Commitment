package com.web.commitment.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class FollowCommitMap {
	
    public UserDto user;

    public List<int[]> commit;
}
