package com.ncit.minor.futsalbookingapp.controller;

import com.ncit.minor.futsalbookingapp.model.Booking;
import com.ncit.minor.futsalbookingapp.service.BookingService;
import com.ncit.minor.futsalbookingapp.service.FutsalService;
import com.ncit.minor.futsalbookingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BookingController {
    @Autowired
    FutsalService futsalService;
    @Autowired
    BookingService bookingService;
    @Autowired
    UserService userService;

private SimpleDateFormat sdf= new SimpleDateFormat("yyyy-mm-dd");
private SimpleDateFormat sdft= new SimpleDateFormat("hh:ss");
    @GetMapping("/book")
    public String showFutsalList(Model model) {
        model.addAttribute("futsals", futsalService.findFutsal());
        model.addAttribute("booking", new Booking());
        return "listFutsal";
    }

    @PostMapping("/book/{futsalId}")
    public String bookFutsal(@PathVariable Long futsalId, @ModelAttribute Booking booking, Model model, Principal principal) {
        int i = 0;
        Date currentDate = new Date();
        List<Booking> book = bookingService.findByUser(userService.findByUsername(principal.getName()));
       List<Booking> booki= bookingService.findByFutsal(futsalService.findById(futsalId));
        for (Booking d : booki) {
            if (!(d.getBookDate() == null)) {
                if (d.getBookDate().compareTo(booking.getBookDate())==0&&d.getBookTime().compareTo(booking.getBookTime())==0)
                    i++;
            }
        }
        if((((booking.getBookDate().getTime())+(booking.getBookTime().getTime()))-(currentDate.getTime())) < -19649562){
            model.addAttribute("error",true);
            model.addAttribute("booking", bookingService.findById(booking.getId()));
            return "bookFutsalForm";
        }
        if (i > 0) {
            model.addAttribute("existError", true);
            model.addAttribute("booking", bookingService.findById(booking.getId()));
            return "bookFutsalForm";
        } else {
            Booking currentBooking = bookingService.findById(booking.getId());
            currentBooking.setBookDate(booking.getBookDate());
            currentBooking.setFakeDate(sdf.format(booking.getBookDate()));
            currentBooking.setFakeTime(sdft.format(booking.getBookTime()));
            currentBooking.setBookTime(booking.getBookTime());
            bookingService.save(currentBooking);
            return "index";
        }
    }

    @GetMapping("/book/{futsalId}")
    public String showBookFutsal(Model model, @PathVariable Long futsalId, Principal principal) {
        Booking booking = new Booking();
        bookingService.save(booking);
        bookingService.createBooking(booking, userService.findByUsername(principal.getName()), futsalService.findById(futsalId));
        model.addAttribute("booking", booking);
        return "bookFutsalForm";
    }

    @GetMapping("/book/list")
    public String bookList(Model model, Principal principal) {

        model.addAttribute("bookings", bookingService.findByUser(userService.findByUsername(principal.getName())));
        return "bookingList";
    }
}
