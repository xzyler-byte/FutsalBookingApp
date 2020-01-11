package com.ncit.minor.futsalbookingapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Futsal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String futsalName;
    private String address;
    private boolean status;
    private long phNo;
    @Transient
    private MultipartFile futsalImage;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @Getter
    @Setter
    User user;

    @OneToMany(mappedBy = "futsal",cascade = CascadeType.ALL)
    @Getter
    @Setter
    List<Booking> booking=new ArrayList<>();

    public Futsal() {
    }

    public Futsal(String futsalName, String address, boolean status, long phNo, String description, double price) {

        this.futsalName = futsalName;
        this.address = address;
        this.status = status;
        this.phNo = phNo;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return this.id;
    }

    public String getFutsalName() {
        return this.futsalName;
    }

    public String getAddress() {
        return this.address;
    }

    public boolean isStatus() {
        return this.status;
    }

    public long getPhNo() {
        return this.phNo;
    }

    public MultipartFile getFutsalImage() {
        return this.futsalImage;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }


    public void setFutsalName(String futsalName) {
        this.futsalName = futsalName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPhNo(long phNo) {
        this.phNo = phNo;
    }

    public void setFutsalImage(MultipartFile futsalImage) {
        this.futsalImage = futsalImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "Futsal(id=" + this.getId() + ", futsalName=" + this.getFutsalName() + ", address=" + this.getAddress() + ", status=" + this.isStatus() + ", phNo=" + this.getPhNo() + ", futsalImage=" + this.getFutsalImage() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ")";
    }

}
