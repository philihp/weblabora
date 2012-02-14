<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://weblabora.philihp.com" prefix="labora"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<div>
		<logic:empty name="games">
			<span style="font-style: italic"><b>No Games Found</b><br /><br />There are no games available with open seats. Create a new game, or if you have any friends, ask them to create one.</span>
			<hr />
			<html:form action="/createGame.do">
				<html:submit>Create New Game</html:submit>
			</html:form>
		</logic:empty>
		<c:if test="${!empty games}">
		  The following games have seats open:
		  <table style="border: 1px solid red">
				<c:forEach items="games" var="game">
					<tr>
						<td><bean:write name="game" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</div>

</body>
</html>