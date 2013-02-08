package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.philihp.weblabora.model.WeblaboraException;

@Entity(name = "Chat")
@Table(name = "weblabora_chat")
@Access(FIELD)
public class Chat extends BasicEntity {
	public static enum Action {
		create,
		edit,
		delete
	}

	@Id
	@GeneratedValue
	@Column(name="chat_id", nullable=false)
	private int chatId;

	@ManyToOne(fetch=LAZY, targetEntity=com.philihp.weblabora.jpa.Game.class)
	@JoinColumn(name="ref_game_id", referencedColumnName="game_id", nullable=false)
	private Game game;

	@ManyToOne(fetch=LAZY, targetEntity=com.philihp.weblabora.jpa.User.class)
	@JoinColumn(name="ref_user_id", referencedColumnName="user_id", nullable=true)
	private User user;

	@Basic
	@Column(name="player_seat", nullable=true)
	private Integer playerSeat;

	@Basic
	@Column(name="action", nullable=false)
	@Enumerated(STRING)
	private Action action;

	@Basic
	@Column(name="text", nullable=true)
	private String text;

	@OneToOne(fetch=LAZY, targetEntity=com.philihp.weblabora.jpa.Chat.class)
	@JoinColumn(name="ref_chat_id", referencedColumnName="chat_id")
	private Chat refChat;

	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getPlayerSeat() {
		return playerSeat;
	}

	public void setPlayerSeat(Integer playerSeat) {
		this.playerSeat = playerSeat;
	}

	@Transient
	public Game.Player getPlayer() throws WeblaboraException {
		if (playerSeat == null) {
			return null;
		} else {
			return game.getSeat(playerSeat);
		}
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Chat getRefChat() {
		return refChat;
	}

	public void setRefChat(Chat refChat) {
		this.refChat = refChat;
	}
}
