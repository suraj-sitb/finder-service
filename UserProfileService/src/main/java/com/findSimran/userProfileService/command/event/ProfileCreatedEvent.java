package com.findSimran.userProfileService.command.event;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCreatedEvent {

	private String profileId;
	private String userId;
	private String firstName;
	private String lastName;
	private String summary;
	private Integer yearsOfExperience;
	private String techStack;
	private List<String> skills;
}
