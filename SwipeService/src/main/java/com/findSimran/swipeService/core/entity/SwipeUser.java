package com.findSimran.swipeService.core.entity;

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
@Table(name = "swipeusers")
public class SwipeUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long swipeUserId;
	private String swipeUserName;
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "swipeUsers")
	private List<User> users;
}
