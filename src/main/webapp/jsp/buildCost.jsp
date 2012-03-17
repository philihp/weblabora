<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${buildCost.wood > 0}">
 ${buildCost.wood} wood
</c:if>
<c:if test="${buildCost.clay > 0}">
 ${buildCost.clay} clay 
</c:if>
<c:if test="${buildCost.stone > 0}">
 ${buildCost.stone} stone
</c:if>
<c:if test="${buildCost.straw > 0}">
 ${buildCost.straw} straw
</c:if>
<c:if test="${buildCost.coin > 0}">
 ${buildCost.coin} coin
</c:if>