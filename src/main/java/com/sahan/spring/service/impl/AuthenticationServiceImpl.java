package com.sahan.spring.service.impl;

import com.sahan.spring.entity.UserDetail;
import com.sahan.spring.repo.AuthenticationRepo;
import com.sahan.spring.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepo authenticationRepo;

    @Override
    public String login(String userName, String password) {
        if ((("Admin").equals(userName)) && (("admin").equals(password))) {
            return "ADMIN";
        }
        Optional<UserDetail> userDetail = authenticationRepo.findAllByUserName(userName);
        if (userDetail.isPresent()) {
            if ((userDetail.get().getUserName().equals(userName) && (userDetail.get().getPassword().equals(password)))) {
                return "Login Successfully.";
            } else {
                throw new RuntimeException("Invalid password !");
            }
        } else {
            throw new RuntimeException("Invalid userName or password !");
        }

    }
}
