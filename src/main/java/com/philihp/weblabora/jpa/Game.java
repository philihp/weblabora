package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import org.eclipse.persistence.annotations.ReadOnly;

import com.philihp.weblabora.model.WeblaboraException;

@Entity(name = "Game")
@Access(FIELD)
@Table(name = "weblabora_game")
public class Game extends BasicEntity {

	@Embeddable
	@Access(FIELD)
	public static class Player {
		@OneToOne(fetch = LAZY, targetEntity = com.philihp.weblabora.jpa.User.class)
		@JoinColumn(name = "user_user_id", referencedColumnName = "user_id")
		private User user;

		@Basic
		@Column(name = "color")
		private String color;

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
	}

	@Id
	@GeneratedValue
	@Column(name = "game_id")
	private int gameId;

	@Embedded
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player1_user_id", referencedColumnName = "user_id"))
	@AttributeOverride(name = "color", column = @Column(name = "player1_color"))
	private Player player1;

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player2_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player2_user_id", referencedColumnName = "user_id"))
	private Player player2;

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player3_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player3_user_id", referencedColumnName = "user_id"))
	private Player player3;

	@Embedded
	@AttributeOverride(name = "color", column = @Column(name = "player4_color"))
	@AssociationOverride(name = "user", joinColumns = @JoinColumn(name = "player4_user_id", referencedColumnName = "user_id"))
	private Player player4;

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

	public boolean isUserAPlayer(User user) {
		if(user == null) return false;
		boolean player1Match = player1.user != null && player1.user.getUserId() == user.getUserId();
		boolean player2Match = player2.user != null && player2.user.getUserId() == user.getUserId();
		boolean player3Match = player3.user != null && player3.user.getUserId() == user.getUserId();
		boolean player4Match = player4.user != null && player4.user.getUserId() == user.getUserId();
		return player1Match || player2Match || player3Match || player4Match;
	}

	public Player getSeat(int seat) throws WeblaboraException {
		switch(seat) {
		case 1: return player1;
		case 2: return player2;
		case 3: return player3;
		case 4: return player4;
		default: throw new WeblaboraException("Invalid seat "+seat+", a game only has four seats");
		}
	}
	
	public Player[] getAllPlayers() {
		return new Player[] {player1, player2, player3, player4};
	}

	public String getName() {
		String name = "Game started @ " + new SimpleDateFormat("yyyy-MM-dd").format(getDateCreated());
		// if(player1.user != null) {
		// name += " by "+player1.user.getName();
		// }
		return name;
	}

}
