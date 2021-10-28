package com.findSimran.swipeService.command.interceptor;

import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.messaging.Message;
import org.axonframework.messaging.MessageDispatchInterceptor;

import com.findSimran.swipeService.command.model.CreateSwipeuserCommand;
import com.findSimran.swipeService.core.entity.LookupUser;
import com.findSimran.swipeService.core.repository.LookupUserRepository;

public class SwipeUserInterceptor implements MessageDispatchInterceptor<Message<?>> {

	private final LookupUserRepository lookupUserRepository;
	
	public SwipeUserInterceptor(LookupUserRepository lookupUserRepository) {
		this.lookupUserRepository = lookupUserRepository;
	}

	@Override
	public BiFunction<Integer, Message<?>, Message<?>> handle(List<? extends Message<?>> messages) {
		return (index, message) -> {
			if (CreateSwipeuserCommand.class.equals(message.getPayloadType())) {
				CreateSwipeuserCommand command = (CreateSwipeuserCommand) message.getPayload();
				
				LookupUser curUser = lookupUserRepository.findByFirstName(command.getCurrentUser());
				LookupUser tarUser = lookupUserRepository.findByFirstName(command.getTargetUser());
				
				if(curUser == null) {
					throw new IllegalArgumentException("User doesn't exist.");
				}
				
				if(tarUser == null) {
					throw new IllegalArgumentException("Swipped User doesn't exist.");
				}		
			}
			return message;
		};
	}

}
