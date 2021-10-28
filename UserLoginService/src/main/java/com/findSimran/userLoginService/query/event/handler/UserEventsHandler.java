package com.findSimran.userLoginService.query.event.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.findSimran.userLoginService.command.event.UserCreatedEvent;
import com.findSimran.userLoginService.core.entity.User;
import com.findSimran.userLoginService.core.repository.UserRepository;

@Component
@ProcessingGroup(value = "login-events")
public class UserEventsHandler {

	private final UserRepository userRepository;
	
	public UserEventsHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@EventHandler
	public void on(UserCreatedEvent userCreatedEvent) {
		User user = new User();
		
		BeanUtils.copyProperties(userCreatedEvent, user);
		
		userRepository.save(user);
	}
}
