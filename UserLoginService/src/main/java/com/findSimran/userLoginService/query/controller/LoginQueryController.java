package com.findSimran.userLoginService.query.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.findSimran.userLoginService.query.model.FindUserQuery;
import com.findSimran.userLoginService.response.model.GetUserResponse;

@RestController
@RequestMapping(path = "/users")
public class LoginQueryController {

	private final QueryGateway queryGateway;
	
	public LoginQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@RequestMapping(path = "/{userName}", method = RequestMethod.GET)
	public GetUserResponse getUser(@PathVariable String userName) {
		FindUserQuery findUserQuery = new FindUserQuery(userName);
		
		GetUserResponse response = queryGateway.query(findUserQuery, ResponseTypes.instanceOf(GetUserResponse.class)).join();
		
		return response;
	}
}
