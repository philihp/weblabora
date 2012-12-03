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
		<c:param name="title" value="Register" />
		<c:param name="referer" value="/" />
	</c:import>
	
	<div class="container">
	
	<html:errors />
	
<html:form action="/registerSubmit.do">

Username:<br />
<html:text property="username" errorStyleClass="error" /><br />
<br />
Password:<br />
<html:password property="password" errorStyleClass="error" redisplay="false" /><br />
<br />
Confirm Password:<br />
<html:password property="confirmPassword" errorStyleClass="error" redisplay="false" /><br />
<br />
Email:<br />
<html:text property="email" errorStyleClass="error" /><br />
<br />
Confirm Email<br />
<html:text property="confirmEmail" errorStyleClass="error" /><br />
<br />
<html:submit>Register</html:submit>

</html:form>

<script>
$('input.error').first().focus();
</script>
	
	</div>

</body>
</html:html>