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

Tried to make this move:
<blockquote><code>${token}</code></blockquote>

But couldn't, because it resulted in an invalid state of the board. This could
happen for a number of things, for example (but not limited to):
<ul>
	<li>Build something without the proper resources</li>
	<li>Building a building that isn't available</li>
	<li>Using a building without an available lay person</li>
	<li>Trying to cut peat/fell trees in something other than moor/forest</li>
	<li>At any point in your turn having a negative amount of anything</li>
</ul>

The specific error was:

<pre>
${error.message}
</pre>

Game ID <code>${game.gameId}</code><br />
from State ID <code>${game.state.stateId}</code>

</body>
</html:html>