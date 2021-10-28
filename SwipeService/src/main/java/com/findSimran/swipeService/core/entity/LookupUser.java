package com.findSimran.swipeService.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "lookupusers")
public class LookupUser {

	@Id
	private String lookupId;
	private String profileId;
	private String firstName;
}
