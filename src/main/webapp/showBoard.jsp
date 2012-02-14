<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://weblabora.philihp.com" prefix="labora"%>
<!DOCTYPE html>
<html>
<head>
<title>Board</title>
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
		<img src="http://graph.facebook.com/<bean:write name="user" property="facebookId" />/picture" height="50" width="50" title="#<bean:write name="user" property="facebookId" />"/>
		<bean:write name="user" property="name" />
		|
		<html:form action="/setActiveGame.do" method="GET" style="display: inline">
			<html:select property="gameId">
				<html:option value="">(no active game)</html:option>
				<html:options collection="myGames" property="gameId" labelProperty="name" />
				<html:submit>Set Active Game</html:submit>
			</html:select>
		</html:form>
		|
		<button id="findGamesButton">Find Games</button>
	</div>

	<img src="http://i.imgur.com/b4rGt.jpg" width="747" height="803" />

	<html:form action="/authenticateHijack.do">
		<html:text property="facebookId"></html:text>
		<html:submit>Hijack</html:submit>
	</html:form>

</body>
</html>