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
<!--<script src="//www.google.com/jsapi"></script>-->
<!-- <script> -->
<!-- 	google.load("jquery", "1.7.1");-->
<!-- </script> -->
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

	function showboard(player) {
		$('#tab1').removeClass('tab--selected').addClass('tab--unselected');
		$('#tab2').removeClass('tab--selected').addClass('tab--unselected');
		$('#tab3').removeClass('tab--selected').addClass('tab--unselected');
		$('#tab4').removeClass('tab--selected').addClass('tab--unselected');
		$('#tab'+player).removeClass('tab--unselected').addClass('tab--selected');
		$('#board1').removeClass('board--selected').addClass('board--unselected');
		$('#board2').removeClass('board--selected').addClass('board--unselected');
		$('#board3').removeClass('board--selected').addClass('board--unselected');
		$('#board4').removeClass('board--selected').addClass('board--unselected');
		$('#board'+player).removeClass('board--unselected').addClass('board--selected');
	}
	
	$(function() {
		$('#findGamesButton').colorbox({
			href : "showGames.do",
			speed : 200,
			transition: "elastic"
		});

		$(".building-link").colorbox({
			rel:'building-link',
			speed : 200,
			transition: "elastic",
			current: "",
			next: "",
			previous: ""
		});
		$(".future-building-link").colorbox({
			rel:'future-building-link',
			speed : 200,
			transition: "elastic",
			current: "",
			next: "",
			previous: ""
		});
		$(".erection-link").colorbox({
			rel:'erection-link',
			speed : 200,
			transition: "elastic",
			current: "",
			next: "",
			previous: ""
		});
		$(".settlement-link").colorbox({
			rel:'settlement-link',
			speed : 200,
			transition: "elastic",
			current: "",
			next: "",
			previous: ""
		});

		$(".show-future-building-button").click(function(event) {
			event.preventDefault();
			$(".future-building").css('display','inline-block');
			$(this).hide();
			$('.hide-future-building-button').show();
		});
		$(".hide-future-building-button").click(function(event) {
			event.preventDefault();
			$(".future-building").css('display','none');
			$(this).hide();
			$('.show-future-building-button').show();
		});
		

		<c:forEach items="${board.players}" var="player" varStatus="playerStatus">
		$(".show-future-settlements-button-${fn:toLowerCase(player.color)}").click(function(event) {
			event.preventDefault();
			$(".future-settlement-${fn:toLowerCase(player.color)}").css('display','inline-block');
			$(this).hide();
			$('.hide-future-settlements-button-${fn:toLowerCase(player.color)}').show();
		});
		$(".hide-future-settlements-button-${fn:toLowerCase(player.color)}").click(function(event) {
			event.preventDefault();
			$(".future-settlement-${fn:toLowerCase(player.color)}").css('display','none');
			$(this).hide();
			$('.show-future-settlements-button-${fn:toLowerCase(player.color)}').show();
		});
		</c:forEach>
		
		$('#tab1').click(function() {showboard(1);});
		$('#tab2').click(function() {showboard(2);});
		$('#tab3').click(function() {showboard(3);});
		$('#tab4').click(function() {showboard(4);});
		
		$('#gamesList').change(function() {
			$(this).closest('form').submit();
		});
		
	});
</script>

<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-27506789-5']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</head>

