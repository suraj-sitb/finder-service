package com.findSimran.swipeService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.findSimran.coreService.command.AddUserCommand;
import com.findSimran.coreService.event.UserAddedEvent;
import com.findSimran.swipeService.command.event.SwipeuserCreatedEvent;
import com.findSimran.swipeService.command.model.CreateSwipeuserCommand;

@Aggregate
@SuppressWarnings("unused")
public class SwipeAggregate {

	@AggregateIdentifier
	private String lookupId;
	private String profileId;
	private String firstName;
	
	public SwipeAggregate() {

	}

	@CommandHandler
	public SwipeAggregate(AddUserCommand addUserCommand) {
		UserAddedEvent userAddedEvent = new UserAddedEvent();
		BeanUtils.copyProperties(addUserCommand, userAddedEvent);
		
		AggregateLifecycle.apply(userAddedEvent);
	}
	
	@EventSourcingHandler
	public void on(UserAddedEvent event) {
		this.lookupId = event.getLookupId();
		this.profileId = event.getProfileId();
		this.firstName = event.getFirstName();
	}
}
