package com.findSimran.swipeService.query.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.findSimran.swipeService.core.entity.LookupUser;
import com.findSimran.swipeService.core.repository.LookupUserRepository;
import com.findSimran.swipeService.query.model.FindUserByNameQuery;

@Component
@ProcessingGroup(value = "swipe-events")
public class SwipeQueryHandler {

	private final LookupUserRepository lookupUserRepository;

	public SwipeQueryHandler(LookupUserRepository lookupUserRepository) {
		this.lookupUserRepository = lookupUserRepository;
	}
	
	@QueryHandler
	private LookupUser findUserByName(FindUserByNameQuery query) {
		String firstName = query.getFirstName();
		
		LookupUser returnUser = lookupUserRepository.findByFirstName(firstName);
		
		return returnUser;
	}
}
