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
<style>
body {
	margin: 10px;
	background: #f0f0f0;
}

body,button,input,select,textarea {
	font-family: Verdana, "BitStream vera Sans", Helvetica, Sans-serif;
	font-size: 13px;
}

div.userbar {
	box-shadow: 0 1px 10px #a7a7a7, inset 0 1px 0 #fff;
	background: #fcfcfc;
	border: 1px solid #b3b3b3;
	border-radius: 4px;
	font-size: 16px;
	padding: 10px;
}

div.userbar img {
	box-shadow: 0 1px 10px #a7a7a7;
	border: 1px solid #b3b3b3;
	border-radius: 4px;
}
</style>
<link rel="stylesheet" href="css/colorbox.css" />


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
			href : "showGames.do"
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