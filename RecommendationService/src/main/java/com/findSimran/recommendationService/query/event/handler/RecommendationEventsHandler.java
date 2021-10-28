package com.findSimran.recommendationService.query.event.handler;

import java.util.ArrayList;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.findSimran.coreService.event.UserStoredEvent;
import com.findSimran.recommendationService.core.entity.Skill;
import com.findSimran.recommendationService.core.entity.User;
import com.findSimran.recommendationService.core.repository.SkillRepository;
import com.findSimran.recommendationService.core.repository.UserRepository;

@Component
@ProcessingGroup(value = "recommendation-events")
public class RecommendationEventsHandler {

	private final UserRepository userRepository;
	private final SkillRepository skillRepository;
	
	public RecommendationEventsHandler(UserRepository userRepository, SkillRepository skillRepository) {
		this.userRepository = userRepository;
		this.skillRepository = skillRepository;
	}

	@EventHandler
	public void on(UserStoredEvent userCreatedEvent) {
		User user = new User();
		user.setFirstName(userCreatedEvent.getFirstName());
		user.setTechStack(userCreatedEvent.getTechStack());
		user.setYearsOfExperience(userCreatedEvent.getYearsOfExperience());
		user.setProfileId(userCreatedEvent.getProfileId());
		user.setStoreId(userCreatedEvent.getStoreId());
		
		if(user.getSkills() == null) {
			user.setSkills(new ArrayList<>());
		}
		
		userCreatedEvent.getSkills().forEach(skillName -> {
				Skill skill = skillRepository.findBySkillName(skillName);
				if(skill == null) {
					skill = new Skill();
					skill.setUsers(new ArrayList<>());
				}
				skill.setSkillName(skillName);
				user.addSkill(skill);
			});
		
		userRepository.save(user);
	}
}
