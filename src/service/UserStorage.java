package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import domain.Role;
import domain.User;

public class UserStorage {
	private static Map<Integer, User> users = new HashMap<>();

	static {
		User user;
		user = new User();
		user.setLogin("user-a");
		user.setPassword("123");
		user.setRole(Role.ADMIN);
		save(user);
		user = new User();
		user.setLogin("user-b");
		user.setPassword("234");
		user.setRole(Role.CASHIER);
		save(user);
		user = new User();
		user.setLogin("user-c");
		user.setPassword("345");
		user.setRole(Role.MANAGER);
		save(user);
		user = new User();
		user.setLogin("user-d");
		user.setPassword("456");
		user.setRole(Role.CLIENT);
		save(user);
		user = new User();
		user.setLogin("user-e");
		user.setPassword("567");
		user.setRole(Role.CLIENT);
		save(user);
	}

	public static List<User> findAll() {
		return new ArrayList<>(users.values());
	}

	public static void save(User user) {
		if(user.getId() == null) {
			Integer id = 1;
			Set<Integer> keys = users.keySet();
			if(!keys.isEmpty()) {
				id += Collections.max(keys);
			}
			user.setId(id);
			user.setPassword("12345");
		}
		users.put(user.getId(), user);
	}
}
