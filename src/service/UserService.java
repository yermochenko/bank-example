package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.UserDatabaseWorker;
import domain.Role;
import domain.User;

public class UserService {
	private Connection connection;
	private UserDatabaseWorker db;

	public UserService() throws SQLException {
		connection = Connector.connect();
		db = new UserDatabaseWorker();
		db.setConnection(connection);
	}

	public User findById(Integer id) throws SQLException {
		return db.read(id);
	}

	public User findByLoginAndPassword(String login, String password) throws SQLException {
		return db.read(login, password);
	}

	public List<User> findAll() throws SQLException {
		return db.read();
	}

	public void save(User user) throws SQLException {
		try {
			connection.setAutoCommit(false);
			if(user.getRole() != Role.CLIENT) {
				if(user.getId() == null) {
					user.setPassword("12345");
					Integer id = db.create(user);
					user.setId(id);
				} else {
					User oldUser = db.read(user.getId());
					if(oldUser != null && oldUser.getRole() != Role.CLIENT) {
						user.setPassword(oldUser.getPassword());
						db.update(user);
					}
				}
			}
			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException e1) {}
			throw e;
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch(SQLException e) {}
		}
	}

	public void delete(List<Integer> ids) throws SQLException {
		try {
			connection.setAutoCommit(false);
			User user;
			for(Integer id : ids) {
				user = db.read(id);
				if(user.getRole() != Role.CLIENT) {
					db.delete(id);
				}
			}
			connection.commit();
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch(SQLException e1) {}
			throw e;
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch(SQLException e) {}
		}
	}

	public void close() {
		try {
			connection.close();
		} catch(SQLException e) {}
	}
}
