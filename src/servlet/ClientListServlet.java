package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Client;
import service.ClientService;

public class ClientListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClientService service = null;
		try {
			service = new ClientService();
			List<Client> clients = service.findAll();
			request.setAttribute("clients", clients);
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/client/index.jsp").forward(request, response);
		} catch(SQLException e) {
			throw new ServletException(e);
		} finally {
			if(service != null) {
				service.close();
			}
		}
	}
}
