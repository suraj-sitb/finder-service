package com.findSimran.recommendationService.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.findSimran.recommendationService.core.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

	Skill findBySkillName(String skillName);
}
