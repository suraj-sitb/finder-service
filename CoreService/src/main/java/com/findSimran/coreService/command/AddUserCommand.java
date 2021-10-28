package com.findSimran.coreService.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddUserCommand {

	@TargetAggregateIdentifier
	private String lookupId;
	private String profileId;
	private String firstName;
}
