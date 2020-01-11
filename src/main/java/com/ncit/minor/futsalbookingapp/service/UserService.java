package com.ncit.minor.futsalbookingapp.service;

import java.util.List;

import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.model.User;

public interface UserService {

	User findByUsername(String username);

	User findById(Long id);

	User createUser(User user, String role);

	User save(User user);

	List<User> getUsers();
	User findUserByFutsal(Futsal futsal);
}
