package com.findSimran.swipeService.command.event;

import lombok.Data;

@Data
public class SwipeuserCreatedEvent {

	private String swipeId;
	private String currentUser;
	private String targetUser;
}
