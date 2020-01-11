package com.ncit.minor.futsalbookingapp.service.impl;

import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.model.User;
import com.ncit.minor.futsalbookingapp.repository.FutsalRepository;
import com.ncit.minor.futsalbookingapp.service.FutsalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FutsalServiceImpl implements FutsalService {
    private FutsalRepository futsalRepository;
    FutsalServiceImpl(FutsalRepository futsalRepository){
        this.futsalRepository=futsalRepository;
    }

    @Override
    public Futsal findById(Long id) {
        return futsalRepository.getOne(id);
    }

    @Override
    public Futsal findByFutsalName(String futsalName) {
        return futsalRepository.findByFutsalName(futsalName);
    }

    @Override
    public Futsal save(Futsal futsal) {
        return futsalRepository.save(futsal);
    }

    @Override
    public List<Futsal> findFutsal() {
        return futsalRepository.findAll();

    }

    @Override
    public void deleteFutsal(Long id) {
        futsalRepository.deleteById(id);
    }

    @Override
    public void updateFutsal(Futsal futsal) throws Exception {
        List<Futsal> futsals=futsalRepository.findAll();
        if(futsals.contains(futsal))
        {
            futsalRepository.save(futsal);
        }
        throw new RuntimeException("Futsal Not Found");
    }

    @Override
    public Futsal mapFutsalForAdmin(User user, Futsal futsal) {
        futsal.setUser(user);
        return futsalRepository.save(futsal);
    }

    @Override
    public List<Futsal> findByUser(User user) {
        return futsalRepository.findByUser(user);
    }
}
