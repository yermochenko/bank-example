<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;&nbsp;&mdash; ${title}</TITLE>
		<c:url var="mainCssUrl" value="/main.css"/>
		<LINK rel="stylesheet" type="text/css" href="${mainCssUrl}">
		<c:if test="${not empty stylesheet}">
			<LINK rel="stylesheet" type="text/css" href="${stylesheet}">
		</c:if>
	</HEAD>
	<BODY>
		<DIV id="header">
			<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</H1>
			<UL>
				<LI>
					<c:choose>
						<c:when test="${not empty currentUser}">
							<c:url var="logoutUrl" value="/logout.html"/>
							<A href="${logoutUrl}">${currentUser.login}&nbsp;&mdash; выйти</A>
						</c:when>
						<c:otherwise>
							<c:url var="loginUrl" value="/login.html"/>
							<A href="${loginUrl}">войти</A>
						</c:otherwise>
					</c:choose>
				</LI>
			</UL>
		</DIV>
		<DIV id="content">
			<jsp:doBody/>
		</DIV>
		<DIV id="copyright">&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>
	</BODY>
</HTML>