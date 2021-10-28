package com.findSimran.userProfileService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.userProfileService.core.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
