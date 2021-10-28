package com.findSimran.swipeService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.swipeService.core.entity.LookupUser;

public interface LookupUserRepository extends JpaRepository<LookupUser, String> {

	LookupUser findByProfileId(String profileId);
	LookupUser findByFirstName(String firstName);
}
