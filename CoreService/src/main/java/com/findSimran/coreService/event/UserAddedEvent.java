package com.findSimran.coreService.event;

import lombok.Data;

@Data
public class UserAddedEvent {

	private String lookupId;
	private String profileId;
	private String firstName;
}
