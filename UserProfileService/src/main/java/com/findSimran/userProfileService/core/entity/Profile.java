package com.findSimran.userProfileService.core.entity;

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
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "profiles")
public class Profile {

	@Id
	private String profileId;
	private String userId;
	private String firstName;
	private String lastName;
	private String summary;
	private Integer yearsOfExperience;
	private String techStack;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "profiles_skills",
				joinColumns = {
						@JoinColumn(name = "profileId")
				},
				inverseJoinColumns = {
						@JoinColumn(name = "skillId")
				})
	private List<Skill> skills;
	
	public void addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getProfile().add(this);
    }
    public void removeSkill(Skill skill) {
        this.getSkills().remove(skill);
        skill.getProfile().remove(this);
    }
    public void removeSkills() {
        for (Skill skill : new ArrayList<>(skills)) {
            removeSkill(skill);
        }
    }
}
