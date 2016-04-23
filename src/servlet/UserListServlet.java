package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserStorage;

public class UserListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = UserStorage.findAll();
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
		writer.println("<HTML>");
		writer.println("<HEAD>");
		writer.println("<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		writer.println("<TITLE>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</TITLE>");
		writer.println("<STYLE>");
		writer.println("TABLE {");
		writer.println("border-collapse: collapse;");
		writer.println("}");
		writer.println("TH, TD {");
		writer.println("border: 1px solid black;");
		writer.println("padding: 5px 30px 5px 10px;");
		writer.println("}");
		writer.println("</STYLE>");
		writer.println("</HEAD>");
		writer.println("<BODY>");
		writer.println("<H1>Банк &laquo;Рога&nbsp;&amp;&nbsp;копыта&raquo;</H1>");
		writer.println("<H2>Пример таблицы</H2>");
		writer.println("<TABLE>");
		writer.println("<TR>");
		writer.println("<TH>Имя пользователя</TH>");
		writer.println("<TH>Пароль</TH>");
		writer.println("<TH>Роль</TH>");
		writer.println("</TR>");
		for(User user: users) {
			writer.printf("<TR id=\"edit.html?id=%d\">", user.getId());
			writer.printf("<TD>%s</TD>", user.getLogin());
			writer.printf("<TD>%s</TD>", user.getPassword());
			writer.printf("<TD>%s</TD>", user.getRole().toString());
			writer.printf("</TR>");
		}
		writer.println("</TABLE>");
		writer.println("<DIV>&copy; Банк &laquo;Черноморское отделение Арбатовской конторы по заготовке рогов и копыт&raquo;</DIV>");
		writer.println("</BODY>");
		writer.println("</HTML>");
	}
}
