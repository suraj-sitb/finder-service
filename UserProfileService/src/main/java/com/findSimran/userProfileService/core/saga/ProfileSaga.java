package com.findSimran.userProfileService.core.saga;

import java.util.UUID;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.findSimran.coreService.command.AddUserCommand;
import com.findSimran.coreService.command.StoreUserCommand;
import com.findSimran.coreService.event.UserAddedEvent;
import com.findSimran.coreService.event.UserStoredEvent;
import com.findSimran.userProfileService.command.event.ProfileCreatedEvent;

@Saga
public class ProfileSaga {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfileSaga.class);

	@Inject
	private transient CommandGateway commandGateway;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "profileId")
	public void on(ProfileCreatedEvent profileCreatedEvent) {
		StoreUserCommand storeUserCommand = StoreUserCommand.builder().
				firstName(profileCreatedEvent.getFirstName()).
				profileId(profileCreatedEvent.getProfileId()).
				yearsOfExperience(profileCreatedEvent.getYearsOfExperience()).
				techStack(profileCreatedEvent.getTechStack()).
				skills(profileCreatedEvent.getSkills()).
				storeId(UUID.randomUUID().toString()).build();
		
		SagaLifecycle.associateWith("storeId", profileCreatedEvent.getProfileId());
				
		LOGGER.info("Command created successfully" + storeUserCommand);
		
		commandGateway.send(storeUserCommand);
	}
	
	@SagaEventHandler(associationProperty = "profileId")
	public void on(UserStoredEvent userStoredEvent) {
		LOGGER.info("Successfully stored user in Recommendation Service.");
		AddUserCommand command = AddUserCommand.builder().
				profileId(userStoredEvent.getProfileId()).
				firstName(userStoredEvent.getFirstName()).
				lookupId(UUID.randomUUID().toString()).build();
		
		commandGateway.send(command);
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "profileId")
	public void on(UserAddedEvent userAddedEvent) {
		LOGGER.info("User successfully added to swipe service.");
	}
}
