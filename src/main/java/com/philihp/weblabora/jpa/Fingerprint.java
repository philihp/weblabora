package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "Fingerprint")
@Table(name = "weblabora_fingerprint")
@Access(FIELD)
public class Fingerprint extends BasicEntity {

	@Id
	@GeneratedValue
	@Column(name = "fingerprint_id")
	private int fingerprintId;

	@Basic
	@Column(name = "uuid")
	private String uuid;
	
	@ManyToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.User.class)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id")
	private User user;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String token) {
		this.uuid = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getFingerprintId() {
		return fingerprintId;
	}

	public void setFingerprintId(int fingerprintId) {
		this.fingerprintId = fingerprintId;
	}

}
