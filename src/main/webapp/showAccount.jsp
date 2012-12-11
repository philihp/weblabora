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

<script src="js/lib/jquery-1.7.1.js"></script>
</head>

<body>

    <c:import url="jsp/userbar.jsp">
    	<c:param name="title">
    		&#x25e4; <html:link action="/showLobby.do">Return to Lobby</html:link>
    	</c:param>
		<c:param name="referer" value="/" />
	</c:import>
	
	<div class="container">
	
	<c:import url="jsp/notifications.jsp" />

<p>
<h2>Avatar</h2>  
<img src="https://secure.gravatar.com/avatar/${user.emailMD5}.jpg?s=100&amp;d=identicon" height="100" width="100" title="${user.username}" /> 
<a href="http://www.gravatar.com">Change your avatar at Gravatar.com</a> 
</p>

<p>
<h2>Facebook</h2>
<c:choose>
	<c:when test="${empty user.facebookId}">
		Account is not linked to Facebook. 
		<html:link action="/loginFacebook.do">Link with the Facebook</html:link>
	</c:when>
	<c:otherwise>
		Account is linked to Facebook ID ${user.facebookId}
		<html:link action="/editAccountUnlinkFacebook.do">Unlink it</html:link>
	</c:otherwise>
</c:choose>
</p>

<p>
<h2>Change Email</h2>
<html:form action="/editAccountEmail.do">
	<html:text property="email" errorStyleClass="error"></html:text>
	<html:submit>Update Email</html:submit>
</html:form>
<c:if test="${not empty user.unvalidatedEmail}">
Currently waiting for confirmation at ${user.unvalidatedEmail}
</c:if>
</p>

<p>
<h2>Reset Password</h2>
<html:form action="/editAccountPassword.do">
	Current Password: <html:password property="currentPassword" errorStyleClass="error" redisplay="false" /><br />
	New Password: <html:password property="newPassword" errorStyleClass="error" redisplay="false" /><br />
	Confirm Password: <html:password property="confirmPassword" errorStyleClass="error" redisplay="false" /><br />
	<html:submit>Reset Password</html:submit>
</html:form>
</p>

	
	</div>

<script>
$('input.error').first().focus();
</script>
</body>
</html:html>