package com.io.trading.service;


import java.io.Reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.io.trading.config.ConstantConfig;
import com.io.trading.config.ConstantUrls;
import com.io.trading.restclient.RestClient;

@Service
public class LoginService {

    @Autowired
    private RestClient restClient;

    public ResponseEntity<String> getAccessTokenByCredentials() {
        return restClient.post(ConstantUrls.INVERTIR_ONLINE_LOGIN_URL, getParamsForUsername(), MediaType.APPLICATION_FORM_URLENCODED);
    }
    
    public ResponseEntity<String> getAccessTokenByRefreshToken(String refreshToken) {
        return restClient.post(ConstantUrls.INVERTIR_ONLINE_LOGIN_URL, getParamsForRefreshToken(refreshToken), MediaType.APPLICATION_FORM_URLENCODED);
    }
    
    private MultiValueMap<String, String> getParamsForUsername() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", ConstantConfig.USERNAME);
        map.add("password", ConstantConfig.PASSWORD);
        map.add("grant_type", ConstantConfig.GRANT_TYPE_PASSWORD);
        return map;
    }

    private MultiValueMap<String, String> getParamsForRefreshToken(String refreshToken) {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("refresh_token", refreshToken);
        map.add("grant_type", ConstantConfig.GRANT_TYPE_REFRESH_TOKEN);
        return map;
    }

}
