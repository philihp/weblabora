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

@Entity(name = "State")
@Table(name = "weblabora_state")
@Access(FIELD)
public class State extends BasicEntity {

	@Id
	@GeneratedValue
	@Column(name = "state_id")
	private int stateId;

	@Basic
	@Column(name = "token")
	private String token;

	@OneToMany(fetch = LAZY, mappedBy = "srcState", targetEntity = com.philihp.weblabora.jpa.State.class)
	private List<State> dstStates;

	@ManyToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.State.class)
	@JoinColumn(name = "src_state_id", referencedColumnName="state_id")
	private State srcState;
	
	@ManyToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.User.class)
	@JoinColumn(name = "explorer_id", referencedColumnName = "user_id")
	private User explorer;
	
	@ManyToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.Game.class)
	@JoinColumn(name = "game_id", referencedColumnName="game_id")
	private Game game;
	
	@Basic
	@Column(name = "active")
	private boolean active;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public List<State> getDstStates() {
		return dstStates;
	}

	public void setDstStates(List<State> dstStates) {
		this.dstStates = dstStates;
	}

	public State getSrcState() {
		return srcState;
	}

	public void setSrcState(State srcState) {
		this.srcState = srcState;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getExplorer() {
		return explorer;
	}

	public void setExplorer(User explorer) {
		this.explorer = explorer;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Transient
	public List<State> getStates() {
		List<State> list = new ArrayList<State>();
		State state = this;
		do {
			if(state.getToken() != null) 
				list.add(state);
			state = state.getSrcState();
		} while(state != null);
		Collections.reverse(list);
		return list;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
