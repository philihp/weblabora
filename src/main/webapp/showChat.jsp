<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="functions"%>

<!DOCTYPE html>
<html:html>
	<head>
		<title>WebLabora</title>
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/chat.css" />
	</head>
	<body>
		<article>
			<ol>
				<core:forEach items="${chat}" var="message">
					<li class="chat-entry chat-message ${(empty message.user) ? 'user-system' : ((empty message.player) ? 'user-observer' : 'user-player-'.concat(functions:toLowerCase(message.player.color)))}">
						<section>
							<p><core:if test="${not empty message.user}"><img src="https://secure.gravatar.com/avatar/${message.user.emailMD5}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${message.user.username}" /></core:if>${message.text}</p>
							<footer>
								<div class="author">by <span class="user-name">${(empty message.user) ? 'WebLabora' : message.user.username}</span><core:if test="${not empty message.user}"> (as <span class="user-role">${(empty message.player) ? 'observer' : functions:toLowerCase(message.player.color).concat(' player')}</span>)</core:if></div>
								<div class="date">posted on <time><fmt:formatDate value="${message.dateCreated}" pattern="yyyy-MM-dd HH:mm:ss" /></time></div>
							</footer>
						</section>
					</li>
				</core:forEach>
				<core:if test="${not empty user}">
					<li class="chat-entry ${(empty game.getUsersPlayer(user)) ? 'user-observer' : 'user-player-'.concat(functions:toLowerCase(game.getUsersPlayer(user).color))}">
						<section>
							<html:form action="/createChat.do">
								<html:hidden property="gameId" value="${game.gameId}" />
								<html:text property="text" value="" />
								<html:submit property="submit">Post</html:submit>
							</html:form>
						</section>
					</li>
				</core:if>
			</ol>
		</article>
	</body>
</html:html>
