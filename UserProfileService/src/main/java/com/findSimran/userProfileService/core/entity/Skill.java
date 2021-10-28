package com.findSimran.userProfileService.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "skills")
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skillId;
	private String skillName;
	@ManyToMany(mappedBy = "skills", cascade = CascadeType.ALL)
	private List<Profile> profile;
}
