<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%--
kinda going for something like
http://www.jankoatwarpspeed.com/post/2008/05/22/css-message-boxes-for-different-message-types.aspx
--%>

<logic:messagesPresent message="false">
	<div class="errorBox">
		<ul>
		<html:messages id="error" message="false">
			<li>${error}</li>
		</html:messages>
		</ul>
	</div>
</logic:messagesPresent>

<logic:messagesPresent message="true">
	<div class="messageBox">
		<ul>
		<html:messages id="message" message="true">
			<li>${message}</li>
		</html:messages>
		</ul>
	</div>
</logic:messagesPresent>