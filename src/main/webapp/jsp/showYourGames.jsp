<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean-el" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic-el" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://philihp.com/jsp/ora" prefix="ora" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<c:if test="${not empty user}">
	
		<h2>Your Games:</h2>

		<c:choose>
			<c:when test="${empty myGames}">
				<span style="font-style: italic">You are not in any games.</span>
			</c:when>
			<c:otherwise>
			  <table border="1" cellspacing="0" cellpadding="3">
			  	<tr>
			  		<th>ID, Started</th>
					<th>Country</th>
					<th>Length</th>
			  		<th>Red</th>
			  		<th>Green</th>
			  		<th>Blue</th>
			  		<th>White</th>
			  	</tr>
					<c:forEach items="${myGames}" var="game">
						<tr>
							<td>
								#${game.gameId}, <fmt:formatDate value="${game.dateCreated}" pattern="yyyy-MM-dd" /><br />
								<html:link action="/showGame.do" paramId="gameId" paramName="game" paramProperty="gameId">View Game</html:link> &#x25e2;
							</td>
							<td>
								<c:choose>
									<c:when test="${game.country eq 'IRELAND'}">
										Ireland<br />
										<img src="images/ireland.svg" />
									</c:when>
									<c:when test="${game.country eq 'FRANCE'}">
										France<br />
										<img src="images/france.svg" />
									</c:when>
									<c:otherwise>
										${game.country}
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:choose>
									<c:when test="${game.length eq 'SHORT'}">
										Short<br />
										<img src="images/clock.svg"/>
									</c:when>
									<c:when test="${game.length eq 'LONG'}">
										Long<br />
										<img src="images/clock.svg" />
										<img src="images/clock.svg" />
									</c:when>
									<c:otherwise>
										${game.length}
									</c:otherwise>
								</c:choose>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 1}">
									<c:choose>
										<c:when test="${empty game.player1.user}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="1"/>
												<html:submit>Sit</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="https://secure.gravatar.com/avatar/${game.player1.user.emailMD5}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${game.player1.user.username}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 2}">
									<c:choose>
										<c:when test="${empty game.player2.user and game.players >= 2}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="2"/>
												<html:submit>Sit</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="https://secure.gravatar.com/avatar/${game.player2.user.emailMD5}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${game.player2.user.username}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 3}">
									<c:choose>
										<c:when test="${empty game.player3.user and game.players >= 3}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="3"/>
												<html:submit>Sit</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="https://secure.gravatar.com/avatar/${game.player3.user.emailMD5}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${game.player3.user.username}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
							<td class="avatar-cell">
								<c:if test="${game.players >= 4}">
									<c:choose>
										<c:when test="${empty game.player4.user and game.players >= 4}">
											<html:form action="/joinGame.do">
												<html:hidden property="gameId" value="${game.gameId}" />
												<html:hidden property="seat" value="4"/>
												<html:submit>Sit</html:submit>
											</html:form>
										</c:when>
										<c:otherwise>
											<img src="https://secure.gravatar.com/avatar/${game.player4.user.emailMD5}.jpg?s=50&amp;d=identicon" height="50" width="50" title="${game.player4.user.username}" />
										</c:otherwise>
									</c:choose>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
		
	</c:if>