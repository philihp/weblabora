<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://weblabora.philihp.com" prefix="labora"%>
<!DOCTYPE html>
<html>
<head>
<title>Board</title>
</head>
<body>

	[
	<logic:empty name="facebook">
		<html:link action="/authenticate.do">Login</html:link>
	</logic:empty>
	<logic:notEmpty name="facebook">
		<bean:write name="facebook" property="firstName" />
		(#<bean:write name="facebook" property="id" />)
		<html:link action="/authenticateLogout.do">Logout</html:link>
	</logic:notEmpty>
	]

	<h1>Wheel</h1>

	<p>
		<i><bean:write name="message" ignore="true" /></i>
	</p>

	<table>
		<tr>
			<th>Component</th>
			<th>Position</th>
		</tr>
		<tr>
			<td>Arm</td>
			<td><bean:write name="arm" property="position" /></td>
		</tr>
		<tr>
			<td>Grain</td>
			<td><bean:write name="grain" property="position" /></td>
		</tr>
		<tr>
			<td>Sheep</td>
			<td><bean:write name="sheep" property="position" /></td>
		</tr>
	</table>

	<html:form action="takeGrain.do">
		<html:submit>Take Grain</html:submit>
	</html:form>
	<html:form action="takeSheep.do">
		<html:submit>Take Sheep</html:submit>
	</html:form>
	<html:form action="pushArm.do">
		<html:submit>Push Arm</html:submit>
	</html:form>

	<logic:iterate name="players" id="player">
	<p>
		<h1><bean:write name="player" property="color" /></h1>
		<ul>
			<logic:iterate name="player" property="inventory" id="entry">
				<li><bean:write name="entry" property="type" />: <bean:write name="entry" property="quantity" /></li>
			</logic:iterate>
		</ul>
	</p>
	</logic:iterate>

</body>
</html>