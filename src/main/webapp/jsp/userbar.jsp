<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="userbar">
	<div class="container">
			<c:choose>
				<c:when test="${empty user}">
					<div style="float: right; margin-top: 8px;">
						<html:link action="/register.do" style="color: white; border-color: #74bb5a; border-bottom-color: #509338; background-color: #60b044; background-image: -webkit-linear-gradient(#8add6d,#60b044); border-radius: 3px; border: 1px solid #ddd; display: inline-block; padding: 8px 15px; cursor: pointer; text-decoration: none;">Sign up for free</html:link>
						<c:choose>
							<c:when test="${empty param.referer}">
								<c:set var="referer" value="${requestScope[\"javax.servlet.forward.servlet_path\"]}" />
								<c:if test="${not empty requestScope[\"javax.servlet.forward.query_string\"]}">
									<c:set var="referer" value="${referer}?${requestScope[\"javax.servlet.forward.query_string\"]}" />
								</c:if>
							</c:when>
							<c:otherwise>
								<c:set var="referer" value="${param.referer}" />
							</c:otherwise>
						</c:choose>
   						<html:link action="/login.do" paramId="referer" paramName="referer" style="display: inline-block; padding: 8px 15px; background-color: #e5e5e5; background-image: -webkit-linear-gradient(whiteSmoke,#e5e5e5);border-radius: 3px;border: 1px solid #ddd; border-bottom-color: #bbb; cursor: pointer; text-decoration: none; color: #666;">Sign in</html:link>
					</div>
				</c:when>
				<c:otherwise>
					<div style="float: right;">
						${user.name}
						<img src="http://graph.facebook.com/${user.facebookId}/picture" height="50" width="50" title="${user.facebookId}"/>
						<html:link action="/logout.do" style="display: inline-block; padding: 8px 15px; background-color: #e5e5e5; background-image: -webkit-linear-gradient(whiteSmoke,#e5e5e5);border-radius: 3px;border: 1px solid #ddd; border-bottom-color: #bbb; cursor: pointer; text-decoration: none; color: #666;">Sign out</html:link>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
