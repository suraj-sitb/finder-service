package com.findSimran.userLoginService.response.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponse {

	private String userName;
	private String mailId;
}
