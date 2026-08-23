package com.lumileaf.lumi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @GetMapping("/test-fetch")
    public String fetchFromFreeSite() {
        RestTemplate restTemplate = new RestTemplate();
        String freeApiUrl = "https://jsonplaceholder.typicode.com/posts/1";

        // This goes to the internet and grabs the fake data
        String fetchedData = restTemplate.getForObject(freeApiUrl, String.class);

        return "Success! Here is the data from the internet: " + fetchedData;
    }
}