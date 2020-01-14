package com.io.trading.service;

import com.io.trading.config.ConstantConfig;
import com.io.trading.config.ConstantUrls;
import com.io.trading.restclient.RestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class MyAccountService {

    @Autowired
    private RestClient restClient;
    
    public ResponseEntity<String> getMyAccount(String access_token) throws Exception {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("Authorization", access_token);
		return restClient.get(ConstantUrls.INVERTIR_ONLINE_ACCOUNT_STATUS, parameters, MediaType.APPLICATION_FORM_URLENCODED);
    }

}
