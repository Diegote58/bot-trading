package com.io.trading.controller;

import com.io.trading.service.LoginService;

import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @PostMapping("/invertir_online/login")
    public ResponseEntity<String> login() {    	
        return loginService.getAccessTokenByCredentials();
    }
    
    @PostMapping("/invertir_online/refresh_token")
    public ResponseEntity<String> refreshToken(@RequestParam String refresh_token) {
        return loginService.getAccessTokenByRefreshToken(refresh_token);
    }
    
    
}
