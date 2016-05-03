package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import service.UserService;

public class UserListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = null;
		try {
			service = new UserService();
			List<User> users = service.findAll();
			request.setAttribute("users", users);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		} catch(SQLException e) {
			throw new ServletException(e);
		} finally {
			if(service != null) {
				service.close();
			}
		}
	}
}
