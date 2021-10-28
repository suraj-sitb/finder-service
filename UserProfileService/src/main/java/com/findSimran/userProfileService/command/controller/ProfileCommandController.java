package com.findSimran.userProfileService.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findSimran.userProfileService.command.model.CreateProfileCommand;
import com.findSimran.userProfileService.request.model.CreateProfileRequest;

@RestController
@RequestMapping(path = "/users")
public class ProfileCommandController {

	private final CommandGateway commandGateway;
	
	@Autowired
	public ProfileCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@RequestMapping(path = "/profile", method = RequestMethod.POST)
	public ResponseEntity<Object> createProfile(@RequestBody CreateProfileRequest createProfileRequest, 
			@RequestHeader(name = "Authorization") String userId) {
		
		CreateProfileCommand command = CreateProfileCommand.builder().
				firstName(createProfileRequest.getFirstName()).
				lastName(createProfileRequest.getLastName()).
				summary(createProfileRequest.getSummary()).
				yearsOfExperience(createProfileRequest.getYearsOfExperience()).
				techStack(createProfileRequest.getTechStack()).
				skills(createProfileRequest.getSkills()).
				userId(userId).
				profileId(UUID.randomUUID().toString()).build();
		
		String returnValue = commandGateway.sendAndWait(command);
		
		return new ResponseEntity<>(returnValue, new HttpHeaders(), HttpStatus.CREATED);
	}
}
