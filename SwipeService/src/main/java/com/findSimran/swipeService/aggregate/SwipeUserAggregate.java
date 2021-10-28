package com.findSimran.swipeService.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.findSimran.swipeService.command.event.SwipeuserCreatedEvent;
import com.findSimran.swipeService.command.model.CreateSwipeuserCommand;

@Aggregate
@SuppressWarnings("unused")
public class SwipeUserAggregate {

	@AggregateIdentifier
	private String swipeId;
	private String currentUser;
	private String targetUser;
	
	public SwipeUserAggregate() {

	}

	@CommandHandler
	public SwipeUserAggregate(CreateSwipeuserCommand command) {
		SwipeuserCreatedEvent event = new SwipeuserCreatedEvent();
		BeanUtils.copyProperties(command, event);
		
		AggregateLifecycle.apply(event);
	}
	
	@EventSourcingHandler
	public void on(SwipeuserCreatedEvent event) {
	
		this.swipeId = event.getSwipeId();
		this.currentUser = event.getCurrentUser();
		this.targetUser = event.getTargetUser();
	}
}
