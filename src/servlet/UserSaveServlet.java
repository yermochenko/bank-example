package servlet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.User;
import service.UserStorage;

public class UserSaveServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = null;
		Role role = null;
		try {
			role = Role.valueOf(request.getParameter("role"));
			login = request.getParameter("login");
			if(!Pattern.matches("\\w+", login)) {
				login = null;
			}
		} catch(NullPointerException | IllegalArgumentException e) {}
		if(login != null && role != null) {
			User user = new User();
			user.setLogin(login);
			user.setRole(role);
			UserStorage.save(user);
		}
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}
}
