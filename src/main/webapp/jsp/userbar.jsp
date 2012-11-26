<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="userbar">
	<div class="container">
		<c:choose>
			<c:when test="${empty param.user}">
			Login Link Here
			</c:when>
			<c:otherwise>
				<img src="http://graph.facebook.com/${param.user.facebookId}/picture" height="50" width="50" title="${param.user.facebookId}"/>
				${param.user.name}
			</c:otherwise>
		</c:choose>
	</div>
</div>
