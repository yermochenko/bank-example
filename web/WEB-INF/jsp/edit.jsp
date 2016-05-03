<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${empty user}">
		<jsp:useBean id="user" class="domain.User"/>
		<c:set var="title" value="Добавление нового работника"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Изменение данных работника &laquo;${user.login}&raquo;"/>
	</c:otherwise>
</c:choose>
<c:url var="cssUrl" value="/admin.css"/>
<u:html title="${title}" stylesheet="${cssUrl}">
	<DIV class="block">
		<H2>${title}</H2>
		<c:url var="saveUrl" value="/save.html"/>
		<FORM action="${saveUrl}" method="post">
			<c:if test="${user.role != 'CLIENT' and not empty user.id}">
				<INPUT type="hidden" name="id" value="${user.id}">
			</c:if>
			<LABEL for="login-id">Имя пользователя:</LABEL>
			<INPUT type="text" id="login-id" name="login" value="${user.login}">
			<LABEL for="role-id">Роль:</LABEL>
			<SELECT id="role-id" name="role">
				<c:forEach var="role" items="${roles}">
					<c:choose>
						<c:when test="${role == user.role}">
							<c:set var="selected" value=" selected"/>
						</c:when>
						<c:otherwise>
							<c:remove var="selected"/>
						</c:otherwise>
					</c:choose>
					<OPTION value="${role}"${selected}>${role}</OPTION>
				</c:forEach>
			</SELECT>
			<c:if test="${user.role == 'CLIENT'}">
				<c:set var="disabled" value=" disabled"/>
			</c:if>
			<BUTTON type="submit"${disabled}>Сохранить</BUTTON>
			<BUTTON type="reset">Сбросить</BUTTON>
		</FORM>
		<c:url var="indexUrl" value="/index.html"/>
		<FORM action="${indexUrl}"><BUTTON type="submit">Назад</BUTTON></FORM>
	</DIV>
</u:html>