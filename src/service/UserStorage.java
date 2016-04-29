package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Role;
import domain.User;

public class UserStorage {
	public static User findById(Integer id) throws SQLException {
		String sql = "SELECT `login`, `password`, `role` FROM `user` WHERE `id`=?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = Connector.connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			User user = null;
			if(resultSet.next()) {
				user = new User();
				user.setId(id);
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(Role.values()[resultSet.getInt("role")]);
			}
			return user;
		} finally {
			try {
				resultSet.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				connection.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	public static List<User> findAll() throws SQLException {
		String sql = "SELECT `id`, `login`, `password`, `role` FROM `user`";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = Connector.connect();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			User user = null;
			List<User> users = new ArrayList<>();
			while(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(resultSet.getString("login"));
				user.setPassword(resultSet.getString("password"));
				user.setRole(Role.values()[resultSet.getInt("role")]);
				users.add(user);
			}
			return users;
		} finally {
			try {
				resultSet.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				connection.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	public static void save(User user) throws SQLException {
		if(user.getId() == null) {
			user.setPassword("12345");
			Integer id = create(user);
			user.setId(id);
		} else {
			User oldUser = findById(user.getId());
			if(oldUser != null) {
				user.setPassword(oldUser.getPassword());
				update(user);
			}
		}
	}

	public static void delete(List<Integer> ids) throws SQLException {
		for(Integer id : ids) {
			delete(id);
		}
	}

	private static Integer create(User user) throws SQLException {
		String sql = "INSERT INTO `user` (`login`, `password`, `role`) VALUES (?, ?, ?)";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = Connector.connect();
			statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getRole().ordinal());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getInt(1);
		} finally {
			try {
				resultSet.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				connection.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	private static void update(User user) throws SQLException {
		String sql = "UPDATE `user` SET `login`=?, `password`=?, `role`=? WHERE `id`=?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Connector.connect();
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getRole().ordinal());
			statement.setInt(4, user.getId());
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				connection.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	private static void delete(Integer id) throws SQLException {
		String sql = "DELETE FROM `user` WHERE `id`=?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = Connector.connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				connection.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}
}
