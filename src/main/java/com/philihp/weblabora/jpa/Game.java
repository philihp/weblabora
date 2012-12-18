package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.LAZY;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.philihp.weblabora.model.WeblaboraException;

@Entity(name = "Game")
@Access(FIELD)
@Table(name = "weblabora_game")
public class Game extends BasicEntity {

	public enum Stage {
		RECRUITING,
		IN_PROGRESS,
		FINISHED
	}
	
	@Embeddable
	@Access(FIELD)
	public static class Player {
		@OneToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.User.class)
		@JoinColumn(name = "user_user_id", referencedColumnName = "user_id")
		private User user;

		@Basic
		@Column(name = "color")
		private String color;
		
		@Basic
		@Column(name = "move")
		private String move;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}
		
		public String getMove() {
			return move;
		}

		public void setMove(String move) {
			this.move = move;
		}

	}

	@Id
	@GeneratedValue
	@Column(name = "game_id")
	private int gameId;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "color", column = @Column(name = "player1_color")),
		@AttributeOverride(name = "move", column = @Column(name = "player1_move"))
	})
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player1_user_id", referencedColumnName = "user_id"))
	private Player player1;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "color", column = @Column(name = "player2_color")),
		@AttributeOverride(name = "move", column = @Column(name = "player2_move"))
	})
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player2_user_id", referencedColumnName = "user_id"))
	private Player player2;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "color", column = @Column(name = "player3_color")),
		@AttributeOverride(name = "move", column = @Column(name = "player3_move"))
	})
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player3_user_id", referencedColumnName = "user_id"))
	private Player player3;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "color", column = @Column(name = "player4_color")),
		@AttributeOverride(name = "move", column = @Column(name = "player4_move"))
	})
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player4_user_id", referencedColumnName = "user_id"))
	private Player player4;
	
//	@ManyToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.State.class)
//	@JoinColumn(name = "state_id", referencedColumnName = "state_id")
//	private State state;

	@Column(name = "length")
	@Basic
	private String length;

	@Column(name = "players")
	@Basic
	private Integer players;
	
	@Column(name = "country")
	@Basic
	private String country;
	
	@Column(name = "stage")
	@Enumerated(EnumType.STRING)
	private Stage stage;
	
	@OneToMany(mappedBy="game", fetch=LAZY)
	@OrderBy("dateCreated ASC")
	private List<State> states;
	
	@Column(name = "state_id")
	@Basic
	private Integer stateId;
	
	public Game() {
		// player1-4 must not be null
		this.player1 = new Player();
		this.player2 = new Player();
		this.player3 = new Player();
		this.player4 = new Player();
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	public void setPlayer3(Player player3) {
		this.player3 = player3;
	}

	public Player getPlayer4() {
		return player4;
	}

	public void setPlayer4(Player player4) {
		this.player4 = player4;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Integer getPlayers() {
		return players;
	}

	public void setPlayers(Integer players) {
		this.players = players;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Transient
	public boolean isUserAPlayer(User user) {
		if(user == null) return false;
		boolean player1Match = player1.user != null && player1.user.getUserId() == user.getUserId();
		boolean player2Match = player2.user != null && player2.user.getUserId() == user.getUserId();
		boolean player3Match = player3.user != null && player3.user.getUserId() == user.getUserId();
		boolean player4Match = player4.user != null && player4.user.getUserId() == user.getUserId();
		return player1Match || player2Match || player3Match || player4Match;
	}

	@Transient
	public Player getSeat(int seat) throws WeblaboraException {
		switch(seat) {
		case 1: return player1;
		case 2: return player2;
		case 3: return player3;
		case 4: return player4;
		default: throw new WeblaboraException("Invalid seat "+seat+", a game only has four seats");
		}
	}
	
	@Transient
	public Player[] getAllPlayers() {
		return new Player[] {player1, player2, player3, player4};
	}
	
	@Transient
	public User[] getAllUsers() {
		return new User[] {player1.user, player2.user, player3.user, player4.user};
	}
	
	@Transient
	public boolean isGameFull() {
		switch(players) {
		case 4:
			if (player4 == null || player4.getUser() == null)
				return false;
			//fall through
		case 3:
			if (player3 == null || player3.getUser() == null)
				return false;
			//fall through
		case 2:
			if (player2 == null || player2.getUser() == null)
				return false;
			//fall through
		case 1:
			if (player1 == null || player1.getUser() == null)
				return false;
			//fall through
		default:
			return true;
		}
	}
	
	@Transient
	public boolean isUndoable() {
		/*
		long movedAt = this.getState().getDateCreated().getTime();
		long now = new Date().getTime();
		return (now-movedAt < 24*60*60*1000) && (getState().getSrcState() != null);
		*/
		return true;
	}
	
	@Transient
	public String getName() {
		String name = "Game #"+gameId+", " + new SimpleDateFormat("yyyy-MM-dd").format(getDateCreated());
		// if(player1.user != null) {
		// name += " by "+player1.user.getName();
		// }
		return name;
	}

	public List<State> getStates() {
		if(states == null) states = new ArrayList<State>();
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	@Transient
	public List<State> getActiveStates() {
		List<State> activeStates = new ArrayList<State>();
		for(State state : getStates()) {
			if(state.isActive()) {
				activeStates.add(state);
			}
		}
		return activeStates;
	}
	
	@Transient
	public State getState() {
		List<State> states = getActiveStates();
		return states.get(states.size()-1);
	}
	
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
}
