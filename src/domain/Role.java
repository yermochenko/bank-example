package domain;

import java.util.ArrayList;
import java.util.List;

public enum Role {
	CLIENT,
	CASHIER,
	MANAGER,
	ADMIN;

	public static List<Role> employees() {
		List<Role> roles = new ArrayList<>();
		for(Role role : values()) {
			if(role != CLIENT) {
				roles.add(role);
			}
		}
		return roles;
	}
}
