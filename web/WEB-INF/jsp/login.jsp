<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</TITLE>
	</HEAD>
	<BODY>
		<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</H1>
		<H2>Авторизация пользователя</H2>
		<c:if test="${not empty param['message']}">
			<P style="color: white; background: #CD0000; padding: 15px; text-align: center; border: 1px solid darkred;">${param['message']}</P>
		</c:if>
		<FORM action="login.html" method="post">
			<LABEL for="login-id">Имя пользователя:</LABEL>
			<INPUT type="text" id="login-id" name="login">
			<LABEL for="password-id">Пароль:</LABEL>
			<INPUT type="password" id="password-id" name="password">
			<BUTTON type="submit">Войти</BUTTON>
		</FORM>
		<DIV>&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>
	</BODY>
</HTML>
