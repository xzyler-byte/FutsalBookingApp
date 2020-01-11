package com.ncit.minor.futsalbookingapp.service.impl;

import java.util.List;

import com.ncit.minor.futsalbookingapp.model.Futsal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncit.minor.futsalbookingapp.model.User;
import com.ncit.minor.futsalbookingapp.repository.UserRepository;
import com.ncit.minor.futsalbookingapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findUserByFutsal(Futsal futsal) {
		return userRepository.findByFutsal(futsal);
	}

	@Override
	public User createUser(User user, String role) {
		User currentUser = userRepository.getOne(user.getId());
		currentUser.setUserRole(role);
		return userRepository.save(currentUser);
	}

}
