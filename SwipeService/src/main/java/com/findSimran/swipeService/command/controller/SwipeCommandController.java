package com.findSimran.swipeService.command.controller;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findSimran.swipeService.command.model.CreateSwipeuserCommand;

@RestController
@RequestMapping(path = "/users")
public class SwipeCommandController {

	private final CommandGateway commandGateway;
	
	public SwipeCommandController(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@RequestMapping(path = "/swipe/{username}", method = RequestMethod.POST)
	public ResponseEntity<Object> storeSwipe(@RequestHeader(name = "Authorization") String key,
			@PathVariable String username) {
		CreateSwipeuserCommand command = CreateSwipeuserCommand.builder().
				currentUser(key).
				targetUser(username).
				swipeId(UUID.randomUUID().toString()).build();
		
		String returnValue = commandGateway.sendAndWait(command);
		
		return new ResponseEntity<Object>(returnValue, new HttpHeaders(), HttpStatus.CREATED);
	}
}
