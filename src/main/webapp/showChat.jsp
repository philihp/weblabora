<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html:html>
	<head>
		<title>WebLabora</title>
		<link rel="stylesheet" href="css/style.css" />
		<link rel="stylesheet" href="css/colorbox.css" />
		<link rel="stylesheet" href="css/weblabora.css" />
	</head>
	<body>
		<article>
			<ol>
				<core:forEach items="${chat}" var="message">
					<li class="withBorder">
						<section>
							<header>
								<h1>${message.user.username}</h1>
							</header>
							<img src="https://secure.gravatar.com/avatar/${message.user.emailMD5}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${message.user.username}" />
							<p>${message.text}</p>
							<footer>
								<span>Posted on: <fmt:formatDate value="${message.dateCreated}" pattern="yyyy-MM-dd" /></span>
							</footer>
						</section>
					</li>
				</core:forEach>
				<li>
					<html:form action="/makeCreateChat.do">
						<html:hidden property="gameId" value="${game.gameId}" />
						<html:text property="text" value="" />
						<html:submit property="submit">Post</html:submit>
					</html:form>
				</li>
			</ol>
		</article>
	</body>
</html:html>
