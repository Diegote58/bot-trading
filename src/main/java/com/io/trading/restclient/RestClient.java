package com.io.trading.restclient;

import com.io.trading.config.ConstantConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    public String postMultiValueMap(MultiValueMap<String, String> map) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        String response = restTemplate.postForObject(ConstantConfig.BASE_URL + "/token", request, String.class);
        return response;
    }
}
