<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<script src="//connect.facebook.net/en_US/all.js"></script>
<script>
console.log('initializing FB');

$(document).ready(function() {
	FB.init({
		appId: '${client_id}',
		frictionlessRequests: true
	});
	console.log('calling message');
	FB.ui({
		method: 'apprequests',
		message: 'Move made: ${move}',
		to: '${to}'
	}, requestCallback);
});

function requestCallback(response) {
	console.log('callback');
	window.location = '<%=request.getContextPath()%>';
}

</script>

</head>

<body>

<h1>Success</h1>

  <div id="fb-root"></div>

</body>
</html:html>
