<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty user}">
	<jsp:useBean id="user" class="domain.User"/>
</c:if>
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
		<H2>Добавление нового работника</H2>
		<FORM action="save.html" method="post">
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
		<FORM action="index.html"><BUTTON type="submit">Назад</BUTTON></FORM>
		<DIV>&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>
	</BODY>
</HTML>
