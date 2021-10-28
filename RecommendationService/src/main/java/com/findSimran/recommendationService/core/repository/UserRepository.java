package com.findSimran.recommendationService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.recommendationService.core.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
