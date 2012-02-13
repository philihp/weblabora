<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://weblabora.philihp.com" prefix="labora"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>

	<div>
		<logic:empty name="games">
			<span style="font-style: italic">No join-able games exist. You should make one.</span>
		</logic:empty>
		<logic:notEmpty name="games">
			<ul>
				<logic:iterate name="games" id="game">
					<li><bean:write name="game" /></li>
				</logic:iterate>
			</ul>
		</logic:notEmpty>
	</div>
	<hr />
	<div>
		<html:form action="/createGame.do">
			<html:submit>Create Game</html:submit>
		</html:form>
	</div>

</body>
</html>