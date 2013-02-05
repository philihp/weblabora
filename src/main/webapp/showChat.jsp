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
					<li class="with-border chat-message${(not empty message.player) ? ' player-'.concat(functions:toLowerCase(message.player.color)) : ''}">
						<section>
							<p><img src="https://secure.gravatar.com/avatar/${message.user.emailMD5}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${message.user.username}" />${message.text}</p>
							<footer>
								<span class="author">by ${message.user.username}</span>
								<span class="date">posted on <fmt:formatDate value="${message.dateCreated}" pattern="yyyy-MM-dd" /></span>
							</footer>
						</section>
					</li>
				</core:forEach>
				<li class="with-border">
					<section>
						<html:form action="/createChat.do">
							<html:hidden property="gameId" value="${game.gameId}" />
							<html:text property="text" value="" />
							<html:submit property="submit">Post</html:submit>
						</html:form>
					</section>
				</li>
			</ol>
		</article>
	</body>
</html:html>
