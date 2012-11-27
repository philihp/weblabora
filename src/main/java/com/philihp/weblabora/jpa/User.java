package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;

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

	@Basic(optional = false)
	@Column(name="facebook_id", nullable = false)
	private String facebookId;

	@Basic(optional = false)
	@Column(name = "name", nullable = false)
	private String name;

	@Basic(optional = false)
	@Column(name = "username", nullable = false)
	private String username;

	@Basic(optional = false)
	@Column(name = "email", nullable = false)
	private String email;
	
	@Basic(optional = false)
	@Column(name = "password", nullable = false)
	private String password;
	
	@ManyToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.Game.class)
	@JoinColumn(name = "active_game_id", referencedColumnName = "game_id")
	private Game activeGame;

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

	public Game getActiveGame() {
		return activeGame;
	}
	public Integer getActiveGameId() {
		return activeGame==null?null:activeGame.getGameId();
	}

	public void setActiveGame(Game activeGame) {
		this.activeGame = activeGame;
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

}
