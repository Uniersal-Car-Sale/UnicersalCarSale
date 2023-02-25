package com.sahan.spring.service.impl;

import com.sahan.spring.entity.UserDetail;
import com.sahan.spring.exception.AuthRejectedException;
import com.sahan.spring.repo.AuthenticationRepo;
import com.sahan.spring.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.sahan.spring.constant.AppConstant.*;
import static com.sahan.spring.constant.ExceptionMessageConstant.INVALID_AUTH;
import static com.sahan.spring.constant.ExceptionMessageConstant.INVALID_PASSWORD;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationRepo authenticationRepo;

    @Override
    public String login(String userName, String password) throws AuthRejectedException {
        if (((ADMIN_USER).equals(userName)) && ((ADMIN_PASSWORD).equals(password))) {
            return ADMIN_RESPONSE;
        }
        Optional<UserDetail> userDetail = authenticationRepo.findAllByUserName(userName);
        if (userDetail.isPresent()) {
            if ((userDetail.get().getUserName().equals(userName) && (userDetail.get().getPassword().equals(password)))) {
                return LOGIN_SUCCESSFUL;
            } else {
                throw new AuthRejectedException(INVALID_PASSWORD);
            }
        } else {
            throw new AuthRejectedException(INVALID_AUTH);
        }
    }
}
