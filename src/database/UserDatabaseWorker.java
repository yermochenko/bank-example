package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Role;
import domain.User;

public class UserDatabaseWorker {
	private Connection connection = null;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public User read(Integer id) throws SQLException {
		String sql = "SELECT `login`, `password`, `role` FROM `user` WHERE `id`=?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
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
		}
	}

	public User read(String login, String password) throws SQLException {
		String sql = "SELECT `id`, `role` FROM `user` WHERE `login`=? AND `password`=?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, password);
			resultSet = statement.executeQuery();
			User user = null;
			if(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setLogin(login);
				user.setPassword(password);
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
		}
	}

	public boolean isLoginUnique(String login) throws SQLException {
		String sql = "SELECT COUNT(*) AS `quantity` FROM `user` WHERE `login`=?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			boolean isUnique = true;
			if(resultSet.next()) {
				isUnique = resultSet.getInt("quantity") == 0;
			}
			return isUnique;
		} finally {
			try {
				resultSet.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	public List<User> read() throws SQLException {
		String sql = "SELECT `id`, `login`, `password`, `role` FROM `user`";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
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
		}
	}

	public Integer create(User user) throws SQLException {
		String sql = "INSERT INTO `user` (`login`, `password`, `role`) VALUES (?, ?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
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
		}
	}

	public void update(User user) throws SQLException {
		String sql = "UPDATE `user` SET `login`=?, `password`=?, `role`=? WHERE `id`=?";
		PreparedStatement statement = null;
		try {
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
		}
	}

	public void delete(Integer id) throws SQLException {
		String sql = "DELETE FROM `user` WHERE `id`=?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}
}
