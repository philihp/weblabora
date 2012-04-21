<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${param.amount != 0}">
	<c:forEach var="i" begin="1" end="${param.amount}"><img class="resource" src="images/chit/${param.type}.jpg" title="${param.type} (${param.abbr})" alt="${param.type} (${param.abbr})" /></c:forEach>
</c:if>