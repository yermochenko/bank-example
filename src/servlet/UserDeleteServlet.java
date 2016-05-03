package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

public class UserDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = null;
		try {
			Integer id = Integer.parseInt(request.getParameter("id"));
			service = new UserService();
			service.delete(id);
		} catch(NumberFormatException e) {
		} catch(SQLException e) {
			throw new ServletException(e);
		} finally {
			if(service != null) {
				service.close();
			}
		}
		response.sendRedirect(request.getContextPath() + "/index.html");
	}
}
