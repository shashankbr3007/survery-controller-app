package com.springboot.secondproject.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.springboot.secondproject.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WelcomeController {

    @Autowired
    WelcomeService welcomeservice;

    @RequestMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public Map<String, String> showwelcomepage() {

        Map<String, String> map = new HashMap<>();
        map.put("Message", "Hello, Welcome to " + welcomeservice.welcomeservice());
        map.put("Date", String.valueOf(System.currentTimeMillis()));
        return map;
    }

    @PostMapping(value = "/Welcome",produces = APPLICATION_JSON_VALUE)
    public Map<String, String> postWelcomePage(@RequestParam String message){
        Map<String, String> map = new HashMap<>();
        map.put("Message", message);
        map.put("Date", String.valueOf(System.currentTimeMillis()));
        return map;
    }
}
