<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html:html>
<head>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/colorbox.css" />
<link rel="stylesheet" href="css/weblabora.css" />
</head>
<body>

	<div>
		<c:choose>
			<c:when test="${empty games}">
				<span style="font-style: italic"><b>No Games Found</b><br /> <br />There are no games available with open seats.</span>
			</c:when>
			<c:otherwise>
			  The following games have seats open:
			  <table>
			  	<tr>
			  		<th>ID, Started</th>
					<th>Country</th>
					<th>Length</th>
			  		<th>Red</th>
			  		<th>Green</th>
			  		<th>Blue</th>
			  		<th>White</th>
			  	</tr>
					<c:forEach items="${games}" var="game">
						<tr>
							<td>#${game.gameId}, <fmt:formatDate value="${game.dateCreated}" pattern="yyyy-MM-dd" /></td>
							<td>${game.country}</td>
							<td>${game.length}</td>
							<td>
								<c:if test="${game.players >= 1}">
									<c:choose>
										<c:when test="${empty game.player1.user}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="1"/>
												<html:submit>Join</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="http://graph.facebook.com/${game.player1.user.facebookId}/picture" class="avatar" title="${game.player1.user.name}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td>
								<c:if test="${game.players >= 2}">
									<c:choose>
										<c:when test="${empty game.player2.user and game.players >= 2}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="2"/>
												<html:submit>Join</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="http://graph.facebook.com/${game.player2.user.facebookId}/picture" class="avatar" title="${game.player2.user.name}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td>
								<c:if test="${game.players >= 3}">
									<c:choose>
										<c:when test="${empty game.player3.user and game.players >= 3}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="3"/>
												<html:submit>Join</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="http://graph.facebook.com/${game.player3.user.facebookId}/picture" class="avatar" title="${game.player3.user.name}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td>
								<c:if test="${game.players >= 4}">
									<c:choose>
										<c:when test="${empty game.player4.user and game.players >= 4}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="4"/>
												<html:submit>Join</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="http://graph.facebook.com/${game.player4.user.facebookId}/picture" class="avatar" title="${game.player4.user.name}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>

		<hr />
		<script>
		$('#createGameButton').colorbox({
			href : "createGameForm.do",
			speed : 200,
			transition: "elastic"
		});
		</script>
		<button id="createGameButton">Create New Game</button>

	</div>

</body>
</html:html>
