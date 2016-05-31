package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import database.UserDatabaseWorker;
import domain.Role;
import domain.User;
import exception.NotUniqueLoginException;

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

	public void save(User user) throws SQLException, NotUniqueLoginException {
		try {
			connection.setAutoCommit(false);
			if(user.getRole() != Role.CLIENT) {
				if(user.getId() == null) {
					if(db.isLoginUnique(user.getLogin())) {
						user.setPassword("12345");
						Integer id = db.create(user);
						user.setId(id);
					} else {
						throw new NotUniqueLoginException(user.getLogin());
					}
				} else {
					User oldUser = db.read(user.getId());
					if(oldUser != null && oldUser.getRole() != Role.CLIENT) {
						if(user.getLogin().equals(oldUser.getLogin()) || db.isLoginUnique(user.getLogin())) {
							user.setPassword(oldUser.getPassword());
							db.update(user);
						} else {
							throw new NotUniqueLoginException(user.getLogin());
						}
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

	public void delete(Integer id) throws SQLException {
		try {
			connection.setAutoCommit(false);
			User user = db.read(id);
			if(user.getRole() != Role.CLIENT) {
				db.delete(id);
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
