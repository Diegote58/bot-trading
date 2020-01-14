package com.io.trading.controller;

import com.io.trading.config.ConstantConfig;
import com.io.trading.service.LoginService;
import com.io.trading.service.MyAccountService;

import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAccountController {

    @Autowired
    private MyAccountService myAccountService;
        
    @GetMapping(path="/myAccount", produces = "application/json")
    @ResponseBody
    public ResponseEntity myAccount(HttpServletRequest request) throws Exception {    	
        return myAccountService.getMyAccount(request.getHeader(ConstantConfig.TOKEN_HEADER_PARAM));
    }
    
}
