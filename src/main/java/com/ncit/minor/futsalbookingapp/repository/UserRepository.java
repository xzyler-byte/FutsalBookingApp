package com.ncit.minor.futsalbookingapp.repository;

import com.ncit.minor.futsalbookingapp.model.Futsal;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ncit.minor.futsalbookingapp.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	User findByFutsal(Futsal futsal);
}
