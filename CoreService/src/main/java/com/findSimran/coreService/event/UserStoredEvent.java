package com.findSimran.coreService.event;

import java.util.List;

import lombok.Data;

@Data
public class UserStoredEvent {

	private String storeId;
	private String profileId;
	private String firstName;
	private Integer yearsOfExperience;
	private String techStack;
	private List<String> skills;
}
