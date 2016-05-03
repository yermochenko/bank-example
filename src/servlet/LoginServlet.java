package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserStorage;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login != null && password != null) {
			try {
				User user = UserStorage.findByLoginAndPassword(login, password);
				if(user != null) {
					HttpSession session = request.getSession();
					session.setAttribute("currentUser", user);
					response.sendRedirect(request.getContextPath());
				} else {
					response.sendRedirect(request.getContextPath() + "/login.html?message=" + URLEncoder.encode("Имя пользователя или пароль неопознанны", "UTF-8"));
				}
			} catch(SQLException e) {
				throw new ServletException(e);
			}
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}
}
