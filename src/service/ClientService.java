package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.ClientDatabaseWorker;
import database.UserDatabaseWorker;
import domain.Client;
import domain.User;

public class ClientService {
	private Connection connection;
	private UserDatabaseWorker userDb;
	private ClientDatabaseWorker clientDb;

	public ClientService() throws SQLException {
		connection = Connector.connect();
		userDb = new UserDatabaseWorker();
		userDb.setConnection(connection);
		clientDb = new ClientDatabaseWorker();
		clientDb.setConnection(connection);
	}

	public List<Client> findAll() throws SQLException {
		List<Client> clients = clientDb.read();
		User user = null;
		for(Client client : clients) {
			user = userDb.read(client.getId());
			client.setLogin(user.getLogin());
			client.setPassword(user.getPassword());
			client.setRole(user.getRole());
		}
		return clients;
	}

	public void close() {
		try {
			connection.close();
		} catch(SQLException e) {}
	}
}
