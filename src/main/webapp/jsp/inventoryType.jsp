<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${param.amount > 0}">
	<c:if test="${fn:endsWith(param.type,'Food') or fn:endsWith(param.type,'Energy')}">
		<c:forEach var="i" begin="1" end="${param.amount}"><img class="resource" src="images/chit/${param.type}.png" title="${param.abbr}" alt="${param.abbr}" data-resource="${param.type}" /></c:forEach>
	</c:if>
	<c:if test="${not fn:endsWith(param.type,'Food') and not fn:endsWith(param.type,'Energy')}"> 
		<c:forEach var="i" begin="1" end="${param.amount}"><img class="resource" src="images/chit/${param.type}.png" title="${param.type} (${param.abbr})" alt="${param.type} (${param.abbr})" data-resource="${param.type}" /></c:forEach>
	</c:if>	
</c:if>
<c:if test="${param.amount < 0}">
  ERROR: ${param.type} is less than zero.
</c:if>