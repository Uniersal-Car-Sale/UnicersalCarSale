package com.sahan.spring.controller;

import com.sahan.spring.dto.UserDetailDto;
import com.sahan.spring.exception.AuthRejectedException;
import com.sahan.spring.exception.BaseException;
import com.sahan.spring.exception.RecordAlreadySubmittedException;
import com.sahan.spring.service.AuthenticationService;
import com.sahan.spring.util.HttpCustomStatus;
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
        } catch (AuthRejectedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.AUTHENTICATION_ERROR).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("register/credential")
    public ResponseEntity<?> register(@RequestBody UserDetailDto userDetailDto) {
        try {
            return ResponseEntity.ok(authenticationService.registerCredentials(userDetailDto));
        } catch (RecordAlreadySubmittedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.RECORD_ALREADY_SUBMIT).body(e.getMessage());
        } catch (AuthRejectedException e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpCustomStatus.AUTHENTICATION_ERROR).body(e.getMessage());
        } catch (BaseException ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
