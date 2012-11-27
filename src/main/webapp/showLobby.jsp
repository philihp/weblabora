<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://philihp.com/jsp/ora" prefix="ora" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9"/>
<title>WebLabora</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/colorbox.css" />
<link rel="stylesheet" href="css/weblabora.css" />
<!--<script src="https://www.google.com/jsapi"></script>-->
<!-- <script> -->
<!-- 	google.load("jquery", "1.7.1");-->
<!-- </script> -->
<style>
td.avatar-cell {
	height: 50px;
	width: 50px;
}
</style>
<script src="js/lib/jquery-1.7.1.js"></script>
<script src="js/lib/jquery.colorbox.js"></script>
<script>
	$(document).bind('cbox_complete', function() {
		$('#cboxLoadedContent').addClass('styled');
		$('#cboxContent').addClass('styled');
		$('#cboxClose').addClass('styled');
	});
	$(document).bind('cbox_cleanup', function() {
		$('#cboxLoadedContent').removeClass('styled');
		$('#cboxContent').removeClass('styled');
		$('#cboxClose').removeClass('styled');
	});
	
	$(function() {
		$('#findGamesButton').colorbox({
			href : "showGames.do",
			speed : 200,
			transition: "elastic"
		});
		$('#createGameButton').colorbox({
			href : "createGameForm.do",
			speed : 200,
			transition: "elastic"
		});
		
		//$('#findGamesButton').click();
	});
</script>		

</head>

<body>

    <c:import url="jsp/userbar.jsp" />
	
	<div class="container">
	
		<h2>Your Games:</h2>

		<c:choose>
			<c:when test="${empty myGames}">
				<span style="font-style: italic">You are not in any games.</span>
			</c:when>
			<c:otherwise>
			  <table border="1" cellspacing="0" cellpadding="3">
			  	<tr>
			  		<th>ID, Started</th>
					<th>Country</th>
					<th>Length</th>
			  		<th>Red</th>
			  		<th>Green</th>
			  		<th>Blue</th>
			  		<th>White</th>
			  	</tr>
					<c:forEach items="${myGames}" var="game">
						<tr>
							<td>
								#${game.gameId}, <fmt:formatDate value="${game.dateCreated}" pattern="yyyy-MM-dd" /><br />
								<html:link action="/showGame.do" paramId="gameId" paramName="game" paramProperty="gameId">View Game</html:link>
							</td>
							<td>${game.country}</td>
							<td>
								<c:choose>
									<c:when test="${game.length eq 'SHORT'}">
										Short<br />
										<img src="images/clock.svg"/>
									</c:when>
									<c:otherwise>
										Long<br />
										<img src="images/clock.svg" />
										<img src="images/clock.svg" />
									</c:otherwise>
								</c:choose>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 1}">
									<c:choose>
										<c:when test="${empty game.player1.user}">
											vacant
										</c:when>
										<c:otherwise>
											<img src="http://graph.facebook.com/${game.player1.user.facebookId}/picture" class="avatar" title="${game.player1.user.name}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 2}">
									<c:choose>
										<c:when test="${empty game.player2.user and game.players >= 2}">
											vacant
										</c:when>
										<c:otherwise>
											<img src="http://graph.facebook.com/${game.player2.user.facebookId}/picture" class="avatar" title="${game.player2.user.name}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 3}">
									<c:choose>
										<c:when test="${empty game.player3.user and game.players >= 3}">
											vacant
										</c:when>
										<c:otherwise>
											<img src="http://graph.facebook.com/${game.player3.user.facebookId}/picture" class="avatar" title="${game.player3.user.name}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 4}">
									<c:choose>
										<c:when test="${empty game.player4.user and game.players >= 4}">
											vacant
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

	</div>
	
	<div class="container">
		<h2>Games looking for players:</h2>
		<ul>
			<logic:iterate id="game" name="recruitingGames">
			<li>${game}</li>
			</logic:iterate>
		</ul>
		<button id="findGamesButton">Find A Game To Join</button>
		<button id="createGameButton">Create New Game</button>
	</div>
	
	<div class="container">
		<h2>Games currently in progress:</h2>
		<ul>
			<logic:iterate id="game" name="inProgressGames">
			<li>${game}</li>
			</logic:iterate>
		</ul>
	</div>

	<div class="container">
		<h2>Finished Games:</h2>
		<ul>
			<logic:iterate id="game" name="finishedGames">
			<li>${game}</li>
			</logic:iterate>
		</ul>
	</div>	

</body>
</html:html>