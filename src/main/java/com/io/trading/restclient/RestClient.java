package com.io.trading.restclient;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

	
	private HttpHeaders getHeaders(MediaType mediaType, MultiValueMap<String, String> body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(mediaType);
		if(body != null && body.containsKey("Authorization") && !body.get("Authorization").isEmpty()){
			headers.add("Authorization", body.get("Authorization").get(0));
			headers.setBearerAuth(body.get("Authorization").get(0).replace("Bearer ", ""));
		}
		
		return headers;
	}

	public ResponseEntity<String> get(String url, MultiValueMap<String, String> body, MediaType mediaType) {
		ResponseEntity<String> response;

		try {
			RestTemplate restTemplate = new RestTemplate();
			//HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, getHeaders(mediaType));
			
			HttpEntity<?> httpEntity = new HttpEntity<Object>(body, getHeaders(mediaType, body));
			response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class, body);
			//response = restTemplate.getForEntity(url, String.class);
		} catch (RestClientResponseException e) {
			response = new ResponseEntity<String>(e.getResponseBodyAsString(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
		return response;
	}
	
	public ResponseEntity<String> post(String url, MultiValueMap<String, String> body, MediaType mediaType) {
		ResponseEntity<String> response;

		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(body, getHeaders(mediaType, null));
			response = restTemplate.postForEntity(url, request, String.class);
		} catch (RestClientResponseException e) {
			response = new ResponseEntity<String>(e.getResponseBodyAsString(), HttpStatus.valueOf(e.getRawStatusCode()));
		}
		return response;
	}
	
}
