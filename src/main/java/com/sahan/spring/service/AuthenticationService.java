package com.sahan.spring.service;

import com.sahan.spring.dto.UserDetailDto;

public interface AuthenticationService {
    String login(String userName, String password);

    String registerCredentials(UserDetailDto userDetailDto);


}
