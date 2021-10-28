package com.findSimran.recommendationService.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	private String storeId;
	private String profileId;
	private String firstName;
	private Integer yearsOfExperience;
	private String techStack;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_skills",
				joinColumns = {
						@JoinColumn(name = "storeId")
				},
				inverseJoinColumns = {
						@JoinColumn(name = "skillId")
				})
	private List<Skill> skills;
	
	public void addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getUsers().add(this);
    }
    public void removeSkill(Skill skill) {
        this.getSkills().remove(skill);
        skill.getUsers().remove(this);
    }
    public void removeSkills() {
        for (Skill skill : new ArrayList<>(skills)) {
            removeSkill(skill);
        }
    }
}
