package com.findSimran.userProfileService.query.event.handler;

import java.util.ArrayList;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.findSimran.userProfileService.command.event.ProfileCreatedEvent;
import com.findSimran.userProfileService.core.entity.Profile;
import com.findSimran.userProfileService.core.entity.Skill;
import com.findSimran.userProfileService.core.repository.ProfileRepository;
import com.findSimran.userProfileService.core.repository.SkillRepository;

@Component
@ProcessingGroup(value = "profile-events")
public class ProfileEventsHandler {

	private final ProfileRepository profileRepository;
	private final SkillRepository skillRepository;
	
	public ProfileEventsHandler(ProfileRepository profileRepository, SkillRepository skillRepository) {
		this.profileRepository = profileRepository;
		this.skillRepository = skillRepository;
	}

	@EventHandler
	public void on(ProfileCreatedEvent profileCreatedEvent) {
		
		Profile profile = new Profile();

		profile.setFirstName(profileCreatedEvent.getFirstName());
		profile.setLastName(profileCreatedEvent.getLastName());
		profile.setSummary(profileCreatedEvent.getSummary());
		profile.setTechStack(profileCreatedEvent.getTechStack());
		profile.setYearsOfExperience(profileCreatedEvent.getYearsOfExperience());
		profile.setProfileId(profileCreatedEvent.getProfileId());
		profile.setUserId(profileCreatedEvent.getUserId());
		
		if(profile.getSkills() == null) {
			profile.setSkills(new ArrayList<>());
		}
		
		profileCreatedEvent.getSkills().forEach(skillName -> {
				Skill skill = skillRepository.findBySkillName(skillName);
				if(skill == null) {
					skill = new Skill();
					skill.setProfile(new ArrayList<>());
				}
				skill.setSkillName(skillName);
				profile.addSkill(skill);
			});
		
		profileRepository.save(profile);
	}
}
