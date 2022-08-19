package com.example.rest_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/home")
    public String getHome(@RequestParam("code") String code) {

        System.out.println("code = " + code);


        return "home page";
    }

}
