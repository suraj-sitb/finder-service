package com.findSimran.userLoginService.request.model;

import lombok.Data;

@Data
public class CreateUserRequest {

	private String userName;
	private String mailId;
	private String password;
}
