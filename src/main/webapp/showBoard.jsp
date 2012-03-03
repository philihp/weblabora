<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://philihp.com/jsp/ora" prefix="ora" %>

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
		Current State ID: ${game.state.stateId}<br />
		
		<div class="wheel">
			<svg style="width: 300px; height: 300px;" viewbox="-150.5 -150.5 300 300">
			  <defs>
    			<linearGradient id="housefill" x1="0%" y1="0%" x2="100%" y2="0%">
      			<stop offset="0%" style="stop-color:#004e85;stop-opacity:1" />
      			<stop offset="100%" style="stop-color:#1973b2;stop-opacity:1" />
    			</linearGradient>
			    <filter id="shadow">
			      <feGaussianBlur in="SourceGraphic" stdDeviation="5" />
			    </filter>
  			</defs>
  			<g id="shadowMask" opacity="0.1">
					<polyline points="${wheelArt.mask}" fill="black" filter="url(#shadow)" />
				</g>
				<g id="wheel">
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeA}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeB}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeC}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeD}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeE}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeF}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeG}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeH}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeI}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeJ}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeK}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeL}" />
				<polyline fill="#fcfcfc" stroke="#b3b3b3" stroke-width="1" points="${wheelArt.wedgeM}" />
				</g>
				<g id="settlement-a" transform="rotate(${wheelArt.rotG})">
					<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
					<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">A</text>
				</g>
				<g id="settlement-b" transform="rotate(${wheelArt.rotJ})">
					<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
					<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">B</text>
				</g>
				<g id="settlement-c" transform="rotate(${wheelArt.rotC})">
					<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
					<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle;  fill:#fff">C</text>
				</g>
				<g id="settlement-d" transform="rotate(${wheelArt.rotF})">
					<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
					<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">D</text>
				</g>
				<g id="settlement-e" transform="rotate(${wheelArt.rotM})">
					<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
					<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">E</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.grain.position)})">
					<text x="0" y="-114" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#000">Grain</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.sheep.position)})">
					<text x="0" y="-105" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#000">Sheep</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.grape.position)})">
					<text x="0" y="-96" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Grape</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.stone.position)})">
					<text x="0" y="-87" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Stone</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.joker.position)})">
					<text x="0" y="-78" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Joker</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.wood.position)})">
					<text x="0" y="-69" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Wood</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.clay.position)})">
					<text x="0" y="-60" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Clay</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.peat.position)})">
					<text x="0" y="-51" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Peat</text>
				</g>
				<g id="arm"  transform="rotate(${ora:deg(board.wheel.arm.position)-13.846})" style="font-size: 10px; text-anchor: middle">
				  <path d="${wheelArt.armPath}"
				        style="fill:#ffffff; fill-opacity: 1; stroke:#686868; stroke-width: 1" />
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotA})">10</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotB})">9</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotC})">8</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotD})">8</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotE})">7</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotF})">7</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotG})">6</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotH})">6</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotI})">5</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotJ})">4</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotK})">3</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotL})">2</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotM})">0</text>
				  <path d="${wheelArt.arrowPath}" fill="#000" />
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



		<c:forEach items="${board.players}" var="player" varStatus="playerStatus">
			<div class="board" id="board${playerStatus.index+1}">
				<table>
					<c:forEach items="${player.landscape.table}" var="row">
						<tr>
							<c:forEach items="${row}" var="cell">
								<td>${cell.terrainType.properCase}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
			</div>
		</c:forEach>

		<c:forEach items="${game.moves}" var="move" varStatus="status">
				<c:if test="${status.index % 5 == 0}">
						<b>Round <fmt:formatNumber value="${status.index / 5 + 1}" maxFractionDigits="0"/></b><br />
				</c:if>
				${move}<br />
				<c:if test="${status.index % 5 == 4 || status.last}">
				...
				</c:if>
			</c:forEach>
		</table>
		
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