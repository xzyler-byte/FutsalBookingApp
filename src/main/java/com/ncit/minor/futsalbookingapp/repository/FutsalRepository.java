package com.ncit.minor.futsalbookingapp.repository;

import com.ncit.minor.futsalbookingapp.model.Booking;
import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FutsalRepository extends JpaRepository<Futsal, Long> {
 Futsal findByFutsalName(String futsalName);
 List<Futsal> findByUser(User user);

}
