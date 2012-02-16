<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://weblabora.philihp.com" prefix="labora"%>
<!DOCTYPE html>
<html:html>
<head>
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
	
	<hr />
	
	Current State ID: ${game.state.stateId}<br />
	Current Move: ${game.state.moveNumber}<br /> 
	
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

</body>
</html:html>