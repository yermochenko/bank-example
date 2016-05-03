<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:url var="cssUrl" value="/css/login.css"/>
<u:html title="Авторизация пользователя" stylesheet="${cssUrl}">
	<DIV class="block">
		<H2>Авторизация пользователя</H2>
		<c:if test="${not empty param['message']}">
			<P class="error">${param['message']}</P>
		</c:if>
		<c:url var="loginUrl" value="/login.html"/>
		<FORM action="${loginUrl}" method="post">
			<LABEL for="login-id">Имя пользователя:</LABEL>
			<INPUT type="text" id="login-id" name="login">
			<LABEL for="password-id">Пароль:</LABEL>
			<INPUT type="password" id="password-id" name="password">
			<BUTTON type="submit">Войти</BUTTON>
		</FORM>
	</DIV>
</u:html>