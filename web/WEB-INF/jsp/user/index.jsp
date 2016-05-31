<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<c:url var="cssUrl" value="/css/admin.css"/>
<u:html title="Список пользователей" stylesheet="${cssUrl}">
	<DIV class="block">
		<H2>Список пользователей</H2>
		<TABLE>
			<TR>
				<TH>Имя пользователя</TH>
				<TH>Пароль</TH>
				<TH>Роль</TH>
			</TR>
			<c:forEach var="user" items="${users}">
				<c:url var="editUrl" value="/user/edit.html">
					<c:param name="id" value="${user.id}"/>
				</c:url>
				<TR id="${editUrl}">
					<TD>${user.login}</TD>
					<TD>${user.password}</TD>
					<TD>${user.role}</TD>
				</TR>
			</c:forEach>
		</TABLE>
		<c:url var="editUrl" value="/user/edit.html"/>
		<FORM action="${editUrl}"><BUTTON type="submit">Добавить работника</BUTTON></FORM>
	</DIV>
</u:html>