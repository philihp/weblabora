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
<script src="js/lib/jquery.colorbox.js"></script>

</head>

<body>


    <c:import url="jsp/userbar.jsp">
		<c:param name="user" value="${user}" />
		<c:param name="title">Login</c:param>
	</c:import>
	
	<div class="container">

	<c:import url="jsp/notifications.jsp" />
	
<p><html:link action="/loginFacebook.do"><img src="images/facebook-signin.png" /></html:link></p>

<i style="font-size: x-large; font-family: serif">or</i>

<html:form action="loginSubmit.do">

Username:<br />
<html:text property="username" errorStyleClass="error" /><br />
<br />
Password:<br />
<html:password property="password" styleId="password" errorStyleClass="error" redisplay="false" /><br />
<html:link action="/forgotPassword.do">Forgot Password?</html:link>
<br />


<html:submit>Sign in</html:submit>

</html:form>
	
	</div>

<script>
$('#password').focus();
$('input.error').first().focus();
</script>
	
</body>
</html:html>