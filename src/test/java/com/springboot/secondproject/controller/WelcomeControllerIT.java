package com.springboot.secondproject.controller;


import com.springboot.secondproject.SpringbootSecondProjectApplication;
import com.springboot.secondproject.model.WelcomeMessage;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootSecondProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WelcomeControllerIT {


    @LocalServerPort
    private int port;


    @Test
    public void validateWelcomeController() {

        String URL = "http://localhost:" + port + "/";

        RestTemplate restTemplate = new RestTemplate();

        WelcomeMessage response = restTemplate.getForObject(URL, WelcomeMessage.class);

        //System.out.println(response.toString());
        System.out.println(response.getMessage());
        System.out.println(response.getDate());

    }

    @Test
    public void valdiatePostWelcomeController() throws JSONException {
        String URL = "http://localhost:" + port + "/Welcome";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        /*headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);*/

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("message", "first.last@example.com");

        HttpEntity<MultiValueMap<String, String>> message = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        try {
            WelcomeMessage response = restTemplate.postForObject(URL, message, WelcomeMessage.class);
            System.out.println(response.getMessage());
            System.out.println(response.getDate());

            String resp = restTemplate.postForObject(URL, message, String.class);
            System.out.println(resp);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        } 


    }
}
