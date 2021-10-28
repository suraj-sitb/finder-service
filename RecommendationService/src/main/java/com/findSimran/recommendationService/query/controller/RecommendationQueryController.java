package com.findSimran.recommendationService.query.controller;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findSimran.recommendationService.query.model.FindUserBySkillQuery;
import com.findSimran.recommendationService.response.model.GetRecommendationUserResponse;

@RestController
@RequestMapping(path = "/users")
public class RecommendationQueryController {

	private final QueryGateway queryGateway;
	
	public RecommendationQueryController(QueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

	@RequestMapping(path = "/recommend/{skillName}")
	public ResponseEntity<Object> getUsersBySkill(@PathVariable String skillName) {
		FindUserBySkillQuery query = new FindUserBySkillQuery();
		query.setSkillName(skillName);
		
		List<GetRecommendationUserResponse> users = queryGateway.query(query, 
				ResponseTypes.multipleInstancesOf(GetRecommendationUserResponse.class)).join();
		
		return new ResponseEntity<Object>(users, new HttpHeaders(), HttpStatus.OK);
	}
}
