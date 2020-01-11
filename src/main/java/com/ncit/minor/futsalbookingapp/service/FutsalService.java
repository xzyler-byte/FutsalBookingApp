package com.ncit.minor.futsalbookingapp.service;

import com.ncit.minor.futsalbookingapp.model.Futsal;
import com.ncit.minor.futsalbookingapp.model.User;

import java.util.List;

public interface FutsalService {
    Futsal findById(Long id);
Futsal findByFutsalName(String futsalName);
Futsal save(Futsal futsal);
List<Futsal> findFutsal();
void deleteFutsal(Long id);
void updateFutsal(Futsal futsal) throws Exception;
Futsal mapFutsalForAdmin(User user, Futsal futsal);
List<Futsal> findByUser(User user);
}
