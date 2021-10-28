package com.findSimran.swipeService.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_swipeusers", 
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "swipeUserId"))
	private List<SwipeUser> swipeUsers;
	
	public void add(SwipeUser swipeUser) {
		this.swipeUsers.add(swipeUser);
		swipeUser.getUsers().add(this);
	}
	
	public void remove(SwipeUser swipeUser) {
		this.swipeUsers.remove(swipeUser);
		swipeUser.getUsers().remove(this);
	}
	
	public void remveSwipeUsers() {
		for(SwipeUser user : new ArrayList<>(swipeUsers)) {
			remove(user);
		}
	}
}
