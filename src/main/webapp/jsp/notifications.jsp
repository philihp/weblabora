<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<logic:messagesPresent message="false">
	<html:messages id="error" message="false">
		<div style="color: red">ERROR: ${error}</div>
	</html:messages>
</logic:messagesPresent>

<logic:messagesPresent message="true">
	<html:messages id="message" message="true">
		<div style="color: blue">NOTE: ${message}</div>
	</html:messages>
</logic:messagesPresent>