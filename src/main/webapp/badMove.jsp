<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html:html>
<head>
</head>
<body>

<h1>Bad Move</h1>

The move you tried to make, <code>${token}</code> could not be made because it resulted in an invalid game state. This could
happen for a number of things, such as:
<ul>
	<li>Build something without the proper resources</li>
	<li>Building a building that isn't available</li>
	<li>Using a building without an available lay person</li>
	<li>Trying to cut peat/fell trees in something other than moor/forest</li>
	<li>Between any action, having less than 0 of anything</li>
</ul>

The specific error was:

<pre>
${error}
</pre>


</body>
</html:html>