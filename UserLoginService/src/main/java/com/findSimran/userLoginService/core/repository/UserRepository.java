package com.findSimran.userLoginService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.userLoginService.core.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

	User findByUserIdOrUserName(String userId, String userName);
	User findByUserName(String userName);
}
