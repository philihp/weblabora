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
		
		<html:form action="/createGame.do">
			<fieldset>
				<legend>Players</legend>
				<html:radio property="players" value="ONE" styleId="players.one"/><label for="players.one"> One</label><br />
				<html:radio property="players" value="TWO" styleId="players.two"/><label for="players.two"> Two</label><br />
				<html:radio property="players" value="THREE" styleId="players.three"/><label for="players.three"> Three</label><br />
				<html:radio property="players" value="FOUR" styleId="players.four"/><label for="players.four"> Four</label>
			</fieldset>
			<fieldset>
				<legend>Length</legend>
				<html:radio property="length" value="SHORT" styleId="length.short"/><label for="length.short"> Short</label><br />
				<html:radio property="length" value="LONG" styleId="length.long"/><label for="length.long"> Long</label>
			</fieldset>
			<fieldset>
				<legend>Country</legend>
				<html:radio property="country" value="FRANCE" styleId="country.france"/><label for="country.france"> France</label><br />
				<html:radio property="country" value="IRELAND" styleId="country.ireland"/><label for="country.ireland"> Ireland</label>
			</fieldset>
			<html:submit>Create New Game</html:submit>
		</html:form>

	</div>

</body>
</html:html>