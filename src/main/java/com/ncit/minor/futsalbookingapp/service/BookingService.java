package com.ncit.minor.futsalbookingapp.service;

import com.ncit.minor.futsalbookingapp.model.Booking;
import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.model.User;

import java.util.Date;
import java.util.List;

public interface BookingService {
    List<Booking> findBookings();
    Booking save(Booking booking);
    void update(Booking booking, User user);
    Booking findById(Long id);
    Booking createBooking(Booking booking, User user, Futsal futsal);
    List<Booking> findByFutsal(Futsal futsal);
    List<Booking> findByUser(User user);
    Booking findByUserAndFutsal(Futsal futsal,User user);
    Booking findByBookDate(Date date);
    void delete(Booking booking );
}
