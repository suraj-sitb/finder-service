package com.findSimran.swipeService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.swipeService.core.entity.SwipeUser;

public interface SwipeUserRepository extends JpaRepository<SwipeUser, Long> {

	SwipeUser findBySwipeUserName(String swipeUserName);
}
