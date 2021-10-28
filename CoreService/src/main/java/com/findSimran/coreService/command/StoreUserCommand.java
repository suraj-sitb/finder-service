package com.findSimran.coreService.command;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreUserCommand {

	@TargetAggregateIdentifier
	private String storeId;
	private String profileId;
	private String firstName;
	private Integer yearsOfExperience;
	private String techStack;
	private List<String> skills;
}
