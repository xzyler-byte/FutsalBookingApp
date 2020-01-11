package com.ncit.minor.futsalbookingapp.service.impl;

import com.ncit.minor.futsalbookingapp.model.Booking;
import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.model.User;
import com.ncit.minor.futsalbookingapp.repository.BookingRepository;
import com.ncit.minor.futsalbookingapp.repository.FutsalRepository;
import com.ncit.minor.futsalbookingapp.repository.UserRepository;
import com.ncit.minor.futsalbookingapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FutsalRepository futsalRepository;

    @Override
    public List<Booking> findBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void update(Booking booking, User user) {
        Booking currentBooking = bookingRepository.getOne(booking.getId());
        currentBooking.setBookDate(booking.getBookDate());
        if (user.getUserRole().equals(new String("USER"))) {
            currentBooking.setUser(user);
        }
        bookingRepository.save(currentBooking);
    }

    @Override
    public Booking findById(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public Booking createBooking(Booking booking, User user, Futsal futsal) {
        booking.setUser(user);
        booking.setFutsal(futsal);
        booking.setBookDate(booking.getBookDate());
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> findByFutsal(Futsal futsal) {
        return bookingRepository.findByFutsal(futsal);
    }

    @Override
    public List<Booking> findByUser(User user) {
        return bookingRepository.findByUser(user);
    }

    @Override
    public Booking findByUserAndFutsal(Futsal futsal, User user) {
        return bookingRepository.findByFutsalAndUser(futsal,user);
    }

    @Override
    public Booking findByBookDate(Date date) {
        return bookingRepository.findByBookDate(date);
    }

    @Override
    public void delete(Booking booking) {
        bookingRepository.delete(booking);
    }

}
