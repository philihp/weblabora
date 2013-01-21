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
<script src="js/lib/jquery-1.8.3.js"></script>
</head>
<body>

	<div>
		
		<html:form action="/createGame.do">
			<fieldset id="playersFieldset">
				<legend>Players</legend>
				<html:radio property="players" value="1" styleId="playersOne"/><label for="playersOne"> One</label><br />
				<html:radio property="players" value="2" styleId="playersTwo"/><label for="playersTwo"> Two</label><br />
				<html:radio property="players" value="3" styleId="playersThree"/><label for="playersThree"> Three</label><br />
				<html:radio property="players" value="4" styleId="playersFour"/><label for="playersFour"> Four</label>
			</fieldset>
			<fieldset id="lengthFieldset">
				<legend>Length</legend>
				<html:radio property="length" value="SHORT" styleId="lengthShort"/><label for="lengthShort"> Short</label><br />
				<html:radio property="length" value="LONG" styleId="lengthLong"/><label for="lengthLong"> Long</label>
			</fieldset>
			<fieldset>
				<legend>Country</legend>
				<html:radio property="country" value="FRANCE" styleId="countryFrance"/><label for="countryFrance"> France</label><br />
				<html:radio property="country" value="IRELAND" styleId="countryIreland"/><label for="countryIreland"> Ireland</label>
			</fieldset>
			<html:submit>Create New Game</html:submit>
		</html:form>
		
		<script>
			$('#playersFieldset input[type=\'radio\']').change(function() {
				$('#lengthFieldset input[type=\'radio\']').prop('disabled', $(this).val() == 1);
			});
		</script>
		
	</div>

</body>
</html:html>