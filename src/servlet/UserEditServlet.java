package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.User;
import service.UserStorage;

public class UserEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		try {
			user = UserStorage.findById(Integer.parseInt(request.getParameter("id")));
		} catch(NumberFormatException e) {}
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
		writer.println("<HTML>");
		writer.println("<HEAD>");
		writer.println("<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		writer.println("<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</TITLE>");
		writer.println("</HEAD>");
		writer.println("<BODY>");
		writer.println("<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</H1>");

		writer.println("<H2>Добавление нового работника</H2>");
		writer.println("<FORM action=\"save.html\" method=\"post\">");
		if(user != null) {
			writer.printf("<INPUT type=\"hidden\" name=\"id\" value=\"%d\">", user.getId());
		}
		writer.println("<LABEL for=\"login-id\">Имя пользователя:</LABEL>");
		writer.printf("<INPUT type=\"text\" id=\"login-id\" name=\"login\" value=\"%s\">", user != null ? user.getLogin() : new String());
		writer.println("<LABEL for=\"role-id\">Роль:</LABEL>");
		writer.println("<SELECT id=\"role-id\" name=\"role\">");
		List<Role> roles = Role.employees();
		for(Role role : roles) {
			writer.printf("<OPTION value=\"%1$s\"%2$s>%1$s</OPTION>", role, user != null && role == user.getRole() ? " selected" : new String());
		}
		writer.println("</SELECT>");
		writer.println("<BUTTON type=\"submit\">Сохранить</BUTTON>");
		writer.println("<BUTTON type=\"reset\">Сбросить</BUTTON>");
		writer.println("</FORM>");
		writer.println("<FORM action=\"index.html\"><BUTTON type=\"submit\">Назад</BUTTON></FORM>");
		writer.println("<DIV>&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>");
		writer.println("</BODY>");
		writer.println("</HTML>");
	}
}
