package com.findSimran.userLoginService.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.findSimran.userLoginService.command.event.UserCreatedEvent;
import com.findSimran.userLoginService.command.model.CreateUserCommand;

@Aggregate
@SuppressWarnings("unused")
public class UserLoginAggregate {

	@AggregateIdentifier
	private String userId;
	private String userName;
	private String mailId;
	private String password;
	
	public UserLoginAggregate() {
		
	}

	@CommandHandler	
	public UserLoginAggregate(CreateUserCommand createUserCommand) {
		UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder().
				userName(createUserCommand.getUserName()).
				mailId(createUserCommand.getMailId()).
				password(createUserCommand.getPassword()).
				userId(createUserCommand.getUserId()).build();
		
		AggregateLifecycle.apply(userCreatedEvent);
	}
	
	@EventSourcingHandler
	public void on(UserCreatedEvent userCreatedEvent) {
		this.userId = userCreatedEvent.getUserId();
		this.userName = userCreatedEvent.getUserName();
		this.mailId = userCreatedEvent.getMailId();
		this.password = userCreatedEvent.getPassword();
	}
}
