<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</TITLE>
	</HEAD>
	<BODY>
		<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</H1>
		<c:choose>
			<c:when test="${not empty currentUser}">
				<P>${currentUser.login}&nbsp;&mdash; <A href="logout.html">выйти</A></P>
			</c:when>
			<c:otherwise>
				<P><A href="login.html">войти</A></P>
			</c:otherwise>
		</c:choose>
		<H2 style="color: white; background: #CD0000; padding: 15px; text-align: center; border: 1px solid darkred;">
			<c:choose>
				<c:when test="${not empty pageContext.exception}">Ошибка работы с базой данных</c:when>
				<c:otherwise>Непредвиденная ошибка приложения</c:otherwise>
			</c:choose>
		</H2>
		<P><A href="index.html">перейти на главную страницу</A></P>
		<DIV>&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>
	</BODY>
</HTML>