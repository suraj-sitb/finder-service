package com.findSimran.userLoginService.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	private String userId;
	@Column(unique = true)
	private String userName;
	@Column(unique = true)
	private String mailId;
	private String password;
}
