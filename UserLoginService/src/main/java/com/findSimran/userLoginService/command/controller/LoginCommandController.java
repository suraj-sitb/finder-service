package com.findSimran.userLoginService.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findSimran.userLoginService.command.model.CreateUserCommand;
import com.findSimran.userLoginService.request.model.CreateUserRequest;

@RestController
@RequestMapping(path = "/users")
public class LoginCommandController {

	private final CommandGateway commandGateway;
	
	public LoginCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/signup")
	public ResponseEntity<Object> createUser(@RequestBody CreateUserRequest createUserRequest) {
		CreateUserCommand createUserCommand = CreateUserCommand.builder().
				userName(createUserRequest.getUserName()).
				mailId(createUserRequest.getMailId()).
				password(createUserRequest.getPassword()).
				userId(UUID.randomUUID().toString()).build();
		
		String returnValue;
		returnValue = commandGateway.sendAndWait(createUserCommand);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization", returnValue);
		
		return new ResponseEntity<Object>(returnValue, httpHeaders, HttpStatus.CREATED);
	}
}
