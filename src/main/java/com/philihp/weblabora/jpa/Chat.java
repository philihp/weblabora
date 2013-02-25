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

import org.apache.commons.lang.StringEscapeUtils;

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

	@Basic(fetch=LAZY)
	@Column(name="original_text", nullable=true)
	private String originalText;

	@Basic
	@Column(name="transformed_text", nullable=true)
	private String transformedText;

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

	public String getOriginalText() {
		return originalText;
	}

	public void setText(String originalText) {
		String transformedText = applyTextTransformation(originalText);

		this.originalText = originalText;
		this.transformedText = transformedText;
	}

	public String getTransformedText() {
		return transformedText;
	}

	public Chat getRefChat() {
		return refChat;
	}

	public void setRefChat(Chat refChat) {
		this.refChat = refChat;
	}

	/**
	 * Returns text escaped and extended with mark-up.
	 *
	 * This function makes user provided text usable directly in HTML.
	 *
	 * We keep the original (user provided) text anyway to be able later to
	 * regenerate with new features. Also future editing of the text would
	 * better use the original text.
	 *
	 * @param source Text to be transformed.
	 * @return Text escaped and extended with mark-up.
	 */
	// TODO: Add at least links and emails detection.
	private String applyTextTransformation(String source) {
		String escaped = StringEscapeUtils.escapeXml(source);

		String[] lines = escaped.split("\\r\\n|\\r|\\n", -1);

		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < lines.length; ++i) {
			if(lines[i].isEmpty()) {
				builder.append("<br />");
			} else {
				builder.append("<p>");
				builder.append(lines[i]);
				builder.append("</p>");
			}
		}

		return builder.toString();
	}
}
