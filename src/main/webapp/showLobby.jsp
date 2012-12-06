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

    <c:import url="jsp/userbar.jsp">
    	<c:param name="title">Weblabora</c:param>
    </c:import>
    
    <div class="container">
		<c:import url="jsp/notifications.jsp" />
	</div>
	
	<div class="container">
		<c:import url="jsp/showYourGames.jsp" />
	</div>
	
	<div class="container">
    	<c:import url="jsp/showRecruitingGames.jsp" />
	</div>
	
	<div class="container">
		<h2>Games currently in progress:</h2>
		<ul>
			<logic:iterate id="game" name="inProgressGames">
				<li>
					<html:link action="/showGame.do" paramId="gameId" paramName="game" paramProperty="gameId">
						#${game.gameId}, <fmt:formatDate value="${game.dateCreated}" pattern="yyyy-MM-dd" />
					</html:link> &#x25e2;
				</li>
			</logic:iterate>
		</ul>
	</div>

	<div class="container">
		<h2>Finished Games:</h2>
		<ul>
			<logic:iterate id="game" name="finishedGames">
				<li>
					<html:link action="/showGame.do" paramId="gameId" paramName="game" paramProperty="gameId">
						#${game.gameId}, <fmt:formatDate value="${game.dateCreated}" pattern="yyyy-MM-dd" />
					</html:link> &#x25e2;
				</li>
			</logic:iterate>
		</ul>
	</div>	

</body>
</html:html>