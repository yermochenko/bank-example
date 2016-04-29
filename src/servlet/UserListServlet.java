package servlet;

import java.io.IOException;
import java.sql.SQLException;
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
		try {
			List<User> users = UserStorage.findAll();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		} catch(SQLException e) {
			throw new ServletException(e);
		}
	}
}
