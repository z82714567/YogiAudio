package com.project3.yogiaudio.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpFormDTO {
	
	private long id;
	private String name;
	private String nickname;
	private String email;
	private String password;
	private String status;
}
