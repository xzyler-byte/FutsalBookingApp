package com.ncit.minor.futsalbookingapp.repository;

import com.ncit.minor.futsalbookingapp.model.Booking;
import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByFutsal(Futsal futsal);
    List<Booking> findByUser(User user);
    Booking findByFutsalAndUser(Futsal futsal,User user);
    Booking findByBookDate(Date date);
}
