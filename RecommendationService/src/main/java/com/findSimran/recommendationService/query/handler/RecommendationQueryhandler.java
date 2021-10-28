package com.findSimran.recommendationService.query.handler;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.findSimran.recommendationService.core.entity.Skill;
import com.findSimran.recommendationService.core.entity.User;
import com.findSimran.recommendationService.core.repository.SkillRepository;
import com.findSimran.recommendationService.query.model.FindUserBySkillQuery;
import com.findSimran.recommendationService.response.model.GetRecommendationUserResponse;

@Component
@ProcessingGroup(value = "recommendation-events")
public class RecommendationQueryhandler {
	
	private final SkillRepository skillRepository;
	
	public RecommendationQueryhandler(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

	@QueryHandler
	public List<GetRecommendationUserResponse> findUserBySkill(FindUserBySkillQuery findUserBySkillQuery) {
		Skill skill = skillRepository.findBySkillName(findUserBySkillQuery.getSkillName());
		List<User> users = skill.getUsers();
		
		List<GetRecommendationUserResponse> returnUsers = new ArrayList<>();
		users.forEach(user -> {
			GetRecommendationUserResponse response = new GetRecommendationUserResponse();
			response.setFirstName(user.getFirstName());
			returnUsers.add(response);
		});
		
		return returnUsers;
	}
}
