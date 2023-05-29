package com.raptor.ecommerceproject.services;

import com.raptor.ecommerceproject.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
        List<User> findAll();
         Optional<User> findById(Long id);
        User save(User user);
        Optional<User> findByMail(String mail);
}
