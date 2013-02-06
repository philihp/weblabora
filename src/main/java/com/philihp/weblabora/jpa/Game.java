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
		FINISHED,
		ABANDONED
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
	
	@Column(name = "active_player")
	@Basic
	private Integer activePlayer;

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
		return getUsersPlayerSeat(user) != null;
	}

	/**
	 * Returns player seat taken by specified user in the game.
	 *
	 * Note that this function silently assumes that given user may take only
	 * one player seat in the game. Thus after finding first such seat the
	 * function returns immediately.
	 *
	 * Yet it seems that there is no technical reason for which a user would not
	 * be able to take more than one player seat. The only thing preventing that
	 * from happening are extra checks in the code (most notably in
	 * {@link com.philihp.weblabora.action.JoinGame}).
	 *
	 * @param user User which player seat we are asking for.
	 * @return Player seat ({@code 1}-based index) taken by {@code user} or
	 *         {@code null} if either {@code user} is {@code null} or
	 *         {@code user} is not a player in the game.
	 */
	@Transient
	public Integer getUsersPlayerSeat(User user) {
		if(user == null) {
			return null;
		}

		int playerSeat = 1;
		for(Player player : getAllPlayers()) {
			// TODO: Is the comparison of getUserId() needed? Shouldn't those
			//       user objects be the same object?
			if((player.user != null) && (player.user.getUserId() == user.getUserId())) {
				return playerSeat;
			}
			++playerSeat;
		}

		return null;
	}

	/**
	 * Returns player taken by specified user in the game.
	 *
	 * Note that this function silently assumes that given user may be only a
	 * single player in the game. Thus after finding first such player the
	 * function returns immediately.
	 *
	 * Yet it seems that there is no technical reason for which a user would not
	 * be able to be more than one player. The only thing preventing that from
	 * happening are extra checks in the code (most notably in
	 * {@link com.philihp.weblabora.action.JoinGame}).
	 *
	 * @param user User which player we are asking for.
	 * @return Player taken by {@code user} or {@code null} if either
	 *         {@code user} is {@code null} or {@code user} is not a player in
	 *         the game.
	 */
	@Transient
	public Player getUsersPlayer(User user) {
		Integer seat = getUsersPlayerSeat(user);
		if (seat == null)
			return null;
		return getSeat(seat);
	}

	@Transient
	public Player getSeat(int seat) {
		return getAllPlayers()[seat - 1];
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
	
	public Integer getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Integer activePlayer) {
		this.activePlayer = activePlayer;
	}
}
