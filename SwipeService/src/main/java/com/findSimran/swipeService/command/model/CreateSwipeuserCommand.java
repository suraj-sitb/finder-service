package com.findSimran.swipeService.command.model;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateSwipeuserCommand {

	@TargetAggregateIdentifier
	private String swipeId;
	private String currentUser;
	private String targetUser;
}
