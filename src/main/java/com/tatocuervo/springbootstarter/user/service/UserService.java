package com.tatocuervo.springbootstarter.user.service;

import com.tatocuervo.springbootstarter.common.model.AppUser;
import com.tatocuervo.springbootstarter.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void createUser(AppUser appUser) {
        repository.save(appUser);
    }

    public List<AppUser> getUsers() {
        return repository.findAll();
    }
}
