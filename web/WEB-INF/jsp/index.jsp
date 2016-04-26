<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="domain.User"%>
<%@page import="java.util.List"%>
<%
	@SuppressWarnings("unchecked")
	List<User> users = (List<User>)request.getAttribute("users");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">
<HTML>
	<HEAD>
		<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</TITLE>
		<STYLE>
			TABLE {
				border-collapse: collapse;
			}
			TH, TD {
				border: 1px solid black;
				padding: 5px 30px 5px 10px;
			}
		</STYLE>
	</HEAD>
	<BODY>
		<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</H1>
		<H2>Пример таблицы</H2>
		<FORM action="delete.html" method="post">
			<TABLE>
				<TR>
					<TD>&nbsp;</TD>
					<TH>Имя пользователя</TH>
					<TH>Пароль</TH>
					<TH>Роль</TH>
				</TR>
				<%
					for(User user: users) {
				%>
				<TR id="edit.html?id=<%=user.getId()%>">
					<TD><INPUT type="checkbox" name="id" value="<%=user.getId()%>"></TD>
					<TD><A href="edit.html?id=<%=user.getId()%>"><%=user.getLogin()%></A></TD>
					<TD><%=user.getPassword()%></TD>
					<TD><%=user.getRole().toString()%></TD>
				</TR>
				<%
					}
				%>
			</TABLE>
			<BUTTON type="submit">Уволить работников</BUTTON>
		</FORM>
		<FORM action="edit.html"><BUTTON type="submit">Добавить работника</BUTTON></FORM>
		<DIV>&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>
	</BODY>
</HTML>
