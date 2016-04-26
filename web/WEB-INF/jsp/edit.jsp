<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="domain.User"%>
<%@page import="domain.Role"%>
<%@page import="java.util.List"%>
<%
	User user = (User)request.getAttribute("user");
	@SuppressWarnings("unchecked")
	List<Role> roles = (List<Role>)request.getAttribute("roles");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</TITLE>
	</HEAD>
	<BODY>
		<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo; JSP</H1>
		<H2>Добавление нового работника</H2>
		<FORM action="save.html" method="post">
			<%
				if(user != null) {
			%>
				<INPUT type="hidden" name="id" value="<%=user.getId()%>">
			<%
				}
			%>
			<LABEL for="login-id">Имя пользователя:</LABEL>
			<INPUT type="text" id="login-id" name="login" value="<%=user != null ? user.getLogin() : new String()%>">
			<LABEL for="role-id">Роль:</LABEL>
			<SELECT id="role-id" name="role">
				<%
					for(Role role : roles) {
				%>
				<OPTION value="<%=role%>"<%=user != null && role == user.getRole() ? " selected" : new String()%>><%=role%></OPTION>
				<%
					}
				%>
			</SELECT>
			<BUTTON type="submit">Сохранить</BUTTON>
			<BUTTON type="reset">Сбросить</BUTTON>
		</FORM>
		<FORM action="index.html"><BUTTON type="submit">Назад</BUTTON></FORM>
		<DIV>&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>
	</BODY>
</HTML>
