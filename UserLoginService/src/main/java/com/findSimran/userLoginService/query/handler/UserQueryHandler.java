package com.findSimran.userLoginService.query.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.findSimran.userLoginService.core.entity.User;
import com.findSimran.userLoginService.core.repository.UserRepository;
import com.findSimran.userLoginService.query.model.FindUserQuery;
import com.findSimran.userLoginService.response.model.GetUserResponse;

@Component
@ProcessingGroup(value = "login-events")
public class UserQueryHandler {

	private final UserRepository userRepository;

	public UserQueryHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@QueryHandler
	public GetUserResponse findUser(FindUserQuery findUserQuery) {
		User user = userRepository.findByUserName(findUserQuery.getUserName());
		
		GetUserResponse response = GetUserResponse.builder().
				userName(user.getUserName()).
				mailId(user.getMailId()).build();
		
		return response;
	}
}
