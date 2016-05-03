package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

public class UserDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> ids = new ArrayList<>();
		for(String id : request.getParameterValues("id")) {
			try {
				ids.add(Integer.parseInt(id));
			} catch(NumberFormatException e) {}
		}
		UserService service = null;
		try {
			service = new UserService();
			service.delete(ids);
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
