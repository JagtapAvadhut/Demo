package com.Answer.Answer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AnsTest")
public class TestControler {
    @GetMapping
    public String AnsTest(){
        return "This Test For API ";
    }
}