<body>
	<div class="userbar">
		<div class="container">
			<img src="//graph.facebook.com/${user.facebookId}/picture" height="50" width="50" title="${user.facebookId}"/>
			
			${user.name}
			|
			<html:form action="/showGame.do" method="GET" style="display: inline">
				<html:select property="gameId" value="${user.activeGame.gameId}" styleId="gamesList">
					<html:options collection="myGames" property="gameId" labelProperty="name" />
					<html:submit>&#x25B6;</html:submit>
				</html:select>
			</html:form>
			|
			<html:form action="/leaveGame.do" method="POST" style="display: inline">
				<html:hidden property="gameId" />
				<html:submit>Leave Game</html:submit>
			</html:form>
			|
			<button id="findGamesButton">Join a Game</button>
		</div>
	</div>
		
	MODE: ${board.mode.players} ${board.mode.length} ${board.mode.country}
	<div class="container">
		<div id="fb-root"></div>
		<div class="wheel">
			<!-- ${board.mode.players} ${board.mode.country} ${board.mode.length} -->
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
				<c:if test="${board.mode.players eq 'FOUR' and board.mode.length eq 'SHORT'}">
					<g id="production-1" transform="rotate(${wheelArt.rotA})">
						<text x="0" y="-111" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#000">Sh</text>
						<text x="0" y="-100" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#000">Gn</text>
					</g>
					<g id="settlement-a" transform="rotate(${wheelArt.rotC})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">A</text>
					</g>
					<g id="settlement-b" transform="rotate(${wheelArt.rotE})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">B</text>
					</g>
					<g id="settlement-c" transform="rotate(${wheelArt.rotG})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle;  fill:#fff">C</text>
					</g>
					<g id="settlement-d" transform="rotate(${wheelArt.rotI})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">D</text>
					</g>
					<g id="settlement-e" transform="rotate(${wheelArt.rotM})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">E</text>
					</g>
				</c:if>
				<c:if test="${board.mode.players eq 'FOUR' and board.mode.length eq 'LONG'}">
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
					<g id="settlement-e" transform="rotate(${wheelArt.rotL})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">E</text>
					</g>
				</c:if>
				<c:if test="${board.mode.players eq 'THREE' and board.mode.length eq 'LONG'}">
					<g id="settlement-a" transform="rotate(${wheelArt.rotF})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">A</text>
					</g>
					<g id="settlement-b" transform="rotate(${wheelArt.rotK})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">B</text>
					</g>
					<g id="settlement-c" transform="rotate(${wheelArt.rotB})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle;  fill:#fff">C</text>
					</g>
					<g id="settlement-d" transform="rotate(${wheelArt.rotG})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">D</text>
					</g>
					<g id="settlement-e" transform="rotate(${wheelArt.rotL})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">E</text>
					</g>
				</c:if>
				<c:if test="${board.mode.players eq 'THREE' and board.mode.length eq 'SHORT'}">
					<g id="A-welfare" transform="rotate(${wheelArt.rotA})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Sheep.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Grain.png" />
					</g>
					<g id="B-welfare" transform="rotate(${wheelArt.rotB})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Clay.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Grain.png" />
					</g>
					<g id="C-welfare" transform="rotate(${wheelArt.rotC})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Wood.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Grain.png" />
					</g>
					<g id="settlement-a" transform="rotate(${wheelArt.rotC})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">A</text>
					</g>
					<g id="D-welfare" transform="rotate(${wheelArt.rotD})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Stone.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Grain.png" />
					</g>
					<g id="E-welfare" transform="rotate(${wheelArt.rotE})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Stone.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Peat.png" />
					</g>
					<g id="settlement-b" transform="rotate(${wheelArt.rotE})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">B</text>
					</g>
					<g id="F-welfare" transform="rotate(${wheelArt.rotF})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Stone.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Clay.png" />
					</g>
					<g id="G-welfare" transform="rotate(${wheelArt.rotG})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Stone.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Wood.png" />
					</g>
					<g id="settlement-c" transform="rotate(${wheelArt.rotG})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle;  fill:#fff">C</text>
					</g>
					<g id="H-welfare" transform="rotate(${wheelArt.rotH})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Stone.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Nickel.png" />
					</g>
					<g id="I-welfare" transform="rotate(${wheelArt.rotI})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Stone.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Meat.png" />
					</g>
					<g id="settlement-d" transform="rotate(${wheelArt.rotI})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">D</text>
					</g>
					<g id="J-welfare" transform="rotate(${wheelArt.rotJ})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Book.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Grain.png" />
					</g>
					<g id="K-welfare" transform="rotate(${wheelArt.rotK})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Pot.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Clay.png" />
					</g>
					<g id="L-welfare" transform="rotate(${wheelArt.rotL})">
						<image height="24" width="24" x="-25" y="-135" xlink:href="images/chit/Ornament.png" />
						<image height="24" width="24" x="0" y="-135" xlink:href="images/chit/Wood.png" />
					</g>
					<g id="settlement-e" transform="rotate(${wheelArt.rotM})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">E</text>
					</g>
				</c:if>
				<c:if test="${board.mode.players eq 'TWO' and board.mode.length eq 'LONG'}">
					<g id="settlement-a" transform="rotate(${wheelArt.rotG})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">A</text>
					</g>
					<g id="settlement-b" transform="rotate(${wheelArt.rotA})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">B</text>
					</g>
					<g id="settlement-c" transform="rotate(${wheelArt.rotH})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle;  fill:#fff">C</text>
					</g>
					<g id="settlement-d" transform="rotate(${wheelArt.rotB})">
						<path d="${wheelArt.housePath}" style="fill:url(#housefill); fill-opacity: 1; stroke:#202020; stroke-width: 1" />
						<text x="0" y="${wheelArt.houseTextY}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#fff">D</text>
					</g>
				</c:if>
				<g id="grape" transform="rotate(${ora:deg(board.wheel.grape.position)})">
					<text x="0" y="${board.wheel.grape.radius}" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Grape</text>
				</g>
				<g id="stone" transform="rotate(${ora:deg(board.wheel.stone.position)})">
					<text x="0" y="${board.wheel.stone.radius}" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Stone</text>
				</g>
				<g id="grain" transform="rotate(${ora:deg(board.wheel.grain.position)})">
					<text x="0" y="${board.wheel.grain.radius}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#000">Grain</text>
				</g>
				<g id="sheep" transform="rotate(${ora:deg(board.wheel.sheep.position)})">
					<text x="0" y="${board.wheel.sheep.radius}" style="font-size: 9px; font-weight: 100; text-anchor: middle; fill:#000">Sheep</text>
				</g>
				<g id="joker" transform="rotate(${ora:deg(board.wheel.joker.position)})">
					<text x="0" y="${board.wheel.joker.radius}" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Joker</text>
				</g>
				<g id="wood" transform="rotate(${ora:deg(board.wheel.wood.position)})">
					<text x="0" y="${board.wheel.wood.radius}" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Wood</text>
				</g>
				<g id="clay" transform="rotate(${ora:deg(board.wheel.clay.position)})">
					<text x="0" y="${board.wheel.clay.radius}" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Clay</text>
				</g>
				<g id="peat" transform="rotate(${ora:deg(board.wheel.peat.position)})">
					<text x="0" y="${board.wheel.peat.radius}" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Peat</text>
				</g>
				<g id="coin" transform="rotate(${ora:deg(board.wheel.coin.position)})">
					<text x="0" y="${board.wheel.coin.radius}" style="font-size: 9px; font-weight: 100; kerning:-0.5; text-anchor: middle; fill:#000">Coin</text>
				</g>
				<g id="arm"  transform="rotate(${ora:deg(board.wheel.arm.position)-board.armOffset})" style="font-size: 10px; text-anchor: middle">
				  <path d="${wheelArt.armPath}"
				        style="fill:#ffffff; fill-opacity: 1; stroke:#686868; stroke-width: 1" />
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotA})">${board.wheel.armValues[12]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotB})">${board.wheel.armValues[11]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotC})">${board.wheel.armValues[10]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotD})">${board.wheel.armValues[9]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotE})">${board.wheel.armValues[8]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotF})">${board.wheel.armValues[7]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotG})">${board.wheel.armValues[6]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotH})">${board.wheel.armValues[5]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotI})">${board.wheel.armValues[4]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotJ})">${board.wheel.armValues[3]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotK})">${board.wheel.armValues[2]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotL})">${board.wheel.armValues[1]}</text>
				  <text x="0" y="${wheelArt.armTextY}" transform="rotate(${wheelArt.rotM})">${board.wheel.armValues[0]}</text>
				  <path d="${wheelArt.arrowPath}" fill="#000" />
				</g>
			</svg>
		</div>
		
		<div class="info">
			<c:choose>
				<c:when test="${board.gameOver}">
					<h3 class="info-line">Game Over</h3>
				</c:when>
				<c:when test="${board.settling}">
					<h3 class="info-line">Settlement ${board.settlementRound}</h3>
					<h3 class="info-line">Waiting on ${board.activePlayerColor}.</h3>
				</c:when>
				<c:otherwise>
					<h3 class="info-line">Round ${board.round}, ${board.move} move.</h3>
					<h3 class="info-line">Waiting on ${board.activePlayerColor}.</h3>
				</c:otherwise>
			</c:choose>
			<hr />
			<h4 class="info-line">Plot Costs: 
				<c:forEach items="${board.plotCosts}" var="cost" varStatus="costStatus">
					${cost}<c:if test="${not costStatus.last}">,</c:if>
				</c:forEach>
			</h4>
			<h4 class="info-line">District Costs: 
				<c:forEach items="${board.districtCosts}" var="cost" varStatus="costStatus">
					${cost}<c:if test="${not costStatus.last}">,</c:if>
				</c:forEach>
			</h4>
			<a class="show-future-building-button">Show Future Buildings</a>
			<a class="hide-future-building-button">Hide Future Buildings</a>
		</div>
		
		<div class="building-list"><!-- comment out white-space for inline-block spacing
		  <c:forEach items="${board.unbuiltBuildings}" var="building">
		  	--><div class="building">
		  	  <a class="building-link" href="images/building/${building.image}.png" title="${building.id}"><img src="images/building/${building.image}.png" class="building-image" /></a>
		  	</div><!--
		  </c:forEach>
		  <c:forEach items="${board.futureBuildings}" var="building">
		  	--><div class="future-building">
		  	  <a class="future-building-link" href="images/building/${building.image}.png" title="${building.id}"><img src="images/building/${building.image}.png" class="future-building-image" /></a>
		  	</div><!--
		  </c:forEach>
		--></div>
			
		<ul class="tabs">
			<c:forEach items="${board.players}" var="player" varStatus="playerStatus">
				<li id="tab${playerStatus.index+1}" class="tab tab--${player.activeClass} tab--${player.selectedClass} tab--${fn:toLowerCase(player.color)}">
					<div style="float:right"><a href="http://facebook.com/${player.user.facebookId}" target="_blank"><img src="images/facebook_16.png" /></a></div>
					<img src="//graph.facebook.com/${player.user.facebookId}/picture" height="50" width="50" title="${player.user.facebookId}"/>
					${player.user.name}
				</li>
			</c:forEach>
		</ul>
		<c:forEach items="${board.players}" var="player" varStatus="playerStatus">
			<div class="board board--${player.activeClass} board--${player.selectedClass}" id="board${playerStatus.index+1}">
	
				<c:set var="player" value="${player}" scope="request" />
				<c:import url="jsp/inventory.jsp" />
				
				<hr style="clear: both" />
	
				<table>
					<c:forEach items="${player.landscape.table}" var="row" varStatus="rowStatus">
						<tr>
							<c:forEach items="${row}" var="cell">
								<c:if test="${cell.terrainType ne 'HIDDEN'}">
									<td${cell.terrainType.rowspanAttr}>
										<c:choose>
											<c:when test="${not empty cell.erection}">
												<div class="building building-${fn:toLowerCase(cell.erection.clergyman.type)}-${fn:toLowerCase(player.color)}">
													<a class="erection-link" href="images/building/${cell.erection.image}.png" title="${cell.erection.id}"><img src="images/building/${cell.erection.image}.png" class="building-image" /></a>
											  	</div>
											</c:when>
											<c:when test="${cell.terrainType eq 'FOREST'}">
												<img src="images/building/Wood.png" class="landscape-tile" title="${cell.coords}" />
											</c:when>
											<c:when test="${cell.terrainType eq 'MOOR'}">
												<img src="images/building/Peat.png" class="landscape-tile" title="${cell.coords}" />
											</c:when>
											<c:otherwise>
												${cell.terrainType.properCase}<br />
												${cell.coords}
											</c:otherwise>
										</c:choose>
									</td>
								</c:if>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				
				<hr />
				
				<c:if test="${not empty player.startingMarker}">
				[Starting Player (contract cost is ${board.startingMarker.cost})]
				</c:if>
				
				<h3>Clergymen</h3>
				<c:if test="${empty player.layBrother1.location}"><img src="images/laybrother-${fn:toLowerCase(player.color)}.svg" alt="Lay Brother" /></c:if>
				<c:if test="${board.mode.secondLayBrotherUsed and empty player.layBrother2.location}"><img src="images/laybrother-${fn:toLowerCase(player.color)}.svg" alt="Lay Brother" /></c:if>
				<c:if test="${empty player.prior.location}"><img src="images/prior-${fn:toLowerCase(player.color)}.svg" /></c:if>
								
				<hr />
				
				<div class="settlement-list"><!-- comment out white-space for inline-block spacing
				  <c:forEach items="${player.unbuiltSettlements}" var="settlement">
				  	--><div class="settlement">
				  		<a class="settlement-link" href="images/building/${settlement.image}.png" title="${settlement.id}"><img src="images/building/${settlement.image}.png" class="settlement-image" /></a>
				  	</div><!--
				  </c:forEach>
				  <c:forEach items="${board.futureSettlements}" var="settlement">
				  	--><div class="settlement future-settlement future-settlement-${fn:toLowerCase(player.color)}">
				  	  <a class="settlement-link settlement-link-${fn:toLowerCase(player.color)}" href="images/building/${settlement.image}.png" title="${settlement.id}"><img src="images/building/${settlement.image}.png" class="settlement-image settlement-image-${fn:toLowerCase(player.color)}" /></a>
				  	</div><!--
				  </c:forEach>
				--></div>
				
			<a class="show-future-settlements-button show-future-settlements-button-${fn:toLowerCase(player.color)}">Show Future Settlements</a>
			<a class="hide-future-settlements-button hide-future-settlements-button-${fn:toLowerCase(player.color)}">Hide Future Settlements</a>
				
				<c:remove var="player"/>
			</div>
		</c:forEach>
		
		<c:if test="${empty param.stateId}">
			<hr />
			New Move:
			<html:form action="/makeMove.do">
				<html:hidden property="stateId" value="${game.state.stateId}" />
				<html:hidden property="gameId" value="${game.gameId}" />
				<html:text property="token" value="${savedMove}" />
				<html:submit property="submit">Explore</html:submit>
				<html:submit property="submit">Save (for later)</html:submit>
			</html:form>
			<a href="http://philihp.github.com/WebLabora/">Command Syntax Reference</a>
		</c:if>
		
		<hr />
		
		<c:forEach items="${board.moveListReversed}" var="move">
			<div class="movelist-color">${move.color}</div>
			<div class="movelist-move">
			<c:choose>
				<c:when test="${empty move.state}">
					<b>${move.text}</b>
				</c:when>
				<c:when test="${move.state.stateId == 0 and empty board.nextState}">
					...
				</c:when>
				<c:when test="${move.state.stateId == 0 and board.nextState.stateId == game.state.stateId}">
					[<a href="showGame.do?gameId=${game.gameId}">view</a>] ...
				</c:when>
				<c:when test="${move.state.stateId == 0}">
					[<a href="showGame.do?gameId=${game.gameId}&amp;stateId=${board.nextState.stateId}">view</a>] ...
				</c:when>
				<c:when test="${move.state.stateId == param.stateId or move.state.stateId == game.state.stateId}">
					<span title="First explored by ${move.state.explorer.name} on <fmt:formatDate value="${move.state.dateCreated}" pattern="yyyy-MM-dd" />">[view] ${move.text}</span>
					<c:if test="${game.undoable}">
						<html:form style="display: inline" action="/undoMove.do">
							<html:hidden property="gameId"/>
							<html:hidden property="stateId"/>
							<html:submit>Undo</html:submit>
						</html:form>
					</c:if>
				</c:when>
				<c:otherwise>
					[<a href="showGame.do?gameId=${game.gameId}&amp;stateId=${move.state.stateId}">view</a>]<span title="First explored by ${move.state.explorer.name} on <fmt:formatDate value="${move.state.dateCreated}" pattern="yyyy-MM-dd" />"> ${move.text}</span>
				</c:otherwise>
			</c:choose>
			</div>
		</c:forEach>
		
		<c:if test="${board.gameOver}">
			<c:forEach items="${board.scorecard.scores}" var="entry">
				<br />
				<b>${entry.key}</b>:<br />
				<c:forEach items="${entry.value.settlementScores}" var="settlementScore">
					${settlementScore.settlement.name}: ${settlementScore.score}<br />
				</c:forEach>
				Settlement Score: ${entry.value.settlementTotalScore}<br />
				Shield Score: ${entry.value.shieldScore}<br />
				Item Score: ${entry.value.itemScore}<br />
				<i>Total Score: ${entry.value.settlementTotalScore + entry.value.shieldScore + entry.value.itemScore}</i><br />
			</c:forEach>
		</c:if>

		<c:if test="${not empty game.state.dstStates and empty param.stateId}">
		<hr />
		Previous moves from this state:
		<ul>
			<c:forEach items="${game.state.dstStates}" var="possibleState">
				<li>
				<html:form action="/makeMove.do">
					<html:hidden property="stateId" value="${game.state.stateId}" />
					<html:hidden property="gameId" value="${game.gameId}" />
					<html:hidden property="token" value="${possibleState.token}" />
					<html:submit>${possibleState.token}</html:submit>
				</html:form>
				</li>
			</c:forEach>
		</ul>
		</c:if>
		
		<hr />
	</div>

</body>
</html:html>
