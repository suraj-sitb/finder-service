package com.findSimran.userProfileService.request.model;

import java.util.List;

import lombok.Data;

@Data
public class CreateProfileRequest {

	private String firstName;
	private String lastName;
	private String summary;
	private Integer yearsOfExperience;
	private String techStack;
	private List<String> skills;
}
