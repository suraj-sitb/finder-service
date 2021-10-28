package com.findSimran.swipeService.query.event.handler;

import java.util.ArrayList;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.findSimran.coreService.event.UserAddedEvent;
import com.findSimran.swipeService.command.event.SwipeuserCreatedEvent;
import com.findSimran.swipeService.core.entity.LookupUser;
import com.findSimran.swipeService.core.entity.SwipeUser;
import com.findSimran.swipeService.core.entity.User;
import com.findSimran.swipeService.core.repository.LookupUserRepository;
import com.findSimran.swipeService.core.repository.SwipeUserRepository;
import com.findSimran.swipeService.core.repository.UserRepository;

@Component
@ProcessingGroup(value = "swipe-events")
public class SwipeEventsHandler {

	private final LookupUserRepository lookupUserRepository;
	private final UserRepository userRepository;
	private final SwipeUserRepository swipeUserRepository;

	public SwipeEventsHandler(LookupUserRepository lookupUserRepository, 
			UserRepository userRepository, SwipeUserRepository swipeUserRepository) {
		this.lookupUserRepository = lookupUserRepository;
		this.userRepository = userRepository;
		this.swipeUserRepository = swipeUserRepository;
	}
	
	@EventHandler
	public void on(UserAddedEvent userAddedEvent) {
		LookupUser user = new LookupUser();
		BeanUtils.copyProperties(userAddedEvent, user);
		
		lookupUserRepository.save(user);
	}
	
	@EventHandler
	public void on(SwipeuserCreatedEvent userCreatedEvent) {
		User user = new User();
		user.setUserName(userCreatedEvent.getCurrentUser());
		
		if (user.getSwipeUsers() == null) {
			user.setSwipeUsers(new ArrayList<>());
		}
		
		String targetUser = userCreatedEvent.getTargetUser();
		SwipeUser swipeUser = swipeUserRepository.findBySwipeUserName(targetUser);
		if(swipeUser == null) {
			swipeUser = new SwipeUser();
			swipeUser.setUsers(new ArrayList<>());
		}
		
		swipeUser.setSwipeUserName(targetUser);
		user.add(swipeUser);
		
		userRepository.save(user);
	}
}
