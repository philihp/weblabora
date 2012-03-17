<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${param.amount != 0}">
	<c:forEach var="i" begin="1" end="${param.amount}">
	[${param.type}]
	</c:forEach>
	<br />
</c:if>