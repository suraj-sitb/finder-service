package com.findSimran.userProfileService.command.aggregate;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.findSimran.userProfileService.command.event.ProfileCreatedEvent;
import com.findSimran.userProfileService.command.model.CreateProfileCommand;

@Aggregate
@SuppressWarnings("unused")
public class ProfileAggregate {

	@AggregateIdentifier
	private String profileId;
	private String userId;
	private String firstName;
	private String lastName;
	private String summary;
	private Integer yearsOfExperience;
	private String techStack;
	private List<String> skills;
	
	public ProfileAggregate() {
		
	}

	@CommandHandler
	public ProfileAggregate(CreateProfileCommand createProfileCommand) {
		ProfileCreatedEvent profileCreatedEvent = new ProfileCreatedEvent();
		
		BeanUtils.copyProperties(createProfileCommand, profileCreatedEvent);
		
		AggregateLifecycle.apply(profileCreatedEvent);
	}
	
	@EventSourcingHandler
	public void on(ProfileCreatedEvent event) {
		this.firstName = event.getFirstName();
		this.lastName = event.getLastName();
		this.profileId = event.getProfileId();
		this.summary = event.getSummary();
		this.techStack = event.getTechStack();
		this.skills = event.getSkills();
		this.userId = event.getUserId();
		this.yearsOfExperience = event.getYearsOfExperience();
	}
}
