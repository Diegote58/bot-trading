package com.io.trading.service;

import com.io.trading.config.ConstantConfig;
import com.io.trading.restclient.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class LoginService {

    @Autowired
    private RestClient restClient;

    @Cacheable(key = "access_token")
    public String getAccessToken(String refreshToken) {
        String response = restClient.postMultiValueMap(getParamsForRefreshToken(refreshToken));


        return response;
    }

    private MultiValueMap<String, String> getParamsForUsername() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("refresh_token", ConstantConfig.USERNAME);
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
