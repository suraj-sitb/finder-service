package com.findSimran.userLoginService.command.model;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserCommand {

	@TargetAggregateIdentifier
	private final String userId;
	private final String userName;
	private final String mailId;
	private final String password;
}
