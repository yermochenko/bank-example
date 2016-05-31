package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Client;

public class ClientDatabaseWorker {
	private Connection connection = null;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Client read(Integer id) throws SQLException {
		String sql = "SELECT `account_id`, `balance`, `is_active` FROM `client` WHERE `id`=?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			Client client = null;
			if(resultSet.next()) {
				client = new Client();
				client.setId(id);
				client.setAccountId(resultSet.getString("account_id"));
				client.setBalance(resultSet.getLong("balance"));
				client.setActive(resultSet.getBoolean("is_active"));
			}
			return client;
		} finally {
			try {
				resultSet.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	public List<Client> read() throws SQLException {
		String sql = "SELECT `id`, `account_id`, `balance`, `is_active` FROM `client`";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			Client client = null;
			List<Client> clients = new ArrayList<>();
			while(resultSet.next()) {
				client = new Client();
				client.setId(resultSet.getInt("id"));
				client.setAccountId(resultSet.getString("account_id"));
				client.setBalance(resultSet.getLong("balance"));
				client.setActive(resultSet.getBoolean("is_active"));
				clients.add(client);
			}
			return clients;
		} finally {
			try {
				resultSet.close();
			} catch(NullPointerException | SQLException e) {}
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	public Integer create(Client client) throws SQLException {
		String sql = "INSERT INTO `client` (`id`, `account_id`, `balance`, `is_active`) VALUES (?, ?, ?, ?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, client.getId());
			statement.setString(2, client.getAccountId());
			statement.setLong(3, client.getBalance());
			statement.setBoolean(4, client.isActive());
			statement.executeUpdate();
			return client.getId();
		} finally {
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	public void update(Client client) throws SQLException {
		String sql = "UPDATE `client` SET `account_id`=?, `balance`=?, `is_active`=? WHERE `id`=?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, client.getAccountId());
			statement.setLong(2, client.getBalance());
			statement.setBoolean(3, client.isActive());
			statement.setInt(4, client.getId());
			statement.executeUpdate();
		} finally {
			try {
				statement.close();
			} catch(NullPointerException | SQLException e) {}
		}
	}

	public void delete(Integer id) throws SQLException {
		String sql = "DELETE FROM `client` WHERE `id`=?";
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
