package com.findSimran.userProfileService.command.model;

import java.util.List;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProfileCommand {

	@TargetAggregateIdentifier
	private String profileId;
	private String userId;
	private String firstName;
	private String lastName;
	private String summary;
	private Integer yearsOfExperience;
	private String techStack;
	private List<String> skills;
}
