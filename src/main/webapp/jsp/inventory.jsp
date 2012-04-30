<%@page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${player.wonders}" var="wonder"><div style="float: left; border: 1px solid red; height: 100px; width: 100px; display: inline-block">Wonder</div></c:forEach>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Wood" />
	<c:param name="abbr" value="Wo" />
	<c:param name="amount" value="${player.wood}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Clay" />
	<c:param name="abbr" value="Cl" />
	<c:param name="amount" value="${player.clay}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Stone" />
	<c:param name="abbr" value="Sn" />
	<c:param name="amount" value="${player.stone}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Straw" />
	<c:param name="abbr" value="Sw" />
	<c:param name="amount" value="${player.straw}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Nickel" />
	<c:param name="abbr" value="Ni" />
	<c:param name="amount" value="${player.nickel}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Penny" />
	<c:param name="abbr" value="Pn" />
	<c:param name="amount" value="${player.penny}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Peat" />
	<c:param name="abbr" value="Pt" />
	<c:param name="amount" value="${player.peat}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Grain" />
	<c:param name="abbr" value="Gn" />
	<c:param name="amount" value="${player.grain}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Sheep" />
	<c:param name="abbr" value="Sh" />
	<c:param name="amount" value="${player.sheep}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Flour" />
	<c:param name="abbr" value="Fl" />
	<c:param name="amount" value="${player.flour}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Grapes" />
	<c:param name="abbr" value="Gp" />
	<c:param name="amount" value="${player.grapes}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Hops" />
	<c:param name="abbr" value="Ho" />
	<c:param name="amount" value="${player.hops}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Coal" />
	<c:param name="abbr" value="Co" />
	<c:param name="amount" value="${player.coal}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Whiskey" />
	<c:param name="abbr" value="Wh" />
	<c:param name="amount" value="${player.whiskey}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Meat" />
	<c:param name="abbr" value="Mt" />
	<c:param name="amount" value="${player.meat}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Bread" />
	<c:param name="abbr" value="Br" />
	<c:param name="amount" value="${player.bread}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Wine" />
	<c:param name="abbr" value="Wn" />
	<c:param name="amount" value="${player.wine}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Beer" />
	<c:param name="abbr" value="Be" />
	<c:param name="amount" value="${player.beer}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Book" />
	<c:param name="abbr" value="Bo" />
	<c:param name="amount" value="${player.book}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Pot" />
	<c:param name="abbr" value="Po" />
	<c:param name="amount" value="${player.pottery}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Ornament" />
	<c:param name="abbr" value="Or" />
	<c:param name="amount" value="${player.ornament}" />
</c:import>
<c:import url="jsp/inventoryType.jsp">
	<c:param name="type" value="Reliquary" />
	<c:param name="abbr" value="Rq" />
	<c:param name="amount" value="${player.reliquary}" />
</c:import>
<c:if test="${player.bonusPoints ne 0}">
<div class="bonusPoints" title="Bp">Bonus Points: ${player.bonusPoints}</div>
</c:if>