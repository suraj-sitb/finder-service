package com.findSimran.recommendationService.command.aggregate;

import java.util.List;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.findSimran.coreService.command.StoreUserCommand;
import com.findSimran.coreService.event.UserStoredEvent;

@Aggregate
@SuppressWarnings("unused")
public class RecommendationAggregate {

	@AggregateIdentifier
	private String storeId;
	private String profileId;
	private String firstName;
	private Integer yearsOfExperience;
	private String techStack;
	private List<String> skills;
	
	public RecommendationAggregate() {

	}

	@CommandHandler
	public RecommendationAggregate(StoreUserCommand command) {

		UserStoredEvent userStoredEvent = new UserStoredEvent();
		BeanUtils.copyProperties(command, userStoredEvent);
		
		AggregateLifecycle.apply(userStoredEvent);
	}
	
	@EventSourcingHandler
	public void on(UserStoredEvent userStoredEvent) {
		this.storeId = userStoredEvent.getStoreId();
		this.profileId = userStoredEvent.getProfileId();
		this.firstName = userStoredEvent.getFirstName();
		this.yearsOfExperience = userStoredEvent.getYearsOfExperience();
		this.techStack = userStoredEvent.getTechStack();
		this.skills = userStoredEvent.getSkills();
	}
}
