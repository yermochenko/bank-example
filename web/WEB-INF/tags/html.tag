<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="stylesheet" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="jshandler" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;&nbsp;&mdash; ${title}</TITLE>
		<c:url var="mainCssUrl" value="/css/main.css"/>
		<LINK rel="stylesheet" type="text/css" href="${mainCssUrl}">
		<c:if test="${not empty stylesheet}">
			<LINK rel="stylesheet" type="text/css" href="${stylesheet}">
		</c:if>
		<c:url var="mainJsUrl" value="/js/main.js"/>
		<SCRIPT type="text/javascript" src="${mainJsUrl}"></SCRIPT>
		<c:url var="generalHandlersJsUrl" value="/js/general-handlers.js"/>
		<SCRIPT type="text/javascript" src="${generalHandlersJsUrl}"></SCRIPT>
		<c:if test="${not empty jshandler}">
			<SCRIPT type="text/javascript" src="${jshandler}"></SCRIPT>
		</c:if>
		<c:if test="${not empty param.message}">
			<SCRIPT type="text/javascript">
				handlers.add(null, HandlerType.ONLOAD, function() {
					show("${param.message}");
				});
			</SCRIPT>
		</c:if>
	</HEAD>
	<BODY>
		<DIV id="header">
			<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</H1>
			<UL>
				<c:if test="${not empty currentUser}">
					<LI>
						<c:url var="arrowImgUrl" value="/img/arrow.gif"/>
						<A href="#">${currentUser.login} <IMG src="${arrowImgUrl}"></A>
						<OL>
							<LI><A href="#">профиль</A></LI>
							<c:url var="logoutUrl" value="/logout.html"/>
							<LI><A href="${logoutUrl}">выход</A></LI>
						</OL>
					</LI>
				</c:if>
			</UL>
		</DIV>
		<DIV id="content">
			<jsp:doBody/>
		</DIV>
		<DIV id="copyright">&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>
	</BODY>
</HTML>