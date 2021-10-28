package com.findSimran.userProfileService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.userProfileService.core.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

	Skill findBySkillName(String skillName);
}