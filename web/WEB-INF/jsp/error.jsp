<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty pageContext.exception}">
		<c:set var="message" value="Ошибка работы с базой данных"/>
	</c:when>
	<c:otherwise>
		<c:set var="message" value="Непредвиденная ошибка приложения"/>
	</c:otherwise>
</c:choose>
<c:url var="cssUrl" value="/css/error.css"/>
<u:html title="${message}" stylesheet="${cssUrl}">
	<DIV class="block">
		<H2 class="error">${message}</H2>
		<c:url var="indexUrl" value="/index.html"/>
		<FORM action="${indexUrl}"><BUTTON type="submit">Перейти на главную страницу</BUTTON></FORM>
	</DIV>
</u:html>