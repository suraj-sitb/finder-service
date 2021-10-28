package com.findSimran.userLoginService.command.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreatedEvent {

	private final String userId;
	private final String userName;
	private final String mailId;
	private final String password;
}
