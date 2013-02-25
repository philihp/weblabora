<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
				<c:forEach items="${chat}" var="message">
					<c:choose>
						<c:when test="${empty message.user}">
							<c:set var="posterClass" value="user-system" />
							<c:set var="posterName" value="" />
						</c:when>
						<c:when test="${empty message.player}">
							<c:set var="posterClass" value="user-observer" />
							<c:set var="posterName" value="observer" />
						</c:when>
						<c:otherwise>
							<c:set var="posterClass" value="user-player-${fn:toLowerCase(message.player.color)}" />
							<c:set var="posterName" value="${fn:toLowerCase(message.player.color)} player" />
						</c:otherwise>
					</c:choose>
					<li class="chat-entry chat-message ${posterClass}">
						<section>
							<div class="contents"><c:if test="${not empty message.user}"><img src="https://secure.gravatar.com/avatar/${fn:escapeXml(message.user.emailMD5)}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${fn:escapeXml(message.user.username)}" /></c:if>${message.transformedText}</div>
							<footer>
								<div class="author">by <span class="user-name">${(empty message.user) ? 'WebLabora' : fn:escapeXml(message.user.username)}</span><c:if test="${not empty posterName}"> (as <span class="user-role">${posterName}</span>)</c:if></div>
								<div class="date">posted on <time><fmt:formatDate value="${message.dateCreated}" pattern="yyyy-MM-dd HH:mm:ss" /></time></div>
							</footer>
						</section>
					</li>
				</c:forEach>
				<c:if test="${not empty user}">
					<c:choose>
						<c:when test="${empty player}">
							<c:set var="posterClass" value="user-observer" />
							<c:set var="posterName" value="observer" />
						</c:when>
						<c:otherwise>
							<c:set var="posterClass" value="user-player-${fn:toLowerCase(player.color)}" />
							<c:set var="posterName" value="${fn:toLowerCase(player.color)} player" />
						</c:otherwise>
					</c:choose>
					<li class="chat-entry edited ${posterClass}">
						<section>
							<html:form action="/createChat.do" acceptCharset="UTF-8" >
								<div class="contents"><html:textarea property="text" value="" /></div>
								<footer>
									<html:hidden property="gameId" value="${game.gameId}" />
									<div class="author">by <span class="user-name">${(empty user) ? 'WebLabora' : fn:escapeXml(user.username)}</span><c:if test="${not empty posterName}"> (as <span class="user-role">${posterName}</span>)</c:if></div>
									<div class="date"><html:submit property="submit">Post</html:submit></div>
								</footer>
							</html:form>
						</section>
					</li>
				</c:if>
			</ol>
		</article>
	</body>
</html:html>
