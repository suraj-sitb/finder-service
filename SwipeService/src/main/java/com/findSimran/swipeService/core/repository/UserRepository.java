package com.findSimran.swipeService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.swipeService.core.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String userName);
}
