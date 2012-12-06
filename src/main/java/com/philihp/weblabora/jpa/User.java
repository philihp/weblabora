package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Basic;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.Hex;

import com.philihp.weblabora.util.UserUtil;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "User")
@Table(name = "weblabora_user")
@Access(FIELD)
@NamedQuery(name = "findUserByFacebookId", query = "SELECT u FROM User u WHERE u.facebookId = :facebookId")
public class User extends BasicEntity {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private int userId;

	@Basic
	@Column(name="facebook_id")
	private String facebookId;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "username")
	private String username;

	@Basic
	@Column(name = "email")
	private String email;
	
	@Basic
	@Column(name = "password")
	private String password;
	
	@Basic
	@Column(name = "unvalidated_email")
	private String unvalidatedEmail;
	
	@Basic
	@Column(name = "email_validator")
	private String emailValidator;
	
	@Basic
	@Column(name = "password_validator")
	private String passwordValidator;

//	@ManyToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.Game.class)
//	@JoinColumn(name = "active_game_id", referencedColumnName = "game_id")
//	private Game activeGame;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return getName()+" ("+getFacebookId()+")";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public boolean isEmailValidated() {
		return email != null;
	}

	public String getEmailValidator() {
		return emailValidator;
	}

	public void setEmailValidator(String emailValidator) {
		this.emailValidator = emailValidator;
	}
	
	@Transient
	public String getEmailMD5() {
		try {
			return UserUtil.md5(this.email);
		}
		catch(Exception e) {
			return "000000000000000000000000000000";
		}
	}

	public String getUnvalidatedEmail() {
		return unvalidatedEmail;
	}

	public void setUnvalidatedEmail(String unvalidatedEmail) {
		this.unvalidatedEmail = unvalidatedEmail;
	}

	public String getPasswordValidator() {
		return passwordValidator;
	}

	public void setPasswordValidator(String passwordValidator) {
		this.passwordValidator = passwordValidator;
	}

}
