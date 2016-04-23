package service;

import java.util.ArrayList;
import java.util.List;

import domain.Role;
import domain.User;

public class UserStorage {
	public static List<User> findAll() {
		ArrayList<User> users = new ArrayList<>();
		User user;
		user = new User();
		user.setId(1);
		user.setLogin("user-a");
		user.setPassword("123");
		user.setRole(Role.ADMIN);
		users.add(user);
		user = new User();
		user.setId(2);
		user.setLogin("user-b");
		user.setPassword("234");
		user.setRole(Role.CASHIER);
		users.add(user);
		user = new User();
		user.setId(3);
		user.setLogin("user-c");
		user.setPassword("345");
		user.setRole(Role.MANAGER);
		users.add(user);
		user = new User();
		user.setId(4);
		user.setLogin("user-d");
		user.setPassword("456");
		user.setRole(Role.CLIENT);
		users.add(user);
		user = new User();
		user.setId(5);
		user.setLogin("user-e");
		user.setPassword("567");
		user.setRole(Role.CLIENT);
		users.add(user);
		return users;
	}
}
