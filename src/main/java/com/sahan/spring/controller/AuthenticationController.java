package com.sahan.spring.controller;

import com.sahan.spring.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/userName/{userName}/password/{password}")
    public ResponseEntity<?> signIn(@PathVariable String userName, @PathVariable String password) {
        try {
            return ResponseEntity.ok(authenticationService.login(userName, password));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
