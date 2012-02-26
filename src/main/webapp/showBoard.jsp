<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://philihp.com/jsp/ora" prefix="ora"%>
<!DOCTYPE html>
<html:html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9"/>
<title>WebLabora</title>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/colorbox.css" />
<link rel="stylesheet" href="css/weblabora.css" />
<script src="https://www.google.com/jsapi"></script>
<script>
	google.load("jquery", "1.7.1");
</script>
<script src="js/jquery.colorbox-min.js"></script>
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

	$().ready(function() {
		$('#findGamesButton').colorbox({
			href : "showGames.do",
			speed : 150,
			transition: "elastic"
		});
	});
</script>

</head>

<body>

	<div class="userbar">
		<div class="container">
			<img src="http://graph.facebook.com/${user.facebookId}/picture" height="50" width="50" title="${user.facebookId}"/>
			
			${user.name}
			|
			<html:form action="/setActiveGame.do" method="GET" style="display: inline">
				<html:select property="gameId" value="${user.activeGame.gameId}">
					<html:option value="">(no active game)</html:option>
					<html:options collection="myGames" property="gameId" labelProperty="name" />
					<html:submit>Set Active Game</html:submit>
				</html:select>
			</html:form>
			|
			<button id="findGamesButton">Find Games</button>
		</div>
	</div>
		
	
	<div class="container">
	
		<div class="wheel">
			<svg>
				<g id="wheel">
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeA}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeB}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeC}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeD}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeE}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeF}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeG}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeH}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeI}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeJ}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeK}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeL}" />
				<polyline fill="#e7e7e7" stroke="#202020" stroke-width="1" points="${wheelArt.wedgeM}" />
				</g>
				<g id="arm"  transform="rotate(0,150.5,150.5)" style="font-size: 10px; text-anchor: middle">
				<!--<circle fill="#ededed" stroke="#202020" cx="${wheelArt.centerX}" cy="${wheelArt.centerY}" r="${wheelArt.armRadius}" />-->
				  <path d="${wheelArt.armPath}"
				        style="fill:#e7e7e7; fill-opacity: 1; stroke:#202020; stroke-width: 1" />
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotA},${wheelArt.centerX},${wheelArt.centerY})">10</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotB},${wheelArt.centerX},${wheelArt.centerY})">9</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotC},${wheelArt.centerX},${wheelArt.centerY})">8</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotD},${wheelArt.centerX},${wheelArt.centerY})">8</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotE},${wheelArt.centerX},${wheelArt.centerY})">7</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotF},${wheelArt.centerX},${wheelArt.centerY})">7</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotG},${wheelArt.centerX},${wheelArt.centerY})">6</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotH},${wheelArt.centerX},${wheelArt.centerY})">6</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotI},${wheelArt.centerX},${wheelArt.centerY})">5</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotJ},${wheelArt.centerX},${wheelArt.centerY})">4</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotK},${wheelArt.centerX},${wheelArt.centerY})">3</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotL},${wheelArt.centerX},${wheelArt.centerY})">2</text>
				  <text x="${wheelArt.centerX}" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotM},${wheelArt.centerX},${wheelArt.centerY})">0</text>
				</g>
			</svg>
		</div>

		<ul class="tabs">
			<li>
				<img src="http://graph.facebook.com/${game.player1.user.facebookId}/picture" height="50" width="50" title="${game.player1.user.facebookId}"/>
				${game.player1.user.name}
			</li>
			<li>
				<img src="http://graph.facebook.com/${game.player2.user.facebookId}/picture" height="50" width="50" title="${game.player2.user.facebookId}"/>
				${game.player2.user.name}
			</li>
			<li>
				<img src="http://graph.facebook.com/${game.player3.user.facebookId}/picture" height="50" width="50" title="${game.player3.user.facebookId}"/>
				${game.player3.user.name}
			</li>
			<li>
				<img src="http://graph.facebook.com/${game.player4.user.facebookId}/picture" height="50" width="50" title="${game.player4.user.facebookId}"/>
				${game.player4.user.name}
			</li>
		</ul>
		
		
		
		<div class="board" id="board1">
			<table>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
			</table>
		</div>
		<div class="board board-inactive" id="board2">
			<table>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
			</table>
		</div>
		<div class="board board-inactive" id="board3">
			<table>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
			</table>
		</div>
		<div class="board board-inactive" id="board4">
			<table>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
				<tr>
					<td>Peat</td>
					<td>Forest</td>
					<td>Plains</td>
					<td>Plains</td>
					<td>Plains</td>
				</tr>
			</table>
		</div>
		
	
		Moves:
		<table>
			<c:forEach items="${game.moves}" var="move" varStatus="status">
				<c:if test="${status.index % 5 == 0}">
					<tr>
						<th>Round <fmt:formatNumber value="${status.index / 5 + 1}" maxFractionDigits="0"/></th>
				</c:if>
				<td>${move}</td>
				<c:if test="${status.index % 5 == 4 || status.last}">
					</tr>
				</c:if>
			</c:forEach>
		</table>
		
		Current State ID: ${game.state.stateId}<br />
		Current Move: ${game.state.moveNumber}<br /> 
		
		<hr />
		
		Board: ${board}
		
		<hr />
		
		Previously Explored Moves:
		<ul>
			<c:forEach items="${game.state.dstStates}" var="possibleState">
				<li>${possibleState.stateId} - ${possibleState.token}</li>
			</c:forEach>
		</ul>
		Explore New Move:
		<html:form action="/makeMove.do">
			<html:hidden property="gameId" value="${game.gameId}" />
			<html:text property="token" value="" />
			<html:submit>Explore</html:submit>
		</html:form>
		
		<hr />
	
		<html:form action="/authenticateHijack.do">
			<html:text property="facebookId"></html:text>
			<html:submit>Hijack</html:submit>
		</html:form>
		
	</div>

</body>
</html:html>