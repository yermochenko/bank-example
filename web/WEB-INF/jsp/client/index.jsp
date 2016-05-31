<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="cssUrl" value="/css/employee.css"/>
<u:html title="Список клиентов" stylesheet="${cssUrl}">
	<DIV class="block">
		<H2>Список клиентов</H2>
		<TABLE>
			<TR>
				<TH>Имя пользователя</TH>
				<TH>Счёт</TH>
				<TH>Баланс</TH>
			</TR>
			<c:forEach var="client" items="${clients}">
				<c:url var="editUrl" value="/client/edit.html">
					<c:param name="id" value="${client.id}"/>
				</c:url>
				<c:choose>
					<c:when test="${not client.active}">
						<c:set var="className" value="insignificant"/>
					</c:when>
					<c:otherwise>
						<c:remove var="className"/>
					</c:otherwise>
				</c:choose>
				<TR id="${editUrl}" class="${className}">
					<TD>${client.login}</TD>
					<TD>${client.accountId}</TD>
					<TD>${client.balance}</TD>
				</TR>
			</c:forEach>
		</TABLE>
		<c:if test="${currentUser.role == 'MANAGER'}">
			<c:url var="editUrl" value="/client/edit.html"/>
			<FORM action="${editUrl}"><BUTTON type="submit">Добавить клиента</BUTTON></FORM>
		</c:if>
	</DIV>
</u:html>