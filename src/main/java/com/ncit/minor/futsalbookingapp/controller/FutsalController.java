package com.ncit.minor.futsalbookingapp.controller;

import com.ncit.minor.futsalbookingapp.model.Booking;
import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.service.BookingService;
import com.ncit.minor.futsalbookingapp.service.FutsalService;
import com.ncit.minor.futsalbookingapp.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;

@Controller
@RequestMapping("/admin/futsal")
public class FutsalController {
    @Autowired
    FutsalService futsalService;
    @Autowired
    UserService userService;
    @Autowired
    BookingService bookingService;

    @GetMapping("/add")
    public String showAddFutsalForm(Model model) {
        model.addAttribute("futsal", new Futsal());
        return "addFutsal";
    }

    @PostMapping("/add")
    public String addFutsal(@ModelAttribute("futsal") Futsal futsal, Model model, Principal principal) throws Exception {

        if(futsalService.findFutsal().contains(futsalService.findByFutsalName(futsal.getFutsalName())))
        {
            model.addAttribute("nameExist",true);
            return "redirect:/add";
        }
        else {
            futsalService.save(futsal);
            model.addAttribute("admin", userService.findByUsername(principal.getName()));
            MultipartFile futsalImage = futsal.getFutsalImage();
            try {
                byte[] bytes = futsalImage.getBytes();
                String name = futsal.getId() + ".png";
                File fileName = new File("src/main/resources/static/images/futsal/" + name);
                if (fileName.exists())
                    Files.delete(Paths.get(String.valueOf(fileName)));
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(fileName));

                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            futsalService.mapFutsalForAdmin(userService.findByUsername(principal.getName()), futsal);
            futsalService.save(futsal);
            return "redirect:/admin";
        }
    }
    @GetMapping("/manage")
    public String showFutsalManage(Model model,Principal principal)
    {
        model.addAttribute("futsals",futsalService.findByUser(userService.findByUsername(principal.getName())));
        return "manageFutsal";
    }
    @GetMapping("/manage/booking")
    public String showBooking(Model model)
    {
        model.addAttribute("approved",new String("approved"));
        model.addAttribute("pending",new String("pending"));
        model.addAttribute("bookings",bookingService.findBookings());
        return "manageBooking";
    }
    @GetMapping("manage/booking/{bookingId}")
    public String approveBooking(@PathVariable("bookingId")Long bookingID)
    {
        Booking currentBooking= bookingService.findById(bookingID);
        bookingService.save(currentBooking);
        return "redirect:/admin/futsal/manage/booking";
    }
    @GetMapping("/manage/update/{futsalId}")
    public String showFutsalUpdateForm(Model model,@PathVariable Long futsalId){
        model.addAttribute("futsal",futsalService.findById(futsalId));
        return "updateFutsal";
    }
    @PostMapping("/manage/update/{futsalId}")
    public String updateFutsal(@ModelAttribute Futsal futsal, @PathVariable("futsalId")Long futsalId,Model model){
        if(futsalService.findFutsal().contains(futsalService.findByFutsalName(futsal.getFutsalName())) || !futsalService.findById(futsalId).getFutsalName().equals(futsal.getFutsalName()))
        {
            model.addAttribute("nameExist",true);
            model.addAttribute("futsal",futsalService.findById(futsalId));
            return "updateFutsal";
        }
        else {
            Futsal currentFutsal = futsalService.findById(futsalId);
            currentFutsal.setFutsalName(futsal.getFutsalName());
            currentFutsal.setFutsalImage(futsal.getFutsalImage());
            currentFutsal.setAddress(futsal.getAddress());
            currentFutsal.setPhNo(futsal.getPhNo());
            currentFutsal.setDescription(futsal.getDescription());
            currentFutsal.setPrice(futsal.getPrice());
            currentFutsal.setStatus(futsal.isStatus());
            futsalService.save(currentFutsal);
            return "redirect:/admin/futsal/manage";
        }
    }
}